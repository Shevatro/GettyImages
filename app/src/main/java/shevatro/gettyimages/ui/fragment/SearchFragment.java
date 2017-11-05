package shevatro.gettyimages.ui.fragment;

import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnEditorAction;
import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import shevatro.gettyimages.App;
import shevatro.gettyimages.R;
import shevatro.gettyimages.data.db.ImageRealm;
import shevatro.gettyimages.data.gson.*;

import java.util.List;

public class SearchFragment extends Fragment {
    private final String DIALOG = "dialog";
    @BindView(R.id.search_layout) TextInputLayout searchLayout;
    @BindView(R.id.et_search) EditText editKeyWord;

    private Realm realm;

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        realm = Realm.getDefaultInstance();
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    @OnEditorAction(R.id.et_search)
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_SEND) {
            search(getTrimmedText());
            return true;
        }
        return false;
    }

    /**
     * Method for save the result in the database
     *
     * @param word The name of the picture
     * @param uri  Link to download picture
     */
    private void addToDb(String word, String uri) {
        realm.beginTransaction();
        ImageRealm book = realm.createObject(ImageRealm.class);
        book.setName(word);
        book.setUri(uri);
        realm.commitTransaction();
    }

    /**
     * The method for checking the text field for empty space
     *
     * @param str A string of EditText
     */
    private boolean checkEmptyField(String str) {
        boolean isEmpty = str.isEmpty();
        searchLayout.setErrorEnabled(isEmpty);
        if (isEmpty) {
            searchLayout.setError(getString(R.string.error_empty));
        } else {
            editKeyWord.setText("");
        }
        return isEmpty;
    }

    /**
     * Method for searching for images by keyword
     *
     * @param phrase Key word for search
     */
    private void search(final String phrase) {
        if (checkEmptyField(phrase)) {
            return;
        }
        Call<SearchImages> messages = App.getComponent().getRetrofit().images(phrase);

        messages.enqueue(new Callback<SearchImages>() {
            @Override
            public void onResponse(@NonNull Call<SearchImages> call, @NonNull Response<SearchImages> response) {
                if (response.isSuccessful()) {
                    List<Image> images = response.body().getImages();
                    if (!images.isEmpty() && !images.get(0).getDisplaySizes().isEmpty()) {
                        String uri = images.get(0).getDisplaySizes().get(0).getUri();
                        addToDb(phrase, uri);
                    } else {
                        showDialog(getString(R.string.error_find));
                    }

                } else {
                    showDialog(getString(R.string.error_internet) + " " + response.code());
                }
            }

            @Override
            public void onFailure(@NonNull Call<SearchImages> call, @NonNull Throwable t) {
                showDialog(getString(R.string.error_internet));
            }
        });
    }

    private String getTrimmedText() {
        return editKeyWord.getText().toString().trim();
    }

    private void showDialog(String msg) {
        DialogFragment newFragment = AlertDialogFragment.newInstance(getString(R.string.app_name), msg);
        newFragment.show(getFragmentManager(), DIALOG);
    }
}

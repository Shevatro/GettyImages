package shevatro.gettyimages.ui.fragment.search;

import android.support.design.widget.TextInputLayout;
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

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnEditorAction;
import shevatro.gettyimages.App;
import shevatro.gettyimages.R;
import shevatro.gettyimages.ui.fragment.LoadingDialog;

public class SearchFragment extends Fragment implements SearchView {
    @BindView(R.id.search_layout)
    TextInputLayout searchLayout;
    @BindView(R.id.et_search)
    EditText editKeyWord;
    private SearchPresenter searchPresenter;
    @Inject
    LoadingDialog loadingDialog;

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.getComponent().injectSearchFragment(this);
        searchPresenter = new SearchPresenter(this);
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
        searchPresenter.closeRealm();
    }

    @OnEditorAction(R.id.et_search)
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        boolean isActionSend = (actionId == EditorInfo.IME_ACTION_SEND);
        if (isActionSend) {
            searchPresenter.search(getTrimmedText());
        }
        return isActionSend;
    }

    @Override
    public void showError(String str) {
        searchLayout.setErrorEnabled(true);
        searchLayout.setError(str);
    }

    @Override
    public void hideError() {
        searchLayout.setErrorEnabled(false);
        editKeyWord.setText("");
    }

    @Override
    public void showLoading() {
        loadingDialog.show(getFragmentManager());
    }

    @Override
    public void hideLoading() {
        loadingDialog.cancel();
    }

    @Override
    public String getStringRes(int id) {
        return getString(id);
    }

    private String getTrimmedText() {
        return editKeyWord.getText().toString().trim();
    }
}

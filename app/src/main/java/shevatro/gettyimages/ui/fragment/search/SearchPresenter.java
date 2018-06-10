package shevatro.gettyimages.ui.fragment.search;

import android.support.annotation.NonNull;

import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import shevatro.gettyimages.App;
import shevatro.gettyimages.R;
import shevatro.gettyimages.data.db.GettyImagesApi;
import shevatro.gettyimages.data.db.ImageRealm;
import shevatro.gettyimages.data.gson.Image;
import shevatro.gettyimages.data.gson.SearchImages;

public class SearchPresenter {

    private final SearchView searchView;
    @Inject
    GettyImagesApi retrofit;
    @Inject
    Realm realm;

    SearchPresenter(@NonNull SearchView searchView) {
        this.searchView = searchView;
        App.getComponent().injectSearchView(this);
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
        if (isEmpty) {
            searchView.showError(searchView.getStringRes(R.string.error_empty));
        } else {
            searchView.hideError();
        }
        return isEmpty;
    }

    /**
     * Method for searching for images by keyword
     *
     * @param phrase Key word for search
     */
    protected void search(final String phrase) {
        if (checkEmptyField(phrase)) {
            return;
        }
        searchView.showLoading();
        Call<SearchImages> messages = retrofit.images(phrase);

        messages.enqueue(new Callback<SearchImages>() {
            @Override
            public void onResponse(@NonNull Call<SearchImages> call, @NonNull Response<SearchImages> response) {
                if (response.isSuccessful()) {
                    List<Image> images = response.body().getImages();
                    if (!images.isEmpty() && !images.get(0).getDisplaySizes().isEmpty()) {
                        String uri = images.get(0).getDisplaySizes().get(0).getUri();
                        addToDb(phrase, uri);
                    } else {
                        searchView.showError(searchView.getStringRes(R.string.error_find));
                    }
                } else {
                    searchView.showError(searchView.getStringRes(R.string.error_internet) + " " + response.code());
                }
                searchView.hideLoading();
            }

            @Override
            public void onFailure(@NonNull Call<SearchImages> call, @NonNull Throwable t) {
                searchView.showError(searchView.getStringRes(R.string.error_internet));
                searchView.hideLoading();
            }
        });
    }

    void closeRealm() {
        realm.close();
    }
}

package shevatro.gettyimages.ui.fragment.search;

public interface SearchView {

    void showLoading();

    void hideLoading();

    void showError(String text);

    void hideError();

    String getStringRes(int id);

}
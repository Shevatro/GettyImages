package shevatro.gettyimages.dagger2;

import javax.inject.Singleton;

import dagger.Component;
import shevatro.gettyimages.ui.fragment.search.SearchFragment;
import shevatro.gettyimages.ui.fragment.search.SearchPresenter;
import shevatro.gettyimages.ui.fragment.ListFragment;

@Singleton
@Component(modules = {NetworkModule.class, DbModule.class, ViewModule.class})
public interface AppComponent {
    void injectSearchView(SearchPresenter presenter);

    void injectSearchFragment(SearchFragment fragment);

    void injectListFragment(ListFragment fragment);
}

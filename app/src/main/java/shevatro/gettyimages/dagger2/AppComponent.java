package shevatro.gettyimages.dagger2;

import javax.inject.Singleton;

import dagger.Component;
import shevatro.gettyimages.ui.fragment.ListFragment;
import shevatro.gettyimages.ui.fragment.SearchFragment;

@Singleton
@Component(modules = {NetworkModule.class, DbModule.class})
public interface AppComponent {
    void injectSearchFragment(SearchFragment fragment);
    void injectListFragment(ListFragment fragment);
}

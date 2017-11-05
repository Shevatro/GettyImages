package shevatro.gettyimages.dagger2;

import dagger.Component;
import shevatro.gettyimages.data.db.GettyImagesApi;

@Component(modules = {NetworkModule.class})
public interface AppComponent {
    GettyImagesApi getRetrofit();
}

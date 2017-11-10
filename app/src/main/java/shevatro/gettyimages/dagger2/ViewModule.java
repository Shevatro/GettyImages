package shevatro.gettyimages.dagger2;

import dagger.Module;
import dagger.Provides;
import shevatro.gettyimages.R;
import shevatro.gettyimages.ui.fragment.LoadingDialog;

@Module
class ViewModule {

    @Provides
    LoadingDialog provideLoadingDialog() {
        return LoadingDialog.create(R.string.images_progress);
    }
}

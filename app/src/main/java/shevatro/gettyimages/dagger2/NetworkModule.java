package shevatro.gettyimages.dagger2;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import shevatro.gettyimages.BuildConfig;
import shevatro.gettyimages.data.db.GettyImagesApi;

@Module
public class NetworkModule {
    @Provides
    GettyImagesApi provideRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(GettyImagesApi.class);
    }
}

package shevatro.gettyimages;
import android.app.Application;

import shevatro.gettyimages.data.db.GettyImagesApi;
import com.squareup.picasso.Picasso;

import io.realm.Realm;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import shevatro.gettyimages.other.Setting;

public class App extends Application {
    private static GettyImagesApi api;

    @Override
    public void onCreate() {
        super.onCreate();
        //initialization Realm и Retrofit
        Realm.init(this);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Setting.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(GettyImagesApi.class);
        Picasso.with(this).setIndicatorsEnabled(true);
    }

    public static GettyImagesApi getApi() {
        return api;
    }
}

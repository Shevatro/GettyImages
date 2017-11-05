package shevatro.gettyimages;
import android.app.Application;

import com.squareup.picasso.Picasso;

import io.realm.Realm;
import shevatro.gettyimages.dagger2.AppComponent;
import shevatro.gettyimages.dagger2.DaggerAppComponent;

public class App extends Application {
    private static AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        //initialization Realm и Dagger
        Realm.init(this);
        component = DaggerAppComponent.create();
        Picasso.with(this).setIndicatorsEnabled(true);
    }

    public static AppComponent getComponent() {
        return component;
    }
}

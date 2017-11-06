package shevatro.gettyimages.dagger2;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

@Module
public class DbModule {
    @Provides
    @Singleton
    Realm provideRealm() {
        return Realm.getDefaultInstance();
    }
}

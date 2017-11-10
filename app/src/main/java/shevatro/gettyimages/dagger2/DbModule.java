package shevatro.gettyimages.dagger2;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

@Module
public class DbModule {
    @Provides
    Realm provideRealm() {
        return Realm.getDefaultInstance();
    }
}

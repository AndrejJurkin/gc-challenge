package jurkin.gctest.injection.module;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import jurkin.gctest.App;

/**
 * The base app module contains Application dependencies
 *
 * Created by Andrej Jurkin on 1/11/18.
 */

@Module
public class AppModule {

    private App app;

    public AppModule(App app) {
        this.app = app;
    }

    @Provides
    @Singleton
    App provideApp() {
        return this.app;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return this.app.getApplicationContext();
    }
}

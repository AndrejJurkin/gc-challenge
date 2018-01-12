package jurkin.gctest.injection.component;

import javax.inject.Singleton;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import jurkin.gctest.App;
import jurkin.gctest.injection.module.ApiModule;
import jurkin.gctest.injection.module.AppModule;
import jurkin.gctest.injection.module.LocalDatabaseModule;
import jurkin.gctest.injection.module.UiModule;
import jurkin.gctest.injection.module.ViewModelModule;

/**
 * AppComponent contains all dependencies that we need to initialize during the app start-up.
 *
 * Created by Andrej Jurkin on 1/11/18.
 */

@Singleton
@Component(modules = {
        AndroidInjectionModule.class,
        AppModule.class,
        ApiModule.class,
        ViewModelModule.class,
        UiModule.class,
})
public interface AppComponent {

    @Component.Builder
    interface Builder {

        Builder appModule(AppModule appModule);

        Builder apiModule(ApiModule apiModule);

        AppComponent build();
    }

    void inject(App app);
}

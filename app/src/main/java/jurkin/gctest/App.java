package jurkin.gctest;

import android.app.Activity;
import android.app.Application;
import android.support.v4.app.Fragment;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import dagger.android.support.HasSupportFragmentInjector;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import jurkin.gctest.data.Repository;
import jurkin.gctest.injection.component.DaggerAppComponent;
import jurkin.gctest.injection.module.ApiModule;
import jurkin.gctest.injection.module.AppModule;

/**
 * Created by Andrej Jurkin on 1/11/18.
 */

public class App extends Application implements HasActivityInjector, HasSupportFragmentInjector {

    private static final String BASE_URL = "https://papagaj-breweria.herokuapp.com/api/";

    @Inject
    DispatchingAndroidInjector<Activity> dispatchingActivityInjector;

    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingFragmentInjector;

    @Inject
    Repository repository;

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration realmConfig = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfig);

        DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .apiModule(new ApiModule(BASE_URL))
                .build()
                .inject(this);

        repository.refreshLocalData();
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingActivityInjector;
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingFragmentInjector;
    }
}

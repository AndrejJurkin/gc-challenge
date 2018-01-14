package jurkin.gctest.injection.module;

import android.arch.lifecycle.ViewModelProvider;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import jurkin.gctest.MainActivity;
import jurkin.gctest.injection.ViewModelFactory;
import jurkin.gctest.view.addons.AddOnActivity;
import jurkin.gctest.view.addons.AddOnFragment;
import jurkin.gctest.view.mealcategories.MealCategoriesFragment;

/**
 * The UiModule contains UI dependencies needed for the injection of Android UI components
 *
 * Created by Andrej Jurkin on 1/11/18.
 */

@Module
public abstract class UiModule {

    @ContributesAndroidInjector
    abstract MainActivity contributeMainActivity();

    @ContributesAndroidInjector
    abstract MealCategoriesFragment contributeMealCategoriesFragment();

    @ContributesAndroidInjector
    abstract AddOnActivity contributeAddOnActivity();

    @ContributesAndroidInjector
    abstract AddOnFragment contributeAddOnFragment();

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);
}
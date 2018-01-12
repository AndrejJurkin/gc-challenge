package jurkin.gctest.injection.module;

import android.arch.lifecycle.ViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import jurkin.gctest.view.mealcategories.MealCategoriesViewModel;

/**
 * Created by Andrej Jurkin on 1/11/18.
 */

@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MealCategoriesViewModel.class)
    abstract ViewModel bindMealCategoriesViewModel(MealCategoriesViewModel mealCategoriesViewModel);
}


package jurkin.gctest.view.mealcategories;

import android.arch.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.realm.RealmResults;
import jurkin.gctest.data.Repository;
import jurkin.gctest.model.MealCategory;

/**
 * Created by Andrej Jurkin on 1/11/18.
 */

public class MealCategoriesViewModel extends ViewModel {

    private Repository repository;

    @Inject
    public MealCategoriesViewModel(Repository repository) {
        this.repository = repository;
    }

    public Flowable<RealmResults<MealCategory>> getMealCategories() {
        return repository.getMealCategories();
    }
}

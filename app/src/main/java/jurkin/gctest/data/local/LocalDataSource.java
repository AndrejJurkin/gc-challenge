package jurkin.gctest.data.local;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Flowable;
import io.realm.Realm;
import io.realm.RealmResults;
import jurkin.gctest.model.Meal;
import jurkin.gctest.model.MealCategory;

/**
 * Created by Andrej Jurkin on 1/11/18.
 */

@Singleton
public class LocalDataSource {

    @Inject
    LocalDataSource() {

    }

    public Flowable<RealmResults<Meal>> getMeals() {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(Meal.class)
                .findAllAsync()
                .asFlowable()
                .filter(RealmResults::isLoaded)
                .doOnComplete(realm::close);
    }

    public void insertMeals(List<Meal> meals) {
        try (Realm realm = Realm.getDefaultInstance()) {
            realm.beginTransaction();
            realm.insertOrUpdate(meals);
            realm.beginTransaction();
        }
    }

    public Flowable<RealmResults<MealCategory>> getCategories() {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(MealCategory.class)
                .findAllAsync()
                .asFlowable()
                .filter(RealmResults::isLoaded)
                .doOnComplete(realm::close);
    }

    public void insertMealCategories(List<MealCategory> mealCategories) {
        try (Realm realm = Realm.getDefaultInstance()) {
            realm.beginTransaction();
            realm.insertOrUpdate(mealCategories);
            realm.commitTransaction();
        }
    }
}

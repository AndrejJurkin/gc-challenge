package jurkin.gctest.data.local;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Flowable;
import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;
import jurkin.gctest.model.AddOn;
import jurkin.gctest.model.AddOnCategory;
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
        return getDataAsFlowable(Meal.class);
    }

    public void insertMeals(List<Meal> meals) {
        insertOrUpdate(meals);
    }

    public Flowable<RealmResults<MealCategory>> getCategories() {
        return getDataAsFlowable(MealCategory.class);
    }

    public void insertMealCategories(List<MealCategory> mealCategories) {
        insertOrUpdate(mealCategories);
    }

    public Flowable<RealmResults<AddOn>> getAddOns() {
        return getDataAsFlowable(AddOn.class);
    }

    public void insertAddOns(List<AddOn> addOns) {
        insertOrUpdate(addOns);
    }

    public Flowable<RealmResults<AddOnCategory>> getAddOnCategories() {
        return getDataAsFlowable(AddOnCategory.class);
    }

    public void insertAddOnCategories(List<AddOnCategory> addOnCategories) {
        insertOrUpdate(addOnCategories);
    }

    private <T extends RealmObject> Flowable<RealmResults<T>> getDataAsFlowable(Class<T> dataClass) {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(dataClass)
                .findAllAsync()
                .asFlowable()
                .filter(RealmResults::isLoaded)
                .doOnComplete(realm::close);
    }

    private <T extends RealmObject> void insertOrUpdate(List<T> data) {
        try (Realm realm = Realm.getDefaultInstance()) {
            realm.beginTransaction();
            realm.insertOrUpdate(data);
            realm.commitTransaction();
        }
    }
}

package jurkin.gctest.data.local;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Flowable;
import io.reactivex.Observable;
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
    private static final String TAG = "LocalDataSource";

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
        return getDataAsFlowable(MealCategory.class)
                .doOnNext(mealCategories -> Log.d(TAG, "getCategories: " + mealCategories.size()));
    }

    public void insertMealCategories(List<MealCategory> mealCategories) {
        insertOrUpdate(mealCategories);
    }

    public void insertAddOns(List<AddOn> addOns) {
        insertOrUpdate(addOns);
    }

    public void insertAddOnCategories(List<AddOnCategory> addOnCategories) {
        insertOrUpdate(addOnCategories);
    }

    public Flowable<RealmResults<AddOn>> getAddOns(String mealId) {
        Realm realm = Realm.getDefaultInstance();

        Meal meal = realm.where(Meal.class).equalTo("id", mealId).findFirst();

        if (meal == null || meal.getAddOnIds() == null || meal.getAddOnIds().isEmpty()) {
            return Flowable.error(new DataNotFoundException());
        }

        String[] query = meal.getAddOnIds().toArray(new String[meal.getAddOnIds().size()]);
        return realm.where(AddOn.class)
                .in("id", query)
                .findAllAsync()
                .asFlowable()
                .filter(RealmResults::isLoaded)
                .doOnComplete(realm::close);
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
        Log.d(TAG, "insertOrUpdate: " + data.size());
        try (Realm realm = Realm.getDefaultInstance()) {
            realm.beginTransaction();
            realm.insertOrUpdate(data);
            realm.commitTransaction();
        }
    }
}

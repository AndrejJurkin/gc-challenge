package jurkin.gctest.data;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Flowable;
import io.realm.RealmResults;
import jurkin.gctest.data.local.LocalDataSource;
import jurkin.gctest.data.remote.RemoteDataSource;
import jurkin.gctest.model.Meal;
import jurkin.gctest.model.MealCategory;

/**
 * Central data repository that abstracts access to the remote and local data sources.
 * It also handles logic for data caching.
 *
 * Created by Andrej Jurkin on 1/11/18.
 */

@Singleton
public class Repository {
    private LocalDataSource local;

    private RemoteDataSource remote;

    @Inject
    Repository(LocalDataSource local, RemoteDataSource remote) {
        this.local = local;
        this.remote = remote;
    }

    /**
     * Get meals from local data source and fetch fresh data from remote data source.
     *
     * When we receive the remote data, cache it to the local data source, which will trigger an
     * update notification, thus update the UI if it is subscribed.
     *
     * @return Observable list of {@link Meal}
     */
    public Flowable<RealmResults<Meal>> getMealsWithRefresh() {
        this.remote.getMeals()
                .doOnNext(meals -> {
                    // Cache to the local data source
                    this.local.insertMeals(meals);
                })
                .subscribe();

        return getMeals();
    }

    /**
     * Get meals from the local data source
     *
     * @return Realm results of {@link Meal}
     */
    public Flowable<RealmResults<Meal>> getMeals() {
        return this.local.getMeals();
    }

    /**
     * Get meal categories from the local data source and fetch fresh data
     * from the remote data source.
     *
     * When we receive the remote data, cache it to the local data source, which will trigger an
     * update notification, thus update the UI if it is subscribed.
     * @return Observable Realm results of {@link MealCategory}
     */
    public Flowable<RealmResults<MealCategory>> getMealCategoriesWithRefresh() {
        this.remote.getMealCategories()
                .doOnNext(mealCategories -> {
                    // Cache to the local data source
                    this.local.insertMealCategories(mealCategories);
                })
                .subscribe();

        return getMealCategories();
    }

    /**
     * Get meal categories from the local data source
     *
     * @return Observable Realm results of {@link MealCategory}
     */
    public Flowable<RealmResults<MealCategory>> getMealCategories() {
        return this.local.getCategories();
    }
}



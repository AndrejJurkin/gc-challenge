package jurkin.gctest.data.remote;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import jurkin.gctest.api.MenuService;
import jurkin.gctest.model.AddOn;
import jurkin.gctest.model.AddOnCategory;
import jurkin.gctest.model.Meal;
import jurkin.gctest.model.MealCategory;

/**
 * Created by Andrej Jurkin on 1/11/18.
 */

@Singleton
public class RemoteDataSource {
    private MenuService service;

    @Inject
    RemoteDataSource(MenuService service) {
        this.service = service;
    }

    public Observable<List<Meal>> getMeals() {
        return this.service.getMeals()
                .flatMap(response -> Observable.just(response.getMeals()));
    }

    public Observable<List<MealCategory>> getMealCategories() {
        return this.service.getMealCategories()
                .flatMap(response -> Observable.just(response.getMealCategories()));
    }

    public Observable<List<AddOn>> getAddOns() {
        return this.service.getAddons()
                .flatMap(response -> Observable.just(response.getAddOns()));
    }

    public Observable<List<AddOnCategory>> getAddOnCategories() {
        return this.service.getAddonCategories()
                .flatMap(response -> Observable.just(response.getAddOnCategories()));
    }
}

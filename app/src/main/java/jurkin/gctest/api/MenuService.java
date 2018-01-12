package jurkin.gctest.api;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by Andrej Jurkin on 1/11/18.
 *
 * http://papagaj-breweria.herokuapp.com/api/v1/menu/54ca39f401731406200082df/meal
 * http://papagaj-breweria.herokuapp.com/api/v1/menu/54ca39f401731406200082df/meal/category
 * http://papagaj-breweria.herokuapp.com/api/v1/menu/54ca39f401731406200082df/addon
 * http://papagaj-breweria.herokuapp.com/api/v1/menu/54ca39f401731406200082df/addon/category
 *
 */

public interface MenuService {

    @GET("v1/menu/54ca39f401731406200082df/meal")
    Observable<MealResponse> getMeals();

    @GET("v1/menu/54ca39f401731406200082df/meal/category")
    Observable<MealCategoryResponse> getMealCategories();
}

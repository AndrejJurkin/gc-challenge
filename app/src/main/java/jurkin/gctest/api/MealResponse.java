package jurkin.gctest.api;

import java.util.List;

import jurkin.gctest.base.BaseResponse;
import jurkin.gctest.model.Meal;

/**
 * Created by Andrej Jurkin on 1/11/18.
 */

public class MealResponse extends BaseResponse {

    private List<Meal> meals;

    public List<Meal> getMeals() {
        return meals;
    }
}

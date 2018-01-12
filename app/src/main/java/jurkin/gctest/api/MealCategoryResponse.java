package jurkin.gctest.api;

import java.util.List;

import jurkin.gctest.base.BaseResponse;
import jurkin.gctest.model.MealCategory;

/**
 * Created by Andrej Jurkin on 1/11/18.
 */

public class MealCategoryResponse extends BaseResponse {

    private List<MealCategory> mealCategories;

    public List<MealCategory> getMealCategories() {
        return mealCategories;
    }
}

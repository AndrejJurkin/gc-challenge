package jurkin.gctest.api;

import java.util.List;

import jurkin.gctest.base.BaseResponse;
import jurkin.gctest.model.AddOnCategory;

/**
 * Created by Andrej Jurkin on 1/14/18.
 */

public class AddonCategoryResponse extends BaseResponse {

    private List<AddOnCategory> addOnCategories;

    public List<AddOnCategory> getAddOnCategories() {
        return addOnCategories;
    }
}

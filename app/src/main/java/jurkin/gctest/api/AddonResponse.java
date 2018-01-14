package jurkin.gctest.api;

import java.util.List;

import jurkin.gctest.base.BaseResponse;
import jurkin.gctest.model.AddOn;

/**
 * Created by Andrej Jurkin on 1/14/18.
 */

public class AddonResponse extends BaseResponse {

    private List<AddOn> addOns;

    public List<AddOn> getAddOns() {
        return addOns;
    }
}

package jurkin.gctest.view.addons;

import android.arch.lifecycle.ViewModel;

import javax.inject.Inject;

import dagger.multibindings.IntoMap;
import jurkin.gctest.data.Repository;

/**
 * Created by Andrej Jurkin on 1/14/18.
 */

public class AddOnViewModel extends ViewModel {

    @Inject
    public AddOnViewModel(Repository repository) {

    }

    public void bind(String mealId) {

    }
}

package jurkin.gctest.view.addons;

import android.arch.lifecycle.ViewModel;

import javax.inject.Inject;

import dagger.multibindings.IntoMap;
import io.reactivex.Flowable;
import io.realm.RealmResults;
import jurkin.gctest.data.Repository;
import jurkin.gctest.model.AddOn;

/**
 * Created by Andrej Jurkin on 1/14/18.
 */

public class AddOnViewModel extends ViewModel {

    private Repository repository;

    @Inject
    public AddOnViewModel(Repository repository) {
        this.repository = repository;
    }

    public Flowable<RealmResults<AddOn>> getAddOns(String mealId) {
        return repository.getAddOns(mealId);
    }
}

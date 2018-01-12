package jurkin.gctest.base;

import android.arch.lifecycle.ViewModelProvider;
import android.content.Context;
import android.support.v4.app.Fragment;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Base fragment class that contains common code for all fragments in the app
 *
 * Created by Andrej Jurkin on 1/11/18.
 */

public abstract class BaseFragment extends Fragment {

    /**
     * Dispose bag for the subscriptions. All subscriptions will be disposed onStop.
     */
    protected CompositeDisposable disposables;

    @Inject
    protected ViewModelProvider.Factory viewModelFactory;

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Override
    public void onStart() {
        super.onStart();
        disposables = new CompositeDisposable();
    }

    @Override
    public void onStop() {
        super.onStop();

        // Clear subscriptions from the dispose bag
        disposables.clear();
    }
}

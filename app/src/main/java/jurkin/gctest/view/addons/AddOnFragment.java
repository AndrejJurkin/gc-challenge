package jurkin.gctest.view.addons;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import jurkin.gctest.R;
import jurkin.gctest.base.BaseFragment;
import jurkin.gctest.view.mealcategories.MealCategoriesViewModel;

/**
 * Created by Andrej Jurkin on 1/14/18.
 */

public class AddOnFragment extends BaseFragment {

    private AddOnViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.viewModel = this.viewModelFactory.create(AddOnViewModel.class);
    }

    @Override
    public void onStart() {
        super.onStart();

        if (getActivity() != null) {
            String mealId = getActivity().getIntent()
                    .getStringExtra(AddOnActivity.ARG_MEAL_ID);
            this.viewModel.bind(mealId);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_addon, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
}

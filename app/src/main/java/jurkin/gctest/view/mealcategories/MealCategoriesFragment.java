package jurkin.gctest.view.mealcategories;

import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import jurkin.gctest.R;
import jurkin.gctest.base.BaseFragment;

/**
 * Created by Andrej Jurkin on 1/11/18.
 */

public class MealCategoriesFragment extends BaseFragment {
    private static final String TAG = "MealCategoriesFragment";

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private MealCategoriesViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.viewModel = this.viewModelFactory.create(MealCategoriesViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_meal_categories, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.viewModel.getMealCategories()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mealCategories -> {
                    final MealCategoriesAdapter adapter = new MealCategoriesAdapter(mealCategories);
                    final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                    layoutManager.setAutoMeasureEnabled(false);
                    recyclerView.setAdapter(adapter);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(layoutManager);
                }, throwable -> {
                    //TODO: Show error view
                    Log.e(TAG, "Failed to get meal categories", throwable);
                });

    }

    @Override
    public void onStart() {
        super.onStart();

    }
}

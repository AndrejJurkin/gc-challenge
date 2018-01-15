package jurkin.gctest.view.addons;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import jurkin.gctest.R;
import jurkin.gctest.base.BaseFragment;

/**
 * Created by Andrej Jurkin on 1/14/18.
 */

public class AddOnFragment extends BaseFragment {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.error_view)
    View errorView;

    private AddOnViewModel viewModel;

    private AddOnCategoriesAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.viewModel = this.viewModelFactory.create(AddOnViewModel.class);
    }

    @Override
    public void onStart() {
        super.onStart();
        Disposable subscription = viewModel.getAddOns(getMealIdFromIntent())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(addOns -> {
                    errorView.setVisibility(View.GONE);
                    final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                    layoutManager.setAutoMeasureEnabled(false);
                    adapter = new AddOnCategoriesAdapter(addOns);
                    recyclerView.setAdapter(adapter);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(layoutManager);
                }, throwable -> errorView.setVisibility(View.VISIBLE));
        disposables.add(subscription);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_addon, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    private String getMealIdFromIntent() {
        if (getActivity() != null) {
            return getActivity().getIntent().getStringExtra(AddOnActivity.ARG_MEAL_ID);
        }

        return null;
    }
}

package jurkin.gctest.view.mealcategories;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jurkin.gctest.R;
import jurkin.gctest.model.Meal;
import jurkin.gctest.model.MealCategory;

/**
 * Created by Andrej Jurkin on 1/11/18.
 */

public class MealCategoriesAdapter extends ExpandableRecyclerViewAdapter
        <MealCategoriesAdapter.MealCategoryHolder, MealCategoriesAdapter.MealViewHolder> {

    public MealCategoriesAdapter(List<MealCategory> categories) {
        super(createCategoryGroups(categories));
    }

    @Override
    public MealCategoryHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.li_meal_category, parent, false);
        return new MealCategoryHolder(view);
    }

    @Override
    public MealViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.li_meal, parent, false);
        return new MealViewHolder(view);
    }

    @Override
    public void onBindChildViewHolder(MealViewHolder holder, int flatPosition,
                                      ExpandableGroup group, int childIndex) {
        final Meal meal = (Meal) group.getItems().get(childIndex);
        holder.mealName.setText(meal.getName());
    }

    @Override
    public void onBindGroupViewHolder(MealCategoryHolder holder, int flatPosition,
                                      ExpandableGroup group) {
        holder.categoryName.setText(group.getTitle());
    }

    private static List<MealCategoryGroup> createCategoryGroups(List<MealCategory> mealCategories) {
        if (mealCategories == null) {
            return new ArrayList<>();
        }

        final List<MealCategoryGroup> groups = new ArrayList<>();

        for (MealCategory category : mealCategories) {
            groups.add(new MealCategoryGroup(category.getName(), category.getMeals()));
        }

        return groups;
    }

    static class MealCategoryGroup extends ExpandableGroup<Meal> {
        MealCategoryGroup(String title, List<Meal> data) {
            super(title, data);
        }
    }

    static class MealCategoryHolder extends GroupViewHolder {
        @BindView(R.id.category_name)
        TextView categoryName;

        MealCategoryHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    static class MealViewHolder extends ChildViewHolder {
        @BindView(R.id.meal_name)
        TextView mealName;

        MealViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

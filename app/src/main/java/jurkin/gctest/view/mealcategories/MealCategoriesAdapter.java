package jurkin.gctest.view.mealcategories;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

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

    @Nullable
    private ExpandableGroup currentExpandedGroup = null;

    @Nullable
    private OnMealClickListener onMealClickListener;

    MealCategoriesAdapter(List<MealCategory> categories) {
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
        holder.price.setText(meal.getPrice());
        holder.servingSize.setText(meal.getServingSize());
        holder.itemView.setOnClickListener(v -> {
            if (onMealClickListener != null) {
                onMealClickListener.onMealClick(meal);
            }
        });
    }

    @Override
    public void onBindGroupViewHolder(MealCategoryHolder holder, int flatPosition,
                                      ExpandableGroup group) {
        holder.categoryName.setText(group.getTitle());
        holder.setExpanded(isGroupExpanded(flatPosition));
    }

    @Override
    public boolean onGroupClick(int flatPos) {
        int groupPosition = expandableList.getUnflattenedPosition(flatPos).groupPos;
        boolean collapsed = super.onGroupClick(flatPos);

        if (!collapsed && currentExpandedGroup != null) {
            toggleGroup(currentExpandedGroup);
        }
        notifyItemChanged(flatPos);
        currentExpandedGroup = collapsed ? null : getGroups().get(groupPosition);
        return collapsed;
    }

    @Override
    public boolean toggleGroup(ExpandableGroup group) {
        boolean collapsed = super.toggleGroup(group);
        int index = expandableList.getFlattenedGroupIndex(group);
        notifyItemChanged(index);
        return collapsed;
    }

    void setOnMealClickListener(@Nullable OnMealClickListener listener) {
        this.onMealClickListener = listener;
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

        @BindView(R.id.arrow_icon)
        ImageView arrowIcon;

        MealCategoryHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void setExpanded(boolean expanded) {
            if (expanded) {
                arrowIcon.setImageResource(R.drawable.ic_keyboard_arrow_up_black_24dp);
            } else {
                arrowIcon.setImageResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
            }
        }
    }

    static class MealViewHolder extends ChildViewHolder {
        @BindView(R.id.meal_name)
        TextView mealName;

        @BindView(R.id.price)
        TextView price;

        @BindView(R.id.serving_size)
        TextView servingSize;

        MealViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    interface OnMealClickListener {

        void onMealClick(Meal meal);
    }
}

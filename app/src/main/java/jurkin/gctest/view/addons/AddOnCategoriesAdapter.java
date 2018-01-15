package jurkin.gctest.view.addons;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Nullable;

import butterknife.BindView;
import butterknife.ButterKnife;
import jurkin.gctest.R;
import jurkin.gctest.model.AddOn;

/**
 * Created by Andrej Jurkin on 1/14/18.
 */

public class AddOnCategoriesAdapter extends ExpandableRecyclerViewAdapter
        <AddOnCategoriesAdapter.AddOnCategoryHolder, AddOnCategoriesAdapter.AddOnHolder> {

    @Nullable
    private ExpandableGroup currentExpandedGroup = null;

    AddOnCategoriesAdapter(List<AddOn> addOns) {
        super(createCategoryGroups(addOns));
    }

    @Override
    public AddOnCategoryHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.li_addon_category, parent, false);
        return new AddOnCategoryHolder(view);
    }

    @Override
    public AddOnHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.li_addon, parent, false);
        return new AddOnHolder(view);
    }

    @Override
    public void onBindChildViewHolder(AddOnHolder holder, int flatPosition, ExpandableGroup group, int childIndex) {
        final AddOn addOn = (AddOn) group.getItems().get(childIndex);
        holder.addOnName.setText(addOn.getName());
    }

    @Override
    public void onBindGroupViewHolder(AddOnCategoryHolder holder, int flatPosition, ExpandableGroup group) {
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

    private static List<AddOnCategoryGroup> createCategoryGroups(List<AddOn> addOns) {
        if (addOns == null) {
            return new ArrayList<>();
        }

        final List<AddOnCategoryGroup> categoryGroups = new ArrayList<>();
        final Map<String, List<AddOn>> groups = new HashMap<>();

        for (AddOn addOn : addOns) {
            String key = addOn.getCategory().getName();
            if (groups.get(key) == null) {
                groups.put(key, new ArrayList<>());
            }
            groups.get(key).add(addOn);
        }

        for (Map.Entry<String, List<AddOn>> entrySet : groups.entrySet()) {
            categoryGroups.add(new AddOnCategoryGroup(entrySet.getKey(), entrySet.getValue()));
        }

        return categoryGroups;
    }

    static class AddOnCategoryGroup extends ExpandableGroup<AddOn> {
        AddOnCategoryGroup(String title, List<AddOn> data) {
            super(title, data);
        }
    }

    static class AddOnCategoryHolder extends GroupViewHolder {
        @BindView(R.id.category_name)
        TextView categoryName;

        @BindView(R.id.arrow_icon)
        ImageView arrowIcon;

        AddOnCategoryHolder(View itemView) {
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

    static class AddOnHolder extends ChildViewHolder {
        @BindView(R.id.addon_name)
        TextView addOnName;

        AddOnHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

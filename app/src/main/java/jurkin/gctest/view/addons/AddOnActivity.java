package jurkin.gctest.view.addons;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import jurkin.gctest.R;
import jurkin.gctest.base.BaseActivity;
import jurkin.gctest.model.Meal;

/**
 * Created by Andrej Jurkin on 1/14/18.
 */

public class AddOnActivity extends BaseActivity {
    public static final String ARG_MEAL_ID = "arg_meal_id";

    public static void startActivity(Context context, Meal meal) {
        Intent i = new Intent(context, AddOnActivity.class);
        i.putExtra(ARG_MEAL_ID, meal.getId());
        context.startActivity(i);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addon);
        showBackButton();
        setTitle(getString(R.string.prilohy));
    }
}

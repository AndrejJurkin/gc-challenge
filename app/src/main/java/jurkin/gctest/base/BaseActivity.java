package jurkin.gctest.base;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by Andrej Jurkin on 1/11/18.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    public void showBackButton() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }
}

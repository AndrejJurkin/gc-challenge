package jurkin.gctest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import jurkin.gctest.base.BaseActivity;
import jurkin.gctest.base.BaseFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}

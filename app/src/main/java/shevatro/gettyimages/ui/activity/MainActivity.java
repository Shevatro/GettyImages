package shevatro.gettyimages.ui.activity;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import shevatro.gettyimages.R;
import shevatro.gettyimages.ui.fragment.SearchFragment;
import shevatro.gettyimages.ui.fragment.ListFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.edition_container, new SearchFragment())
                    .add(R.id.list_container, new ListFragment())
                    .commit();
        }
    }


}

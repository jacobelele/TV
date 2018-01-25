package com.example.nazam.grandhika;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SceneryActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scenery);
        if (findViewById(R.id.scenery_menu_fragment) != null) {
            if (savedInstanceState != null) {
                return;
            }
            SceneryFragment firstFragment = new SceneryFragment();
            firstFragment.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.scenery_menu_fragment, firstFragment).commit();
        }

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }
}

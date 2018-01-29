package com.example.nazam.grandhika;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DiningActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dining);
        if(findViewById(R.id.dining_menu_fragment)!=null){
            if(savedInstanceState!=null){
                return;
            }
            MenuDiningFragment menuDiningFragment = new MenuDiningFragment();
            menuDiningFragment.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.dining_menu_fragment, menuDiningFragment).commit();
        }

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }
}

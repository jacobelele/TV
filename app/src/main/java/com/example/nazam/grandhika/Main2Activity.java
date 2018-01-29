package com.example.nazam.grandhika;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        TextView runningText = (TextView)findViewById(R.id.runningText);
        runningText.setSelected(true);
        runningText.setHorizontallyScrolling(true);
    }
}

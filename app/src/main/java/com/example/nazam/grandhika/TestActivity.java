package com.example.nazam.grandhika;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import junit.framework.Test;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class TestActivity extends AppCompatActivity {
    ImageButton im;
    private String url = "http://195.110.58.237:8080/iptvportal";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        im = findViewById(R.id.imageButton);
        im.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tvIntent = new Intent(TestActivity.this, TvActivity.class);
                startActivity(tvIntent);
            }
        });

        im = findViewById(R.id.imageButton1);
        im.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent fullIntent = new Intent(TestActivity.this, FullscreenActivity.class);
                startActivity(fullIntent);
            }
        });

        new BackgroundNetwork(TestActivity.this){
            @Override
            protected String doInBackground(String... strings) {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder().url(url+"/GetFoodType").build();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                        Log.d("Test Api", e.getMessage());
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if (response.isSuccessful()) {
                            String resString = response.body().string();

//                    Gson gson = new Gson();
//                    Type type = new TypeToken<List<Food>>() {
//                    }.getType();
//                    List<Food> foodList = gson.fromJson(response.body().string(), type);
                            Log.d("Test Api", resString);
                        } else{
                            Log.d("Test Api", response.message());
                        }
                    }
                });
                return super.doInBackground(strings);
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
            }
        }.execute();

    }



}

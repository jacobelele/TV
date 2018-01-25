package com.example.nazam.grandhika;

import android.annotation.SuppressLint;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import model.Setting;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class SettingActivity extends AppCompatActivity {

    private TextView txtProtocol;
    private TextView txtShowLogo;
    private TextView txtPlayPort;
    private TextView txtServerPort;
    private TextView txtShowWatermark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_setting);

        txtProtocol = (TextView)findViewById(R.id.txtProtocol);
        txtShowLogo = (TextView)findViewById(R.id.txtShowLogo);
        txtShowWatermark = (TextView)findViewById(R.id.txtShowWatermark);
        txtPlayPort = (TextView)findViewById(R.id.txtPlayPort);
        txtServerPort = (TextView)findViewById(R.id.txtServerPort);

        api.Adapter.service().setting().enqueue(new Callback<Setting>() {
            @Override
            public void onResponse(Call<Setting> call, Response<Setting> response) {
                if(response.isSuccessful()){
                    Setting setting = response.body();
                    if(setting != null){
                        txtPlayPort.setText(setting.getTimeshiftPlayPort());
                        txtProtocol.setText(setting.getProtocol());
                        txtServerPort.setText(setting.getTimeshiftServerPort());
                        txtShowLogo.setText(setting.getShowLogo().toString());
                        txtShowWatermark.setText(setting.getShowWaterMark().toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<Setting> call, Throwable t) {

            }
        });

    }
}

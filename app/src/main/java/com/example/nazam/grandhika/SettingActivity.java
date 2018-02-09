package com.example.nazam.grandhika;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ResolveInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TableRow;
import android.widget.TextView;

import api.Function;

public class SettingActivity extends AppCompatActivity {

    private EditText eTxtServerIp,eTxtServerPort,eTxtLocalIp,eTxtPassword,eTxtConfimPassword;
    private TextView txtMac;
    private Switch switchPassword;
    private SharedPreferences settings;
    private TableRow rowPass,rowConfirmPass;
    private Button btnApps;
    private Button btnAndroidSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_setting);

        eTxtServerIp = (EditText)findViewById(R.id.eTxtServerIp);
        eTxtServerPort = (EditText)findViewById(R.id.eTxtServerPort);
        eTxtLocalIp = (EditText)findViewById(R.id.eTxtLocalIp);
        txtMac = (TextView)findViewById(R.id.txtMac);
        btnApps = (Button)findViewById(R.id.btnApps);
        btnAndroidSetting = (Button)findViewById(R.id.btnAndroidSetting);

        settings = getSharedPreferences("UserInfo", 0);
        eTxtServerIp.setText(settings.getString("server_ip", "101.101.101.9").toString());
        eTxtServerPort.setText(settings.getString("server_port", "8080"));
        eTxtLocalIp.setText(settings.getString("local_ip", Function.getIPAddress(true)).toString());
        txtMac.setText(Function.getMacAddress());

        btnApps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingActivity.this, ListAppActivity.class);
                startActivity(intent);
            }
        });
        btnAndroidSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(android.provider.Settings.ACTION_SETTINGS), 0);
            }
        });

        eTxtServerIp.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(!hasFocus){
                    EditText serverIp = (EditText) view;
                    SharedPreferences.Editor editor = settings.edit();
                    editor.putString("server_ip",serverIp.getText().toString());
                    editor.commit();
                }
            }
        });

        eTxtServerPort.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(!hasFocus){
                    EditText text = (EditText) view;
                    SharedPreferences.Editor editor = settings.edit();
                    editor.putString("server_port",text.getText().toString());
                    editor.commit();
                }
            }
        });

        eTxtLocalIp.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(!hasFocus){
                    EditText text = (EditText) view;
                    SharedPreferences.Editor editor = settings.edit();
                    editor.putString("local_ip",text.getText().toString());
                    editor.commit();
                }
            }
        });

       /* ConnectivityManager connectivityManager=(ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
//        Ether
        NetworkInfo networkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_ETHERNET);
        if(networkInfo.isConnectedOrConnecting()){
//            connectivityManager.
        }*/
    }


}

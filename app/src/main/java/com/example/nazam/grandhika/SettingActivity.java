package com.example.nazam.grandhika;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.FileReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import db.DatabaseHandler;
import model.Setting;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class SettingActivity extends AppCompatActivity {

    private EditText eTxtServerIp,eTxtServerPort,eTxtLocalIp,eTxtPassword,eTxtConfimPassword;
    private TextView txtMac;
    private Switch switchPassword;
    private DatabaseHandler db;
    private SharedPreferences settings;
    private TableRow rowPass,rowConfirmPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_setting);

        eTxtServerIp = (EditText)findViewById(R.id.eTxtServerIp);
        eTxtServerPort = (EditText)findViewById(R.id.eTxtServerPort);
        eTxtLocalIp = (EditText)findViewById(R.id.eTxtLocalIp);
        switchPassword = (Switch)findViewById(R.id.switchPassword);
        eTxtPassword = (EditText)findViewById(R.id.eTxtPassword);
        eTxtConfimPassword = (EditText)findViewById(R.id.eTxtConfirmPassword);
        txtMac = (TextView)findViewById(R.id.txtMac);
        rowPass = (TableRow)findViewById(R.id.rowPass);
        rowConfirmPass = (TableRow)findViewById(R.id.rowConfirmPass);

        settings = getSharedPreferences("UserInfo", 0);
        eTxtServerIp.setText(settings.getString("server_ip", "").toString());
        eTxtServerPort.setText(settings.getString("server_port", "").toString());
        eTxtLocalIp.setText(settings.getString("local_ip", "").toString());
        txtMac.setText(getMacAddress());
        switchPassword.setChecked(settings.getBoolean("enabled_pass",false));

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

        eTxtConfimPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(!hasFocus){
                    EditText text = (EditText) view;
                    if(eTxtPassword.getText().toString().equalsIgnoreCase(text.getText().toString())){
                        try {
                            String result = MessageDigest.getInstance("MD5").digest(text.getText().toString().getBytes()).toString();
                            SharedPreferences.Editor editor = settings.edit();
                            editor.putString("pass",result);
                            editor.commit();
                        } catch (NoSuchAlgorithmException e) {
                            e.printStackTrace();
                        }
                    }else{
                        Toast.makeText(SettingActivity.this, "Password tidak sama, silahkan cek kembali password anda.",5);
                    }
                }
            }
        });

        switchPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean selected) {
                if(selected){
                    SharedPreferences.Editor editor = settings.edit();
                    editor.putBoolean("enabled_pass",true);
                    editor.commit();
                    rowPass.setVisibility(View.VISIBLE);
                    rowConfirmPass.setVisibility(View.VISIBLE);
                }else{
                    SharedPreferences.Editor editor = settings.edit();
                    editor.putBoolean("enabled_pass",false);
                    editor.commit();
                    rowPass.setVisibility(View.INVISIBLE);
                    rowConfirmPass.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    public String getMacAddress(){
        try {
//            return loadFileAsString("/sys/class/net/eth0/address")
//                    .toUpperCase().substring(0, 17);
            return "D0:76:58:01:13:B0";
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String loadFileAsString(String filePath) throws java.io.IOException{
        StringBuffer fileData = new StringBuffer(1000);
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        char[] buf = new char[1024];
        int numRead=0;
        while((numRead=reader.read(buf)) != -1){
            String readData = String.valueOf(buf, 0, numRead);
            fileData.append(readData);
        }
        reader.close();
        return fileData.toString();
    }
}

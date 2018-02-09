package com.example.nazam.grandhika;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.StrictMode;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;

import api.Function;
import model.AdvImage;
import model.Pass;
import model.Room;
import model.SettingApplication;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class FullscreenActivity extends AppCompatActivity {

//    private static final boolean AUTO_HIDE = true;
    private static int currentPage = 0;
    private ImageButton[] im = new ImageButton[5];
    private static final String PASSWORD = "88888888";

//    private static final int AUTO_HIDE_DELAY_MILLIS = 300;

    /**
     * Some older devices needs a small delay between UI widget updates
     * and a change of the status and navigation bar.
     */
//    private static final int UI_ANIMATION_DELAY = 300;
//    private final Handler mHideHandler = new Handler();
//    private View mContentView;
//    private final Runnable mHidePart2Runnable = new Runnable() {
//        @SuppressLint("InlinedApi")
//        @Override
//        public void run() {
//            // Delayed removal of status and navigation bar
//
//            // Note that some of these constants are new as of API 16 (Jelly Bean)
//            // and API 19 (KitKat). It is safe to use them, as they are inlined
//            // at compile-time and do nothing on earlier devices.
//            mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
//                    | View.SYSTEM_UI_FLAG_FULLSCREEN
//                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
//                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
//        }
//    };
//    private View mControlsView;
    /*private final Runnable mShowPart2Runnable = new Runnable() {
        @Override
        public void run() {
            // Delayed display of UI elements
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.show();
            }
//            mControlsView.setVisibility(View.VISIBLE);
        }
    };
    private boolean mVisible;
    private final Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            hide();
        }
    };*/
    /**
     * Touch listener to use for in-layout UI controls to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.
     */
    /*private final View.OnTouchListener mDelayHideTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (AUTO_HIDE) {
                delayedHide(AUTO_HIDE_DELAY_MILLIS);
            }
            return false;
        }
    };*/
    private CustomPageAdapter mCustomPagerAdapter;
    private ViewPager mViewPager;
    private String url;
    private TextView mNameTitle;
    private TextView mTimeTitle;
    private TextView mRoomNumber;
    private TextView mDateTitle;
    private SharedPreferences settings;
    private long timeMilis;
    private List<Pass> listPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fullscreen);

        mNameTitle = findViewById(R.id.txtNameTitle);
        mRoomNumber = findViewById(R.id.txtRoomNumber);
        mDateTitle = findViewById(R.id.txtDateTitle);
        mTimeTitle = findViewById(R.id.txtTimeTitle);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);

        settings = getSharedPreferences("UserInfo", 0);
        ((SettingApplication)this.getApplication()).setServerIp(settings.getString("server_ip", "101.101.101.9").toString());
        ((SettingApplication)this.getApplication()).setServerPort(settings.getString("server_port", "8080"));
        ((SettingApplication)this.getApplication()).setMacAddress(settings.getString("mac_address", Function.getMacAddress()));
        url = "http://"+((SettingApplication)this.getApplication()).getServerIp()+":"+((SettingApplication)this.getApplication()).getServerPort()+"/iptvportal";
        api.Adapter.setBaseUrl(url+"/");
        api.Adapter.service().room(Function.getMacAddress()).enqueue(new Callback<Room>() {
            @Override
            public void onResponse(Call<Room> call, Response<Room> response) {
                if(response.isSuccessful()){
                    Room room = response.body();
                    mNameTitle.setText(room.getCheckPerson());
                    mRoomNumber.setText(mRoomNumber.getText().toString()
                            .subSequence(0,mRoomNumber.getText().length()-1)+room.getName());
                }
            }

            @Override
            public void onFailure(Call<Room> call, Throwable t) {

            }
        });

        api.Adapter.service().listAdvImage().enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                if(response.isSuccessful()){
                    List<String> listAdvImage = response.body();
                    /*if(mBitmap.size()>0)
                        mBitmap.clear();
                    for(int i=0;i<listAdvImage.size();i++){
                        mBitmap.add(Function.getBitmapFromURL(url+listAdvImage.get(i).substring(2)));
                    }*/
                    setPageAdapter(listAdvImage);
                }
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                Log.d("Api Test",t.getMessage());
            }
        });

        api.Adapter.service().getCurrentTimeMillis().enqueue(new Callback<Long>() {
            @Override
            public void onResponse(Call<Long> call, Response<Long> response) {
                timeMilis = response.body();
                SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy   HH:mm:ss");
                sdf.setTimeZone(TimeZone.getTimeZone("Asia/Jakarta"));
                Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Jakarta")
                        ,new Locale("id","ID"));
                sdf.setCalendar(calendar);
                calendar.setTimeInMillis(timeMilis);
                mDateTitle.setText(sdf.format(calendar.getTime()));
                Timer time = new Timer();
                time.schedule(new TimerTask() {
                   @Override
                   public void run() {
                       mDateTitle.post(new Runnable() {
                           public void run() {
                               SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy   HH:mm:ss");
                               sdf.setTimeZone(TimeZone.getTimeZone("Asia/Jakarta"));
                               Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Jakarta"),new Locale("id","ID"));
                               timeMilis+=1000;
                               calendar.setTimeInMillis(timeMilis);
                               mDateTitle.setText(sdf.format(calendar.getTime()));
                           }
                       });
                   }
                },1000,1000);
            }

            @Override
            public void onFailure(Call<Long> call, Throwable t) {

            }
        });


        ImageButton imx = findViewById(R.id.TvChannel);
        imx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tvIntent = new Intent(FullscreenActivity.this, TvActivity.class);
                startActivity(tvIntent);
            }
        });
        im[0] = imx;

        ImageButton im1 = findViewById(R.id.InHousePromo);
        im1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent diningIntent = new Intent(FullscreenActivity.this, InHousePromoActivity.class);
                diningIntent.putExtra("DATE_NOW",mDateTitle.getText());
                startActivity(diningIntent);
            }
        });
        im[1] = im1;

        ImageButton im2 = findViewById(R.id.Dining);
        im2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sceneryIntent = new Intent(FullscreenActivity.this, DiningActivity.class);
                startActivity(sceneryIntent);
            }
        });
        im[2]=im2;

        ImageButton im3 = findViewById(R.id.Scenery);
        im3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent settingIntent = new Intent(FullscreenActivity.this, SceneryActivity.class);
                startActivity(settingIntent);
            }
        });
        im[3]=im3;

        api.Adapter.service().listPass().enqueue(new Callback<List<Pass>>() {
            @Override
            public void onResponse(Call<List<Pass>> call, Response<List<Pass>> response) {
                if(response.isSuccessful()){
                    listPass = response.body();
                }
            }

            @Override
            public void onFailure(Call<List<Pass>> call, Throwable t) {

            }
        });
        ImageButton im4 = findViewById(R.id.Setting);
        im4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                LayoutInflater li = LayoutInflater.from(FullscreenActivity.this);
                View promptsView = li.inflate(R.layout.layout_dialog, null);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        FullscreenActivity.this);
                alertDialogBuilder.setView(promptsView);
                final EditText userInput = (EditText) promptsView
                        .findViewById(R.id.editTextPassword);
                userInput.setTransformationMethod(new AsteriskPasswordTransformationMethod());
                alertDialogBuilder
                        .setCancelable(false)
                        .setOnKeyListener(new DialogInterface.OnKeyListener() {
                            @Override
                            public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                                if(keyEvent.getKeyCode()==KeyEvent.KEYCODE_DPAD_CENTER||
                                        keyEvent.getKeyCode()==KeyEvent.KEYCODE_ENTER||
                                        keyEvent.getKeyCode()==KeyEvent.KEYCODE_BUTTON_SELECT||
                                        keyEvent.getKeyCode()==KeyEvent.KEYCODE_NUMPAD_ENTER){
                                    if(!listPass.isEmpty()) {
                                        if (Function.md5(userInput.getText().toString()).toUpperCase()
                                                .equalsIgnoreCase(listPass.get(0).getHashPassword())) {
                                            Intent settingIntent = new Intent(FullscreenActivity.this, SettingActivity.class);
                                            startActivity(settingIntent);
                                            dialogInterface.dismiss();
                                            return true;
                                        } else {
                                            Toast.makeText(FullscreenActivity.this, "Wrong Password", Toast.LENGTH_SHORT);
                                            dialogInterface.dismiss();
                                            return false;
                                        }
                                    }else
                                        return false;
                                }else
                                    return false;
                            }
                        });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });
        im[4]=im4;
    }

    private void setPageAdapter(List<String> listURLImage){
        mCustomPagerAdapter = new CustomPageAdapter(getApplicationContext(),listURLImage);
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mCustomPagerAdapter);
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage >= 6) {
                    currentPage = 0;
                }
                mViewPager.setCurrentItem(currentPage++, true);
                mViewPager.setFocusable(false);
                mViewPager.setFocusableInTouchMode(false);
//                im.setFocusable(true);
//                im.setFocusableInTouchMode(true);///add this line
//                im.requestFocus();
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 2500, 2500);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        im[0].setFocusable(true);
        im[0].setFocusableInTouchMode(true);///add this line
        im[0].requestFocus();
    }
/*
    private void toggle() {
        if (mVisible) {
            hide();
        } else {
            show();
        }
    }

    private void hide() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        mVisible = false;
    }

    @SuppressLint("InlinedApi")
    private void show() {
        // Show the system bar
        mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        mVisible = true;

        // Schedule a runnable to display UI elements after a delay
        mHideHandler.removeCallbacks(mHidePart2Runnable);
        mHideHandler.postDelayed(mShowPart2Runnable, UI_ANIMATION_DELAY);
    }

    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }*/

//    private List<Bitmap> mBitmap = new ArrayList<Bitmap>();

    class CustomPageAdapter extends PagerAdapter{
        Context mContext;
        LayoutInflater mLayoutInflater;
        List<String> mListURLImage;

        public CustomPageAdapter(Context context,List<String> listUrlImage){
            mContext = context;
            mListURLImage = listUrlImage;
            mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public void notifyDataSetChanged() {
            super.notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return 6;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == ((LinearLayout) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View itemView = mLayoutInflater.inflate(R.layout.pager_item, container, false);

            ImageView imageView = (ImageView) itemView.findViewById(R.id.imageSlideView);
            Picasso.with(itemView.getContext()).load(url + mListURLImage.get(position).substring(2)).into(imageView);

            container.addView(itemView);
            return itemView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((LinearLayout)object);
        }
    }

    public class AsteriskPasswordTransformationMethod extends PasswordTransformationMethod {
        @Override
        public CharSequence getTransformation(CharSequence source, View view) {
            return new PasswordCharSequence(source);
        }

        private class PasswordCharSequence implements CharSequence {
            private CharSequence mSource;
            public PasswordCharSequence(CharSequence source) {
                mSource = source; // Store char sequence
            }
            public char charAt(int index) {
                return '*'; // This is the important part
            }
            public int length() {
                return mSource.length(); // Return default
            }
            public CharSequence subSequence(int start, int end) {
                return mSource.subSequence(start, end); // Return default
            }
        }
    };
}

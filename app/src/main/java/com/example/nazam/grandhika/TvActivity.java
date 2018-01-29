package com.example.nazam.grandhika;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import model.Food;
import model.RunningText;
import model.TvChannel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class TvActivity extends AppCompatActivity {
    /**
     * Whether or not the system UI should be auto-hidden after
     * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
     */
    /*private static final boolean AUTO_HIDE = true;

    *//**
     * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
     * user interaction before hiding the system UI.
     *//*
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

    *//**
     * Some older devices needs a small delay between UI widget updates
     * and a change of the status and navigation bar.
     *//*
    private static final int UI_ANIMATION_DELAY = 300;
    private final Handler mHideHandler = new Handler();
    private View mContentView;
    private final Runnable mHidePart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            // Delayed removal of status and navigation bar

            // Note that some of these constants are new as of API 16 (Jelly Bean)
            // and API 19 (KitKat). It is safe to use them, as they are inlined
            // at compile-time and do nothing on earlier devices.
            mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }
    };
    private View mControlsView;
    private final Runnable mShowPart2Runnable = new Runnable() {
        @Override
        public void run() {
            // Delayed display of UI elements
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.show();
            }
            mControlsView.setVisibility(View.VISIBLE);
        }
    };
    private boolean mVisible;
    private final Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            hide();
        }
    };
    *//**
     * Touch listener to use for in-layout UI controls to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.
     *//*
    private final View.OnTouchListener mDelayHideTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (AUTO_HIDE) {
                delayedHide(AUTO_HIDE_DELAY_MILLIS);
            }
            return false;
        }
    };*/
    private ListView listMenuItem;
    private String url = "http://195.110.58.237:8080/iptvportal";
    private VideoView videoView;
    private List<TvChannel> listChannel;
    private ProgressDialog pDialog;
    private TextView runningText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_tv);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
//        mVisible = true;
//        mControlsView = findViewById(R.id.fullscreen_content_controls);
        /*mContentView = findViewById(R.id.fullscreen_content);


        // Set up the user interaction to manually show or hide the system UI.
        mContentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggle();
            }
        });*/

        videoView = (VideoView) findViewById(R.id.videoView);
        listMenuItem = (ListView)findViewById(android.R.id.list);
        listMenuItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.v("Fariz","index:"+i);
                pDialog = new ProgressDialog(TvActivity.this);
                // Set progressbar title
                pDialog.setTitle("Live Tv Hotel GranDhika");
                // Set progressbar message
                pDialog.setMessage("Buffering...");
                pDialog.setIndeterminate(false);
                pDialog.setCancelable(false);
                // Show progressbar
                pDialog.show();

                try {
                    // Start the MediaController
                    MediaController mediacontroller = new MediaController(
                            TvActivity.this);
//                    mediacontroller.setAnchorView(videoView);
                    // Get the URL from String VideoURL
                    Log.v("Fariz","url:"+listChannel.get(i).getUrl());
                    Uri video = Uri.parse(listChannel.get(i).getUrl());
                    videoView.setMediaController(mediacontroller);
                    videoView.setVideoURI(video);

                } catch (Exception e) {
                    Log.e("Error Fariz", e.getMessage());
                    e.printStackTrace();
                }

                videoView.requestFocus();
                videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    // Close the progress bar and play the video
                    public void onPrepared(MediaPlayer mp) {
                        pDialog.dismiss();
                        videoView.start();
                    }
                });
            }
        });

        api.Adapter.service().tvChannel(getMacAddress(),0).enqueue(new Callback<List<TvChannel>>() {
            @Override
            public void onResponse(Call<List<TvChannel>> call, Response<List<TvChannel>> response) {
                if(response.isSuccessful()){
                    listChannel = response.body();
                    StableArrayAdapter adapter = new StableArrayAdapter(TvActivity.this,
                            android.R.layout.simple_list_item_1, android.R.id.text1,
                            listChannel);
                    listMenuItem.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<TvChannel>> call, Throwable t) {

            }
        });

        // Upon interacting with UI controls, delay any scheduled hide()
        // operations to prevent the jarring behavior of controls going away
        // while interacting with the UI.
//        findViewById(R.id.).setOnTouchListener(mDelayHideTouchListener);
        runningText = (TextView)findViewById(R.id.txtMarquee);
        runningText.setSelected(true);
        TranslateAnimation tanim = new TranslateAnimation(
                TranslateAnimation.ABSOLUTE, 1.0f * videoView.getWidth(),
                TranslateAnimation.ABSOLUTE, -1.0f * videoView.getWidth(),
                TranslateAnimation.ABSOLUTE, 0.0f,
                TranslateAnimation.ABSOLUTE, 0.0f);
        tanim.setDuration(100);
        tanim.setInterpolator(new LinearInterpolator());
        tanim.setRepeatCount(Animation.INFINITE);
        tanim.setRepeatMode(Animation.ABSOLUTE);
        runningText.startAnimation(tanim);

        api.Adapter.service().runningText(getMacAddress()).enqueue(new Callback<List<RunningText>>() {
            @Override
            public void onResponse(Call<List<RunningText>> call, Response<List<RunningText>> response) {
                if(response.isSuccessful()){
                    List<RunningText> listRt = response.body();
//                    runningText.setText(listRt.get(0).getContent());
                }
            }

            @Override
            public void onFailure(Call<List<RunningText>> call, Throwable t) {

            }
        });
    }

/*    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        delayedHide(100);
    }

    private void toggle() {
        if (mVisible) {
            hide();
        } else {
            show();
        }
    }

    private void hide() {
        // Hide UI first
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        mControlsView.setVisibility(View.GONE);
        mVisible = false;

        // Schedule a runnable to remove the status and navigation bar after a delay
        mHideHandler.removeCallbacks(mShowPart2Runnable);
        mHideHandler.postDelayed(mHidePart2Runnable, UI_ANIMATION_DELAY);
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

    *//**
     * Schedules a call to hide() in delay milliseconds, canceling any
     * previously scheduled calls.
     *//*
    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }*/

    private class StableArrayAdapter extends ArrayAdapter<TvChannel> {
        private final Context context;
        HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

        public StableArrayAdapter(Context context,int layoutResourceId, int textViewResourceId,
                                  List<TvChannel> objects) {

            super(context,layoutResourceId, textViewResourceId, objects);
            this.context = context;
            for (int i = 0; i < objects.size(); ++i) {
                mIdMap.put(objects.get(i).getName(), i);
            }
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            TvChannel tvChannel = getItem(position);
            if(convertView==null)
                convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
            TextView textView = (TextView) convertView.findViewById(android.R.id.text1);
            textView.setTextSize(12f);
            textView.setText(tvChannel.getNumber()+". "+tvChannel.getName());
//            textView.setBackground(Drawable.createFromStream(getBitmapFromURL(url+tvChannel.getImagePath().substring(2)),null));
            textView.setTextColor(getResources().getColor(android.R.color.white));

            return convertView;
        }

        @Override
        public long getItemId(int position) {
            String item = getItem(position).getName();
            return mIdMap.get(item);
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

    }

    public static InputStream getBitmapFromURL(String src){
        try{
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            return input;
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
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

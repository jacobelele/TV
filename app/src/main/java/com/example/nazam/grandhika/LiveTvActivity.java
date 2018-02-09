package com.example.nazam.grandhika;

import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import java.util.Timer;
import java.util.TimerTask;

public class LiveTvActivity extends AppCompatActivity {

    private static final int HANDLE_INPUT_NUMBER = 4097;
    private static final int HANDLE_INPUT_NUMBER_HIDE = 4098;
    private ProgressDialog pDialog;
    private VideoView videoView;
    private MediaController mediaController;
    private String[] videoList;
    private int index=0;
//    private int[] channelNumList;
//    private String[] channelNameList;
    private TextView txtChannel;
    private Timer inputNumberTimer;
    private String inputNumber = null;
    private Handler handler = new ChannelHandler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_live_tv);
        
        videoView = (VideoView)findViewById(R.id.videoView);
        txtChannel = (TextView) findViewById(R.id.editTextChannel);

        videoView.setZOrderMediaOverlay(true);
        pDialog = new ProgressDialog(LiveTvActivity.this);
        pDialog.setTitle("Tv");
        pDialog.setMessage("Buffering...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(true);
        pDialog.show();
        /*try {
            mediaController = new MediaController(this);
            mediaController.setAnchorView(videoView);
            mediaController.hide();

        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }*/
        startVideo(getIntent().getIntExtra("SELECTED_UDP",0));
    }

    private void startVideo(int i){
        int sizeVideoList = getIntent().getIntExtra("LINK_UDP_SIZE",0);
        index =i;
        videoList = new String[sizeVideoList];
        videoList = getIntent().getStringArrayExtra("LINK_UDP").clone();
        videoView.setMediaController(mediaController);
        videoView.setVideoURI(Uri.parse(videoList[i]));
        videoView.requestFocus();
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            public void onPrepared(MediaPlayer mp) {
                pDialog.dismiss();
                videoView.start();
            }
        });
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch(keyCode){
            case KeyEvent.KEYCODE_DPAD_UP:
                index -= 1;
                if(index < 0) {
                    index = videoList.length-1;
                    videoView.stopPlayback();
                    startVideo(index);
                }else {
                    videoView.stopPlayback();
                    startVideo(index);
                }
                return true;
            case KeyEvent.KEYCODE_DPAD_DOWN:
                index += 1;
                if(index >= videoList.length){
                    videoView.stopPlayback();
                    index = 0;
                    startVideo(index);
                }else {
                    videoView.stopPlayback();
                    startVideo(index);
                }
                return true;
            default:
                if(keyCode >= KeyEvent.KEYCODE_0 && keyCode <= KeyEvent.KEYCODE_9) {
                    int number = keyCode - 7;
                    if (inputNumberTimer != null) {
                        inputNumberTimer.cancel();
                    }
                    if (inputNumber != null) {
                        if (inputNumber.length() != 1) {
                            if (inputNumber.length() == 2) {
                                inputNumber += number;
                                handler.sendEmptyMessage(HANDLE_INPUT_NUMBER);
                                break;
                            }
                        }
                        this.inputNumber += number;
                        txtChannel.setText(this.inputNumber + "-");
                        txtChannel.setVisibility(View.VISIBLE);
                        inputNumberTimer = new Timer();
                        inputNumberTimer.schedule(new TimerTask() {
                            public void run() {
                                LiveTvActivity.this.handler.sendEmptyMessage(HANDLE_INPUT_NUMBER);
                            }
                        }, 2000);
                        break;
                    }
                    inputNumber = new StringBuilder(String.valueOf(number)).toString();
                    txtChannel.setText(this.inputNumber + "--");
                    txtChannel.setVisibility(View.VISIBLE);
                    inputNumberTimer = new Timer();
                    inputNumberTimer.schedule(new TimerTask() {
                        public void run() {
                            LiveTvActivity.this.handler.sendEmptyMessage(HANDLE_INPUT_NUMBER);
                        }
                    }, 2000);
                    break;
                }
                break;
        }
        return super.onKeyDown(keyCode, event);
    }

    /*@Override
    public boolean onKeyMultiple(int keyCode, int repeatCount, KeyEvent event) {
//        switch (keycode)
        if(repeatCount==1){

            Toast.makeText(getBaseContext(),"fariz key count 1 :"+keyCode,Toast.LENGTH_LONG);
        }else if(repeatCount==2){
            if(Integer.parseInt(event.getCharacters())==11) {
                videoView.stopPlayback();
                startVideo(index);
            }
            Toast.makeText(getBaseContext(),"fariz key count 2 :"+keyCode,Toast.LENGTH_LONG);
        }
        return super.onKeyMultiple(keyCode, repeatCount, event);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if(menu.size()>0)
            menu.clear();
        channelNumList = new int[getIntent().getIntExtra("LINK_UDP_SIZE",0)];
        channelNumList = getIntent().getIntArrayExtra("CHANNEL_NUM_LIST").clone();
        channelNameList = new String[getIntent().getIntExtra("LINK_UDP_SIZE",0)];
        channelNameList = getIntent().getStringArrayExtra("CHANNEL_NAME_LIST").clone();
        for(int i = 0;i<channelNumList.length;i++) {
            menu.add(1, channelNumList[i], channelNumList[i], channelNumList[i] + " - " + channelNameList[i]);
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()!=0) {
            videoView.stopPlayback();
            startVideo(item.getItemId() - 1);
            return true;
        }else
            return super.onOptionsItemSelected(item);
    }*/

    class ChannelHandler extends Handler {

        class ChannelTimerTask extends TimerTask {
            ChannelTimerTask() {
            }

            public void run() {
                LiveTvActivity.this.handler.sendEmptyMessage(HANDLE_INPUT_NUMBER_HIDE);
            }
        }

        ChannelHandler() {
        }

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case HANDLE_INPUT_NUMBER:
                    int number = Integer.parseInt(LiveTvActivity.this.inputNumber);
                    if (number < 0) {
                        LiveTvActivity.this.handler.sendEmptyMessage(HANDLE_INPUT_NUMBER_HIDE);
                    } else {
                        LiveTvActivity.this.txtChannel.setText(new StringBuilder(String.valueOf(number)).toString());
                        if (!(number <= 0 || number > LiveTvActivity.this.videoList.length)) {
                            index = number - 1;
                            LiveTvActivity.this.startVideo(index);
                        }
                        LiveTvActivity.this.inputNumberTimer = new Timer();
                        LiveTvActivity.this.inputNumberTimer.schedule(new ChannelTimerTask(), 2000);
                    }
                    LiveTvActivity.this.inputNumber = null;
                    return;
                case HANDLE_INPUT_NUMBER_HIDE:
                    LiveTvActivity.this.txtChannel.setText(BuildConfig.FLAVOR);
                    LiveTvActivity.this.txtChannel.setVisibility(View.GONE);
                    LiveTvActivity.this.inputNumberTimer = null;
                    return;
                default:
                    return;
            }
        }
    }
}
package com.example.nazam.grandhika;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.SurfaceView;
import android.view.View;

import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.Extractor;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.extractor.ts.TsExtractor;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.MediaSourceEventListener;
import com.google.android.exoplayer2.source.SingleSampleMediaSource;
import com.google.android.exoplayer2.source.dash.DashMediaSource;
import com.google.android.exoplayer2.source.dash.DefaultDashChunkSource;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.source.smoothstreaming.DefaultSsChunkSource;
import com.google.android.exoplayer2.source.smoothstreaming.SsMediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.DefaultAllocator;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.*;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.upstream.TransferListener;
import com.google.android.exoplayer2.upstream.UdpDataSource;
import com.google.android.exoplayer2.util.PriorityTaskManager;
import com.google.android.exoplayer2.util.TimestampAdjuster;
import com.google.android.exoplayer2.util.Util;
import com.google.android.exoplayer2.extractor.ts.DefaultTsPayloadReaderFactory;

import static com.google.android.exoplayer2.extractor.ts.TsExtractor.MODE_SINGLE_PMT;

public class ExoActivity extends AppCompatActivity {

    private SimpleExoPlayerView playerView;
    private SimpleExoPlayer player;
    private String url = "udp://239.255.42.202:4321";
//    private String url = "udp://10.10.29.18:1234";
//    private String url = "udp://10.0.2.2:1234";
//    private String url = "http://10.0.2.2:8080/test1";
    private long playbackPosition;
    private int currentWindow;
    private boolean playWhenReady;
    private DataSource.Factory mediaDataSourceFactory;
    private static final DefaultBandwidthMeter BANDWIDTH_METER = new DefaultBandwidthMeter();
    private SurfaceView sufaceview;
    private DefaultExtractorsFactory extractorsFactory;
    private UdpDataSource udpDataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exo);

//        Intent intent = new Intent(ExoActivity.this, VideoDecoderActivity.class);
//        startActivity(intent);
        playerView = findViewById(R.id.video_view);
//        initializePlayer();

        /*sufaceview = (SurfaceView) findViewById(R.id.surfaceView2);
        BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        TrackSelection.Factory videoTrackSelectionFactory =
                new AdaptiveTrackSelection.Factory(bandwidthMeter);
        TrackSelector trackSelector =
                new DefaultTrackSelector(videoTrackSelectionFactory);

        LoadControl loadControl;
        DefaultAllocator da = new DefaultAllocator(true, C.DEFAULT_BUFFER_SEGMENT_SIZE);
        loadControl = new DefaultLoadControl(da);


        player = ExoPlayerFactory.newSimpleInstance(this, trackSelector, loadControl);


        Uri uri =
                Uri.parse
                        (url);

        final DefaultBandwidthMeter bandwidthMeterA = new DefaultBandwidthMeter();

        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(this,
                Util.getUserAgent(this, "teveolauncher"), bandwidthMeterA);

        extractorsFactory = new DefaultExtractorsFactory();

        DataSource.Factory udsf = new UdpDataSource.Factory() {
            @Override
            public DataSource createDataSource() {
                return new UdpDataSource(null, 3000, 100000);
            }
        };
        ExtractorsFactory tsExtractorFactory = new ExtractorsFactory() {
            @Override
            public Extractor[] createExtractors() {
                return new TsExtractor[]{new TsExtractor(MODE_SINGLE_PMT,
                        new TimestampAdjuster(0), new DefaultTsPayloadReaderFactory())};
            }
        };



        MediaSource videoSource = new ExtractorMediaSource
                (uri, udsf, tsExtractorFactory, null, null);

        player.setVideoSurfaceView(sufaceview);
        player.prepare(videoSource);
        player.setPlayWhenReady(true);*/
    }

    private void initializePlayer() {
        player = ExoPlayerFactory.newSimpleInstance(
                new DefaultRenderersFactory(this),
                new DefaultTrackSelector(), new DefaultLoadControl());

        playerView.setPlayer(player);

        player.setPlayWhenReady(playWhenReady);
        player.seekTo(currentWindow, playbackPosition);

        Uri uri = Uri.parse(url);
//        DataSpec ds = new DataSpec(uri);
//        try {
//            udpDataSource = new UdpDataSource(new DefaultBandwidthMeter());
//            udpDataSource.close();
//            udpDataSource.open(ds);
//        } catch (UdpDataSource.UdpDataSourceException e) {
//            e.printStackTrace();
//            udpDataSource.close();
//        }
//        UriDataSource
//        mediaDataSourceFactory = new DefaultDataSourceFactory(this, Util.getUserAgent(this, "DemoApplication"), BANDWIDTH_METER);
//        mediaDataSourceFactory = new PriorityDataSourceFactory(this, new PriorityTaskManager().add(1),1);
        mediaDataSourceFactory = new DataSource.Factory() {
            @Override
            public DataSource createDataSource() {
                return new UdpDataSource(new DefaultBandwidthMeter());
            }
        };
//        MediaSource mediaSource = new SingleSampleMediaSource(uri,mediaDataSourceFactory, Format
//                .createVideoContainerFormat(null,null,null,"H264",1200, 720, 482, 25f,null,0 ),);
        MediaSource mediaSource = new SingleSampleMediaSource.Factory(mediaDataSourceFactory).createMediaSource(uri,Format
                .createVideoContainerFormat(null,
                        null,
                        null,
                        "MPEG-1/2",
                        3200,
                        720,
                        576,
                        25f,
                        null,
                        0 ),0);
//        MediaSource mediaSource = buildMediaSource(uri,C.TYPE_DASH);
//        MediaSource mediaSource = new ExtractorMediaSource(uri,...);
        player.prepare(mediaSource, true, false);
    }

    /*private MediaSource buildMediaSource(Uri uri) {
        return  new
//        return new DefaultExtractorsFactory().
        *//*return new ExtractorMediaSource.Factory(
                new DefaultHttpDataSourceFactory("exoplayer-codelab")).
                createMediaSource(uri);*//*
    }*/
    private MediaSource buildMediaSource(
            Uri uri, int type
            /*String overrideExtension,
            @Nullable Handler handler,
            @Nullable MediaSourceEventListener listener*/) {
//        @C.ContentType int type = TextUtils.isEmpty(overrideExtension) ? Util.inferContentType(uri)
//                : Util.inferContentType("." + overrideExtension);
        switch (type) {
            case C.TYPE_DASH:
                return new DashMediaSource.Factory(
                        new DefaultDashChunkSource.Factory(mediaDataSourceFactory),
                        buildDataSourceFactory(false))
                        .createMediaSource(uri/*, handler, listener*/);
            case C.TYPE_SS:
                return new SsMediaSource.Factory(
                        new DefaultSsChunkSource.Factory(mediaDataSourceFactory),
                        buildDataSourceFactory(false))
                        .createMediaSource(uri/*, handler, listener*/);
            case C.TYPE_HLS:
                return new HlsMediaSource.Factory(mediaDataSourceFactory)
                        .createMediaSource(uri/*, handler, listener*/);
            case C.TYPE_OTHER:
                return new ExtractorMediaSource.Factory(mediaDataSourceFactory)
                        .createMediaSource(uri/*, handler, listener*/);
//            case 29:
//                return new ExtractorMediaSource.Factory(mediaDataSourceFactory).`
            default: {
                throw new IllegalStateException("Unsupported type: " + type);
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (Util.SDK_INT > 23) {
            initializePlayer();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        hideSystemUi();
        if ((Util.SDK_INT <= 23 || player == null)) {
            initializePlayer();
        }
    }

    @SuppressLint("InlinedApi")
    private void hideSystemUi() {
        playerView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }

    @Override
    public void onPause() {
        super.onPause();
        if (Util.SDK_INT <= 23) {
            releasePlayer();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (Util.SDK_INT > 23) {
            releasePlayer();
        }
    }

    private void releasePlayer() {
        if (player != null) {
            playbackPosition = player.getCurrentPosition();
            currentWindow = player.getCurrentWindowIndex();
            playWhenReady = player.getPlayWhenReady();
            player.release();
            player = null;
        }
    }

    private DataSource.Factory buildDataSourceFactory(boolean useBandwidthMeter) {
        return ((DemoApplication) getApplication())
                .buildDataSourceFactory(useBandwidthMeter ? BANDWIDTH_METER : null);
    }
}

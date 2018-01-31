package com.example.nazam.grandhika;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import api.Function;
import model.Scenery;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SceneryActivity extends AppCompatActivity {
    private List<Bitmap> mBitmap=new ArrayList<Bitmap>();
    private String url;
    private CustomPageAdapter mCustomPagerAdapter;
    private ViewPager mViewPager;
    private static int currentPage = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scenery);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
        SharedPreferences settings = getSharedPreferences("UserInfo", 0);
        String serverIp = settings.getString("server_ip", "101.101.101.9").toString();
        String serverPort = settings.getString("server_port", "8080").toString();
        url = "http://"+serverIp+":"+serverPort+"/iptvportal";

        api.Adapter.service().listScenery(0).enqueue(new Callback<List<Scenery>>() {
            @Override
            public void onResponse(Call<List<Scenery>> call, Response<List<Scenery>> response) {
                if(response.isSuccessful()){
                    List<Scenery> sceneryList = response.body();
                    if(mBitmap.size()>0)
                        mBitmap.clear();
                    for(int i=0;i<sceneryList.size();i++){
                        mBitmap.add(Function.getBitmapFromURL(url + sceneryList.get(i)
                                .getImagePath().substring(2)));
                    }
                    setPageAdapter(mBitmap.size(),sceneryList);
                }
            }

            @Override
            public void onFailure(Call<List<Scenery>> call, Throwable t) {

            }
        });

        /*if (findViewById(R.id.scenery_menu_fragment) != null) {
            if (savedInstanceState != null) {
                return;
            }
            SceneryFragment firstFragment = new SceneryFragment();
            firstFragment.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.scenery_menu_fragment, firstFragment).commit();
        }*/
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    private void setPageAdapter(Integer count,List<Scenery> sceneryList){
        mCustomPagerAdapter = new SceneryActivity.CustomPageAdapter(getApplicationContext(),sceneryList);
        mCustomPagerAdapter.setCount(count);
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mCustomPagerAdapter);
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage >= mCustomPagerAdapter.getCount()) {
                    currentPage = 0;
                }
                mViewPager.setCurrentItem(currentPage++, true);
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

    class CustomPageAdapter extends PagerAdapter {
        Context mContext;
        LayoutInflater mLayoutInflater;
        Integer imageCount=0;
        List<Scenery> mSceneryList;

        public CustomPageAdapter(Context context,List<Scenery> sceneryList){
            mContext = context;
            mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mSceneryList = sceneryList;
        }

        @Override
        public int getCount() {
            return imageCount;
        }

        public void setCount(int count){
            imageCount = count;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == ((FrameLayout) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container,final int position) {
            View itemView = mLayoutInflater.inflate(R.layout.pager_item_text, container, false);

            TextView textView = (TextView) itemView.findViewById(R.id.textDesc);
            textView.setVisibility(View.INVISIBLE);
            ImageView imageView = (ImageView) itemView.findViewById(R.id.imageSlideViewText);
            imageView.setBackgroundColor(0x80FFFFFF);
            imageView.setImageBitmap(mBitmap.get(position));

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    TextView textView = (TextView) findViewById(R.id.textDesc);
                    textView.setText(mSceneryList.get(position).getDescription());
                    textView.setVisibility(View.VISIBLE);
                }
            });

            container.addView(itemView);
            return itemView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((FrameLayout)object);
        }
    }
}

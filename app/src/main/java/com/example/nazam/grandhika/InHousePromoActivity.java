package com.example.nazam.grandhika;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import model.Promo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InHousePromoActivity extends AppCompatActivity {

    private CustomPageAdapter mCustomPagerAdapter;
    private ViewPager mViewPager;
    private static int currentPage = 0;
    private List<Bitmap> mBitmap = new ArrayList<Bitmap>();
    private String url = "http://195.110.58.237:8080/iptvportal";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_in_house_promo);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);

        api.Adapter.service().listPromo(0).enqueue(new Callback<List<Promo>>() {
            @Override
            public void onResponse(Call<List<Promo>> call, Response<List<Promo>> response) {
                List<Promo> listPromo = response.body();
                if(mBitmap.size()>0)
                    mBitmap.clear();
                for(int i=0;i<listPromo.size();i++){
                    mBitmap.add(getBitmapFromURL(url+listPromo.get(i).getImageUrl().substring(2)));
                }
                setPageAdapter(listPromo.size());
            }

            @Override
            public void onFailure(Call<List<Promo>> call, Throwable t) {

            }
        });
    }

    private void setPageAdapter(Integer count){
        mCustomPagerAdapter = new CustomPageAdapter(getApplicationContext());
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

        public CustomPageAdapter(Context context){
            mContext = context;
            mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
            return view == ((LinearLayout) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View itemView = mLayoutInflater.inflate(R.layout.pager_item, container, false);

            ImageView imageView = (ImageView) itemView.findViewById(R.id.imageSlideView);
            imageView.setImageBitmap(mBitmap.get(position));

            container.addView(itemView);
            return itemView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((LinearLayout)object);
        }
    }

    public static Bitmap getBitmapFromURL(String src){
        try{
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(input);
            return bitmap;
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }
}

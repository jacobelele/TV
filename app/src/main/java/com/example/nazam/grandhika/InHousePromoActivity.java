package com.example.nazam.grandhika;

import android.content.Context;
import android.content.SharedPreferences;
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

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import api.Function;
import model.Promo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InHousePromoActivity extends AppCompatActivity {

    private CustomPageAdapter mCustomPagerAdapter;
    private ViewPager mViewPager;
    private static int currentPage = 0;
//    private List<Bitmap> mBitmap = new ArrayList<Bitmap>();
    private String url;
    private SharedPreferences settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_in_house_promo);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);

        settings = getSharedPreferences("UserInfo", 0);
        String serverIp = settings.getString("server_ip", "101.101.101.9").toString();
//        String serverIp = "183.91.78.50";
        String serverPort = settings.getString("server_port", "8080").toString();
//        String serverPort = "2380";
        url = "http://"+serverIp+":"+serverPort+"/iptvportal";
        api.Adapter.service().listPromo(0).enqueue(new Callback<List<Promo>>() {
            @Override
            public void onResponse(Call<List<Promo>> call, Response<List<Promo>> response) {
                List<Promo> listPromo = response.body();
                Log.v("Nazam",listPromo.get(0).getEveryday().toString());
                setPageAdapter(listPromo.size(),listPromo);
            }

            @Override
            public void onFailure(Call<List<Promo>> call, Throwable t) {

            }
        });
    }

    private void setPageAdapter(Integer count, List<Promo> promoList){
        mCustomPagerAdapter = new CustomPageAdapter(getApplicationContext(), promoList);
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
        List<Promo> mPromoList;

        public CustomPageAdapter(Context context, List<Promo> promoList){
            mPromoList = promoList;
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
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
            ImageView imageView = (ImageView) itemView.findViewById(R.id.imageSlideView);

            if(mPromoList.get(position).getEveryday()==0){
                try {
                    if(sdf.parse(mPromoList.get(position).getStartDate()).compareTo(now())>= 0  && sdf.parse(mPromoList.get(position).getEndDate()).compareTo(now())<= 0){
                       Picasso.with(itemView.getContext()).load(url + mPromoList.get(position).getImageUrl().substring(2)).into(imageView);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }else {
                Picasso.with(itemView.getContext()).load(url + mPromoList.get(position).getImageUrl().substring(2)).into(imageView);
            }

            container.addView(itemView);
            return itemView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((LinearLayout)object);
        }
    }

    private Date now(){
        String dateNow = getIntent().getStringExtra("DATE_NOW");
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy   HH:mm:ss");
        try {
            return sdf.parse(dateNow);
        } catch (ParseException e) {
            e.printStackTrace();
            return new Date();
        }
    }
}

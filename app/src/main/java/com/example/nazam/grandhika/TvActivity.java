package com.example.nazam.grandhika;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Parcelable;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import api.Function;
import model.SettingApplication;
import model.TvChannel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TvActivity extends AppCompatActivity {
    private String url;
    private List<TvChannel> listChannel;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_tv);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);

        recyclerView = (RecyclerView)findViewById(R.id.channelGal);
        recyclerView.setHasFixedSize(true);
        url = "http://"+((SettingApplication)this.getApplication()).getServerIp()+":"+((SettingApplication)this.getApplication()).getServerPort()+"/iptvportal";
        api.Adapter.service().tvChannel(Function.getMacAddress(),0).enqueue(new Callback<List<TvChannel>>() {
            @Override
            public void onResponse(Call<List<TvChannel>> call, Response<List<TvChannel>> response) {
                if(response.isSuccessful()){
                    listChannel = response.body();
                    RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),5);
                    recyclerView.setLayoutManager(layoutManager);
                    MyAdapter adapter = new MyAdapter(getApplicationContext(), listChannel);
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<TvChannel>> call, Throwable t) {

            }
        });
    }

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        private List<TvChannel> galleryList;
        private Context context;

        public MyAdapter(Context context, List<TvChannel> galleryList) {
            this.galleryList = galleryList;
            this.context = context;
        }

        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cell_layout_tv, viewGroup, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(MyAdapter.ViewHolder viewHolder, int i) {
            final int index = i;
            viewHolder.numTitle.setText(galleryList.get(i).getNumber().toString());
//            viewHolder.title.setText(galleryList.get(i).getName());
            viewHolder.img.setScaleType(ImageView.ScaleType.CENTER_CROP);
            if(!galleryList.get(i).getImagePath().equalsIgnoreCase(""))
                Picasso.with(context).load(url+galleryList.get(i).getImagePath().substring(2)).resize(180, 100).into(viewHolder.img);
            else
                Picasso.with(context).load(Uri.parse("android.resource://com.example.nazam.grandhika/" + R.drawable.no_image)).resize(180, 100).into(viewHolder.img);
            viewHolder.btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent liveTvIntent = new Intent(TvActivity.this, LiveTvActivity.class);
                    String[] udpList = new String[galleryList.size()];
                    int[] channelNumber = new int[galleryList.size()];
                    String[] channelName = new String[galleryList.size()];
                    for(int i = 0; i<galleryList.size();i++){
                        udpList[i] = galleryList.get(i).getUrl();
                        channelNumber[i] = galleryList.get(i).getNumber();
                        channelName[i] = galleryList.get(i).getName();
                    }
                    liveTvIntent.putExtra("LINK_UDP",udpList);
                    liveTvIntent.putExtra("CHANNEL_NUM_LIST",channelNumber);
                    liveTvIntent.putExtra("CHANNEL_NAME_LIST",channelName);
                    liveTvIntent.putExtra("SELECTED_UDP",index);
                    liveTvIntent.putExtra("LINK_UDP_SIZE",udpList.length);
                    startActivity(liveTvIntent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return galleryList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder{
//            private TextView title;
            private ImageView img;
            private TextView numTitle;
            private Button btn;
            public ViewHolder(View view) {
                super(view);
                numTitle = (TextView)view.findViewById(R.id.numTitle);
                btn = (Button)view.findViewById(R.id.btnTvCh);
                img = (ImageView) view.findViewById(R.id.img);
            }
        }
    }
}

package com.example.nazam.grandhika;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import model.TvChannel;

public class ListAppActivity extends AppCompatActivity {

    private static final String TAG = "Fariz";
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_app);

        recyclerView = (RecyclerView)findViewById(R.id.appList);
        recyclerView.setHasFixedSize(true);
        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> pkgAppsList = getBaseContext().getPackageManager()
                .queryIntentActivities( mainIntent, 0);

        final PackageManager pm = getPackageManager();

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),5);
        recyclerView.setLayoutManager(layoutManager);
        MyAdapter adapter = new MyAdapter(getApplicationContext(), pkgAppsList);
        recyclerView.setAdapter(adapter);

    }

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        private List<ResolveInfo> galleryList;
        private List<ApplicationInfo> appList = new ArrayList<ApplicationInfo>();
        private Context context;

        public MyAdapter(Context context, List<ResolveInfo> galleryList) {
            this.galleryList = galleryList;
            this.context = context;
        }

        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cell_layout_app, viewGroup, false);
            return new MyAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(MyAdapter.ViewHolder viewHolder, int i) {
            final int index = i;
            viewHolder.numTitle.setText(galleryList.get(i).loadLabel(getPackageManager()));
            viewHolder.img.setImageDrawable(galleryList.get(i).loadIcon(getPackageManager()));

            ResolveInfo info = galleryList.get(index);

            ApplicationInfo application = new ApplicationInfo();
            application.setActivity(new ComponentName(info.activityInfo.applicationInfo.packageName,
                    info.activityInfo.name), Intent.FLAG_ACTIVITY_NEW_TASK);
            if(i<appList.size())
                appList.set(i,application);
            else
                appList.add(application);

//            application.title = info.loadLabel(getPackageManager());
//            viewHolder.numTitle.setText(galleryList.get(i).getNumber().toString());
//            viewHolder.title.setText(galleryList.get(i).getName());
//            viewHolder.img.setScaleType(ImageView.ScaleType.CENTER_CROP);
//            if(!galleryList.get(i).getImagePath().equalsIgnoreCase(""))
//                Picasso.with(context).load(galleryList.get(i).getIconResource()).into(viewHolder.img);
//            else
//                Picasso.with(context).load(Uri.parse("android.resource://com.example.nazam.grandhika/" + R.drawable.no_image)).resize(200, 120).into(viewHolder.img);
            viewHolder.btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    List<ApplicationInfo> packages = getPackageManager().getInstalledApplications(PackageManager.GET_META_DATA);
//                    Intent mIntent = getBaseContext().getPackageManager().getLaunchIntentForPackage(
//                            packages.get(index).packageName);
                    if (appList != null) {
                        try {
                            startActivity(appList.get(index).intent);
                        } catch (ActivityNotFoundException err) {
                            Toast t = Toast.makeText(getApplicationContext(),
                                    "Apps Not Found", Toast.LENGTH_SHORT);
                            t.show();
                        }
                    }
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
                numTitle = (TextView)view.findViewById(R.id.titleApp);
                btn = (Button)view.findViewById(R.id.btnAppList);
                img = (ImageView) view.findViewById(R.id.imgApp);
            }
        }
    }

    class ApplicationInfo {
        public ComponentName componentName;
        public boolean filtered;
        public Drawable icon;
        public Bitmap iconBitmap;
        public Intent intent;
        public CharSequence title;
        public Bitmap titleBitmap;

        public final void setActivity(ComponentName className, int launchFlags) {
            this.intent = new Intent("android.intent.action.MAIN");
            this.intent.addCategory("android.intent.category.LAUNCHER");
            this.intent.setComponent(className);
            this.intent.setFlags(launchFlags);
            this.componentName = className;
        }
    }
}

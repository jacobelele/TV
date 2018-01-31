package com.example.nazam.grandhika;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import model.FoodType;
import model.SettingApplication;
import model.TvChannel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuDiningFragment extends Fragment implements DiningItemFragment.OnFragmentInteractionListener{
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final Integer percentage = 50;

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private RecyclerView recyclerView;
    private String url;
    private List<FoodType> foodTypeList;

    public MenuDiningFragment() {
        // Required empty public constructor
    }

    public static MenuDiningFragment newInstance(String param1, String param2) {
        MenuDiningFragment fragment = new MenuDiningFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
        View v = inflater.inflate(R.layout.fragment_menu_dining, container, false);
        /*View.OnClickListener btnclick = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch(view.getId()){
                    case R.id.btnAppetizer:
                        DiningItemFragment categoryFragment = DiningItemFragment.newInstance(DiningItemFragment.APPETIZER,"APPETIZER");
                        categoryFragment.setArguments(getActivity().getIntent().getExtras());
                        final FragmentTransaction ft = getFragmentManager().beginTransaction();
                        ft.replace(R.id.dining_menu_fragment, categoryFragment, "categoryFragmentTag");
                        ft.addToBackStack(null);
                        ft.commit();
                        break;
                    case R.id.btnMainCourse:
                        DiningItemFragment categoryFragment2 = DiningItemFragment.newInstance(DiningItemFragment.MAIN_COURSE,"MAIN COURSE");
                        categoryFragment2.setArguments(getActivity().getIntent().getExtras());
                        final FragmentTransaction ft2 = getFragmentManager().beginTransaction();
                        ft2.replace(R.id.dining_menu_fragment, categoryFragment2, "categoryFragmentTag");
                        ft2.addToBackStack(null);
                        ft2.commit();
                        break;
                    case R.id.btnBeverage:
                        DiningItemFragment categoryFragment3 = DiningItemFragment.newInstance(DiningItemFragment.BEVERAGE,"BEVERAGE");
                        categoryFragment3.setArguments(getActivity().getIntent().getExtras());
                        final FragmentTransaction ft3 = getFragmentManager().beginTransaction();
                        ft3.replace(R.id.dining_menu_fragment, categoryFragment3, "categoryFragmentTag");
                        ft3.addToBackStack(null);
                        ft3.commit();
                        break;
                    case R.id.btnDessert:
                        DiningItemFragment categoryFragment4 = DiningItemFragment.newInstance(DiningItemFragment.DESSERT,"DESSERT");
                        categoryFragment4.setArguments(getActivity().getIntent().getExtras());
                        final FragmentTransaction ft4 = getFragmentManager().beginTransaction();
                        ft4.replace(R.id.dining_menu_fragment, categoryFragment4, "categoryFragmentTag");
                        ft4.addToBackStack(null);
                        ft4.commit();
                        break;
                    default:
                        break;
                }

            }
        };

        ImageButton imAppetizer = v.findViewById(R.id.btnAppetizer);
        Bitmap scaled = Bitmap.createScaledBitmap(((BitmapDrawable)getResources().getDrawable(R.drawable.appetizer)).
                getBitmap(),
                ((BitmapDrawable)getResources().getDrawable(R.drawable.appetizer)).getBitmap().getWidth()*percentage/100,
                ((BitmapDrawable)getResources().getDrawable(R.drawable.appetizer)).getBitmap().getHeight()*percentage/100,false);
        imAppetizer.setImageBitmap(scaled);
        imAppetizer.setOnClickListener(btnclick);
        ImageButton ibMainCourse = v.findViewById(R.id.btnMainCourse);
        scaled = Bitmap.createScaledBitmap(((BitmapDrawable)getResources().getDrawable(R.drawable.maincourse)).
                        getBitmap(),
                ((BitmapDrawable)getResources().getDrawable(R.drawable.maincourse)).getBitmap().getWidth()*percentage/100,
                ((BitmapDrawable)getResources().getDrawable(R.drawable.maincourse)).getBitmap().getHeight()*percentage/100,false);
        ibMainCourse.setImageBitmap(scaled);
        ibMainCourse.setOnClickListener(btnclick);
        ImageButton ibBeverage = v.findViewById(R.id.btnBeverage);
        scaled = Bitmap.createScaledBitmap(((BitmapDrawable)getResources().getDrawable(R.drawable.beverage)).
                        getBitmap(),
                ((BitmapDrawable)getResources().getDrawable(R.drawable.beverage)).getBitmap().getWidth()*percentage/100,
                ((BitmapDrawable)getResources().getDrawable(R.drawable.beverage)).getBitmap().getHeight()*percentage/100,false);
        ibBeverage.setImageBitmap(scaled);
        ibBeverage.setOnClickListener(btnclick);
        ImageButton ibDessert = v.findViewById(R.id.btnDessert);
        scaled = Bitmap.createScaledBitmap(((BitmapDrawable)getResources().getDrawable(R.drawable.dessert)).
                        getBitmap(),
                ((BitmapDrawable)getResources().getDrawable(R.drawable.dessert)).getBitmap().getWidth()*percentage/100,
                ((BitmapDrawable)getResources().getDrawable(R.drawable.dessert)).getBitmap().getHeight()*percentage/100,false);
        ibDessert.setImageBitmap(scaled);
        ibDessert.setOnClickListener(btnclick);*/
        recyclerView = (RecyclerView)v.findViewById(R.id.menuDining);
        recyclerView.setHasFixedSize(true);
        url = "http://"+((SettingApplication)getActivity().getApplication()).getServerIp()+":"+((SettingApplication)getActivity().getApplication()).getServerPort()+"/iptvportal";
        Log.v("fariz url",url);
        api.Adapter.service().listFoodType().enqueue(new Callback<List<FoodType>>() {
            @Override
            public void onResponse(Call<List<FoodType>> call, Response<List<FoodType>> response) {
                if(response.isSuccessful()){
                    foodTypeList = response.body();
                    Log.v("fariz food type", foodTypeList.get(0).getImagePath());
                    RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(),4);
                    recyclerView.setLayoutManager(layoutManager);
                    MyAdapter adapter = new MyAdapter(getContext(), foodTypeList);
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<FoodType>> call, Throwable t) {

            }
        });

        return v;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    public class MyAdapter extends RecyclerView.Adapter<MenuDiningFragment.MyAdapter.ViewHolder> {
        private List<FoodType> galleryList;
        private Context context;

        public MyAdapter(Context context, List<FoodType> galleryList) {
            this.galleryList = galleryList;
            this.context = context;
        }

        @Override
        public MenuDiningFragment.MyAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cell_layout, viewGroup, false);
            return new MenuDiningFragment.MyAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(MenuDiningFragment.MyAdapter.ViewHolder viewHolder, int i) {
            final int index = i;
            viewHolder.title.setText(galleryList.get(i).getName());

            if(!galleryList.get(i).getImagePath().equalsIgnoreCase("")) {
//                viewHolder.img.setScaleType(ImageView.ScaleType.CENTER_CROP);
//                viewHolder.img.setText(galleryList.get(i).getName());
                Picasso.with(context).load(url + galleryList.get(i).getImagePath().substring(2)).resize(200, 160).into(viewHolder.img);
                viewHolder.img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DiningItemFragment categoryFragment = DiningItemFragment.newInstance(galleryList.get(index).getId(),galleryList.get(index).getName());
                        categoryFragment.setArguments(getActivity().getIntent().getExtras());
                        final FragmentTransaction ft = getFragmentManager().beginTransaction();
                        ft.replace(R.id.dining_menu_fragment, categoryFragment, "categoryFragmentTag");
                        ft.addToBackStack(null);
                        ft.commit();
                    }
                });
            }else{
//                viewHolder.img.setScaleType(ImageView.ScaleType.CENTER_CROP);
//                viewHolder.img.setText(galleryList.get(i).getName());
                Picasso.with(context).load(Uri.parse("android.resource://com.example.nazam.grandhika/" + R.drawable.no_image)).resize(200, 160).into(viewHolder.img);
                viewHolder.img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DiningItemFragment categoryFragment = DiningItemFragment.newInstance(galleryList.get(index).getId(),galleryList.get(index).getName());
                        categoryFragment.setArguments(getActivity().getIntent().getExtras());
                        final FragmentTransaction ft = getFragmentManager().beginTransaction();
                        ft.replace(R.id.dining_menu_fragment, categoryFragment, "categoryFragmentTag");
                        ft.addToBackStack(null);
                        ft.commit();
                    }
                });
            }
        }

        @Override
        public int getItemCount() {
            return galleryList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder{
            private TextView title;
            private ImageButton img;
            public ViewHolder(View view) {
                super(view);

                title = (TextView)view.findViewById(R.id.title);
                img = (ImageButton) view.findViewById(R.id.img);
            }
        }
    }
}

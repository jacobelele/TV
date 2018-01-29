package com.example.nazam.grandhika;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import model.Food;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DiningItemFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DiningItemFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DiningItemFragment extends ListFragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static final Integer APPETIZER = 1;
    public static final Integer MAIN_COURSE = 2;
    public static final Integer DESSERT = 3;
    public static final Integer BEVERAGE = 4;
    private String url = "http://195.110.58.237:8080/iptvportal";

    private static Integer mParam1;
    private static String mParam2;

    private OnFragmentInteractionListener mListener;

    private static List<Food> listFood;
    private TextView ketItem;
    private TextView namaItem;
    private TextView hargaItem;
    private ImageView imageItem;
    private SharedPreferences settings;

    public DiningItemFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DiningItemFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DiningItemFragment newInstance(Integer param1, String param2) {
        DiningItemFragment fragment = new DiningItemFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        mParam1 = param1;
        mParam2 = param2;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getInt(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_dining_item, container, false);
        TextView menuTitle =(TextView)v.findViewById(R.id.itemTestTitle);
        menuTitle.setText(mParam2);
        ListView listMenuItem = (ListView)v.findViewById(android.R.id.list);
        namaItem = (TextView)v.findViewById(R.id.namaItem);
        hargaItem = (TextView)v.findViewById(R.id.hargaItem);
        ketItem = (TextView)v.findViewById(R.id.ketItem);
        imageItem = (ImageView)v.findViewById(R.id.imageItemDetail);

        settings = getActivity().getSharedPreferences("UserInfo", 0);
        String serverIp = settings.getString("server_ip", "").toString();
        String serverPort = settings.getString("server_port", "").toString();
        url = "http://"+serverIp+":"+serverPort+"/";
        api.Adapter.setBaseUrl("http://"+serverIp+":"+serverPort+"/");
        api.Adapter.service().listFood(mParam1).enqueue(new Callback<List<Food>>() {
            @Override
            public void onResponse(Call<List<Food>> call, Response<List<Food>> response) {
                if(response.isSuccessful()){
                    listFood = response.body();
                    if(listFood != null){
                        StableArrayAdapter adapter = new StableArrayAdapter(getActivity(),
                                android.R.layout.simple_list_item_1, android.R.id.text1,
                                listFood);
                        setListAdapter(adapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Food>> call, Throwable t) {

            }
        });

        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
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

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private class StableArrayAdapter extends ArrayAdapter<Food> {
        private final Context context;
        HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

        public StableArrayAdapter(Context context,int layoutResourceId, int textViewResourceId,
                                  List<Food> objects) {

            super(context,layoutResourceId, textViewResourceId, objects);
            this.context = context;
            for (int i = 0; i < objects.size(); ++i) {
                mIdMap.put(objects.get(i).getName(), i);
            }
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            Food food = getItem(position);
            if(convertView==null)
                convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
            TextView textView = (TextView) convertView.findViewById(android.R.id.text1);
            textView.setText(food.getName());
            textView.setTextColor(getResources().getColor(android.R.color.white));
            NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("id","ID"));
            hargaItem.setText(nf.format((double)food.getPrice()));
            namaItem.setText(food.getName());
            ketItem.setText(food.getContent());
            Bitmap b = getBitmapFromURL(url+food.getImagePath().substring(2));
            imageItem.setImageBitmap(b);

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

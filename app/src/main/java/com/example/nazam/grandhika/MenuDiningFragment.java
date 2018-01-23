package com.example.nazam.grandhika;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MenuDiningFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MenuDiningFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MenuDiningFragment extends Fragment implements DiningItemFragment.OnFragmentInteractionListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final Integer percentage = 50;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public MenuDiningFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MenuDiningFragment.
     */
    // TODO: Rename and change types and number of parameters
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
        View v = inflater.inflate(R.layout.fragment_menu_dining, container, false);
        View.OnClickListener btnclick = new View.OnClickListener() {
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
        ibDessert.setOnClickListener(btnclick);
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

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}

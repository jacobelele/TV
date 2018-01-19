package com.example.nazam.grandhika;

import android.content.Context;
import android.content.res.ColorStateList;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SceneryFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SceneryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SceneryFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private Button btnMaimun;
    private EditText txtMaimun;

    public SceneryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SceneryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SceneryFragment newInstance(String param1, String param2) {
        SceneryFragment fragment = new SceneryFragment();
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
//        btnMaimun = getView().findViewById(R.id.btnMaimun);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_menu_scenery, container, false);
        txtMaimun = v.findViewById(R.id.txtMaimun);
        btnMaimun = v.findViewById(R.id.btnMaimun);
        btnMaimun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtMaimun.setText("GREAT MOSQUE OF MEDAN \n" +
                        "\nGreat Mosque of Medan or Masjid Raya Al-Mashun is a mosque " +
                        "\nbuilt in the year 1906 and completed in 1909. In the beginning" +
                        "\nof its establishment, the mosque was a part of the Maimun" +
                        "\npalace complex." +
                        "\n\tJl. Sisingamangaraja No. 178, Medan Kota, Medan.");
                txtMaimun.setVisibility(View.VISIBLE);
                btnMaimun.setVisibility(View.GONE);
                /*btnMaimun.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
                btnMaimun.setText("GREAT MOSQUE OF MEDAN \n" +
                        "\nGreat Mosque of Medan or Masjid Raya Al-Mashun is a mosque " +
                        "\nbuilt in the year 1906 and completed in 1909. In the beginning" +
                        "\nof its establishment, the mosque was a part of the Maimun" +
                        "\npalace complex." +
                        "\n\tJl. Sisingamangaraja No. 178, Medan Kota, Medan.");
                btnMaimun.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                btnMaimun.setCompoundDrawablesRelativeWithIntrinsicBounds(null,null,null,null);*/
            }
        });
//        v.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                //do your operation here
//                // this will be called whenever user click anywhere in Fragment
//            }
//        });
//        Button b = (Button) inflater.inflate(R.id.btnMaimun,container);
        return v;


//        return inflater.inflate(R.layout.fragment_menu_scenery, container, false);
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

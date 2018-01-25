package com.example.nazam.grandhika;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


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
    private TextView txtMaimun;
    private Button btnTjongafie;
    private TextView txtTjongafie;
    private Button btnMosque;
    private TextView txtMosque;
    private Button btnDanau;
    private TextView txtDanau;

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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_menu_scenery, container, false);
        txtMaimun = v.findViewById(R.id.txtMaimun);
        txtMaimun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtMaimun.getVisibility()==View.VISIBLE){
                    txtMaimun.setVisibility(View.INVISIBLE);
                    btnMaimun.setVisibility(View.VISIBLE);
                }
            }
        });
        btnMaimun = v.findViewById(R.id.btnMaimun);
//        btnMaimun.drawable
        btnMaimun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(btnMaimun.getVisibility()==View.VISIBLE) {
                    txtMaimun.setText("Maimun Palace \n" +
                            "\nis an istana (royal palace) of the Sultanate of Deli and a well-known landmark in Medan, the capital city of North Sumatra, Indonesia. " +
                            "Built by Sultan Ma'mun Al Rashid Perkasa Alamyah in years 1887–1891, " +
                            "the palace was designed by the Dutch architect Theodoor van Erp." +
                            "The Palace has become a popular tourist destination in the city. " +
                            "Combining elements of Malay cultural heritage, Islamic and Indian architecture, with Spanish and Italian furniture and fittings." );
                    txtMaimun.setVisibility(View.VISIBLE);
                    btnMaimun.setVisibility(View.INVISIBLE);
                }
            }
        });

        txtTjongafie = v.findViewById(R.id.txtTjongafie);
        txtTjongafie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtTjongafie.getVisibility()==View.VISIBLE){
                    txtTjongafie.setVisibility(View.INVISIBLE);
                    btnTjongafie.setVisibility(View.VISIBLE);
                }
            }
        });
        btnTjongafie = v.findViewById(R.id.btnTjongafie);
        btnTjongafie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(btnTjongafie.getVisibility()==View.VISIBLE) {
                    txtTjongafie.setText("Tjong A Fie Mansion \n" +
                            "\nTwo Story Mansion In Medan, Built By Tjong A Fie(1860-1921)" +
                            "a Merchant who came to own much of the land in Medan through plantations" +
                            "later becoming Majoor der Chineezen' (leader of the Chinese') in Medan and constructing the Medan-Belawan railway." +
                            "The mosque has an octagonal shape and has wings to the south, east, north and west.");
                    txtTjongafie.setVisibility(View.VISIBLE);
                    btnTjongafie.setVisibility(View.INVISIBLE);
                }
            }
        });

        txtMosque = v.findViewById(R.id.txtMosque);
        txtMosque.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtMosque.getVisibility()==View.VISIBLE){
                    txtMosque.setVisibility(View.INVISIBLE);
                    btnMosque.setVisibility(View.VISIBLE);
                }
            }
        });
        btnMosque = v.findViewById(R.id.btnMosque);
        btnMosque.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(btnMosque.getVisibility()==View.VISIBLE) {
                    txtMosque.setText("Great Mosque Of Medan \n" +
                            "\nThe mosque was built in the year 1906 and completed in 1909." +
                            "In beginning of its establishment, the mosque was a part of the Maimun palace complex." +
                            "Its architectural style combines Middle Eastern, Indian and Spanish elements.");
                    txtMosque.setVisibility(View.VISIBLE);
                    btnMosque.setVisibility(View.INVISIBLE);
                }
            }
        });

        txtDanau = v.findViewById(R.id.txtDanau);
        txtDanau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtDanau.getVisibility()==View.VISIBLE){
                    txtDanau.setVisibility(View.INVISIBLE);
                    btnDanau.setVisibility(View.VISIBLE);
                }
            }
        });
        btnDanau = v.findViewById(R.id.btnDanau);
        btnDanau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(btnDanau.getVisibility()==View.VISIBLE) {
                    txtDanau.setText("Lake Toba \n"+
                            "\nLake Toba is an immense volcanic lake covering an area of 1,707 km² (1,000 km² bigger than Singapore) with an island in its centre."+
                            "\nActually, Lake Toba is located in Prapat area in North Sumatra.");
                    txtDanau.setVisibility(View.VISIBLE);
                    btnDanau.setVisibility(View.INVISIBLE);
                }
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
}
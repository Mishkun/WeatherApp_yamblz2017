package com.mishkun.weatherapp.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mishkun.weatherapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    public static final String TAG = HomeFragment.class.getSimpleName();

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

}

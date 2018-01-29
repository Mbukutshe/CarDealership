package com.wiseman.cardealership.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.wiseman.cardealership.R;

/**
 * Created by Wiseman on 2018-01-27.
 */

public class UploadVehicle extends Fragment {
    View view;
    FrameLayout choose;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_upload_vehicle,container,false);
        choose = (FrameLayout)view.findViewById(R.id.choose_vehicle_layout);
        choose.getBackground().setAlpha(180);
        return view;
    }
}

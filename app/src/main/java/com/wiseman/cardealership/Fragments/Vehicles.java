package com.wiseman.cardealership.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.wiseman.cardealership.Adapters.VehiclesAdapter;
import com.wiseman.cardealership.Objects.VehicleObject;
import com.wiseman.cardealership.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wiseman on 2018-01-25.
 */

public class Vehicles extends Fragment implements View.OnClickListener{
    View view;
    LinearLayout search_layout,search,search_vehicle_layout,search_elements;
    RecyclerView mRecyclerView;
    LinearLayoutManager mLayoutManager;
    VehiclesAdapter adapter;
    List<VehicleObject> mDataset;
    String make,year,price;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_vehicles,container,false);
        mRecyclerView = (RecyclerView)view.findViewById(R.id.vehicles_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mDataset = getmDataset();
        mLayoutManager = new LinearLayoutManager( view.getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        adapter = new VehiclesAdapter(view.getContext(),mDataset,mRecyclerView,mLayoutManager);
        mRecyclerView.setAdapter(adapter);
        search_layout = (LinearLayout)view.findViewById(R.id.search_layout);
        search_elements = (LinearLayout)view.findViewById(R.id.search_elements);
        search_vehicle_layout = (LinearLayout)view.findViewById(R.id.search_vehicle_layout);
        search_layout.getBackground().setAlpha(180);
        search = (LinearLayout)view.findViewById(R.id.searchs_button);

        Spinner spinner = (Spinner)view.findViewById(R.id.make_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(view.getContext(),R.array.make,R.layout.dropdown_items);
        adapter.setDropDownViewResource(R.layout.dropdown_items);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
              make = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
               make = adapterView.getItemAtPosition(0).toString();
            }
        });

        Spinner spin = (Spinner)view.findViewById(R.id.years_spinner);
        ArrayAdapter<CharSequence> adapt = ArrayAdapter.createFromResource(view.getContext(),R.array.years,R.layout.dropdown_items);
        adapt.setDropDownViewResource(R.layout.dropdown_items);
        spin.setAdapter(adapt);
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
               year = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                year = adapterView.getItemAtPosition(0).toString();
            }
        });

        Spinner spinna = (Spinner)view.findViewById(R.id.price_spinner);
        ArrayAdapter<CharSequence> adapta = ArrayAdapter.createFromResource(view.getContext(),R.array.prices,R.layout.dropdown_items);
        adapta.setDropDownViewResource(R.layout.dropdown_items);
        spinna.setAdapter(adapta);
        spinna.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
               price = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                price = adapterView.getItemAtPosition(0).toString();
            }
        });

        search.setOnClickListener(this);

        return view;
    }
    private List<VehicleObject> getmDataset()
    {
        List<VehicleObject> objects = new ArrayList<>();
        objects.add(new VehicleObject(R.drawable.bmw,"R350 000","Alpine White, 2016, 7 500km, M Sport Package, Xenonx, 19\"wheels, Sunroof, PDC.","BMW 520i A"));
        objects.add(new VehicleObject(R.drawable.polo,"R250 000","Full house, leather, panoramic sunroof, 19\"wheels, Rev camera, Dynamic chassis control, 34 000km, 2016.","VW Polo 2.0 TSI"));
        objects.add(new VehicleObject(R.drawable.jaguar,"R390 000","A/c, p/s, e/w, c/l, r/cd, airbags, 45 000km, 2015.","Jaguar XE 25t RWD MSRP"));
        return  objects;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch(id)
        {
            case R.id.searchs_button:
                if(search_elements.getVisibility()==View.GONE)
                {
                    search_vehicle_layout.setBackgroundResource(R.drawable.searchback);
                    search_elements.setVisibility(View.VISIBLE);
                }
                else
                {
                    search_vehicle_layout.setBackgroundResource(R.drawable.searchbutton);
                    search_elements.setVisibility(View.GONE);
                }
            break;
        }
    }
}

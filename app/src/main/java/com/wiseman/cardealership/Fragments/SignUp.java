package com.wiseman.cardealership.Fragments;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ScrollView;
import android.widget.Spinner;

import com.wiseman.cardealership.R;

/**
 * Created by Wiseman on 2018-01-28.
 */

public class SignUp extends AppCompatActivity {
    ScrollView subscribe_layout;
    String price;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subscribe_fragment);
        subscribe_layout =(ScrollView) findViewById(R.id.subscribe_layout);
        subscribe_layout.getBackground().setAlpha(180);
        Spinner spin = (Spinner)findViewById(R.id.vehicle_price);
        ArrayAdapter<CharSequence> adapt = ArrayAdapter.createFromResource(getApplicationContext(),R.array.prices,R.layout.dropdown_items);
        adapt.setDropDownViewResource(R.layout.dropdown_items);
        spin.setAdapter(adapt);
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                price = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                price = adapterView.getItemAtPosition(0).toString();
            }
        });
    }
}

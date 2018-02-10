package com.wiseman.cardealership.Fragments;


import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.wiseman.cardealership.Globals.Globals;
import com.wiseman.cardealership.R;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Wiseman on 2018-01-27.
 */

public class UploadVehicle extends Fragment implements View.OnClickListener{
    View view;
    FrameLayout choose;
    EditText make,transmission,model,mileage,year,price,features,province;
    String car_make,car_trans,car_model,car_mileage,car_year,car_price,car_features,car_province;
    ImageView car;
    Bitmap vehicle;
    Uri uri;
    LinearLayout upload;
    ProgressDialog myProgressDialog;
    RequestQueue requestQueue;
    Bitmap bitmap;
    private static final int RESULT_LOAD_IMAGE=1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_upload_vehicle,container,false);
        choose = (FrameLayout)view.findViewById(R.id.choose_vehicle_layout);
        choose.getBackground().setAlpha(150);
        getActivity().setTitle("Upload Vehicle");

        myProgressDialog = new ProgressDialog(view.getContext());

        make = (EditText)view.findViewById(R.id.vehicle_make);
        transmission = (EditText)view.findViewById(R.id.vehicle_transmission);
        model = (EditText)view.findViewById(R.id.vehicle_model);
        mileage = (EditText)view.findViewById(R.id.vehicle_mileage);
        year = (EditText)view.findViewById(R.id.vehicle_year);
        price = (EditText)view.findViewById(R.id.vehicle_price);
        features = (EditText)view.findViewById(R.id.vehicle_features);
        province = (EditText)view.findViewById(R.id.vehicle_province);

        upload = (LinearLayout)view.findViewById(R.id.upload_button);
        car = (ImageView)view.findViewById(R.id.vehilce_photo);
        choose.setOnClickListener(this);
        upload.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId())
        {
            case R.id.choose_vehicle_layout:
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,RESULT_LOAD_IMAGE);
            break;
            case R.id.upload_button:
                car_make = make.getText().toString();car_trans = transmission.getText().toString();car_model = model.getText().toString();
                car_mileage = mileage.getText().toString();car_year = year.getText().toString();car_price = price.getText().toString();
                car_features = features.getText().toString();car_province=province.getText().toString();
                if(!(car_make.isEmpty()||car_province.isEmpty()||car_trans.isEmpty()||car_model.isEmpty()||car_mileage.isEmpty()||car_year.isEmpty()||car_price.isEmpty()||car_features.isEmpty()))
                {
                    upload(car_make,car_trans,car_model,car_mileage,car_year,car_price,car_features,car_province);
                }
                else
                {
                    Toast.makeText(view.getContext(),"All fields are required",Toast.LENGTH_LONG).show();
                }
            break;
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK) {
            Uri photo = data.getData();

            car.setDrawingCacheEnabled(true);
            car.buildDrawingCache();
            car.setImageURI(photo);
            bitmap = ((BitmapDrawable) car.getDrawable()).getBitmap();
        }
    }
    public void upload(final String car_make, final String car_trans, final String car_model, final String car_mileage, final String car_year, final String car_price, final String car_features,final String car_province)
    {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        byte[] imageBytes =  bytes.toByteArray();
        final String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);

        myProgressDialog.show();
        myProgressDialog.setContentView(R.layout.progress);
        ProgressBar progressBar = (ProgressBar) myProgressDialog.findViewById(R.id.progressBar);
        progressBar.getIndeterminateDrawable().setColorFilter(Color.parseColor("#FFFFFF"), PorterDuff.Mode.MULTIPLY);
        StringRequest request = new StringRequest(Request.Method.POST, Globals.INSERT_VEHICLE_URL,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if( Globals.GROUP_CREATION_SUCCESS.equalsIgnoreCase(response))
                        {
                            myProgressDialog.dismiss();
                            Toast.makeText(view.getContext(),"Vehicle has been uploaded!",Toast.LENGTH_LONG) .show();
                        }
                        else
                        {
                            myProgressDialog.dismiss();
                            Toast.makeText(view.getContext(),response,Toast.LENGTH_LONG).show();
                        }


                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> parameters = new HashMap<String, String>();
                parameters.put("province",car_province);
                parameters.put("make",car_make);
                parameters.put("transmission",car_trans);
                parameters.put("model",car_model);
                parameters.put("mileage",car_mileage);
                parameters.put("year",car_year);
                parameters.put("price",car_price);
                parameters.put("features",car_features);
                parameters.put("photo",encodedImage);
                return parameters;
            }
        };
        requestQueue = Volley.newRequestQueue(view.getContext());
        requestQueue.add(request);
    }
}

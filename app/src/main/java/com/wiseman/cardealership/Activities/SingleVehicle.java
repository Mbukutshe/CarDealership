package com.wiseman.cardealership.Activities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.wiseman.cardealership.R;

/**
 * Created by Wiseman on 2018-01-25.
 */

public class SingleVehicle extends AppCompatActivity {
    Bitmap vehicle;
    TextView prices,feature;
    ImageView vehicles;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_vehicle_fragment);
        Bundle extras = getIntent().getExtras();
        getSupportActionBar().setTitle(extras.getString("name"));

        prices = (TextView)findViewById(R.id.subject_message);
        feature = (TextView)findViewById(R.id.message_message);
        vehicles = (ImageView)findViewById(R.id.video_thumbnail);

        byte[] b = extras.getByteArray("vehicle");
        vehicle = BitmapFactory.decodeByteArray(b, 0, b.length);
        vehicles.setImageBitmap(vehicle);
        prices.setText(extras.getString("price"));
        feature.setText(extras.getString("features"));

    }
}

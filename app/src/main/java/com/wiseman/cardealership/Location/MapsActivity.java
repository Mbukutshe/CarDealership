package com.wiseman.cardealership.Location;

/**
 * Created by Wiseman on 2017-10-17.
 */

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.wiseman.cardealership.R;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends Fragment implements OnMapReadyCallback,LocationListener {

    private GoogleMap mMap;
    private double lat;
    private double log;
    private LocationManager mlocManager;
    MyLocationListener locationListener;
    MarkerOptions options;
    Marker marker;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_maps,container,false);
        mlocManager = (LocationManager)view.getContext().getSystemService(Context.LOCATION_SERVICE);
        //locationListener = new MyLocationListener(getApplicationContext());
        boolean enabled = mlocManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (!enabled) {
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(intent);
        }
        try
        {
            mlocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0,this);
        }
        catch(SecurityException e)
        {

        }
        SupportMapFragment mapFragment = (SupportMapFragment)getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng points = new LatLng(lat, log);
        options = new MarkerOptions().position(points).title("Your Location");
        marker = mMap.addMarker(options);
        mMap.animateCamera(CameraUpdateFactory.newLatLng(points));
      // mMap.animateCamera(CameraUpdateFactory.zoomTo(100));
        if(ActivityCompat.checkSelfPermission(view.getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(view.getContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
        }
        mMap.setMyLocationEnabled(true);
    }
    @Override
    public void onLocationChanged(Location loc)
    {
        lat=loc.getLatitude();
        log=loc.getLongitude();
        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(view.getContext(), Locale.getDefault());
        LatLng points = new LatLng(lat, log);
        marker.setPosition(points);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(points, 16.0f));
        if(ActivityCompat.checkSelfPermission(view.getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(view.getContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
        }

        mMap.setMyLocationEnabled(true);
        try {
            addresses = geocoder.getFromLocation(loc.getLatitude(), loc.getLongitude(), 1);
            //  String address = addresses.get(0).getAddressLine(0);
            //    String City = addresses.get(0).getLocality().toString();

        }catch (MalformedURLException e) {
            Toast.makeText(view.getContext(), e.getMessage().toString() , Toast.LENGTH_LONG).show();
        }

        catch (IOException e) {
            Toast.makeText(view.getContext(), e.getMessage().toString() , Toast.LENGTH_LONG).show();
        }
    }

    @Override

    public void onProviderDisabled(String provider)
    {
        Toast.makeText(view.getContext(),"Gps Disabled", Toast.LENGTH_SHORT ).show();
    }

    @Override

    public void onProviderEnabled(String provider)
    {
        Toast.makeText( view.getContext(),"Gps Enabled", Toast.LENGTH_SHORT).show();
    }

    @Override

    public void onStatusChanged(String provider, int status, Bundle extras)
    {

    }
}

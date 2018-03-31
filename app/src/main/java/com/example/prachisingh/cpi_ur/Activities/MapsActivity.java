package com.example.prachisingh.cpi_ur.Activities;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.example.prachisingh.cpi_ur.R;
import com.example.prachisingh.cpi_ur.models.Shop;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    ArrayList<Shop> mShopList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps2);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        mShopList = (ArrayList<Shop>) getIntent().getSerializableExtra(ScheduleActivity.SHOP_LIST_KEY);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        for (Shop shop : mShopList) {
            mMap.addMarker(new MarkerOptions().position(shop.getLatLng()).title(shop.name));
        }
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(mShopList.get(mShopList.size()-1).getLatLng(), 12.0f));
    }
}

package com.example.prachisingh.cpi_ur.Fragments;

import android.content.Intent;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.prachisingh.cpi_ur.R;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by prachisingh on 28/03/18.
 */

public class ProfileFragment extends Fragment {
    TextView userAdress;
   double userLatitude;
   double userLongitude;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.profile_fragment_layout,container,false);
        userAdress=(TextView) view.findViewById(R.id.profile_address);
        Intent i=getActivity().getIntent();
       userLatitude=i.getDoubleExtra("user_lat",-1);
       userLongitude=i.getDoubleExtra("user_lon",-1);
       getAddress(userLatitude,userLongitude);
        return view;
    }

    public String  getAddress(double latitude, double longitude)
    {
        Geocoder geocoder;
        List addresses;
        String adress;
        geocoder = new Geocoder(getActivity(), Locale.getDefault());

        try
        {
            // Here 1 represent max location result to returned, by documents it recommended 1 to 5
            addresses = geocoder.getFromLocation(latitude,longitude, 1);
            // Log.i("address",String.valueOf(addresses.get(0)));
            if(addresses.size()!=0){
                adress=addresses.get(0).toString().substring(24,addresses.get(0).toString().indexOf("India"));
                userAdress.setText(adress.toString());
            }
            else adress="ADDRESS : NOT FOUND";
            return adress;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

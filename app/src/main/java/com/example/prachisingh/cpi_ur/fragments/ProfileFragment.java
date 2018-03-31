package com.example.prachisingh.cpi_ur.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.prachisingh.cpi_ur.activities.LoginActivity;
import com.example.prachisingh.cpi_ur.R;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by prachisingh on 28/03/18.
 */

public class ProfileFragment extends Fragment {
    Button logout;
    TextView userAdress;
   float userLatitude;
   float userLongitude;
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.profile_fragment_layout,container,false);
        sp=getActivity().getSharedPreferences("CPI",MODE_PRIVATE);
        editor=sp.edit();
        userAdress=(TextView) view.findViewById(R.id.profile_address);
        logout=(Button)view.findViewById(R.id.logout_button);
        Intent i=getActivity().getIntent();
       userLatitude=i.getFloatExtra("user_lat",-1);
       userLongitude=i.getFloatExtra("user_lon",-1);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getActivity(), LoginActivity.class);
                editor.remove("access_token");
                editor.commit();
                startActivity(i);
                getActivity().finish();
            }
        });
       getAddress((double)userLatitude,(double)userLongitude);
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

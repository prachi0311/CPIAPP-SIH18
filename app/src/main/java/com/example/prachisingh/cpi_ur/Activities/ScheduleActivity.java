package com.example.prachisingh.cpi_ur.Activities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telecom.Call;
import android.util.Log;

import com.example.prachisingh.cpi_ur.Adapters.ShopListAdapter;
import com.example.prachisingh.cpi_ur.ApiResponses.ShopListOfDay;
import com.example.prachisingh.cpi_ur.ApiResponses.ShopScheduleData;
import com.example.prachisingh.cpi_ur.ApiResponses.ShopScheduleResponse;
import com.example.prachisingh.cpi_ur.Network.ApiClient;
import com.example.prachisingh.cpi_ur.Network.ApiInterface;
import com.example.prachisingh.cpi_ur.R;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Callback;
import retrofit2.Response;

public class ScheduleActivity extends AppCompatActivity {
    @BindView(R.id.shop_list_recyclerview)
    RecyclerView recyclerView;
    ArrayList<String> addresses;
    ShopListAdapter adapter;
    Intent i;
    String accessToken;
    String selectedDate;
    ArrayList<ShopListOfDay> shopList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        ButterKnife.bind(this);
     //   shopList=new ArrayList<>();
        addresses=new ArrayList<>();
        i=getIntent();
        Bundle args = i.getBundleExtra("BUNDLE");
        shopList = (ArrayList<ShopListOfDay>) args.getSerializable("shops_of_day");
        Log.i("shoplistsize",String.valueOf(shopList.size()));
        accessToken=i.getStringExtra("access_token");
        selectedDate=i.getStringExtra("selected_date");
        for (int i = 0; i < shopList.size(); i++) {
                        addresses.add(i, String.valueOf(getAddress(shopList.get(i).getLatitude(), shopList.get(i).getLongitude())));
                    }
        adapter=new ShopListAdapter(ScheduleActivity.this,shopList,addresses,selectedDate);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
      //  getShopList();

    }

//    private void getShopList() {
//        ApiInterface apiInterface= ApiClient.getAuthorizedApiInterface();
//        retrofit2.Call<ShopScheduleResponse> call=apiInterface.shopschedule("Mon, 26 Mar 2018","87d3c6d08a4d77d429f5ddb63e63a261");
//        //Log.i("token",accessToken);
//        call.enqueue(new Callback<ShopScheduleResponse>() {
//            @Override
//            public void onResponse(retrofit2.Call<ShopScheduleResponse> call, Response<ShopScheduleResponse> response) {
//                Log.i("code",String.valueOf(response.code()));
//                if(response.isSuccessful()) {
//                    shopList.addAll(response.body().getData());
//                    for (int i = 0; i < response.body().getData().size(); i++) {
//                        addresses.add(i, String.valueOf(getAddress(response.body().getData().get(i).getLatitude(), response.body().getData().get(i).getLongitude())));
//                    }
//                    adapter.notifyDataSetChanged();
//                    Log.i("shopdata", response.body().getMessage());
//                }
//
//            }
//
//            @Override
//            public void onFailure(retrofit2.Call<ShopScheduleResponse> call, Throwable t) {
//
//            }
//        });
//
//    }

    public String  getAddress(double latitude, double longitude)
    {
        Geocoder geocoder;
        List addresses;
        String adress;
        geocoder = new Geocoder(this, Locale.getDefault());

        try
        {
            // Here 1 represent max location result to returned, by documents it recommended 1 to 5
            addresses = geocoder.getFromLocation(latitude,longitude, 1);
           // Log.i("address",String.valueOf(addresses.get(0)));
            if(addresses.size()!=0){
            adress=addresses.get(0).toString().substring(24,addresses.get(0).toString().indexOf("India"));}
            else adress="ADDRESS : NOT FOUND";
            return adress;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

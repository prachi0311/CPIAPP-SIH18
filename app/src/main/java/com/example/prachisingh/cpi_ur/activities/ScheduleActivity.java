package com.example.prachisingh.cpi_ur.activities;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.prachisingh.cpi_ur.adapters.ShopListAdapter;
import com.example.prachisingh.cpi_ur.models.Shop;
import com.example.prachisingh.cpi_ur.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ScheduleActivity extends AppCompatActivity {
    @BindView(R.id.shop_list_recyclerview)
    RecyclerView recyclerView;
    ArrayList<String> addresses;
    ShopListAdapter adapter;
    Intent i;
    String accessToken;
    String selectedDate;
    ArrayList<Shop> shopList;

    public final static String SHOP_LIST_KEY = "shops";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        ActionBar abar = getSupportActionBar();
        abar.setDisplayHomeAsUpEnabled(true);
        abar.setTitle("SHOP LIST");
        ButterKnife.bind(this);
     //   shopList=new ArrayList<>();
        addresses=new ArrayList<>();
        i=getIntent();
        Bundle args = i.getBundleExtra("BUNDLE");
        shopList = (ArrayList<Shop>) args.getSerializable("shops_of_day");
        Log.i("shoplistsize",String.valueOf(shopList.size()));
        accessToken=i.getStringExtra("access_token");
        selectedDate=i.getStringExtra("selected_date");
        for (int i = 0; i < shopList.size(); i++) {
                        addresses.add(i, String.valueOf(getAddress(shopList.get(i).getLatitude(), shopList.get(i).getLongitude())));
                    }
        adapter=new ShopListAdapter(ScheduleActivity.this,shopList,addresses,selectedDate);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    public String  getAddress(double latitude, double longitude)
    {
        Geocoder geocoder;
        List<Address> addresses;
        String shopAddress;
        geocoder = new Geocoder(this, Locale.getDefault());

        try
        {
            addresses = geocoder.getFromLocation(latitude,longitude, 1);
            if(addresses.size()!=0){
                shopAddress=addresses.get(0).getAddressLine(0);}
            else shopAddress="---";
            return shopAddress;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }else if (item.getItemId() == R.id.map){
            Intent intent = new Intent(this, MapsActivity.class);
            intent.putExtra(SHOP_LIST_KEY, shopList);
            startActivity(intent);
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.map_menu, menu);
        return true;
    }
}

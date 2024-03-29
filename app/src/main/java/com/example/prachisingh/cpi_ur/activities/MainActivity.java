package com.example.prachisingh.cpi_ur.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.prachisingh.cpi_ur.responses.OfflineSyncResponse;
import com.example.prachisingh.cpi_ur.responses.SyncItem;
import com.example.prachisingh.cpi_ur.responses.SyncMarkets;
import com.example.prachisingh.cpi_ur.responses.SyncShops;
import com.example.prachisingh.cpi_ur.fragments.ProfileFragment;
import com.example.prachisingh.cpi_ur.fragments.WorkFragment;
import com.example.prachisingh.cpi_ur.network.ApiClient;
import com.example.prachisingh.cpi_ur.network.ApiInterface;
import com.example.prachisingh.cpi_ur.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    ArrayList<SyncItem> items;
    ArrayList<SyncMarkets> markets;
    ArrayList<SyncShops> shops;
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_profile:
                    mViewPager.setCurrentItem(0, true);
                    return true;
                case R.id.navigation_home:
                    mViewPager.setCurrentItem(1, true);
                    return true;

            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        items=new ArrayList<>();
        markets=new ArrayList<>();
        shops=new ArrayList<>();
        //  mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.view_pager_main_activity);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        //Log.i("fcm_key",FirebaseInstanceId.getInstance().getToken());
        mViewPager.setCurrentItem(1,true);
        navigation.setSelectedItemId(R.id.navigation_home);
        updateofflinedb();

    }

    private void updateofflinedb() {

        ApiInterface apiInterface= ApiClient.getAuthorizedApiInterface();
        Call<OfflineSyncResponse> call=apiInterface.getofflineData();
        call.enqueue(new Callback<OfflineSyncResponse>() {
            @Override
            public void onResponse(Call<OfflineSyncResponse> call, Response<OfflineSyncResponse> response) {
                if(response.isSuccessful()){
                    items.addAll(response.body().getData().getItems());
                    markets.addAll(response.body().getData().getMarkets());
                    shops.addAll(response.body().getData().getShops());


                }
            }

            @Override
            public void onFailure(Call<OfflineSyncResponse> call, Throwable t) {

            }
        });


    }

    class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            // return PlaceholderFragment.newInstance(position + 1);
            switch (position) {
                case 0:
                    return new ProfileFragment();
                case 1:
                    return new WorkFragment();



            }
            return null;
        }

        @Override
        public int getCount() {
            return 2;
        }


    }
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return true;
    }
}

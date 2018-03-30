package com.example.prachisingh.cpi_ur.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.Call;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.prachisingh.cpi_ur.ApiResponses.ShopListOfDay;
import com.example.prachisingh.cpi_ur.ApiResponses.userDatesResponse;
import com.example.prachisingh.cpi_ur.Network.ApiClient;
import com.example.prachisingh.cpi_ur.Network.ApiInterface;
import com.example.prachisingh.cpi_ur.R;
import com.google.gson.internal.bind.ReflectiveTypeAdapterFactory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Callback;
import retrofit2.Response;

public class UserDateList extends AppCompatActivity {
    @BindView(R.id.dateListView)
    ListView datesListView;
    ArrayAdapter<String> adapter;
    ArrayList<String> datelist;
    ArrayList<ShopListOfDay> shopsTOVisit;
    HashMap<String,ArrayList<ShopListOfDay>> map;
    String username;
    String accessToken;
    Date date;
    int month;
    int year;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_date_list);
        ButterKnife.bind(this);
        Intent i=getIntent();
        date=new Date();
        month=date.getMonth();
        year=date.getYear();
        username=i.getStringExtra("username");
        accessToken=i.getStringExtra("access_token");
        Log.i("access_token",accessToken);
        datelist=new ArrayList<>() ;
        map=new HashMap<>();
        adapter=new ArrayAdapter<String>(UserDateList.this,android.R.layout.simple_list_item_1,datelist);
        datesListView.setAdapter(adapter);
        datesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(UserDateList.this,ScheduleActivity.class);
                intent.putExtra("selected_date",datelist.get(i));
                Bundle args = new Bundle();
                args.putSerializable("shops_of_day",map.get(datelist.get(i)));
                intent.putExtra("BUNDLE",args);
                startActivity(intent);
            }
        });
        getDates(month,year);


    }

    private void getDates(int month,int year) {
        Log.i("month",String.valueOf(month));
        Log.i("year",String.valueOf(year));
        ApiInterface apiInterface= ApiClient.getAuthorizedApiInterface();
        retrofit2.Call<userDatesResponse> call=apiInterface.userDates(accessToken,month+2,year+1900);
        call.enqueue(new Callback<userDatesResponse>() {
            @Override
            public void onResponse(retrofit2.Call<userDatesResponse> call, Response<userDatesResponse> response) {
                Log.i("date",String.valueOf(response.code()));
                if(response.isSuccessful()){
                    map=response.body().getData();
                    datelist.addAll(map.keySet());
                   // shopsTOVisit.addAll(map.get(datelist.get(i)));
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(retrofit2.Call<userDatesResponse> call, Throwable t) {

            }
        });
    }
}

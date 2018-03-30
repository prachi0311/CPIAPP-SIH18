package com.example.prachisingh.cpi_ur.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;

import com.example.prachisingh.cpi_ur.Adapters.DateListAdapter;
import com.example.prachisingh.cpi_ur.models.Shop;
import com.example.prachisingh.cpi_ur.ApiResponses.userDatesResponse;
import com.example.prachisingh.cpi_ur.Network.ApiClient;
import com.example.prachisingh.cpi_ur.Network.ApiInterface;
import com.example.prachisingh.cpi_ur.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Callback;
import retrofit2.Response;

public class UserDateList extends AppCompatActivity {
    @BindView(R.id.dateRecyclerView)
    RecyclerView recyclerView;
    DateListAdapter adapter;
    ArrayList<String> datelist;
    ArrayList<Shop> shopsTOVisit;
    HashMap<String,ArrayList<Shop>> map;
    String username;
    String accessToken;
    Date date;
    int month;
    int year;
    ProgressDialog progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_date_list);
        ActionBar abar = getSupportActionBar();
        abar.setDisplayHomeAsUpEnabled(true);
        abar.setTitle("SCHEDULE");
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
        adapter=new DateListAdapter(UserDateList.this,datelist,shopsTOVisit,map);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    //    recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
//        datesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Intent intent=new Intent(UserDateList.this,ScheduleActivity.class);
//                intent.putExtra("selected_date",datelist.get(i));
//                Bundle args = new Bundle();
//                args.putSerializable("shops_of_day",map.get(datelist.get(i)));
//                intent.putExtra("BUNDLE",args);
//                startActivity(intent);
//            }
//        });
        getDates(month,year);


    }

    private void getDates(int month,int year) {
        progress=new ProgressDialog(this);
        progress.setMessage("Fetching Schedule Dates");
        progress.show();
        Log.i("month",String.valueOf(month));
        Log.i("year",String.valueOf(year));
        ApiInterface apiInterface= ApiClient.getAuthorizedApiInterface();
        retrofit2.Call<userDatesResponse> call=apiInterface.userDates(accessToken,month+2,year+1900);
        call.enqueue(new Callback<userDatesResponse>() {
            @Override
            public void onResponse(retrofit2.Call<userDatesResponse> call, Response<userDatesResponse> response) {
                Log.i("date",String.valueOf(response.code()));
                if(response.isSuccessful()){
                    map.putAll(response.body().getData());
                    datelist.addAll(map.keySet());
                 //   shopsTOVisit.addAll(map.get(datelist.get(i)));
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            progress.cancel();//to delay progressbar
                            adapter.notifyDataSetChanged();
                        }
                    },800);
                }
            }

            @Override
            public void onFailure(retrofit2.Call<userDatesResponse> call, Throwable t) {

            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return true;
    }
}

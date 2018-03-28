package com.example.prachisingh.cpi_ur.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.Call;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.prachisingh.cpi_ur.ApiResponses.userDatesResponse;
import com.example.prachisingh.cpi_ur.Network.ApiClient;
import com.example.prachisingh.cpi_ur.Network.ApiInterface;
import com.example.prachisingh.cpi_ur.R;
import com.google.gson.internal.bind.ReflectiveTypeAdapterFactory;

import java.util.ArrayList;
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
    String username;
    String accessToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_date_list);
        ButterKnife.bind(this);
        Intent i=getIntent();
        username=i.getStringExtra("username");
        accessToken=i.getStringExtra("access_token");
        datelist=new ArrayList<>() ;
        adapter=new ArrayAdapter<String>(UserDateList.this,android.R.layout.simple_list_item_1,datelist);
        datesListView.setAdapter(adapter);
        datesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(UserDateList.this,ScheduleActivity.class);
                intent.putExtra("selected_date",datelist.get(i));
                startActivity(intent);

            }
        });
        getDates();


    }

    private void getDates() {
        ApiInterface apiInterface= ApiClient.getAuthorizedApiInterface();
        retrofit2.Call<userDatesResponse> call=apiInterface.userDates(username,accessToken);
        call.enqueue(new Callback<userDatesResponse>() {
            @Override
            public void onResponse(retrofit2.Call<userDatesResponse> call, Response<userDatesResponse> response) {
                if(response.isSuccessful()){
                    datelist.addAll(response.body().getDate());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(retrofit2.Call<userDatesResponse> call, Throwable t) {

            }
        });
    }
}

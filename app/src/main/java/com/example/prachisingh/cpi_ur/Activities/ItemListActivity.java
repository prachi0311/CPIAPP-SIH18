package com.example.prachisingh.cpi_ur.Activities;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.example.prachisingh.cpi_ur.Adapters.ItemListAdapter;
import com.example.prachisingh.cpi_ur.ApiResponses.ScheduleResponse;
import com.example.prachisingh.cpi_ur.Network.ApiClient;
import com.example.prachisingh.cpi_ur.R;
import com.example.prachisingh.cpi_ur.Utils;
import com.example.prachisingh.cpi_ur.models.Item;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemListActivity extends AppCompatActivity {
    private ArrayList<Item> mItemList;
    private ListView mListView;
    private ItemListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        mItemList = new ArrayList<>();
        mListView = findViewById(R.id.recycler_view);
        mAdapter = new ItemListAdapter(this, mItemList);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                showDialogueForItem(i);
            }
        });
      //  getShopList();

    }

    private void showDialogueForItem(int position) {
        Item item = mItemList.get(position);
        new AlertDialog.Builder(this)
                .setTitle("")
                .setPositiveButton("DONE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                // set post request for data updation
                .setView(new EditText(this))
                .create()
                .show();
    }

//    private void getShopList() {
//        Calendar calendar = Calendar.getInstance();
//        String date = new SimpleDateFormat("E, dd MMM yyyy").format(calendar);
//        ApiClient.getAuthorizedApiInterface().getSchedule(Utils.getUserId(), date).enqueue(new Callback<ScheduleResponse>() {
//            @Override
//            public void onResponse(Call<ScheduleResponse> call, Response<ScheduleResponse> response) {
//                if (response.isSuccessful()){
//                    // populate list
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ScheduleResponse> call, Throwable t) {
//
//            }
//        });
//    }
}

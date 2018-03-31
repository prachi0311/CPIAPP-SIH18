package com.example.prachisingh.cpi_ur.activities;

import android.app.ProgressDialog;
import android.arch.persistence.room.RoomDatabase;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import com.example.prachisingh.cpi_ur.adapters.ItemListAdapter;
import com.example.prachisingh.cpi_ur.recievers.ConnectivityReceiver;
import com.example.prachisingh.cpi_ur.responses.ItemResponse;
import com.example.prachisingh.cpi_ur.network.ApiClient;
import com.example.prachisingh.cpi_ur.R;
import com.example.prachisingh.cpi_ur.models.Item;
import com.example.prachisingh.cpi_ur.responses.userDatesResponse;
import com.example.prachisingh.cpi_ur.room.CPIDatabase;
import com.example.prachisingh.cpi_ur.utils.IOHelper;
import com.example.prachisingh.cpi_ur.utils.MyApplication;
import com.example.prachisingh.cpi_ur.utils.Utils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemListActivity extends AppCompatActivity implements ItemListAdapter.OnItemClickListener {
    private final String TAG = getClass().getSimpleName();
    private ArrayList<Item> mItemList;
    private GridView mGridView;
    private ItemListAdapter mAdapter;
    private int mShopId;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        mShopId = getIntent().getExtras().getInt("shop_id");
        mItemList = new ArrayList<>();
        mGridView = findViewById(R.id.grid);
        mAdapter = new ItemListAdapter(this, mItemList, this);
        mGridView.setAdapter(mAdapter);
        getShopList();

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Fetching shop items...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        if (!Utils.isConnected())
            Snackbar.make(mGridView, "OFFLINE MODE", Snackbar.LENGTH_INDEFINITE).show();


        MyApplication.getInstance().setConnectivityListener(new ConnectivityReceiver.ConnectivityReceiverListener() {
            @Override
            public void onNetworkConnectionChanged(boolean isConnected) {
                String message;
                int color;
                int duration;
                if (isConnected) {
                    message = "ONLINE";
                    color = Color.WHITE;
                    duration = Snackbar.LENGTH_SHORT;
                } else {
                    message = "OFFLINE MODE";
                    color = Color.RED;
                    duration = Snackbar.LENGTH_INDEFINITE;
                }
                Snackbar snackbar = Snackbar
                        .make(mGridView, message, duration);
                View sbView = snackbar.getView();
                TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                textView.setTextColor(color);
                snackbar.show();
            }
        });

    }

    private void showDialogueForItem(final int position) {
        final Item item = mItemList.get(position);
        final int itemId = item.getItemId();
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_price_update, null);
        final View alertView = LayoutInflater.from(this).inflate(R.layout.alert_dialog, null);
        final EditText priceEditText = dialogView.findViewById(R.id.dialog_price_view);
        TextView prevMonthPriceView = dialogView.findViewById(R.id.prev_month_price_view);
        prevMonthPriceView.setText(item.getPrevMonthPrice() + "");
        if (item.getPrice() != 0)
            priceEditText.setText(item.getPrice().toString());
        new AlertDialog.Builder(this)
                .setTitle(item.getName())
                .setPositiveButton("UPDATE PRICE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        float price = Float.parseFloat(priceEditText.getText().toString());
                        if (((Math.abs(item.getPrevMonthPrice() - price) / item.getPrevMonthPrice()) > 0.2)) {
                            new AlertDialog.Builder(ItemListActivity.this).setTitle("ALERT")
                                    .setPositiveButton("Add Remark", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            // send remark to api
                                        }
                                    })
                                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {

                                        }
                                    }).setView(alertView).create().show();
                        }
                        updatePrice(position, price);
                    }
                })
                .setView(dialogView)
                .create()
                .show();
        // Add fragment later
//        ItemDialogFragment dialogFragment = new ItemDialogFragment();
//        dialogFragment.setArguments();
    }

    private void updatePrice(final int position, final float price) {
        if (!Utils.isConnected()){
            // Access local Database
            final Item item = mItemList.get(position);
            item.setPrice(price);
            // Update DB
            Gson gson = new Gson();
            Type type = new TypeToken<ItemResponse>() {}.getType();
            ItemResponse response = new ItemResponse(mItemList);
            String json = gson.toJson(response, type);
            IOHelper.writeToFile(this, "r" + item.getItemId(), json);
            // Insert in Queue
            new Thread(new Runnable() {
                @Override
                public void run() {
                    CPIDatabase.getInstance(ItemListActivity.this).itemDao().insertMessage(item);
                }
            });
            return;
        }
        ApiClient.getAuthorizedApiInterface().updatePrice("fc05a4758b5ad958f0a3bf55e470dbea"
                , mItemList.get(position).getItemId()
                , price
                , /*Calendar.getInstance().getDisplayName(
                        Calendar.MONTH
                        , LONG
                        , getResources().getConfiguration().locale)*/
                "April"
        ).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.i(TAG, "Update Price Response Code: " + response.code());
                if (response.isSuccessful()) {
                    mItemList.get(position).setPrice(price);
                    mAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.i(TAG, "UpdatePrice Retrofit Failure");
            }
        });
    }

    private void getShopList() {
        if (!Utils.isConnected()){
            // Access local Database
            Gson gson = new Gson();
            Type type = new TypeToken<ItemResponse>() {}.getType();
            String json = IOHelper.stringFromAsset(this, "r" + mShopId);
            ItemResponse response = gson.fromJson(json, type);
            mItemList.addAll(response.getData().getItems());
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    progressDialog.cancel();
                    mAdapter.notifyDataSetChanged();
                }
            },800);
            return;
        }
        ApiClient.getAuthorizedApiInterface().getItemsForShop("fc05a4758b5ad958f0a3bf55e470dbea", mShopId).enqueue(new Callback<ItemResponse>() {
            @Override
            public void onResponse(Call<ItemResponse> call, Response<ItemResponse> response) {
                Log.i(TAG, "ShopList Response Code: " + response.code());
                if (response.isSuccessful()) {
                    // populate list
                    mItemList.addAll(response.body().getData().getItems());
                    // to delay progress bar
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            progressDialog.cancel();
                            mAdapter.notifyDataSetChanged();
                        }
                    }, 800);
                }
            }

            @Override
            public void onFailure(Call<ItemResponse> call, Throwable t) {
                Log.i(TAG, "ShopList Response Failure");
            }
        });
    }

    @Override
    public void onItemClick(int position) {
        showDialogueForItem(position);
    }
}

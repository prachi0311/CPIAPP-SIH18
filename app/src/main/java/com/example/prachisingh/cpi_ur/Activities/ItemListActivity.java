package com.example.prachisingh.cpi_ur.Activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prachisingh.cpi_ur.Adapters.ItemListAdapter;
import com.example.prachisingh.cpi_ur.ApiResponses.ItemResponse;
import com.example.prachisingh.cpi_ur.Fragments.ItemDialogFragment;
import com.example.prachisingh.cpi_ur.Network.ApiClient;
import com.example.prachisingh.cpi_ur.R;
import com.example.prachisingh.cpi_ur.models.Item;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.util.Calendar.LONG;

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

    }

    private void showDialogueForItem(final int position) {
        Item item = mItemList.get(position);
        final int itemId = item.getItemId();
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_price_update, null);
        final EditText priceEditText = dialogView.findViewById(R.id.dialog_price_view);
        TextView prevMonthPriceView = dialogView.findViewById(R.id.prev_month_price_view);
        prevMonthPriceView.setText(item.getPrevMonthPrice()+"");
        if (item.getPrice() != 0)
                priceEditText.setText(item.getPrice().toString());
        new AlertDialog.Builder(this)
                .setTitle(item.getName())
                .setPositiveButton("UPDATE PRICE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        float price = Float.parseFloat(priceEditText.getText().toString());
                        if (price != 0){
                            // update item price
                            updatePrice(position, price);

                        }
                    }
                })
                .setView(dialogView)
                .create()
                .show();
        // Add fragment later
//        ItemDialogFragment dialogFragment = new ItemDialogFragment();
//        dialogFragment.setArguments();
    }

    private void updatePrice(final int position,final float price) {
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
                if (response.isSuccessful()){
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

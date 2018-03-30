package com.example.prachisingh.cpi_ur.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.prachisingh.cpi_ur.Activities.ItemListActivity;
import com.example.prachisingh.cpi_ur.ApiResponses.ShopListOfDay;
import com.example.prachisingh.cpi_ur.ApiResponses.ShopScheduleData;
import com.example.prachisingh.cpi_ur.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by prachisingh on 27/03/18.
 */

public class ShopListAdapter extends RecyclerView.Adapter<ShopListAdapter.ShopListViewHolder> {
    Context mcontext;
    ArrayList<ShopListOfDay> mshopList;
    ArrayList<String> mshopAddresses;
    String mSelectedDate;

    public ShopListAdapter(Context mcontext, ArrayList<ShopListOfDay> mshopList,ArrayList<String> mshopAddresses,String selectedDate) {
        this.mcontext = mcontext;
        this.mshopList = mshopList;
        this.mshopAddresses= mshopAddresses;
        this.mSelectedDate=selectedDate;
    }

    @Override
    public ShopListAdapter.ShopListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mcontext).inflate(R.layout.schedule_shop_item,parent,false);
        return new ShopListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ShopListAdapter.ShopListViewHolder holder, final int position) {
        holder.shopName.setText(mshopList.get(position).getName());
        holder.shopAddress.setText(mshopAddresses.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(mcontext,ItemListActivity.class);
                i.putExtra("shop_id",mshopList.get(position).getId());
                i.putExtra("selected_date",mSelectedDate);
                mcontext.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mshopList.size();
    }

    public class ShopListViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.shop_name)
        TextView shopName;
        @BindView(R.id.shop_adress)
        TextView shopAddress;
        public ShopListViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}

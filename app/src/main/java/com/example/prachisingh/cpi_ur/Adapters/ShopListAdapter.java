package com.example.prachisingh.cpi_ur.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
    ArrayList<ShopScheduleData> mshopList;
    ArrayList<String> mshopAddresses;

    public ShopListAdapter(Context mcontext, ArrayList<ShopScheduleData> mshopList,ArrayList<String> mshopAddresses) {
        this.mcontext = mcontext;
        this.mshopList = mshopList;
        this.mshopAddresses= mshopAddresses;
    }

    @Override
    public ShopListAdapter.ShopListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mcontext).inflate(R.layout.schedule_shop_item,parent,false);
        return new ShopListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ShopListAdapter.ShopListViewHolder holder, int position) {
        holder.shopName.setText(mshopList.get(position).getName());
        holder.shopAddress.setText(mshopAddresses.get(position));

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

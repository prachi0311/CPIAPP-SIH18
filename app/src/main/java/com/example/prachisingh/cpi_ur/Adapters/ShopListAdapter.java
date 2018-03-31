package com.example.prachisingh.cpi_ur.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.prachisingh.cpi_ur.Activities.ItemListActivity;
import com.example.prachisingh.cpi_ur.Network.ApiClient;
import com.example.prachisingh.cpi_ur.Network.ApiInterface;
import com.example.prachisingh.cpi_ur.models.Shop;
import com.example.prachisingh.cpi_ur.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by prachisingh on 27/03/18.
 */

public class ShopListAdapter extends RecyclerView.Adapter<ShopListAdapter.ShopListViewHolder> {
    Context mcontext;
    ArrayList<Shop> mshopList;
    ArrayList<String> mshopAddresses;
    String mSelectedDate;
    Intent i;

    public ShopListAdapter(Context mcontext, ArrayList<Shop> mshopList, ArrayList<String> mshopAddresses, String selectedDate) {
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
                i=new Intent(mcontext,ItemListActivity.class);
                i.putExtra("shop_id",mshopList.get(position).getId());
                i.putExtra("selected_date",mSelectedDate);
                verifyLatLon(mshopList.get(position).getLatitude(),mshopList.get(position).getLongitude());
                mcontext.startActivity(i);
            }

            private void verifyLatLon(double latitude, double longitude) {
                ApiInterface apiInterface=ApiClient.getAuthorizedApiInterface();

            }
        });
        holder.callIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:0123456789"));
                mcontext.startActivity(intent);
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
        @BindView(R.id.call_icon)
        ImageView callIcon;
        public ShopListViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

        }
    }
}

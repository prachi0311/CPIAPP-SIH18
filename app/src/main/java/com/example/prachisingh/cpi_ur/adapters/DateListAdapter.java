package com.example.prachisingh.cpi_ur.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.prachisingh.cpi_ur.activities.ScheduleActivity;
import com.example.prachisingh.cpi_ur.models.Shop;
import com.example.prachisingh.cpi_ur.R;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by prachisingh on 30/03/18.
 */

public class DateListAdapter extends RecyclerView.Adapter<DateListAdapter.DateListViewHolder> {
    Context mcontext;
    ArrayList<String> mdatelist;
    ArrayList<Shop> mshopsTOVisit;
    HashMap<String,ArrayList<Shop>> map;
    public DateListAdapter(Context mcontext, ArrayList<String> mdatelist, ArrayList<Shop> mshopsTOVisit, HashMap<String,ArrayList<Shop>> vmap)
    {
        this.mcontext = mcontext;
        this.mdatelist = mdatelist;
        this.mshopsTOVisit = mshopsTOVisit;
        this.map=vmap;

    }

    @Override
    public DateListAdapter.DateListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mcontext).inflate(R.layout.shop_list_item,parent,false);
        return new DateListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DateListAdapter.DateListViewHolder holder, final int position) {
        holder.Date.setText(mdatelist.get(position));
        switch (position){
            case 0 : holder.status.setImageResource(R.drawable.greenticksmall);
                break;
            case 1: holder.status.setImageResource(R.drawable.current_arrow);
                break;
            case 2 : holder.status.setImageResource(R.drawable.schedule_button);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mcontext,ScheduleActivity.class);
                intent.putExtra("selected_date",mdatelist.get(position));
                Bundle args = new Bundle();
                args.putSerializable("shops_of_day",map.get(mdatelist.get(position)));
                intent.putExtra("BUNDLE",args);
                mcontext.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return mdatelist.size();
    }

    public class DateListViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.schedule_date)
        TextView Date;
        @BindView(R.id.schedule_date_status)
        ImageView status;
        public DateListViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}

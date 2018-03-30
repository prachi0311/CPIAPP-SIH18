package com.example.prachisingh.cpi_ur.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.prachisingh.cpi_ur.R;
import com.example.prachisingh.cpi_ur.models.Item;

import java.util.ArrayList;

/**
 * Created by YourFather on 27-03-2018.
 */

public class ItemListAdapter extends BaseAdapter {
    ArrayList<Item> mItemList;
    Context mContext;
    OnItemClickListener mListener;

    public ItemListAdapter(Context context, ArrayList<Item> objects, OnItemClickListener listener) {
        mContext = context;
        mItemList = objects;
        mListener = listener;
    }

    @Override
    public int getCount() {
        return mItemList.size();
    }

    @Override
    public Object getItem(int i) {
        return mItemList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.item_shop_item, parent, false);

        Item currentItem = mItemList.get(position);

        TextView mItemNameView = listItem.findViewById(R.id.item_name_view);
        mItemNameView.setText(currentItem.getName());
        TextView mPriceView = listItem.findViewById(R.id.item_price_view);
        mPriceView.setText(currentItem.getPrice().toString());
        listItem.setOnClickListener(new ClickListener(position));
        return listItem;
    }

    private class ClickListener implements View.OnClickListener {
        int mPosition;

        public ClickListener(int position) {
            mPosition = position;
        }

        @Override
        public void onClick(View view) {
            mListener.onItemClick(mPosition);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}

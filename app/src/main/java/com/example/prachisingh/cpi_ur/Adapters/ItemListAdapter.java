package com.example.prachisingh.cpi_ur.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.example.prachisingh.cpi_ur.R;
import com.example.prachisingh.cpi_ur.models.Item;

import java.util.ArrayList;

/**
 * Created by YourFather on 27-03-2018.
 */

public class ItemListAdapter extends ArrayAdapter<Item>{
    ArrayList<Item> mItemList;
    Context mContext;

    public ItemListAdapter(@NonNull Context context, @NonNull ArrayList<Item> objects) {
        super(context, R.layout.item_shop_item, objects);
        mContext = context;
        mItemList = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.item_shop_item,parent,false);

        Item currentItem = mItemList.get(position);

        TextView mItemNameView = listItem.findViewById(R.id.item_name_view);
        mItemNameView.setText("");
        EditText mPriceView = listItem.findViewById(R.id.item_price_edit_text);
        mPriceView.setText("");
        return listItem;
    }
}

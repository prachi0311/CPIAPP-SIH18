package com.example.prachisingh.cpi_ur.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.prachisingh.cpi_ur.activities.UserDateList;
import com.example.prachisingh.cpi_ur.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by prachisingh on 28/03/18.
 */

public class WorkFragment extends ProfileFragment {
    TextView marketSurevey;
    @BindView(R.id.select_price_collection)
    TextView priceCollection;
    int userId;
    String accessToken;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_selection, container, false);
        ButterKnife.bind(this,view);
        userId=getActivity().getIntent().getIntExtra("user_id",-1);
        accessToken=getActivity().getIntent().getStringExtra("access_token");
        priceCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), UserDateList.class);
                i.putExtra("user_id", userId);
                i.putExtra("access_token", accessToken);
                startActivity(i);
            }
        });
        return view;
    }
}



package com.example.prachisingh.cpi_ur.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.prachisingh.cpi_ur.R;

import org.w3c.dom.Text;

import butterknife.BindView;

public class SelectionActivity extends AppCompatActivity {
    @BindView(R.id.select_market_survey)
    TextView marketSurevey;
    @BindView(R.id.select_price_collection)
    TextView priceCollection;
    String username;
    String accessToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);
//        Intent i=getIntent();
//        username=i.getStringExtra("username");
//        accessToken=i.getStringExtra("access_token");
        marketSurevey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        priceCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent i=new Intent(SelectionActivity.this,UserDateList.class);
//                i.putExtra("username",username);
//                i.putExtra("")
            }
        });
    }
}

package com.example.prachisingh.cpi_ur.recievers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.prachisingh.cpi_ur.utils.MyApplication;
import com.example.prachisingh.cpi_ur.utils.Utils;

public class ConnectivityReceiver
        extends BroadcastReceiver {
 
    public static ConnectivityReceiverListener connectivityReceiverListener;

    private Context mContext;
 
    public ConnectivityReceiver() {
        super();
    }
 
    @Override
    public void onReceive(Context context, Intent arg1) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null
                && activeNetwork.isConnectedOrConnecting();

        mContext = context;
        if (isConnected){
            // check for queue
        }
        if (connectivityReceiverListener != null) {
            connectivityReceiverListener.onNetworkConnectionChanged(isConnected);
        }
    }
 
    public static boolean isConnected() {
        ConnectivityManager
                cm = (ConnectivityManager) MyApplication.getInstance().getApplicationContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null
                && activeNetwork.isConnectedOrConnecting();
        Utils.setConnected(isConnected);
        return isConnected;
    }
 
 
    public interface ConnectivityReceiverListener {
        void onNetworkConnectionChanged(boolean isConnected);
    }
}
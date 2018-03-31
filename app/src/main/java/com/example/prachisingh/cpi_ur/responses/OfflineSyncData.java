package com.example.prachisingh.cpi_ur.responses;

import java.util.ArrayList;

/**
 * Created by prachisingh on 31/03/18.
 */

public class OfflineSyncData {
    ArrayList<SyncItem> items;
    ArrayList<SyncMarkets> markets;
    ArrayList<SyncShops> shops;

    public ArrayList<SyncItem> getItems() {
        return items;
    }

    public ArrayList<SyncMarkets> getMarkets() {
        return markets;
    }

    public ArrayList<SyncShops> getShops() {
        return shops;
    }
}

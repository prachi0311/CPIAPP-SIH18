package com.example.prachisingh.cpi_ur.responses;

import com.example.prachisingh.cpi_ur.models.Item;

import java.util.ArrayList;

/**
 * Created by YourFather on 27-03-2018.
 */

public class ItemResponse {
    ItemData data;

    public ItemData getData() {
        return data;
    }

    public class ItemData {
        ArrayList<Item> items;

        public ArrayList<Item> getItems() {
            return items;
        }
    }

    public ItemResponse(ArrayList<Item> items) {
        this.data = new ItemData();
        this.data.items = items;
    }
}

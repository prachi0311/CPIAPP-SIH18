package com.example.prachisingh.cpi_ur.responses;

import com.example.prachisingh.cpi_ur.models.Shop;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by prachisingh on 28/03/18.
 */

public class userDatesResponse {
    @SerializedName("data")
    HashMap<String,ArrayList<Shop>> data;
    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("status")
    @Expose
    public int status;

    public HashMap<String, ArrayList<Shop>> getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }
}

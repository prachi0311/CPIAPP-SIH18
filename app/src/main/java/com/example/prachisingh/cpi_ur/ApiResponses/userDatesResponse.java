package com.example.prachisingh.cpi_ur.ApiResponses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.SimpleTimeZone;

/**
 * Created by prachisingh on 28/03/18.
 */

public class userDatesResponse {

    HashMap<String,ArrayList<ShopListOfDay>> data;
    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("status")
    @Expose
    public int status;

    public HashMap<String, ArrayList<ShopListOfDay>> getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }
}

package com.example.prachisingh.cpi_ur.ApiResponses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by prachisingh on 27/03/18.
 */

public class ShopScheduleResponse {
    ArrayList<ShopScheduleData> data;
    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("status")
    @Expose
    public int status;
    @SerializedName("error")
    @Expose
    public Object error;
    @SerializedName("disabled")
    @Expose
    public boolean disabled;
    @SerializedName("update")
    @Expose
    public boolean update;

    public ArrayList<ShopScheduleData> getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }

    public Object getError() {
        return error;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public boolean isUpdate() {
        return update;
    }
}

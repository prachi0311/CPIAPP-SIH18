package com.example.prachisingh.cpi_ur.models;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by prachisingh on 29/03/18.
 */

public class Shop implements Serializable {
    @SerializedName("id")
    @Expose
    public int id;
    @SerializedName("latitude")
    @Expose
    public double latitude;
    @SerializedName("longitude")
    @Expose
    public double longitude;
    @SerializedName("created_at")
    @Expose
    public String createdAt;
    @SerializedName("updated_at")
    @Expose
    public String updatedAt;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("market_id")
    @Expose
    public int marketId;
    @SerializedName("schedule_day")
    @Expose
    public int scheduleDay;

    public int getId() {
        return id;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public String getName() {
        return name;
    }

    public int getMarketId() {
        return marketId;
    }

    public int getScheduleDay() {
        return scheduleDay;
    }

    public LatLng getLatLng(){
        return new LatLng(latitude, longitude);
    }
}

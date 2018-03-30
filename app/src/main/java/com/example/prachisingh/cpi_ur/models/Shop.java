package com.example.prachisingh.cpi_ur.models;

import com.activeandroid.annotation.Column;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by prachisingh on 29/03/18.
 */

public class Shop implements Serializable {
    @Column(name="id")
    @SerializedName("id")
    @Expose
    public int id;
    @Column(name = "latitude")
    @SerializedName("latitude")
    @Expose
    public double latitude;
    @Column(name="longitude")
    @SerializedName("longitude")
    @Expose
    public double longitude;
    @SerializedName("created_at")
    @Expose
    public String createdAt;
    @SerializedName("updated_at")
    @Expose
    public String updatedAt;
    @Column(name="name")
    @SerializedName("name")
    @Expose
    public String name;
    @Column(name="market_id")
    @SerializedName("market_id")
    @Expose
    public int marketId;
    @Column(name="schedule_day")
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

    public Shop(){
        super();
    }

    public Shop(int id, double latitude, double longitude, String name, int marketId, int scheduleDay) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
        this.marketId = marketId;
        this.scheduleDay = scheduleDay;
    }
}

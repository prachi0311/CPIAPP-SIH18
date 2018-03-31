package com.example.prachisingh.cpi_ur.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by prachisingh on 31/03/18.
 */

public class SyncShops {
    @SerializedName("id")
    @Expose
    public int id;
    @SerializedName("latitude")
    @Expose
    public int latitude;
    @SerializedName("longitude")
    @Expose
    public int longitude;
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

    public int getLatitude() {
        return latitude;
    }

    public int getLongitude() {
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
}

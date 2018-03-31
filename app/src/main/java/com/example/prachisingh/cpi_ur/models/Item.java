package com.example.prachisingh.cpi_ur.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.example.prachisingh.cpi_ur.room.CPIContract;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by YourFather on 28-03-2018.
 */
@Entity(tableName = CPIContract.ItemContract.TABLE_NAME)
public class Item {
    @PrimaryKey
    @SerializedName("id")
    @Expose
    @ColumnInfo(name = CPIContract.ItemContract.ID_COLUMN_NAME)
    public Integer itemId;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("item_code")
    @Expose
    public String itemCode;
    public float price;
    @SerializedName("previous_month_price")
    @Expose
    public float prevMonthPrice;

    public Integer getItemId() {
        return itemId;
    }

    public String getName() {
        return name;
    }

    public String getItemCode() {
        return itemCode;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getPrevMonthPrice() {
        return prevMonthPrice;
    }

    public Item(){
        super();
    }
    public Item(Integer itemId, String name, String itemCode, float price, float prevMonthPrice) {
        this.itemId = itemId;
        this.name = name;
        this.itemCode = itemCode;
        this.price = price;
        this.prevMonthPrice = prevMonthPrice;
    }
}

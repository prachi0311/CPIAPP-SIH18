package com.example.prachisingh.cpi_ur.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by YourFather on 28-03-2018.
 */

public class Item extends Model {
    @Column(name="id")
    @SerializedName("id")
    @Expose
    private Integer itemId;
    @Column(name="name")
    @SerializedName("name")
    @Expose
    private String name;
    @Column(name="item_code")
    @SerializedName("item_code")
    @Expose
    private String itemCode;
    @Column(name="price")
    private float price;
    @Column(name="previous_month_price")
    @SerializedName("previous_month_price")
    @Expose
    private float prevMonthPrice;

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

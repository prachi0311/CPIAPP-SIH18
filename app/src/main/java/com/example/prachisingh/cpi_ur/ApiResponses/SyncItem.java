package com.example.prachisingh.cpi_ur.ApiResponses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by prachisingh on 31/03/18.
 */

public class SyncItem {
    @SerializedName("id")
    @Expose
    public int id;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("item_code")
    @Expose
    public String itemCode;
    @SerializedName("category_id")
    @Expose
    public Object categoryId;
    @SerializedName("group_id")
    @Expose
    public Object groupId;
    @SerializedName("sub_group_id")
    @Expose
    public Object subGroupId;
    @SerializedName("section_id")
    @Expose
    public Object sectionId;
    @SerializedName("goods_services")
    @Expose
    public Object goodsServices;
    @SerializedName("weighted_item_id")
    @Expose
    public Object weightedItemId;
    @SerializedName("varierty")
    @Expose
    public Object varierty;
    @SerializedName("created_at")
    @Expose
    public String createdAt;
    @SerializedName("updated_at")
    @Expose
    public String updatedAt;
    @SerializedName("market_id")
    @Expose
    public int marketId;
    @SerializedName("original_shop_id")
    @Expose
    public int originalShopId;
    @SerializedName("first_reserve_shop_id")
    @Expose
    public int firstReserveShopId;
    @SerializedName("second_reserve_shop_id")
    @Expose
    public int secondReserveShopId;
    @SerializedName("open_shop_id")
    @Expose
    public Object openShopId;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getItemCode() {
        return itemCode;
    }

    public Object getCategoryId() {
        return categoryId;
    }

    public Object getGroupId() {
        return groupId;
    }

    public Object getSubGroupId() {
        return subGroupId;
    }

    public Object getSectionId() {
        return sectionId;
    }

    public Object getGoodsServices() {
        return goodsServices;
    }

    public Object getWeightedItemId() {
        return weightedItemId;
    }

    public Object getVarierty() {
        return varierty;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public int getMarketId() {
        return marketId;
    }

    public int getOriginalShopId() {
        return originalShopId;
    }

    public int getFirstReserveShopId() {
        return firstReserveShopId;
    }

    public int getSecondReserveShopId() {
        return secondReserveShopId;
    }

    public Object getOpenShopId() {
        return openShopId;
    }
}

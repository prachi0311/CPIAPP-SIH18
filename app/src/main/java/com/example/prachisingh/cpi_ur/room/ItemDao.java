package com.example.prachisingh.cpi_ur.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.prachisingh.cpi_ur.models.Item;

import java.util.List;

/**
 * Created by YourFather on 08-02-2018.
 */

@Dao
public interface ItemDao {

    @Insert
    void insertMessage(Item item);

    @Delete
    void deleteMessage(Item item);

    @Query("SELECT * FROM " + CPIContract.ItemContract.TABLE_NAME
            + " WHERE " + CPIContract.ItemContract.ID_COLUMN_NAME + " = :itemId")
    Item getItemWithId(int itemId);
}

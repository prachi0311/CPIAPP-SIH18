package com.example.prachisingh.cpi_ur.Network;

import com.example.prachisingh.cpi_ur.ApiResponses.ItemResponse;
import com.example.prachisingh.cpi_ur.ApiResponses.MarketResponseData;
import com.example.prachisingh.cpi_ur.ApiResponses.ShopScheduleResponse;
import com.example.prachisingh.cpi_ur.ApiResponses.SignInResponse;
import com.example.prachisingh.cpi_ur.ApiResponses.SignUpResponse;
import com.example.prachisingh.cpi_ur.ApiResponses.UserMarketResponse;
import com.example.prachisingh.cpi_ur.ApiResponses.userDatesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by prachisingh on 26/03/18.
 */

public interface ApiInterface {

    @POST("signin")
    Call<SignInResponse> userSignIn(@Query("username") String username,@Query("password") String password);

    @POST("signup")
    Call<SignUpResponse> userSignUp(@Query("username") String username, @Query("password") String password, @Query("reconfirm_password") String reconfirm_password);

    @GET("schedule")
    Call<userDatesResponse> userDates(@Query("token") String accessToken,@Query("month") int month,@Query("year") int year);

    @GET("get-items")
    Call<ItemResponse> getItemsForShop(@Query("token") String accessToken, @Query("shop_id") int shopId);

    @POST("update-price-quotation")
    Call<Void> updatePrice(@Query("token") String accessToken, @Query("item_id") int itemId, @Query("price") float price, @Query("month") String month);

    @GET("get-required-user-market")
    Call<UserMarketResponse> getuserMarket(@Query("user_id") int userId, @Query("token") String accessToken);


}

package com.example.prachisingh.cpi_ur.Network;

import com.example.prachisingh.cpi_ur.ApiResponses.ShopScheduleResponse;
import com.example.prachisingh.cpi_ur.ApiResponses.SignInResponse;
import com.example.prachisingh.cpi_ur.ApiResponses.SignUpResponse;

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

    @GET("shops-to-visit")
    Call<ShopScheduleResponse> shopschedule(@Query("date") String date,@Query("token") String accessToken);


}

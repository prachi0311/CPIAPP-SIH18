package com.example.prachisingh.cpi_ur.Network;

import com.example.prachisingh.cpi_ur.ApiResponses.ShopScheduleResponse;
import com.example.prachisingh.cpi_ur.ApiResponses.ScheduleResponse;
import com.example.prachisingh.cpi_ur.ApiResponses.SignInResponse;
import com.example.prachisingh.cpi_ur.ApiResponses.SignUpResponse;
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

    @GET("shops-to-visit")
    Call<ShopScheduleResponse> shopschedule(@Query("date") String date,@Query("token") String accessToken);

    @GET("schedule")
    Call<userDatesResponse> userDates(@Query("token") String accessToken,@Query("month") int month,@Query("year") int year);

    @GET("schedule")
    Call<ScheduleResponse> getSchedule(@Query("userId") long userId, @Query("date") String date);


}

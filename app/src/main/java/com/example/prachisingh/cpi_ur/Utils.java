package com.example.prachisingh.cpi_ur;

/**
 * Created by YourFather on 28-03-2018.
 */

public class Utils {
    private static long USER_ID;
    // TODO : implement utils class
    public static void init(long userId){
        USER_ID = userId;
    }
    public static long getUserId(){return USER_ID;}
}

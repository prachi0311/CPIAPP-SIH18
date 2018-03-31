package com.example.prachisingh.cpi_ur.utils;

/**
 * Created by YourFather on 28-03-2018.
 */

public class Utils {
    private static long USER_ID;
    private static boolean IS_CONNECTED;
    // TODO : implement utils class
    public static void init(long userId){
        USER_ID = userId;
    }
    public static long getUserId(){return USER_ID;}
    public static void setConnected(boolean isConnected){IS_CONNECTED = isConnected;}
    public static boolean isConnected(){return IS_CONNECTED;}
}

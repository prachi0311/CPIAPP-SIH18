package com.example.prachisingh.cpi_ur.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by prachisingh on 26/03/18.
 */

public class SignUpResponse {
    @SerializedName("data")
    @Expose
    public SignUpData data;
    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("status")
    @Expose
    public int status;
    @SerializedName("error")
    @Expose
    public Object error;
    @SerializedName("disabled")
    @Expose
    public boolean disabled;
    @SerializedName("update")
    @Expose
    public boolean update;

    public SignUpData getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }

    public Object getError() {
        return error;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public boolean isUpdate() {
        return update;
    }
}

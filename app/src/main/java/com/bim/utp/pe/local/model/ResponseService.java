package com.bim.utp.pe.local.model;

import com.google.gson.annotations.SerializedName;

public class ResponseService <T>{

    @SerializedName("Header")
    public String Header;

    @SerializedName("Response")
    public T Response;

    @SerializedName("Status")
    public String Status;
}

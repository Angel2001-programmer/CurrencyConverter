package com.angelwitchell.currencyconverter;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PlaceholderAPI {

    @GET("/api/live?access_key=1695a9d46d4091e20172e9400a64c44d&currencies=GBP,HKD,EUR,CHF,JPY")
    Call<Model> getAllData();
}

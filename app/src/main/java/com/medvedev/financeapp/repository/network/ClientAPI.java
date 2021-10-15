package com.medvedev.financeapp.repository.network;

import com.medvedev.financeapp.domain.model.StockPriceData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ClientAPI {

    @GET("quote?")
    Call<StockPriceData> getStockPriceFromAPI(@Query("symbol") String symbol, @Query("token") String token);

}
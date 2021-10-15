package com.medvedev.financeapp.repository.network;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.medvedev.financeapp.BuildConfig;
import com.medvedev.financeapp.domain.model.StockPriceData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class QuoteLogic {
    private ClientAPI api;

    public QuoteLogic(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://finnhub.io/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(ClientAPI.class);
    }


    public LiveData<StockPriceData> getStockPrice(String query){

        MutableLiveData<StockPriceData> stockPrice = new MutableLiveData<>();

        Call<StockPriceData> call = api.getStockPriceFromAPI(query, BuildConfig.FINHUB_API_KEY);
        call.enqueue(new Callback<StockPriceData>() {
            @Override
            public void onResponse(Call<StockPriceData> call, Response<StockPriceData> response) {
                if (response.isSuccessful() && response.body() != null){
                    stockPrice.setValue(response.body());
                }
            }
            @Override
            public void onFailure(Call<StockPriceData> call, Throwable t) {
                Log.e("API", "server is not responding"+t+call);
            }
        });
        return stockPrice;
    }
}

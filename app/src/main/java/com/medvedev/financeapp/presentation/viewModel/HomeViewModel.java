package com.medvedev.financeapp.presentation.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.medvedev.financeapp.domain.model.Stock;
import com.medvedev.financeapp.domain.model.StockPriceData;
import com.medvedev.financeapp.repository.model.StockDTO;
import com.medvedev.financeapp.repository.network.QuoteLogic;
import com.medvedev.financeapp.repository.room.StockRepository;

public class HomeViewModel extends AndroidViewModel {

    private StockRepository repository;

    public HomeViewModel(@NonNull Application application) {
        super(application);
        repository = new StockRepository(application);
    }

    public LiveData<StockPriceData> getStockPrice(String query){
        return new QuoteLogic().getStockPrice(query);
    }

    public LiveData<StockDTO> getByTicker(String ticker){
        return repository.getByTicker(ticker);
    }

    public void addStock(StockDTO stockDTO){
        repository.insert(stockDTO);
    }
}
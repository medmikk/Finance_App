package com.medvedev.financeapp.presentation.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.medvedev.financeapp.domain.model.StockPriceData;
import com.medvedev.financeapp.repository.model.StockDTO;
import com.medvedev.financeapp.repository.network.QuoteLogic;
import com.medvedev.financeapp.repository.room.StockRepository;

public class StockInfoViewModel extends AndroidViewModel {

    private StockRepository repository;

    public StockInfoViewModel(@NonNull Application application) {
        super(application);
        repository = new StockRepository(application);//TODO init in main ui
    }

    public LiveData<StockDTO> getById(int id){
        return repository.getById(id);
    }

    public LiveData<StockPriceData> getStockPrice(String query){
        return new QuoteLogic().getStockPrice(query);
    }
}

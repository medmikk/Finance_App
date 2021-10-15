package com.medvedev.financeapp.presentation.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.medvedev.financeapp.repository.model.StockDTO;
import com.medvedev.financeapp.repository.room.StockRepository;

import java.util.List;

public class FavouriteViewModel  extends AndroidViewModel {

    private StockRepository repository;
    private LiveData<List<StockDTO>> allStocks;

    public FavouriteViewModel(@NonNull Application application) {
        super(application);
        repository = new StockRepository(application);
        allStocks = repository.getAllStocks();
    }

    public void delete(StockDTO stock){
        repository.delete(stock);
    }

    public LiveData<List<StockDTO>> getAllStocks(){
        return allStocks;
    }

    public void addStock(String ticker,
                         Double lastCost,
                         String companyName,
                         int cost){

        StockDTO stockDTO = new StockDTO(ticker, companyName, lastCost, cost);
        repository.insert(stockDTO);

    }
}

package com.medvedev.financeapp.repository.room;


import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.room.Room;

import com.medvedev.financeapp.repository.model.StockDTO;
import com.medvedev.financeapp.repository.room.DAO.StockDAO;

import java.util.List;

public class StockRepository {

    private StockDAO stockDAO;
    private LiveData<List<StockDTO>> allStocks;

    public StockRepository(Application application){
        StockRoomDatabase database = StockRoomDatabase.getDatabase(application);
//        database = Room.databaseBuilder(application.getApplicationContext(), StockRoomDatabase.class, "stock_database")
//                .fallbackToDestructiveMigration()
//                .build();
        stockDAO = database.orderDAO();
        allStocks = stockDAO.getAllStocks();
    }

    public void insert(StockDTO stock){
        StockRoomDatabase.databaseWriteExecutor.execute(() -> {
            stockDAO.addStock(((StockDTO) stock));
        });
    }

    public void update(StockDTO stock){

    }


    public LiveData<StockDTO> getByTicker(String ticker){
        return stockDAO.getByTicker(ticker);
    }

    public LiveData<StockDTO> getById(int id){
        return stockDAO.getById(id);
    }

    public void delete(StockDTO stock){
        StockRoomDatabase.databaseWriteExecutor.execute(() -> {
            stockDAO.deleteStock(((StockDTO) stock));
        });
    }

    public void deleteAllStocks(StockDTO stock){

    }

    public LiveData<List<StockDTO>> getAllStocks(){
        return allStocks;
    }

}
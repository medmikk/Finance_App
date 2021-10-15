package com.medvedev.financeapp.repository.room.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.medvedev.financeapp.repository.model.StockDTO;

import java.util.List;

@Dao
public interface StockDAO {

    @Insert
    void addStock(StockDTO stock);

    @Delete
    void deleteStock(StockDTO stock);

    @Update
    void updateStock(StockDTO stock);

    @Query("SELECT * FROM stock WHERE id = :id")
    LiveData<StockDTO> getById(int id);

    @Query("SELECT * FROM stock")
    LiveData<List<StockDTO>> getAllStocks();

}

package com.medvedev.financeapp.repository.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.medvedev.financeapp.repository.model.StockDTO;
import com.medvedev.financeapp.repository.room.DAO.StockDAO;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {StockDTO.class}, version = 1)
public abstract class StockRoomDatabase extends RoomDatabase {

    //TODO rename orderDAO
    public abstract StockDAO orderDAO();

    private static volatile StockRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static StockRoomDatabase getDatabase(final Context context){

        if (INSTANCE == null) {
            synchronized (StockRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            StockRoomDatabase.class, "stock_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
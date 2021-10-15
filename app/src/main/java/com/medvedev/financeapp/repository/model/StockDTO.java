package com.medvedev.financeapp.repository.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "stock")
public class StockDTO {

    @PrimaryKey(autoGenerate = true)
    @NotNull
    @ColumnInfo
    public int id;

    @ColumnInfo
    public String ticker;
    @ColumnInfo
    public String companyName;
    @ColumnInfo
    public double lastCost;
    @ColumnInfo
    public double cost;

    public StockDTO() {
    }

    public StockDTO(String ticker, String companyName, double lastCost, double cost) {
        this.ticker = ticker;
        this.companyName = companyName;
        this.lastCost = lastCost;
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public double getLastCost() {
        return lastCost;
    }

    public void setLastCost(int lastCost) {
        this.lastCost = lastCost;
    }

    public double getCost() { return cost; }

    public void setCost(int cost) { this.cost = cost; }
}


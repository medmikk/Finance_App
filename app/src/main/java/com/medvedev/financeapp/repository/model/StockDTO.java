package com.medvedev.financeapp.repository.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.medvedev.financeapp.domain.model.Stock;

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
    public double changePerCent;
    @ColumnInfo
    public double cost;

    public StockDTO() {
    }

    public StockDTO(String ticker, String companyName, double changePerCent, double cost) {
        this.ticker = ticker;
        this.companyName = companyName;
        this.changePerCent = changePerCent;
        this.cost = cost;
    }

    public StockDTO(Stock stock){
        this.ticker = stock.getTicker();
        this.companyName = stock.getCompanyName();
        this.changePerCent = stock.getChangePerCent();
        this.cost = stock.getCost();
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

    public double getChangePerCent() {
        return changePerCent;
    }

    public void setChangePerCent(int changePerCent) {
        this.changePerCent = changePerCent;
    }

    public double getCost() { return cost; }

    public void setCost(int cost) { this.cost = cost; }
}


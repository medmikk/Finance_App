package com.medvedev.financeapp.domain.model;

public class Stock {

    private String ticker;
    private String companyName;
    private Double cost;
    private Double changePerCent;

    public Stock(String ticker, String companyName, Double cost, Double changePerCent) {
        this.ticker = ticker;
        this.companyName = companyName;
        this.cost = cost;
        this.changePerCent = changePerCent;
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

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Double getChangePerCent() {
        return changePerCent;
    }

    public void setChangePerCent(Double changePerCent) {
        this.changePerCent = changePerCent;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "ticker='" + ticker + '\'' +
                ", companyName='" + companyName + '\'' +
                ", cost=" + cost +
                ", changePerCent=" + changePerCent +
                '}';
    }
}

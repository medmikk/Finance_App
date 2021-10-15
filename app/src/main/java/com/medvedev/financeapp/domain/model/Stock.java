package com.medvedev.financeapp.domain.model;

import java.time.LocalDateTime;

public class Stock {

    private String place;
    private LocalDateTime arrivalTime;
    private int numberOfVisitors;

    public Stock(String place, LocalDateTime arrivalTime, int numberOfVisitors) {
        this.place = place;
        this.arrivalTime = arrivalTime;
        this.numberOfVisitors = numberOfVisitors;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getNumberOfVisitors() {
        return numberOfVisitors;
    }

    public void setNumberOfVisitors(int numberOfVisitors) {
        this.numberOfVisitors = numberOfVisitors;
    }
}

package com.example.demo.CONTROL;


import com.example.demo.BOUNDARY.Event;

public class MakePrice implements Event {

    private String DriverName;
    private String EventTime;
    private double price;




    public MakePrice( String EventTime,Offer offer) {

        this.EventTime = EventTime;
        this.price=offer.getPrice();
        this.DriverName=offer.getDriverName();

    }

    @Override
    public String toString() {
        return "{" + "EventName=" + "captin put a price to the ride"+ ", DriverName=" + DriverName + ", EventTime=" + EventTime + ", price=" + price + '}';

    }








}
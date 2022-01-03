package com.example.demo.CONTROL;


import com.example.demo.BOUNDARY.Event;

public class TripFinishingPoint implements Event {

    private  String UserName;
    private  String EventTime;
    private String DriverName;
    public TripFinishingPoint(String EventTime, Offer offer) {

        this.EventTime = EventTime;
        this.DriverName=offer.getRide().getDriverName();
        this.UserName=offer.getRide().getUserName();
    }

    @Override
    public String toString() {
        return "{" + "EventName=" + "Captain arrived to user destination " + ", UserName=" + UserName + ", EventTime=" + EventTime + ", DriverName=" + DriverName + '}';
    }

}



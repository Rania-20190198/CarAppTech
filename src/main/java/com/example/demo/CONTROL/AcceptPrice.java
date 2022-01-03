package com.example.demo.CONTROL;


import com.example.demo.BOUNDARY.Event;

public class AcceptPrice implements Event {

    private String UserName;
    private String EventTime;


    public AcceptPrice( String EventTime, Ride ride) {

        this.EventTime = EventTime;
        this.UserName=ride.getUserName();
    }



    @Override
    public String toString() {
        return "{" + "EventName=" + "user accepts captin price" + ", UserName=" + UserName + ", EventTime=" + EventTime + '}';
    }


}

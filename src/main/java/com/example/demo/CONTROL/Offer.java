package com.example.demo.CONTROL;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class Offer {
    Driver d;
    Ride ride;
    public double price;
    private double DriverRating;
    private String DriverName;

    public Offer(Ride ride, double price, double DriverRating, String DriverName) {
        this.ride = ride;
        this.price = price;
        this.DriverRating = DriverRating;
        this.DriverName = DriverName;
    }
    public Offer(Ride ride){
        this.ride = ride;
        //this.price = price;

    }
    public Ride getRide() {
        return ride;
    }

    public double getPrice() {
        return this.price;
    }

    public double getDriverRating() {
        return DriverRating;
    }

    public String getDriverName() {
        return this.DriverName;
    }

    public void setRide(Ride ride) {
        this.ride = ride;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDriverRating(double DriverRating) {
        this.DriverRating = DriverRating;
    }

    public void setDriverName(String DriverName) {
        this.DriverName = DriverName;
    }

    public Offer() {
    }



    public Offer(Driver d, double price) {
        this.d = d;
        this.price = price;
    }
    public Driver getDriver() {
        return d;

    }
    public Offer getOffer() {
        return this;
    }



    @Override
    public String toString() {
        return "Offer{" + "ride=" + ride + ", price=" + price + ", DriverRating=" + DriverRating + ", DriverName=" + DriverName + '}';
    }








}
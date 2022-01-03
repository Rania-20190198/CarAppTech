package com.example.demo.BOUNDARY;


import java.util.ArrayList;
import java.util.Set;
import com.example.demo.CONTROL.User;
import com.example.demo.CONTROL.Driver;
import com.example.demo.CONTROL.Ride;

public interface DataFunctions {
    void addUser(User user);//function to add user in users arraylist
    ArrayList<User> getUsers();//function get arraylists of the user
    Set<String> getUsernames(); //check the username if there is a username with this name
    void addDriver(Driver driver);//function to add Driver in drivers arraylist
    ArrayList<Driver> getDrivers(); ;//function get arraylists of the driver
    void addRide(Ride ride);
    void removeRide(Ride ride);
    ArrayList<Ride> getRides();//function get arraylists of the ride
    void addDiscountArea(String area);
    ArrayList<String> getDiscountArea();
    ArrayList<Event> getEvents();
    void addEvent(Event event);
    ArrayList<String> getHolidays();

}
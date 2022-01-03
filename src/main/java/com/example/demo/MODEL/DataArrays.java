package com.example.demo.MODEL;

import com.example.demo.CONTROL.Driver;
import com.example.demo.CONTROL.Ride;
import com.example.demo.CONTROL.User;
import com.example.demo.BOUNDARY.DataFunctions;
import com.example.demo.BOUNDARY.Event;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import java.util.Arrays;
/*
class DataArrays that use singleton pattern

*/

public class DataArrays implements DataFunctions {

    private static ArrayList<User> users = new ArrayList<>();
    private static ArrayList<Driver> drivers = new ArrayList<>();
    private static Set<String> systemUsersname = new HashSet<>();
    public static ArrayList<Ride> rides = new ArrayList<>();
    private static DataArrays Data;
    private static ArrayList<String> Discount_area =new ArrayList<>();
    private ArrayList<Event> events = new ArrayList<>();

    public static ArrayList<String> PublicHolidays = new ArrayList<String>(Arrays.asList(
            "04-01-2022", "02-01-2022","07-01-2022","25-01-2022","25-04-2022","01-05-2022","02-05-2022","03-05-2022","04-05-2022","05-05-2022","30-06-2022",
            "08-07-2022","09-07-2022","10-07-2022","11-07-2022","12-07-2022","23-07-2022","30-07-2022","06-10-2022","08-10-2022"));

    private DataArrays(){}

    public static DataArrays getInstance(){
        if(Data==null)Data=new DataArrays();
        return Data;
    }


    public void addUser(User user) {
        users.add(user);
        systemUsersname.add(user.getUsername());
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void addEvent(Event event) {
        events.add(event);

    }
    public ArrayList<Event> getEvents() {
        return events;
    }

    public Set<String> getUsernames() {
        return systemUsersname;
    }

    @Override
    public void addDriver(Driver driver) {
        drivers.add(driver);
        systemUsersname.add(driver.getUsername());
    }

    public ArrayList<Driver> getDrivers() {
        return drivers;
    }
    public ArrayList<String> getHolidays() {
        return PublicHolidays;
    }

    public void addRide(Ride ride) {
        rides.add(ride);
    }

    public void removeRide(Ride ride) {
        for(Ride r : rides){
            if((r.getDestination().equalsIgnoreCase(ride.getDestination()))&&(r.getSource().equalsIgnoreCase(ride.getSource()))&&(r.getUser()==ride.getUser()))
            {
                rides.remove(r);
                break;
            }
        }
    }

    public ArrayList<Ride> getRides() {
        return rides;
    }


    public void addDiscountArea(String area) {
        Discount_area.add(area);
    }

    public ArrayList<String> getDiscountArea() {
        return Discount_area;
    }
}

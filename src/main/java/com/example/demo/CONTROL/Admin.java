package com.example.demo.CONTROL;



import com.example.demo.BOUNDARY.DataFunctions;
import com.example.demo.BOUNDARY.Event;
import com.example.demo.MODEL.DataArrays;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

/*
class Admin that use singleton pattern
*/
@RestController
public class Admin {

    private static Admin admin;
    private DataFunctions Data= DataArrays.getInstance();
    private Admin() {} //private constructor

    public static Admin getinstance(){ //getter check if admin null make new admin if not return this admin
        if(admin==null)admin=new Admin();
        return admin;
    }
    @PostMapping("/suspendUser/{username}")

    public boolean suspendUser(@PathVariable String username) {
        ArrayList<User> users = Data.getUsers(); //get arraylist of the users

        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername().equals(username)) {
                return true;
            }
        }
        ArrayList<Driver> Drivers = Data.getDrivers();//get arraylist of the drivers

        for (int i = 0; i < Drivers.size(); i++) {
            if (Drivers.get(i).getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }
@PostMapping("/verifyDriver")
    public boolean verifyDriver(@RequestBody Driver driver) { //make pending false to make driver verified
        driver.setPending(false);
        return true;
    }
@GetMapping("/listPendingDrivers")
    public ArrayList<Driver> listPendingDrivers() {
        ArrayList<Driver> drivers = Data.getDrivers(); //get arraylist of the drivers
        ArrayList<Driver> pendingDrivers = new ArrayList<>();

        for (Driver driver : drivers) //copy all the pending drivers list in the pending driver list
            if (driver.isPending())
                pendingDrivers.add(driver);
        return pendingDrivers;
    }
    public void addDiscountArea(String Area){
        Data.addDiscountArea(Area);
    }
    public void ShowEvents(){
        ArrayList<Event> events = Data.getEvents();
        for(int i=0;i<events.size();i++){
            System.out.println("The Event" +events.get(i));

        }
    }

}

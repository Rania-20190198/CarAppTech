package com.example.demo.CONTROL;


import com.example.demo.BOUNDARY.DataFunctions;
import com.example.demo.MODEL.DataArrays;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

import java.text.SimpleDateFormat;
import java.util.List;

@RestController
public class User extends Person {
    private String birthdayDate;
    private List<Offer> pastRides;
    private  Ride offers = new Ride();// obj form class rides
    private ArrayList<Ride> rides=new ArrayList<>(); //list that all offers will be  added in
    private DataFunctions Data= DataArrays.getInstance();
    public User(String username, String email, String phone, String password,String birthdayDate) {
        super(username, email, phone, password);
        this.birthdayDate=birthdayDate;
    }

    public User() {
    }
    @PostMapping("/register")

    @Override

    public boolean register(@RequestBody Person person) {
        if (Data.getUsernames().contains(person.getUsername()))
            return false;
        Data.addUser((User) person);
        return true;
    }





    @PostMapping("/loginuser/{username}/{password}")
    @Override

    public Person login(@PathVariable String username, @PathVariable String password){
        ArrayList<User> users = Data.getUsers();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername().equals(username) && users.get(i).getPassword().equals(password) && !users.get(i).isSuspended()) {
                return users.get(i);
            }
        }
        return null;
    }
@GetMapping("/getOffers")
    public Ride getOffers() {
        return offers;
    }

@PostMapping("/requestRide/{source}/{destination}/{passenger}")
    public boolean requestRide(@PathVariable String source, @PathVariable String destination,@PathVariable int passenger) {
        Ride newRide = new Ride(source, destination, this ,passenger);
        Data.addRide(newRide);
        return true;
    }
@PostMapping("/rateDriver/{stars}")
    public boolean rateDriver(@RequestBody Driver driver, @PathVariable int stars) {
        driver.addUserRating(new Rating(this, stars));
        return true;
    }
 @PutMapping("/checkDriverRating")
    public double checkDriverRating(@RequestBody Driver driver) {
        return driver.getAvgRating();
    }

@PostMapping("/sentOffer")
    public String sentOffer(@RequestBody Offer offer){
        return ("The driver: " + offer.getDriver().getUsername() + " Offers Your Ride with: " + offer.getPrice() + " LE.");

    }
@PostMapping("/acceptOffer/{accept}")
    public boolean acceptOffer(@RequestBody Ride ride,@PathVariable Boolean accept,@RequestBody Offer offer){
        offer.getDriver().notify(offer.getDriver(), "User " + (accept ? "accepted" : "rejected") + " the offer", ride);
        if(accept) {
            rides.add(ride); // if accepted add this ride to rides list
            ride.setDriver(offer.getDriver()); //add ride to this driver
            ride.setPrice(offer.getPrice());
            offer.getDriver().setCurrentRide(ride);
            //Data.removeRide(ride);
            return true;
        }
        return false;
    }
@PostMapping("/clearOffers")
    public boolean clearOffers(){
        offers = null;
        return true;
    }


    @PutMapping("/notifyDriver/{message}")
    public boolean notify(@RequestBody User user, @PathVariable String message,@RequestBody Ride ride) {
        user.addNotification(message);
        offers =ride;
        return true;
    }

    public void setBirthdayDate(String birthdayDate) {
        this.birthdayDate = birthdayDate;
    }

    public String getBirthdayDate() {
        return birthdayDate;
    }

    public ArrayList<Ride> getRides() {
        return rides;
    }

    public void setRides(ArrayList<Ride> rides) {
        this.rides = rides;
    }

    public DataFunctions getData() {
        return Data;
    }

    public void setData(DataFunctions Data) {
        this.Data = Data;
    }
@PostMapping("/isBirthDay")
    public boolean isBirthDay() {
        java.util.Date currentDate = new java.util.Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("dd-MM-yyyy");
        String []dateNow = dateFormat.format(currentDate).toString().split("-");
        String[] birthdayUser =getBirthdayDate().toString().split("-");
        for (int i = 1; i < 3; i++) {
            if (!(birthdayUser[i].equals(dateNow[i]))) {
                return false;
            }
        }
        return true;
    }


}

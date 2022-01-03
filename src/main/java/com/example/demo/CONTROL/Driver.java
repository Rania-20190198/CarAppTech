package com.example.demo.CONTROL;



import com.example.demo.BOUNDARY.DataFunctions;
import com.example.demo.MODEL.DataArrays;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/*
class Driver that use Strategy patterns

*/
@RestController
public class Driver extends Person {
    private String drivingLicence, nationalID;
    private boolean pending;
    private Ride CurrentRide=null;
    private String CurrentLocation;
    private boolean available; //boolean variable to show if the driver is availble or not
    private DataFunctions Data= DataArrays.getInstance();
    private Set<String> favoriteAreas = new HashSet<>();
    private ArrayList<Rating> ratings = new ArrayList<>(); //array list of ratings

    public Driver(String username, String email, String phone, String password, String drivingLicence, String nationalID) {
        super(username, email, phone, password);
        this.drivingLicence = drivingLicence;
        this.nationalID = nationalID;
        this.pending = true;// any driver will be pending by defult until the admin setpending(false)
        available =true; //any driver will be available as defult until it changed
        CurrentLocation = "defultArea";
    }

    public Driver() {
    }
 @PostMapping("/Register")
    public boolean register(@RequestBody Person person) {
        if (Data.getUsernames().contains(person.getUsername())) //check if there is username with this name before
            return false;
        Data.addDriver((Driver) person);
        return true;
    }

    public Ride getCurrentRide() {
        return CurrentRide;
    }

    public void setCurrentRide(Ride CurrentRide) {
        this.CurrentRide = CurrentRide;
    }

    public String getCurrentLocation() {
        return CurrentLocation;
    }

    public boolean isAvailable() {
        return available;
    }

@PostMapping("/login/{username}/{password}")
    public Person login (@PathVariable String username, @PathVariable String password) {
        ArrayList<Driver> drivers = Data.getDrivers();//get the array of the driver
        for (int i = 0; i < drivers.size(); i++) {
            if (drivers.get(i).getUsername().equals(username) && drivers.get(i).getPassword().equals(password) //check if there is account with this name and this password
                    && !drivers.get(i).isSuspended() && !drivers.get(i).isPending()) {// and make sure it's not suspended or pended
                return drivers.get(i);
            }
        }
        return null;
    }

    public void setDrivingLicence(String drivingLicence) {
        this.drivingLicence = drivingLicence;
    }

    public void setPending(boolean pending) {
        this.pending = pending;
    }

 @PostMapping("/adduserrate")
    public boolean addUserRating(@RequestBody Rating rating) { //function to add the user rating for this driver  and add it for the ratings list
        ratings.add(rating);
        System.out.println("Thanks for Your Rating "+rating.getRate()+" to me !!");
        return true;
    }

    @PostMapping("/addfavoritearea/{area}")
    public boolean addFavoriteArea(@PathVariable String area) {
        favoriteAreas.add(area);
        return true;
    }

    @GetMapping("/listuserRatings")
    public ArrayList<Rating> listUserRatings() { //list the arraylist of the ratings
        return ratings;
    }

    public String getDrivingLicence() {
        return drivingLicence;
    }

    public String getNationalID() {
        return nationalID;
    }

    public boolean isPending() {
        return pending;
    }
@GetMapping("/getAvgRating")
    public double getAvgRating() {
        return AvgRating.calculateAvgRating(this);
    }
    @GetMapping("/getFavoriteAreas")
    public Set<String> getFavoriteAreas() {
        return favoriteAreas;
    }

    @GetMapping("/getRides")
    public ArrayList<Ride> getRides() {
        ArrayList<Ride> rides = Data.getRides();
        ArrayList<Ride> favoriteAreaRides = new ArrayList<>();

        for (Ride ride : rides) {
            if (favoriteAreas.contains(ride.getSource()))
                favoriteAreaRides.add(ride);
        }
        return favoriteAreaRides;
    }

@PostMapping("/makeOffer/{price}")
    public boolean makeOffer(@RequestBody Ride ride,@PathVariable double price) {

        User user = ride.getUser();
        ride.addOffer(new Offer(this,price));

        user.notify(user, "The driver offers your ride. check the price!", ride);
        return true;
    }

    public void setCurrentLocation(String currentlocation) {
        CurrentLocation = currentlocation;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
 @PutMapping("/finish_Ride")
    public boolean finish_Ride(@RequestBody Ride ride)
    {
        CurrentLocation = ride.getDestination();
        available = true;
        return true;
    }


@PutMapping("/notify/{message}")
    public boolean  notify(@RequestBody Driver driver, @PathVariable String message) {
        if(driver.getCurrentRide()==null){
            driver.addNotification(message);
            return true;
        }
        else{System.out.println("drivre not avilable");
        }
        return false;
    }
}

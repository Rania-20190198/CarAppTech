package com.example.demo.CONTROL;



import com.example.demo.BOUNDARY.DataFunctions;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

import static com.example.demo.MODEL.DataArrays.getInstance;
@RestController

public class Ride {

    private String source;
    private String destination;
    private User user;
    private Driver driver;
    private String driverName;
    private Double price;
    private int numOfpassenger;
    private String UserName;

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }
    private DataFunctions Data= (DataFunctions) getInstance();
    private ArrayList<Offer> offers = new ArrayList<>();

    ArrayList<Ride> rides = Data.getRides();
    ArrayList<Driver> drivers =Data.getDrivers();
    private Object date;
    public ArrayList<Offer> getOffers() {
        return offers;
    }

    public Ride() {
    }
    public void addOffer(Offer offer)
    {
        offers.add(offer);
    }

    public Ride(String source, String destination, User user, int passenger) {
        this.source = source;
        this.destination = destination;
        this.user = user;
        // this. price = price;
        //     driver = null;
        numOfpassenger = passenger;


        for (Driver driver : drivers) {
            if (driver.getFavoriteAreas().contains(source) && (driver.getCurrentLocation().equalsIgnoreCase(source) || driver.getCurrentLocation().equalsIgnoreCase("defultArea")) && driver.isAvailable()) {
                driver.notify(driver, "You Got a Ride that matches your Favorite Area..", this);
            }
        }
    }


    public int getNumOfpassenger() {
        return numOfpassenger;
    }

    public void setNumOfpassenger(int numOfpassenger) {
        this.numOfpassenger = numOfpassenger;
    }


    public void setSource(String source) {
        this.source = source;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public User getUser() {
        return user;
    }

    public Double getPrice() {
        return price;
    }

    public Driver getDriver() {
        return driver;
    }


    public void List_ride(Ride ride) {
        Data.addRide(ride);

    }

    public boolean checkdate(){
        for(User user :Data.getUsers())
            if (user.getBirthdayDate().equals(date))
                return true;
        return false;
    }
    /////////////////////
    @PutMapping("/Discount/{price}/{discount}")
    public  double Discount(@PathVariable Double price, @PathVariable double discount){
        double dis = (price/100)*discount;
        double cost=price-dis;
        System.out.println("After Discount = "+cost);
        System.out.println("Price of offer driver  "+getPrice()+"");

        return cost;
    }

    // System.out.println("Price of offer driver  "+/price/+" ");
    @PutMapping("/Discount/{price}/{numPassenger}")
    public  double calcDiscount(@PathVariable double price, @RequestBody User user,@PathVariable int numPassenger){
        double discount=0.0;
        //first ride
        for(Ride r: rides){
            if(r.getUser().getUsername()==user.getUsername()){
                discount=discount+10;
                System.out.println("first ride discount = "+ discount+"%");
                //System.out.println("cost = "+discount);
            }}
        // 2 passenger
        if(numPassenger==2)
        { discount=discount+5;
            System.out.println("2 passengers discount = "+discount+"%");
        }
        //birthday
        else if(user.isBirthDay()==true){
            discount=discount+10;
            System.out.println("HAPPY BDay you have discount = "+discount+"%");
        }
        //admin areas
        else if (!Data.getHolidays().isEmpty()){
            discount = discount +10;
            System.out.println("Admin discount = "+discount+"%");

        }

        //holiday
        for(int i=0;i< getInstance().PublicHolidays.size();i++){
            discount=discount+5;
            System.out.println("HAPPY Holiday you have discount = "+discount+"%");
            break;
        }

        price = Discount(price, discount);
        return price;

    }



    @Override
    public String toString() {
        return "Ride{" + "source=" + source + ", destination=" + destination + ", numOfpassenger=" + numOfpassenger + ", UserName=" + UserName + '}';
    }

}

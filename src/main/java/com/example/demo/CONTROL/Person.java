package com.example.demo.CONTROL;

import com.example.demo.BOUNDARY.ILogin;
import com.example.demo.BOUNDARY.INotifier;
import com.example.demo.BOUNDARY.IRegister;

import java.util.ArrayList;

;

public abstract class Person implements IRegister, INotifier, ILogin { //abstract class that has information matches between driverClass and userClass
    private String username;
    private String email;
    private String phone;
    private String password;
    private ArrayList<String> notifications = new ArrayList<>();
    private boolean suspended;
    private INotifier notifier;
    private ILogin login;
    private IRegister register;

    public Person(String username, String email, String phone, String password) {
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.suspended = false;
    }

    public Person() {
    }

    public Person login(String username, String Password){ //login will be implemented in both driver and user
        return login.login(username,password);
    }
    
    public boolean register(Person person){ // will be implemented in driver and user depends on the type of the person (driver,user)
        return register.register(person);
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSuspended(boolean suspended) {
        this.suspended = suspended;
    }

    public void addNotification(String message) {
        notifications.add(message);
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }

    public boolean isSuspended() {
        return suspended;
    }

    public ArrayList<String> getNotifications() {
        return notifications;
    }

    @Override
    public void notify(Person person, String message, Ride ride) {
        notifier.notify(person, message, ride);
    }
}

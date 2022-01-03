package com.example.demo.CONTROL;

public class Rating {

    private User user;
    private int rate;

    public Rating(User user, int rate) {
        this.user = user;
        rate = Math.min(rate, 5);
        rate = Math.max(rate, 1);
        this.rate = rate;
    }

    public User getUser() {
        return user;
    }

    public int getRate() {
        return rate;
    }

    public void rate(Driver driver) {
        driver.addUserRating(this);
    }
}

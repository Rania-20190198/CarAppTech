package com.example.demo.CONTROL;

public class AvgRating {

    public static double calculateAvgRating(Driver driver) {
        double sum = 0;
        for (int i = 0; i < driver.listUserRatings().size(); i++) {
            sum += driver.listUserRatings().get(i).getRate();
        }
        return sum / driver.listUserRatings().size();
    }
}

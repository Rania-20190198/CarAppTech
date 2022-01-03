package com.example.demo.BOUNDARY;

import com.example.demo.CONTROL.Person;
import com.example.demo.CONTROL.Ride;
/**
 *
 * @author rania
 */
public interface INotifier {


    void notify(Person user, String message, Ride ride);


}

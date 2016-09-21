package com.application.teleshopnative;

/**
 * Created by INDIA on 9/19/2016.
 */
public class Address {

    int id;
    String name;
    String addr;
    String ph;

    // Empty constructor
    public Address(){

    }
    // constructor
    public Address(int id, String name, String addr, String ph){
        this.id = id;
        this.name = name;
        this.addr = addr;
        this.ph = ph;
    }

    public String getName() {
        return name;
    }

    public String getaddr() {
        return addr;
    }

    public String getph() {
        return ph;
    }


}

package com.application.teleshopnative;

/**
 * Created by INDIA on 9/15/2016.
 */
public class User {

    //private variables
    int id;
    String name;
    String email;
    String key;

    // Empty constructor
    public User(){

    }
    // constructor
    public User(int id, String name, String email, String key){
        this.id = id;
        this.name = name;
        this.email = email;
        this.key = key;
    }

    // constructor
    public User(String name, String email, String key){
        this.name = name;
        this.email = email;
        this.key = key;
    }
    // getting ID
    public int getID(){
        return this.id;
    }

    // setting id
    public void setID(int id){
        this.id = id;
    }

    // getting name
    public String getName(){
        return this.name;
    }

    // setting name
    public void setName(String name){
        this.name = name;
    }

    // getting phone number
    public String getEmail(){
        return this.email;
    }

    // setting phone number
    public void setEmail(String email){
        this.email = email;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}

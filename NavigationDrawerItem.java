package com.application.teleshopnative;

/**
 * Created by INDIA on 9/13/2016.
 */
public class NavigationDrawerItem {

    //private variables
    int id;
    String name;
    String catID;
    String SUBCat;

    // Empty constructor
    public NavigationDrawerItem(){

    }
    // constructor
    public NavigationDrawerItem(int id, String name, String catID, String SUBCat){
        this.id = id;
        this.name = name;
        this.catID = catID;
        this.SUBCat = SUBCat;
    }

    // constructor
    public NavigationDrawerItem(String name, String catID, String SUBCat){
        this.name = name;
        this.catID = catID;
        this.SUBCat = SUBCat;
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
    public String getCatID(){
        return this.catID;
    }

    // setting phone number
    public void setCatID(String catID){
        this.catID = catID;
    }

    public void setSUBCat(String SUBCat) {
        this.SUBCat = SUBCat;
    }

    public String getSUBCat() {
        return SUBCat;
    }
}

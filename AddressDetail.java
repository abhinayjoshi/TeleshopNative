package com.application.teleshopnative;

/**
 * Created by INDIA on 9/20/2016.
 */
public class AddressDetail {
    private  String add_id;
    private  String name;
    private  String add;
    private  String city;
    private  String pincode;
    private  String mob_no;


    AddressDetail(String s, String s1, String s2, String s3, String s4, String s5){
        name=s1;
        add=s2;
        city=s3;
        pincode=s4;
        mob_no=s5;
        mob_no=s5;

    }

    public void setadd_id(String add_id) {
        this.add_id = add_id;
    }

    public void setname(String name) {
        this.name = name;
    }

    public  void setadd(String add) {
        this.add = add;
    }

    public  void setcity(String city) {
        this.city = city;
    }

    public  void setpincode(String pincode) {
        this.pincode = pincode;
    }

    public  void setmob_no(String mob_no) {
        this.mob_no = mob_no;
    }

    public String getmob_no() {
        return mob_no;
    }

    public String getpincode() {
        return pincode;
    }

    public String getcity() {
        return city;
    }

    public String getadd() {
        return add;
    }

    public String getname() {
        return name;
    }
}

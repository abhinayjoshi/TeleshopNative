package com.application.teleshopnative;

/**
 * Created by INDIA on 9/19/2016.
 */
public class CartItem {

    String vender;
    int id;
    String name;
    String oprice;
    String discount;
    String quan;
    String dprice;
    String img;
    String dlChrge;

    // Empty constructor
    public CartItem() {

    }

    // constructor
    public CartItem( String vender, int id, String name, String oprice, String discount, String quan, String dprice, String img, String dlChrge) {


        this.vender = vender;
        this.id = id;
        this.name = name;
        this.oprice = oprice;
        this.discount = discount;
        this.quan = quan;
        this.dprice = dprice;
        this.img = img;
        this.dlChrge = dlChrge;
    }

    public String getvender() {
        return vender;
    }

    public String getName() {
        return name;
    }

    public String getoprice() {
        return oprice;
    }

    public String getdprice() {
        return dprice;
    }

    public String getdiscount() {
        return discount;
    }

    public String getimg() {
        return img;
    }

    public String getdlChrge() {
        return dlChrge;
    }
}

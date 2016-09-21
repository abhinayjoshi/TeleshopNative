package com.application.teleshopnative;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;


public class PagerAdapter extends FragmentStatePagerAdapter {
    String tabname[];
    String[][] name,dPrice,oPrice,discount,star,imageId,id;

    List<List<SubCatArraylist>> sublist;


    public PagerAdapter(FragmentManager fm, String[] tabName, String[][] id,String[][] prdName, String[][] prdOprice, String[][] prdDprice, String[][] prdDiscount, String[][] prdStar, String[][] prdImages) {
        super(fm);
        this.tabname =tabName;

        name=prdName;
        oPrice=prdOprice;
        dPrice=prdDprice;
        discount=prdDiscount;
        star=prdStar;
        this.id=id;
       imageId=prdImages;


    }

    public PagerAdapter(FragmentManager manager, String[] tabName, List<List<SubCatArraylist>> sublist) {
        super(manager);
        this.tabname =tabName;
this.sublist=sublist;
    }

    @Override
    public Fragment getItem(int position) {




                   Fragment fragment = new GridFragment();
                   Bundle bundle = new Bundle();


                   bundle.putStringArray("prdName", name[position]);
                   bundle.putStringArray("prdOprice", oPrice[position]);
                   bundle.putStringArray("prdDprice", dPrice[position]);
                   bundle.putStringArray("prdDiscount", discount[position]);
                   bundle.putStringArray("prdStar", star[position]);
        bundle.putStringArray("id", id[position]);
                   bundle.putStringArray("imageId", imageId[position]);

                   fragment.setArguments(bundle);



        return fragment;




    }

    @Override
    public int getCount() {
        return tabname.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabname[position];
    }
}

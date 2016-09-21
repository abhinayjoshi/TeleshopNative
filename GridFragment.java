package com.application.teleshopnative;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class GridFragment extends Fragment {
GridView gridView;
    ImageView like;
     int flag=1,lFalg=0;
    private SubCatGridViewAdapter gridAdapter;





    String[] name,dPrice,oPrice,discount,star,imageId,id;
     int likeButton=1;


    public GridFragment()
    {
        super();

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
          View rootView = inflater.inflate(R.layout.fragment_activity, container, false);



        name =  getArguments().getStringArray("prdName");
        oPrice =  getArguments().getStringArray("prdOprice");
        dPrice =  getArguments().getStringArray("prdDprice");
        discount =  getArguments().getStringArray("prdDiscount");
        star =  getArguments().getStringArray("prdStar");
        imageId =  getArguments().getStringArray("imageId");

        id =  getArguments().getStringArray("id");


        gridView = (GridView)rootView.findViewById(R.id.gridView);
        gridAdapter = new SubCatGridViewAdapter(inflater ,id,name,oPrice,dPrice,discount,star,imageId);
        gridView.setAdapter(gridAdapter);

        return rootView;
    }


}

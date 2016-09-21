package com.application.teleshopnative;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;

public class SubCatGridViewAdapter extends BaseAdapter {

    String[] name,dPrice,oPrice,discount,star,imageId,id;
    Context context;

    ImageLoader imageLoader = ImageLoader.getInstance();
    DisplayImageOptions options = new DisplayImageOptions.Builder()
            .showStubImage(R.mipmap.ic_launcher)
            .cacheInMemory()
            .cacheOnDisc()
            .build();
    private ImageLoadingListener imageListener;



    int likeButton=1;
    private static LayoutInflater inflater=null;
    public SubCatGridViewAdapter(LayoutInflater inflater,String[] id, String[] prdName, String[] prdOprice, String[] prdDprice, String[]  prdDiscount, String[] prdStar, String[] prdImages) {

        // TODO Auto-generated constructor stub
        name=prdName;
        oPrice=prdOprice;
        dPrice=prdDprice;
        discount=prdDiscount;
        star=prdStar;
        this.id=id;
        imageId=prdImages;
       this.inflater=inflater;

    }


    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return name.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class Holder
    {
        TextView pName, pOprice,pDprice,pdiscount,pStar;
        ImageView pimg,plike;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        final Holder holder=new Holder();
        View rowView;

rowView = inflater.inflate(R.layout.grid_item_layout, null);

        holder.pName=(TextView) rowView.findViewById(R.id.name);
        holder.pOprice=(TextView) rowView.findViewById(R.id.oPrice);
        holder.pDprice=(TextView) rowView.findViewById(R.id.dPrice);
        holder.pdiscount=(TextView) rowView.findViewById(R.id.discount);
        holder.pStar=(TextView) rowView.findViewById(R.id.star);
        holder.plike=(ImageView) rowView.findViewById(R.id.like);
        holder.pimg=(ImageView) rowView.findViewById(R.id.image);


        if (position <= name.length-1) {

            holder.pName.setText(name[position]);
            holder.pOprice.setText(oPrice[position]);
            holder.pOprice.setPaintFlags(holder.pOprice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.pDprice.setText(dPrice[position]);
            holder.pdiscount.setText(discount[position]);
            holder.pStar.setText(star[position]);

//            ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(context));

            imageLoader.displayImage(
                    imageId[position], holder.pimg,
                    options, imageListener);



         //       holder.pimg.setImageResource(R.drawable.logo);
      //      holder.pimg.setImageResource(imageId[position]);


        }
        rowView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Toast.makeText(v.getContext(), id[position], Toast.LENGTH_SHORT).show();
            }
        });
        holder.plike.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                if(likeButton==1){ holder.plike.setImageResource(R.drawable.like2);likeButton=0;}
                else{ holder.plike.setImageResource(R.drawable.like);likeButton=1;}
            }
        });
        return rowView;
    }

}
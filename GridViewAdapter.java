package com.application.teleshopnative;

import android.content.Context;
import android.content.Intent;
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

public class GridViewAdapter extends BaseAdapter {

    String[] name, dPrice, oPrice, discount, star, rate, dis, imageId;
    Context context;
    int likeButton = 1, lflag;
    public View rowView;
    private static LayoutInflater inflater = null;

    public GridViewAdapter(Products products, String[] prdName, String[] prdDis, String[] prdOprice, String[] prdDprice, String[] prdDiscount, String[] prdStar, String[] prdRate, String[] prdImages, int flag) {

        // TODO Auto-generated constructor stub
        name = prdName;
        oPrice = prdOprice;
        dPrice = prdDprice;
        discount = prdDiscount;
        star = prdStar;
        context = products;
        imageId = prdImages;
        dis = prdDis;
        lflag = flag;
        inflater = (LayoutInflater) context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    public void changeFlag(int flag) {

        // TODO Auto-generated constructor stub
        lflag = flag;


    }

    ImageLoader imageLoader = ImageLoader.getInstance();
    DisplayImageOptions options = new DisplayImageOptions.Builder()
            .showStubImage(R.mipmap.ic_launcher)
            .cacheInMemory()
            .cacheOnDisc()
            .build();
    private ImageLoadingListener imageListener;

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

    public class Holder {
        TextView pName, pOprice, pDprice, pdiscount, pStar, pRate, pDis;
        ImageView img, plike;
    }

    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        final Holder holder = new Holder();
        //View rowView;

        if (lflag == 1) {
            rowView = inflater.inflate(R.layout.grid_item_layout2, null);

        } else {
            rowView = inflater.inflate(R.layout.grid_item_layout, null);
        }

        holder.pName = (TextView) rowView.findViewById(R.id.name);
        holder.pOprice = (TextView) rowView.findViewById(R.id.oPrice);
        holder.pDprice = (TextView) rowView.findViewById(R.id.dPrice);
        if (lflag == 1) {
            holder.pDis = (TextView) rowView.findViewById(R.id.dis);
        }
        holder.pdiscount = (TextView) rowView.findViewById(R.id.discount);
        holder.pStar = (TextView) rowView.findViewById(R.id.star);
        holder.plike = (ImageView) rowView.findViewById(R.id.like);
        holder.img = (ImageView) rowView.findViewById(R.id.image);

        if (position <= name.length) {

            holder.pName.setText(name[position]);
            holder.pOprice.setText(oPrice[position]);
            holder.pDprice.setText(dPrice[position]);
            holder.pOprice.setPaintFlags(holder.pOprice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.pdiscount.setText(discount[position]);
            holder.pStar.setText(star[position]);
            if (lflag == 1) {
                holder.pDis.setText(dis[position]);
            }



            ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(context));

            imageLoader.displayImage(
                    imageId[position], holder.img,
                    options, imageListener);


        }
        rowView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //     Toast.makeText(context, "You Clicked "+name[position], Toast.LENGTH_LONG).show();

                Intent intent = new Intent(context, SingleProduct.class);
                intent.putExtra("id", String.valueOf(position));
                context.startActivity(intent);


            }
        });
        holder.plike.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                if (likeButton == 1) {
                    holder.plike.setImageResource(R.drawable.like2);
                    likeButton = 0;
                } else {
                    holder.plike.setImageResource(R.drawable.like);
                    likeButton = 1;
                }
            }
        });
        return rowView;
    }

}
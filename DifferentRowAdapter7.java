package com.application.teleshopnative;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;

import java.util.ArrayList;

/**
 * Created by INDIA on 9/5/2016.
 */
public class DifferentRowAdapter7 extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    Address adrs;
    ArrayList<CartItem> items;
    final int size;
    int pr=0,del=0,save_prc=0;

    ImageLoader imageLoader = ImageLoader.getInstance();
    DisplayImageOptions options = new DisplayImageOptions.Builder()
            .showStubImage(R.mipmap.ic_launcher)
            .cacheInMemory()
            .cacheOnDisc()
            .build();
    private ImageLoadingListener imageListener;


    public DifferentRowAdapter7(Address adrs, ArrayList<CartItem> items) {

        this.adrs = adrs;
        this.items = items;
        size = items.size();

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;


        if (viewType == 0) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.delivery_user_detail, parent, false);
            return new Addrs(view);
        } else if (viewType == size + 1) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_price_detail, parent, false);
            return new Amount(view);
        } else {

            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_prodetail, parent, false);
            return new Product(view);

        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {




        if (position == 0) {

            ((Addrs) holder).name.setText(adrs.getName());
            ((Addrs) holder).addrs.setText(adrs.getaddr());
            ((Addrs) holder).phNo.setText(adrs.getph());


        } else if (position == size + 1) {


            ((Amount) holder).price.setText(String.valueOf(pr));
            ((Amount) holder).delivery.setText(String.valueOf(del));
            ((Amount) holder).no.setText(String.valueOf(size));
            ((Amount) holder).save.setText(String.valueOf(save_prc));
            ((Amount) holder).total.setText(String.valueOf(pr+del));



        } else {

            CartItem item = items.get(position-1);
            ((Product) holder).seller.setText(item.getvender());
            ((Product) holder).name.setText(item.getName());
            ((Product) holder).oprice.setText(item.getoprice());
            ((Product) holder).oprice.setPaintFlags(((Product) holder).oprice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            ((Product) holder).Dprice.setText(item.getdprice());
            ((Product) holder).discount.setText(item.getdiscount());
            ((Product) holder).delChrge.setText(item.getdlChrge());

            pr += Integer.parseInt(item.getdprice());
            del += Integer.parseInt(item.getdlChrge());
            save_prc+=(Integer.parseInt(item.getoprice())-Integer.parseInt(item.getdprice()));
            Log.e("delprice", String.valueOf(del));


            imageLoader.displayImage(
                    item.getimg(), ((Product) holder).Img,
                    options, imageListener);

//            ((Product) holder).name.setText(item.getName());


        }


    }

    @Override
    public int getItemCount() {

        return size + 2;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }


    public static class Addrs extends RecyclerView.ViewHolder {
        private TextView name, addrs, phNo;
        RelativeLayout ch_add;


        public Addrs(final View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.seller_deliver_to);
            addrs = (TextView) itemView.findViewById(R.id.text);
            phNo = (TextView) itemView.findViewById(R.id.pno);
            ch_add=(RelativeLayout)itemView.findViewById(R.id.change_add);

            ch_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(itemView.getContext(), ChangeAddActivity.class);
                    intent.putExtra("id", 1);
                    itemView.getContext().startActivity(intent);
                }
            });
        }



    }

    public static class Product extends RecyclerView.ViewHolder {
        private TextView Id;
        private TextView name;
        private TextView seller;
        private TextView Dprice;
        private TextView oprice;
        private TextView discount;
        private TextView delChrge;
        private ImageView Img;
        private TextView Quan;

        public Product(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.seller_deliver_to);
            Id = (TextView) itemView.findViewById(R.id.text);
            seller = (TextView) itemView.findViewById(R.id.seller);
            Dprice = (TextView) itemView.findViewById(R.id.prdDprice);
            oprice = (TextView) itemView.findViewById(R.id.prdOprice);
            discount = (TextView) itemView.findViewById(R.id.prdDis);
                delChrge = (TextView) itemView.findViewById(R.id.delivery);
            Img = (ImageView) itemView.findViewById(R.id.img);
            //   Quan = (TextView) itemView.findViewById(R.id.pno);

        }
    }

    public static class Amount extends RecyclerView.ViewHolder {
        private TextView price, delivery, total,no,save;

        public Amount(View itemView) {
            super(itemView);
            price = (TextView) itemView.findViewById(R.id.price);
            delivery = (TextView) itemView.findViewById(R.id.delivery);
            no = (TextView) itemView.findViewById(R.id.no);
            total = (TextView) itemView.findViewById(R.id.totalprice);
            save = (TextView) itemView.findViewById(R.id.save);
        }
    }


}
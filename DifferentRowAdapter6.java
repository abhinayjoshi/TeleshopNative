package com.application.teleshopnative;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by INDIA on 9/5/2016.
 */
public class DifferentRowAdapter6 extends RecyclerView.Adapter<DifferentRowAdapter6.Holder> {

    Context context;
    String s,s1,s2;

    public DifferentRowAdapter6(String s, String s1, String s2) {
        this.s = s;
        this.s1 = s1;
        this.s2 = s2;


    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;

        switch (viewType) {
            case 0:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.payment_detail, parent, false);
                return new  Holder(view);
            case 1:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.price_detail, parent, false);
                return new  Holder(view);

        }
        return null;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {

        switch (position) {

            case 1:

                holder.price.setText(s);
                holder.delivery.setText(s1);
                holder.tprice.setText(s2);


        }


    }

    @Override
    public int getItemCount() {

        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }


    public static class Holder extends RecyclerView.ViewHolder {
        private TextView price, delivery, tprice;

        public Holder(View itemView) {
            super(itemView);

            price = (TextView) itemView.findViewById(R.id.price);
            delivery = (TextView) itemView.findViewById(R.id.delivery);
            tprice = (TextView) itemView.findViewById(R.id.totalprice);



        }

    }


}
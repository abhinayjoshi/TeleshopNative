package com.application.teleshopnative;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AddCardAdapter extends RecyclerView.Adapter<AddCardAdapter.Holder> {


    Context context;
    List<AddressDetail>  addresslist;


    public AddCardAdapter(List<AddressDetail> addresslist) {
this.addresslist=addresslist;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        context = parent.getContext();

        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.add_card, parent, false);
        Holder holder1 = new Holder(view);
        return holder1;
    }

    @Override
    public void onBindViewHolder(final Holder holder, final int position) {

        AddressDetail addressDetail = addresslist.get(position);
       holder.name.setText(addressDetail.getname());
        holder.add.setText(addressDetail.getadd());
        holder.city.setText(addressDetail.getcity());
        holder.pincode.setText(addressDetail.getpincode());
        holder.mobno.setText(addressDetail.getmob_no());





    }

    @Override
    public int getItemCount() {

        return addresslist.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }


    public static class Holder extends RecyclerView.ViewHolder {
        private TextView add_id, add, name,city,pincode,mobno;


        public Holder(View itemView) {
            super(itemView);
            add = (TextView) itemView.findViewById(R.id.address);
            name = (TextView) itemView.findViewById(R.id.name);
            city = (TextView) itemView.findViewById(R.id.city);
            pincode = (TextView) itemView.findViewById(R.id.pincode);
            mobno = (TextView) itemView.findViewById(R.id.mobile);

        }
    }


}
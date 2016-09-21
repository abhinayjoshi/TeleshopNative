package com.application.teleshopnative;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by INDIA on 9/16/2016.
 */
public class FilterAdapter extends RecyclerView.Adapter<FilterAdapter.Holder> {


    List<String> brand = new ArrayList<String>();
    List<Integer> id = new ArrayList<Integer>();

    Context context;
    List<FilterDefaultMultipleListModel> branddata = new ArrayList<FilterDefaultMultipleListModel>();

    public FilterAdapter(List<Integer> id, List<String> data) {


        this.id = id;
        brand = data;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {

        context = parent.getContext();

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.filter_list_val_item_layout, parent, false);


        return new Holder(v);

    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {


        holder.brandName.setText(brand.get(position));


        final FilterDefaultMultipleListModel filterDefaultMultipleListModel = new FilterDefaultMultipleListModel();


        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(filterDefaultMultipleListModel.isChecked())
                {
                    filterDefaultMultipleListModel.setChecked(false);
                }
                else {
                    filterDefaultMultipleListModel.setChecked(true);
                }



            }
        });

        filterDefaultMultipleListModel.setId(id.get(position));


        branddata.add(position, filterDefaultMultipleListModel );

    }


    public  List<FilterDefaultMultipleListModel> Filterbranddata(){

        return branddata;
    }


    @Override
    public int getItemCount() {


        return brand.size();
    }


    public class Holder extends RecyclerView.ViewHolder {

        TextView brandName;
        CheckBox checkBox;


        public Holder(View itemView) {
            super(itemView);


            brandName = (TextView) itemView.findViewById(R.id.txt_item_list_title);
            checkBox = (CheckBox) itemView.findViewById(R.id.cbSelected);



        }
    }
}

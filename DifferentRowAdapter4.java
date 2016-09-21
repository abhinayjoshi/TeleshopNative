package com.application.teleshopnative;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by INDIA on 9/5/2016.
 */
public class DifferentRowAdapter4 extends RecyclerView.Adapter<DifferentRowAdapter4.Holder> {

    public ArrayList<String> prd_size = new ArrayList<String>();

    private int size_no;

    Context context;


    public DifferentRowAdapter4(ArrayList<String> size) {
        prd_size = size;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        context = parent.getContext();

        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.color_size_3, parent, false);
        Holder holder1 = new Holder(view);
        return holder1;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {


        holder.mTitle.setText(prd_size.get(position));

        holder.bg_color.setBackgroundColor(Color.TRANSPARENT);
    }

    @Override
    public int getItemCount() {

        size_no = prd_size.size();
        return prd_size.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }


    public class Holder extends RecyclerView.ViewHolder {

        public TextView mTitle;
        RelativeLayout bg_color;
        String id;


        public Holder(View itemView) {
            super(itemView);
            mTitle = (TextView) itemView.findViewById(R.id.size);
            bg_color = (RelativeLayout) itemView.findViewById(R.id.size_bg);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    for (int i = 0; i < size_no; i++) {

                        if (i == getAdapterPosition()) {

                            bg_color.setBackgroundColor(Color.BLUE);
                        } else {
                            notifyItemChanged(i);

                        }

                    }
                }
            });


        }
    }
}
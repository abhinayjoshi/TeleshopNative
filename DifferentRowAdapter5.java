package com.application.teleshopnative;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextClock;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by INDIA on 9/5/2016.
 */
public class DifferentRowAdapter5 extends RecyclerView.Adapter<DifferentRowAdapter5.Holder> {

    Context context;
    ArrayList<String> details, head, subhead;

    public DifferentRowAdapter5(ArrayList<String> details, ArrayList<String> head, ArrayList<String> subhead) {
        this.details = details;
        this.head = head;
        this.subhead = subhead;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;


        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.all_detail_2, parent, false);
        return new Holder(view);


    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {


        holder.dis1.setText(details.get(0));
        holder.dis2.setText(details.get(1));
        holder.dis3.setText(details.get(2));
        holder.dis4.setText(details.get(3));

        holder.head1.setText(head.get(0));
        holder.head2.setText(head.get(1));
        holder.sub1.setText(subhead.get(0));
        holder.sub2.setText(subhead.get(1));

    }



    @Override
    public int getItemCount() {

        return 1;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }


    public static class Holder extends RecyclerView.ViewHolder {
        private TextView dis1, dis2, dis3, dis4 , head1, head2, sub1, sub2;

        public Holder(View itemView) {
            super(itemView);

            dis1 = (TextView )itemView.findViewById(R.id.kf1);
            dis2 = (TextView )itemView.findViewById(R.id.kf2);
            dis3 = (TextView )itemView.findViewById(R.id.kf3);
            dis4 = (TextView )itemView.findViewById(R.id.kf4);
            head1 = (TextView )itemView.findViewById(R.id.d1);
            head2 = (TextView )itemView.findViewById(R.id.d2);
            sub1 = (TextView )itemView.findViewById(R.id.sd1);
            sub2 = (TextView )itemView.findViewById(R.id.sd2);

        }

    }


}
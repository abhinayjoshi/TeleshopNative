package com.application.teleshopnative;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by INDIA on 9/2/2016.
 */
public class DifferentRowAdapter2  extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<String> data;
    Context context;




    public DifferentRowAdapter2(List<String> data) {
        this.data = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;

        context = parent.getContext();

        switch (viewType) {
            case 0:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.delivery_user_detail, parent, false);
                return new CityViewHolder(view);
            case 1:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.delivery_des, parent, false);
                return new EventViewHolder(view);
            case 2:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.delivery_price, parent, false);
                return new EventViewHolder(view);
            case 3:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.delivery_des, parent, false);
                return new EventViewHolder(view);

        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        String object = data.get(position);
        //    if (object != null) {
        //  switch (object.getType()) {
        //   case detail:
        switch (position) {
            case 0:


                break;
            case 1:


                break;
            case 2:


                break;

            case 3:

                // data = mDataSet.get(position);


                // ArrayList singleSectionItems = dataList.get(position).getAllItemsInSection();


                break;


            case 4:




                break;

        }

    }


    @Override
    public int getItemCount() {
        if (data == null)
            return 0;
        return data.size();
    }

    @Override
    public int getItemViewType(int position) {

        return position;

    }

    public static class CityViewHolder extends RecyclerView.ViewHolder {
        private TextView mTitle;

        public CityViewHolder(View itemView) {
            super(itemView);
            mTitle = (TextView) itemView.findViewById(R.id.text);
        }
    }

    public static class EventViewHolder extends RecyclerView.ViewHolder {
        private TextView mTitle;
        private TextView mDescription;
        protected TextView itemTitle;

        protected String barId;

        protected RecyclerView recycler_view_list;

        protected Button btnMore;

        public EventViewHolder(View itemView) {
            super(itemView);
            mTitle = (TextView) itemView.findViewById(R.id.text);

            this.itemTitle = (TextView) itemView.findViewById(R.id.itemTitle);
            this.recycler_view_list = (RecyclerView) itemView.findViewById(R.id.recycler_view_list);
            this.btnMore = (Button) itemView.findViewById(R.id.btnMore);
            // mDescription = (TextView) itemView.findViewById(R.id.descriptionTextView);
        }
    }
}
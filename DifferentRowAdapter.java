package com.application.teleshopnative;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;


public class DifferentRowAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<String> data;
    public static Context context;




    public DifferentRowAdapter(List<String> data) {
        this.data = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;

        context = parent.getContext();

        switch (viewType) {
            case 0:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_product_1, parent, false);
                return new CityViewHolder(view);
            case 1:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_product_size, parent, false);
                return new EventViewHolder(view);
            case 2:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_product_vender, parent, false);
                return new EventViewHolder(view);
            case 3:
                view = LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.single_product_detail, parent, false);

                return new EventViewHolder(view);

            case 4:

                view = LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.list_item, parent, false);

                return new EventViewHolder(view);

            case 5:
                view = LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.single_product_detail, parent, false);

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

                ((EventViewHolder) holder).type = 1;


                break;
            case 2:


                break;

            case 3:


                ((EventViewHolder) holder).type = 3;
                // data = mDataSet.get(position);

                ((EventViewHolder) holder).relativeLayoutdetails.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        Intent intent = new Intent(context, DetailsActivity.class);
                        context.startActivity(intent);

                    }
                });


                // ArrayList singleSectionItems = dataList.get(position).getAllItemsInSection();


                break;

            case 4:

                ((EventViewHolder) holder).itemTitle.setText("similar");
                //  ((EventViewHolder) holder).barId = data.id;
                final String abc = ((EventViewHolder) holder).barId;
                ((EventViewHolder) holder).btnMore.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        Toast.makeText(v.getContext(), "1", Toast.LENGTH_SHORT).show();


                    }
                });


                List<DataItems> dataItemsList = new ArrayList<>();
                String string[] = object.split(Pattern.quote("#"));
                Log.e("sdkjfgsdhkfg", object);
                for (int j = 3; j < string.length; j++) {

                    String xyz[] = string[j].split(Pattern.quote("@"));
                    DataItems dataItems = new DataItems();
                    dataItems.id = xyz[0];
                    dataItems.img = xyz[1];
                    dataItems.name = xyz[2];
                    dataItems.dPrice = xyz[3];
                    dataItems.oPrice = xyz[4];
                    dataItems.discount = xyz[5];
                    dataItemsList.add(dataItems);

                }

                SectionListDataAdapter itemListDataAdapter = new SectionListDataAdapter(context,dataItemsList);

                ((EventViewHolder) holder).recycler_view_list.setHasFixedSize(true);
                ((EventViewHolder) holder).recycler_view_list.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
                ((EventViewHolder) holder).recycler_view_list.setAdapter(itemListDataAdapter);


                ((EventViewHolder) holder).recycler_view_list.setNestedScrollingEnabled(false);
//                    ((CityViewHolder) holder).mTitle.setText(object.getName());
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

        protected  int type = 0;

        protected String barId;

        protected RecyclerView recycler_view_list;

        protected Button btnMore;


        protected RelativeLayout relativeLayoutdetails;

        public EventViewHolder(View itemView) {
            super(itemView);
            mTitle = (TextView) itemView.findViewById(R.id.text);

            this.relativeLayoutdetails = (RelativeLayout) itemView.findViewById(R.id.relative_layout_details);
            this.itemTitle = (TextView) itemView.findViewById(R.id.itemTitle);
            this.recycler_view_list = (RecyclerView) itemView.findViewById(R.id.recycler_view_list);
            this.btnMore = (Button) itemView.findViewById(R.id.btnMore);



            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    if(type==1) {
                        Intent intent = new Intent(context, SizeActivity.class);
                        context.startActivity(intent);

                    }

                    else
                    Toast.makeText(v.getContext(), String.valueOf(type), Toast.LENGTH_SHORT).show();
                }
            });



            // mDescription = (TextView) itemView.findViewById(R.id.descriptionTextView);
        }
    }
}
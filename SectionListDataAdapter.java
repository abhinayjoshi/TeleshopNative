package com.application.teleshopnative;

/**
 * Created by pratap.kesaboyina on 24-12-2014.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;

import java.util.ArrayList;
import java.util.List;

public class SectionListDataAdapter extends RecyclerView.Adapter<SectionListDataAdapter.SingleItemRowHolder> {

    private List<DataItems> itemsList;
    private Context mContext;

    ImageLoader imageLoader = ImageLoader.getInstance();
    DisplayImageOptions options = new DisplayImageOptions.Builder()
            .showStubImage(R.mipmap.ic_launcher)
            .cacheInMemory()
            .cacheOnDisc()
            .build();
    private ImageLoadingListener imageListener;


    public SectionListDataAdapter(Context context, List<DataItems> itemsList) {
        this.itemsList = itemsList;
        this.mContext = context;
    }

    @Override
    public SingleItemRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_single_card, null);
        SingleItemRowHolder mh = new SingleItemRowHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(SingleItemRowHolder holder, int i) {

        DataItems singleItem = itemsList.get(i);

        holder.Pid = singleItem.id;
        holder.tvTitle.setText(singleItem.name);
        holder.oprice.setText(singleItem.oPrice);
        holder.dprice.setText(singleItem.dPrice);
        holder.discount.setText(singleItem.discount);



        ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(mContext));

        imageLoader.displayImage(
                singleItem.img, holder.itemImage,
                options, imageListener);



       /* Glide.with(mContext)
                .load(feedItem.getImageURL())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .error(R.drawable.bg)
                .into(feedListRowHolder.thumbView);*/
    }

    @Override
    public int getItemCount() {
        return (null != itemsList ? itemsList.size() : 0);
    }

    public class SingleItemRowHolder extends RecyclerView.ViewHolder {

        protected TextView tvTitle;

        protected ImageView itemImage;
        protected TextView dprice;
        protected TextView oprice;
        protected TextView discount;

        protected String Pid;


        public SingleItemRowHolder(View view) {
            super(view);

            this.tvTitle = (TextView) view.findViewById(R.id.title);
            this.itemImage = (ImageView) view.findViewById(R.id.itemImage);
            this.dprice = (TextView) view.findViewById(R.id.dPrice);
            this.oprice = (TextView) view.findViewById(R.id.oPrice);
            this.discount = (TextView) view.findViewById(R.id.discount);


            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    Toast.makeText(v.getContext(), Pid + "dlfjlasdj", Toast.LENGTH_SHORT).show();

                }
            });


        }

    }

}
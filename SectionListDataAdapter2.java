package com.application.teleshopnative;

/**
 * Created by pratap.kesaboyina on 24-12-2014.
 */

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

public class SectionListDataAdapter2 extends RecyclerView.Adapter<SectionListDataAdapter2.SingleItemRowHolder> {

    private ArrayList<SampleData6> itemsList;
    private Context mContext;
    ImageLoader imageLoader = ImageLoader.getInstance();
    DisplayImageOptions options = new DisplayImageOptions.Builder()
            .showStubImage(R.mipmap.ic_launcher)
            .cacheInMemory()
            .cacheOnDisc()
            .build();
    private ImageLoadingListener imageListener;

    public SectionListDataAdapter2(Context context, ArrayList<SampleData6> itemsList) {
        this.itemsList = itemsList;
        this.mContext = context;
    }

    @Override
    public SingleItemRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_single_card6, null);
        SingleItemRowHolder mh = new SingleItemRowHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(SingleItemRowHolder holder, int i) {

        SampleData6 singleItem = itemsList.get(i);

        ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(mContext));


         imageLoader.displayImage
            (singleItem.img, holder.itemImage,
              options,imageListener);

        holder.tvTitle.setText(singleItem.text);
        holder.id = singleItem.id;



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

        protected  String id;


        public SingleItemRowHolder(final View view) {
            super(view);

            this.tvTitle = (TextView) view.findViewById(R.id.tvTitle);
            this.itemImage = (ImageView) view.findViewById(R.id.itemImage);


            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    Intent i = new Intent(view.getContext(), SubCatActivity.class);
                    i.putExtra("id", id);

                    view.getContext().startActivity(i);

                    Toast.makeText(v.getContext(), id, Toast.LENGTH_SHORT).show();

                }
            });


        }

    }

}
package com.application.teleshopnative;

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

/**
 * Created by yqritc on 2015/03/20.
 */
public class Sample4Binder extends DataBinder<Sample4Binder.ViewHolder> {

    private List<SampleData4> mDataSet = new ArrayList<>();

    ImageLoader imageLoader = ImageLoader.getInstance();
    DisplayImageOptions options = new DisplayImageOptions.Builder()
            .showStubImage(R.mipmap.ic_launcher)
            .cacheInMemory()
            .cacheOnDisc()
            .build();
    private ImageLoadingListener imageListener;

    Context mContext;


    public Sample4Binder(DataBindAdapter dataBindAdapter) {
        super(dataBindAdapter);
    }

    @Override
    public ViewHolder newViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.layout_sample4, parent, false);
        mContext = parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void bindViewHolder(final ViewHolder holder, int position) {
        SampleData4 data = mDataSet.get(position);
        holder.id = data.id;


        ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(mContext));

        imageLoader.displayImage(
                data.img, holder.img,
                options, imageListener);

        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(mContext, holder.id, Toast.LENGTH_SHORT).show();

            }
        });


    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public void addAll(List<SampleData4> dataSet) {
        mDataSet.addAll(dataSet);
        notifyBinderDataSetChanged();
    }

    public void clear() {
        mDataSet.clear();
        notifyBinderDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView img;
        String id;

        public ViewHolder(View view) {
            super(view);

            img = (ImageView) view.findViewById(R.id.bnr_img);
        }
    }
}

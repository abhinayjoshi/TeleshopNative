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
public class Sample3Binder extends DataBinder<Sample3Binder.ViewHolder> {

    private List<SampleData3> mDataSet = new ArrayList<>();


    ImageLoader imageLoader = ImageLoader.getInstance();
    DisplayImageOptions options = new DisplayImageOptions.Builder()
            .showStubImage(R.mipmap.ic_launcher)
            .cacheInMemory()
            .cacheOnDisc()
            .build();
    private ImageLoadingListener imageListener;

    Context context;

    public Sample3Binder(DataBindAdapter dataBindAdapter) {
        super(dataBindAdapter);
    }

    @Override
    public ViewHolder newViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.layout_sample3, parent, false);
        context = parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void bindViewHolder(ViewHolder holder, int position) {
        SampleData3 data = mDataSet.get(position);
        holder.mTitleText.setText(data.mTitle);
        holder.mContent.setText(data.mContent);
        holder.id = data.id;

        ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(context));

        imageLoader.displayImage(
                data.url, holder.imageView,
                options, imageListener);

    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public void addAll(List<SampleData3> dataSet) {
        mDataSet.addAll(dataSet);
        notifyBinderDataSetChanged();
    }

    public void clear() {
        mDataSet.clear();
        notifyBinderDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView mTitleText;
        TextView mContent;
        ImageView imageView;

        String id;

        public ViewHolder(View view) {
            super(view);
            mTitleText = (TextView) view.findViewById(R.id.title_type3);
            mContent = (TextView) view.findViewById(R.id.content_type3);
            imageView = (ImageView) view.findViewById(R.id.img);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Toast.makeText(view.getContext(), id, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}

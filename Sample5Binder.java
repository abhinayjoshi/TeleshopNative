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
public class Sample5Binder extends DataBinder<Sample5Binder.ViewHolder> {


    ImageLoader imageLoader = ImageLoader.getInstance();
    DisplayImageOptions options = new DisplayImageOptions.Builder()
            .showStubImage(R.mipmap.ic_launcher)
            .cacheInMemory()
            .cacheOnDisc()
            .build();
    private ImageLoadingListener imageListener;

    Context context;

    private List<SampleData5> mDataSet = new ArrayList<>();

    public Sample5Binder(DataBindAdapter dataBindAdapter) {
        super(dataBindAdapter);
    }

    @Override
    public ViewHolder newViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.layout_sample5, parent, false);

        context = parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void bindViewHolder(ViewHolder holder, int position) {
        SampleData5 data = mDataSet.get(position);

        ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(context));

        imageLoader.displayImage(
                data.mImg, holder.imageView,
                options, imageListener);
        holder.mTitleText.setText(data.mTitle);
        holder.mContent.setText(data.mContent);
        holder.id = data.id;



    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public void addAll(List<SampleData5> dataSet) {
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
            mTitleText = (TextView) view.findViewById(R.id.itemTitle);
            mContent = (TextView) view.findViewById(R.id.bnr_description);
            imageView = (ImageView)view.findViewById(R.id.bnr_img);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Toast.makeText(view.getContext(), id ,Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}

package com.application.teleshopnative;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by yqritc on 2015/03/20.
 */
public class Sample2Binder extends DataBinder<Sample2Binder.ViewHolder> implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {

    private List<List<SampleData2>> mDataSet = new ArrayList<>();

    List<SampleData2> data = new ArrayList<>();

    Context context;

    public Sample2Binder(DataBindAdapter dataBindAdapter) {
        super(dataBindAdapter);
    }

    @Override
    public ViewHolder newViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.layout_sample2, parent, false);

        context = parent.getContext();


        return new ViewHolder(view);
    }


    @Override
    public void bindViewHolder(ViewHolder holder, int position) {
        data = mDataSet.get(position);



        HashMap<String, String> url_maps = new HashMap<String, String>();

        for (int i = 0; i< data.size();i++) {
            url_maps.put(data.get(i).id,data.get(i).img );
        }



        //add your extra information


        for (String name : url_maps.keySet()) {
            DefaultSliderView textSliderView = new DefaultSliderView(context);
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(url_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            //add your extra information


            holder.pager.addSlider(textSliderView);
        }

        holder.pager.setPresetTransformer(SliderLayout.Transformer.Default);
        holder.pager.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        holder.pager.setClipToPadding(false);
        holder.pager.setCustomAnimation(new DescriptionAnimation());
        holder.pager.setDuration(3000);
        holder.pager.addOnPageChangeListener(this);


    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public void addAll(List<List<SampleData2>> dataSet) {
        mDataSet.addAll(dataSet);
        notifyBinderDataSetChanged();
    }

    public void clear() {
        mDataSet.clear();
        notifyBinderDataSetChanged();
    }



    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    @Override
    public void onSliderClick(BaseSliderView slider) {


        Toast.makeText(context,slider.getDescription(), Toast.LENGTH_SHORT).show();
    }



    static class ViewHolder extends RecyclerView.ViewHolder {

        SliderLayout pager;

        public ViewHolder(View view) {
            super(view);
            pager = (SliderLayout) view.findViewById(R.id.slider);
        }
    }
}

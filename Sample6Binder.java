package com.application.teleshopnative;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yqritc on 2015/03/20.
 */
public class Sample6Binder extends DataBinder<Sample6Binder.ViewHolder> {

    private List<List<SampleData6>> mDataSet = new ArrayList<>();

    Context context;

    public Sample6Binder(DataBindAdapter dataBindAdapter) {
        super(dataBindAdapter);
    }

    @Override
    public ViewHolder newViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.layout_sample6, parent, false);

        context = parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void bindViewHolder(ViewHolder holder, int position) {
        List<SampleData6> data = mDataSet.get(position);
        // holder.mTitleText.setText("dfgdfg");
        // holder.mContent.setText("dfgdfgdfg");





        SectionListDataAdapter2 itemListDataAdapter = new SectionListDataAdapter2(context, (ArrayList<SampleData6>) data);

        holder.recycler_view_list.setHasFixedSize(true);
        holder.recycler_view_list.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        holder.recycler_view_list.setAdapter(itemListDataAdapter);

        holder.recycler_view_list.setNestedScrollingEnabled(false);


    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public void addAll(List<List<SampleData6>> dataSet) {
        mDataSet.addAll(dataSet);
        notifyBinderDataSetChanged();
    }

    public void clear() {
        mDataSet.clear();
        notifyBinderDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {


        RecyclerView recycler_view_list;

        public ViewHolder(View view) {
            super(view);

            recycler_view_list = (RecyclerView) view.findViewById(R.id.recycler_view_list);
        }
    }
}

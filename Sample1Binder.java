package com.application.teleshopnative;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yqritc on 2015/03/20.
 */
public class Sample1Binder extends DataBinder<Sample1Binder.ViewHolder> {

    private List<SampleData> mDataSet = new ArrayList<>();
    SampleData data = new SampleData();

    Context context;

    public Sample1Binder(DataBindAdapter dataBindAdapter) {
        super(dataBindAdapter);
    }

    @Override
    public ViewHolder newViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.list_item, parent, false);
        context = parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void bindViewHolder(final ViewHolder itemRowHolder, int position) {
        data = mDataSet.get(position);



       // ArrayList singleSectionItems = dataList.get(position).getAllItemsInSection();

        itemRowHolder.itemTitle.setText(data.Title);
        itemRowHolder.barId = data.id;
        final String abc = itemRowHolder.barId;
        itemRowHolder.btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Intent intent = new Intent(v.getContext(), Products.class);
                intent.putExtra("id", abc);
                intent.putExtra("type", "0");
                context.startActivity(intent);



                Toast.makeText(v.getContext(), abc, Toast.LENGTH_SHORT).show();



            }
        });

        SectionListDataAdapter itemListDataAdapter = new SectionListDataAdapter(context, data.mContent);

        itemRowHolder.recycler_view_list.setHasFixedSize(true);
        itemRowHolder.recycler_view_list.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        itemRowHolder.recycler_view_list.setAdapter(itemListDataAdapter);


        itemRowHolder.recycler_view_list.setNestedScrollingEnabled(false);


       /*  itemRowHolder.recycler_view_list.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        // Disallow ScrollView to intercept touch events.
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        break;
                    case MotionEvent.ACTION_UP:
                        //Allow ScrollView to intercept touch events once again.
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                }
                // Handle RecyclerView touch events.
                v.onTouchEvent(event);
                return true;
            }
        });*/




    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public void addAll(List<SampleData> dataSet) {
        mDataSet.addAll(dataSet);
        notifyBinderDataSetChanged();
    }

    public void clear() {
        mDataSet.clear();
        notifyBinderDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        protected TextView itemTitle;

        protected String barId;

        protected RecyclerView recycler_view_list;

        protected Button btnMore;



        public ViewHolder(View view) {
            super(view);

            this.itemTitle = (TextView) view.findViewById(R.id.itemTitle);
            this.recycler_view_list = (RecyclerView) view.findViewById(R.id.recycler_view_list);
            this.btnMore= (Button) view.findViewById(R.id.btnMore);


        }

    }
}

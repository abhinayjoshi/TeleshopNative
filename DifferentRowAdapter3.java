package com.application.teleshopnative;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;

import java.util.ArrayList;

/**
 * Created by INDIA on 9/5/2016.
 */
public class DifferentRowAdapter3 extends RecyclerView.Adapter<DifferentRowAdapter3.Holder> {

    private final ArrayList<String> color;
    private final ArrayList<String> img;
    private  int img_no;
    Context context;

    ImageLoader imageLoader = ImageLoader.getInstance();
    DisplayImageOptions options = new DisplayImageOptions.Builder()
            .showStubImage(R.mipmap.ic_launcher)
            .cacheInMemory()
            .cacheOnDisc()
            .build();
    private ImageLoadingListener imageListener;



    public DifferentRowAdapter3(ArrayList<String> color, ArrayList<String> img) {
        this.color=color;
        this.img=img;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        context = parent.getContext();


        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.color_size_2, parent, false);
        Holder holder1 =new Holder(view);
        return holder1;
        //return new  Holder(view);

    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.mTitle.setText(color.get(position));

        ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(context));

        imageLoader.displayImage(
                img.get(position), holder.img,
                options, imageListener);


        holder.bg_color.setBackgroundColor(Color.TRANSPARENT);


    }

    @Override
    public int getItemCount() {
img_no=img.size();
        return img.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }



    public class Holder extends RecyclerView.ViewHolder {
        private  RelativeLayout bg_color;
        public TextView mTitle;
        public ImageView img;

        public Holder(View itemView) {
            super(itemView);
            mTitle = (TextView) itemView.findViewById(R.id.color);
            img = (ImageView) itemView.findViewById(R.id.img);
            bg_color = (RelativeLayout) itemView.findViewById(R.id.color_bg);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {




                    for (int i = 0 ; i<img_no; i++){

                        if (i==getAdapterPosition()){

                            bg_color.setBackgroundColor(Color.BLUE);
                        }
                        else {
                            notifyItemChanged(i);

                        }

                    }




                }
            });


        }
    }


}
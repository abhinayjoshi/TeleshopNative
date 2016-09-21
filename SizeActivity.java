package com.application.teleshopnative;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class SizeActivity extends BaseActivity {

    ImageLoader imageLoader = ImageLoader.getInstance();
    DisplayImageOptions options = new DisplayImageOptions.Builder()
            .showStubImage(R.mipmap.ic_launcher)
            .cacheInMemory()
            .cacheOnDisc()
            .build();
    private ImageLoadingListener imageListener;

    TextView title,price, dprice, discount ;

    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_size);

        new InsideScreenData().execute();


    }

    class InsideScreenData extends AsyncTask<Void, Void, String> {


        ProgressDialog pd = null;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();


            // Create a progressdialog

        }


        @Override
        protected String doInBackground(Void... params) {
            // Create an array
            String theResult = "";

            try {
                theResult = new LSVBTBCPSoap().remotereq(4);

            } catch (Exception e) {
                e.printStackTrace();
            }

            return theResult;
        }


        @Override
        protected void onPostExecute(String theResult) {
            super.onPostExecute(theResult);






            data = theResult;
            Toast.makeText(SizeActivity.this, theResult, Toast.LENGTH_SHORT).show();


            String parts[] = theResult.split(Pattern.quote("$"));

            String abc[] = parts[1].split(Pattern.quote("#"));




             imageView = (ImageView)findViewById(R.id.img);


            ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(SizeActivity.this));

            imageLoader.displayImage(
                    abc[0], imageView,
                    options, imageListener);


            title = (TextView) findViewById(R.id.product_name);
            price = (TextView) findViewById(R.id.prdOprice);
            dprice = (TextView) findViewById(R.id.prdDprice);
            discount = (TextView) findViewById(R.id.prdDis);


            title.setText(abc[1]);
            price.setText(abc[3]);
            dprice.setText(abc[2]);
            discount.setText(abc[4]);



            String xyz[] = parts[3].split(Pattern.quote("#"));

            ArrayList<String> size = new ArrayList<String>();

            for (int i=0; i<xyz.length;i++)
            {
                size.add(xyz[i]);

            }

            String str[] = parts[2].split(Pattern.quote("#"));

            ArrayList<String> color = new ArrayList<String>();
            ArrayList<String> img = new ArrayList<String>();

            for (int i=0; i<str.length;i++)
            {

                String str2[] = str[i].split(Pattern.quote("@"));
                img.add(str2[0]);
                color.add(str2[1]);
            }



            DifferentRowAdapter3 adapter = new DifferentRowAdapter3(color,img);
            DifferentRowAdapter4 adapter2 = new DifferentRowAdapter4(size);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SizeActivity.this, OrientationHelper.HORIZONTAL, false);
            RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
            mRecyclerView.setLayoutManager(linearLayoutManager);
            // mRecyclerView.setItemAnimator(new DefaultItemAnimator());
            mRecyclerView.setAdapter(adapter);

            LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(SizeActivity.this, OrientationHelper.HORIZONTAL, false);
            RecyclerView mRecyclerView2 = (RecyclerView) findViewById(R.id.recyclerView2);
            mRecyclerView2.setLayoutManager(linearLayoutManager2);
            // mRecyclerView.setItemAnimator(new DefaultItemAnimator());
            mRecyclerView2.setAdapter(adapter2);



        }


    }





    @Override
    protected boolean useDrawerToggle() {
        return false;
    }


    @Override
    public void onBackPressed() {
        //handle the back press :D close the drawer first and if the drawer is closed close the activity

        super.onBackPressed();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        if (item.getItemId() == android.R.id.home) {

            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }


}

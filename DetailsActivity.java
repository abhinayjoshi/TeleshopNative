package com.application.teleshopnative;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class DetailsActivity extends BaseActivity {



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
        setContentView(R.layout.activity_details);




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
                theResult = new LSVBTBCPSoap().remotereq(5);

            } catch (Exception e) {
                e.printStackTrace();
            }

            return theResult;
        }


        @Override
        protected void onPostExecute(String theResult) {
            super.onPostExecute(theResult);


            data = theResult;


            String parts[] = theResult.split(Pattern.quote("$"));

            String abc[] = parts[0].split(Pattern.quote("#"));




            imageView = (ImageView)findViewById(R.id.img);


            ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(DetailsActivity.this));

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



            String xyz2[] = parts[1].split(Pattern.quote("#"));

            ArrayList<String> details = new ArrayList<String>();

            for (int i=0; i<xyz2.length;i++)
            {


                details.add(xyz2[i]);

            }



            String xyz[] = parts[2].split(Pattern.quote("#"));

            ArrayList<String> head = new ArrayList<String>();
            ArrayList<String> subhead = new ArrayList<String>();

            for (int i=0; i<xyz.length;i++)
            {

                String str[] = xyz[i].split(Pattern.quote("@"));


                head.add(str[0]);
                subhead.add(str[1]);

            }



            DifferentRowAdapter5 adapter = new DifferentRowAdapter5(details, head, subhead);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(DetailsActivity.this, OrientationHelper.VERTICAL, false);
            RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
            mRecyclerView.setLayoutManager(linearLayoutManager);
            // mRecyclerView.setItemAnimator(new DefaultItemAnimator());
            mRecyclerView.setAdapter(adapter);



        }


    }


    @Override
    protected void onStart() {
        super.onStart();



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

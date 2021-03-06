package com.application.teleshopnative;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Delivery extends BaseActivity {


    int str1 = 3, str2;
    String data;

RelativeLayout relativeLayout ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery);


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
                theResult = new LSVBTBCPSoap().remotereq(str1);

            } catch (Exception e) {
                e.printStackTrace();
            }

            return theResult;
        }


        @Override
        protected void onPostExecute(String theResult) {
            super.onPostExecute(theResult);


            List<String> singleProductItemList = new ArrayList<>();


            data = theResult;

            String parts[] = theResult.split(Pattern.quote("$"));


            for (int i = 0; i < parts.length; i++) {


                singleProductItemList.add(parts[i]);

            }



            //        Toast.makeText(Products.this, theResult, Toast.LENGTH_SHORT).show();


            DifferentRowAdapter2 adapter = new DifferentRowAdapter2(singleProductItemList);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Delivery.this, OrientationHelper.VERTICAL, false);
            RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
            mRecyclerView.setLayoutManager(linearLayoutManager);
            // mRecyclerView.setItemAnimator(new DefaultItemAnimator());
            relativeLayout = (RelativeLayout ) findViewById(R.id.payment);
            relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(Delivery.this, PaymentActivity.class);
                    startActivity(intent);

                }
            });


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

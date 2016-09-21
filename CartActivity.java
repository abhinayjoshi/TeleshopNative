package com.application.teleshopnative;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class CartActivity extends BaseActivity {



    RelativeLayout relativeLayout;
    TextView tprice;
    int price, delcharge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);


        relativeLayout = (RelativeLayout)findViewById(R.id.payment);
        tprice =(TextView) findViewById(R.id.totalprice);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CartActivity.this, PaymentActivity.class);
                startActivity(intent);
            }
        });

        new CartData().execute();

    }


    @Override
    protected void onStart() {
        super.onStart();



    }




    private class CartData extends AsyncTask<Void, Void, String> {


        ProgressDialog pd = null;
        private String data;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();


            // Create a progressdialog

        }


        @Override
        protected String doInBackground(Void... params) {
            // Create an array
            String theResult = "";
            String zero = "0";

//            try {



                theResult = new CartSOAP().remotereq();


//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }


            return theResult;
        }


        @Override
        protected void onPostExecute(String theResult) {
            super.onPostExecute(theResult);


            Log.e("jhasdgfjsdgf", theResult);

            String parts[] = theResult.split(Pattern.quote("["));


            String abc[] = parts[0].split(Pattern.quote("^"));

            Address adrs = new Address(Integer.parseInt(abc[0]),abc[1],abc[2],abc[3]);


            String xyz[] = parts[1].split(Pattern.quote("$"));


            ArrayList<CartItem> items = new ArrayList<>();

            for (int i=0; i < xyz.length ;i++){

                 String asd[] = xyz[i].split(Pattern.quote("^"));


                CartItem cartItem = new CartItem(asd[0], Integer.parseInt(asd[1]), asd[2], asd[3], asd[4], asd[5], asd[6], asd[7], asd[8]);
                delcharge+=Integer.parseInt(asd[8]);
                price+=Integer.parseInt(asd[6]);
                items.add(cartItem);
            }



            tprice.setText(String.valueOf(delcharge+price));
            DifferentRowAdapter7 adapter = new DifferentRowAdapter7(adrs,items);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(CartActivity.this, OrientationHelper.VERTICAL, false);
            RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
            mRecyclerView.setLayoutManager(linearLayoutManager);
            // mRecyclerView.setItemAnimator(new DefaultItemAnimator());
            mRecyclerView.setAdapter(adapter);





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

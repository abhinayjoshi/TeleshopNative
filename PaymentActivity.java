package com.application.teleshopnative;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import java.util.regex.Pattern;

public class PaymentActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

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
                theResult = new LSVBTBCPSoap().remotereq(6);

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



            String xyz[] = parts[1].split(Pattern.quote("#"));

            DifferentRowAdapter6 adapter = new DifferentRowAdapter6(xyz[0],xyz[1],xyz[2]);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(PaymentActivity.this, OrientationHelper.VERTICAL, false);
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

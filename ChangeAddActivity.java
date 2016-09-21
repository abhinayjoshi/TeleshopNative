package com.application.teleshopnative;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class ChangeAddActivity extends BaseActivity {

    String theResult = "";
    int id;
    CardView address;



   // ArrayList<AddressDetail> Addresslist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_add);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        id = bundle.getInt("id");


        new AddrressData().execute();

        address = (CardView)findViewById(R.id.add_address);
        address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ChangeAddActivity.this, NewAddressActivity.class);
                startActivity(intent);
            }
        });
    }

    class AddrressData extends AsyncTask<Void, Void, String> {


        ProgressDialog pd = null;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();


            // Create a progressdialog

        }


        @Override
        protected String doInBackground(Void... params) {
            // Create an array
            String zero = "0", one = "1", three = "3";

//            try {

            theResult = new ShippingAddressSoap().remotereq(id);


//            } catch (Exception e) {
//                e.printStackTrace();
//            }

            return theResult;
        }


        @Override
        protected void onPostExecute(String theResult) {
            super.onPostExecute(theResult);


            Toast.makeText(ChangeAddActivity.this, theResult, Toast.LENGTH_SHORT).show();


            Log.e("Filterresultpart2", theResult);

            String fsplit[] = theResult.split(Pattern.quote("$"));


            List<AddressDetail> Addresslist = new ArrayList<AddressDetail>();

            for (int i = 0; i < fsplit.length; i++) {
                String Ssplit[] = fsplit[i].split(Pattern.quote("^"));

                AddressDetail addressDetail = new AddressDetail(Ssplit[0],Ssplit[1],Ssplit[2],Ssplit[3],Ssplit[4],Ssplit[5] );


//                addressDetail.setadd_id(Ssplit[0]);
//                addressDetail.setname(Ssplit[1]);
//                addressDetail.setadd(Ssplit[2]);
//                addressDetail.setcity(Ssplit[3]);
//                addressDetail.setpincode(Ssplit[4]);
//                addressDetail.setmob_no(Ssplit[5]);
                Log.e("qwe1"+i+"  ", (addressDetail.getname()));
                Log.e("qwe2"+i+"  ", (addressDetail.getadd()));
                Log.e("qwe3"+i+"  ", addressDetail.getcity());
                Log.e("qwe4"+i+"  ", (addressDetail.getpincode()));
                Log.e("qwe5"+i+"  ", (addressDetail.getmob_no()));

                //Addresslist.add(i,addressDetail);
                Addresslist.add(addressDetail);


                Log.e("addressdetail"+i+"  ", String.valueOf(addressDetail.getname()));
            }

            for(int i=0;i<fsplit.length;i++){
            AddressDetail addressDetail = Addresslist.get(i);
            Log.e("addressssdetail"+i+"  ", (addressDetail.getname()));
            Log.e("addressdssetail"+i+"  ", (addressDetail.getadd()));
            Log.e("addressdetssail"+i+"  ", addressDetail.getcity());
            Log.e("addressdetassil"+i+"  ", (addressDetail.getpincode()));
            Log.e("addressdetassil"+i+"  ", (addressDetail.getmob_no()));
            }


            AddCardAdapter adapter = new AddCardAdapter(Addresslist);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ChangeAddActivity.this, OrientationHelper.VERTICAL, false);
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

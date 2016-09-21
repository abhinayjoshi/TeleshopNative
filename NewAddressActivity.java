package com.application.teleshopnative;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class NewAddressActivity extends BaseActivity implements AdapterView.OnItemSelectedListener {
    EditText new_name, pno, email, city, address, pincode, landmark;
    Spinner state, district, country;
    RelativeLayout save;
    String theResult = "", add_detail, theResult2 = "";
    int[] state_id, dis_id;
    int id = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_address);

        new_name = (EditText) findViewById(R.id.name);
        pno = (EditText) findViewById(R.id.ph_no);
        email = (EditText) findViewById(R.id.email);
        city = (EditText) findViewById(R.id.city);
        state = (Spinner) findViewById(R.id.state);
        district = (Spinner) findViewById(R.id.district);
        country = (Spinner) findViewById(R.id.country);
        address = (EditText) findViewById(R.id.address);
        pincode = (EditText) findViewById(R.id.pincode);
        landmark = (EditText) findViewById(R.id.landmark);

        save = (RelativeLayout) findViewById(R.id.deliver);


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //         add_detail= String.valueOf(new_name.getText())+"#"+ String.valueOf(pno.getText())+"#"+  String.valueOf(email.getText())+ "#"+ String.valueOf(city.getText())+"#"+  String.valueOf(state.getText())+ "#"+ String.valueOf(district.getText())+"#"+  String.valueOf(country.getText())+ "#"+ String.valueOf(address.getText())+"#"+  String.valueOf(pincode.getText())+ "#"+ String.valueOf(landmark.getText());
                Log.e("addressstring", add_detail);

                theResult2 = new NewAddressSoap().remotereq(id, add_detail);
                Log.e("finalresultresult", theResult);
                Toast.makeText(NewAddressActivity.this, theResult, Toast.LENGTH_SHORT).show();


//                Intent intent = new Intent(NewAddressActivity.this, PaymentActivity.class);
//                startActivity(intent);
            }
        });

        new NewAddressData().execute();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //String item = parent.getItemAtPosition(position).toString();
        String item = String.valueOf(state_id[position]);

        theResult2 = new StateDistrictVSoap().remotereq("dist", item);


        String fsplit[] = theResult2.split(Pattern.quote("$"));
        //dis_id = new int[fsplit.length];

        for (int k = 0; k < fsplit.length; k++) {

            String Ssplit[] = fsplit[k].split(Pattern.quote("#"));
            for (int j = 0; j < Ssplit.length; j++) {
//    Log.e("cxsghgfdfdfd", j+" "+Ssplit[j]);
//}
                if (j == 1) {


//                    Log.e("cxsghgfdfdfd", Ssplit[j]);

                }
                Log.e("cxsghgfdfdfd", String.valueOf(Ssplit.length)+"   "+String.valueOf(j));
            }
//            dis_id[i]= Integer.parseInt(Ssplit[0]);
//            categories.add(Ssplit[1]);

        }


//        // Showing selected spinner item
//        List<String> categories = new ArrayList<String>();
//
//
//        String fsplit[] = theResult2.split(Pattern.quote("$"));
//        dis_id = new int[fsplit.length];
//
//        for(int i=0;i<fsplit.length;i++){
//
//            String Ssplit[] = fsplit[i].split(Pattern.quote("#"));
////            dis_id[i]= Integer.parseInt(Ssplit[0]);
////            categories.add(Ssplit[1]);
//
//        }
//
//        Log.e("ghjhhgjhgjh", String.valueOf(dis_id));
//
//
//       // district.setOnItemSelectedListener(NewAddressActivity.this);
//
//        ArrayAdapter<String> stateAdapter = new ArrayAdapter<String>(NewAddressActivity.this, android.R.layout.simple_spinner_item, categories);
//
//        // Drop down layout style - list view with radio button
//        stateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//
//        // attaching data adapter to spinner
//        district.setAdapter(stateAdapter);
//
//

        Log.e("cxsdfdfd", theResult2);
        Toast.makeText(parent.getContext(), "Selected: " + theResult2, Toast.LENGTH_LONG).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    class NewAddressData extends AsyncTask<Void, Void, String> {


        ProgressDialog pd = null;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();


            // Create a progressdialog

        }


        @Override
        protected String doInBackground(Void... params) {

            try {


                theResult = new StateDistrictVSoap().remotereq("state", "");


            } catch (Exception e) {
                e.printStackTrace();
            }

            return theResult;
        }


        @Override
        protected void onPostExecute(String theResult) {
            super.onPostExecute(theResult);


            Log.e("ijojjojiojioj", theResult);
            List<String> categories = new ArrayList<String>();


            String fsplit[] = theResult.split(Pattern.quote("$"));
            state_id = new int[fsplit.length];

            for (int i = 0; i < fsplit.length; i++) {

                String Ssplit[] = fsplit[i].split(Pattern.quote("#"));
                state_id[i] = Integer.parseInt(Ssplit[0]);
                categories.add(Ssplit[1]);

            }

            Log.e("ghjhhgjhgjh", String.valueOf(state_id));


            state.setOnItemSelectedListener(NewAddressActivity.this);

            ArrayAdapter<String> stateAdapter = new ArrayAdapter<String>(NewAddressActivity.this, android.R.layout.simple_spinner_item, categories);

            // Drop down layout style - list view with radio button
            stateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            // attaching data adapter to spinner
            state.setAdapter(stateAdapter);

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

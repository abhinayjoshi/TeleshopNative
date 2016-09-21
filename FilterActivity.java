package com.application.teleshopnative;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class FilterActivity extends BaseActivity {


    List<FilterDefaultMultipleListModel> brandData = new ArrayList<FilterDefaultMultipleListModel>();
    Button filter;


    String brand = "", price, discount,sort;
    int prd_id;
    String prd_type;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        // Setup the new range seek bar
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        prd_id = bundle.getInt("id");
        sort = bundle.getString("sort");
        prd_type = bundle.getString("type");

//        prd_id= Integer.parseInt(id);
        Toast.makeText(FilterActivity.this, "product type(filter) :" + prd_type, Toast.LENGTH_SHORT).show();


        new FilterData().execute();

        filter = (Button) findViewById(R.id.btn_filter);

    }


    private class FilterData extends AsyncTask<Void, Void, String> {


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

            if (prd_type.equals(zero)) {

                theResult = new LSVBTBCPInitialBrandsSOAP().remotereq(prd_id);

            } else {

                theResult = new SubcatBrandsSOAP().remotereq(prd_id);

            }
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }


            return theResult;
        }


        @Override
        protected void onPostExecute(String theResult) {
            super.onPostExecute(theResult);


            Toast.makeText(FilterActivity.this, theResult, Toast.LENGTH_LONG).show();


            String parts[] = theResult.split(Pattern.quote("$"));

            List<String> data = new ArrayList<String>();
            final List<Integer> id = new ArrayList<Integer>();

            for (int i = 0; i < parts.length; i++) {


                String abc[] = parts[i].split(Pattern.quote("#"));

                id.add(i, Integer.valueOf(abc[0]));
                data.add(i, abc[1]);

            }

            final FilterAdapter adapter = new FilterAdapter(id, data);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(FilterActivity.this, OrientationHelper.VERTICAL, false);
            RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view_filter);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setAdapter(adapter);


            final RangeSeekBar<Integer> rangeSeekBar1 = new RangeSeekBar<Integer>(FilterActivity.this);
            final RangeSeekBar<Integer> rangeSeekBar2 = new RangeSeekBar<Integer>(FilterActivity.this);
            // Set the range
            rangeSeekBar1.setRangeValues(0, 50000);
            rangeSeekBar1.setSelectedMinValue(20);
            rangeSeekBar1.setSelectedMaxValue(40000);


            // Set the range
            rangeSeekBar2.setRangeValues(0, 100);
            rangeSeekBar2.setSelectedMinValue(20);
            rangeSeekBar2.setSelectedMaxValue(80);

            // Add to layout
            final LinearLayout layout1 = (LinearLayout) findViewById(R.id.seekbar_placeholder1);
            layout1.addView(rangeSeekBar1);

            LinearLayout layout2 = (LinearLayout) findViewById(R.id.seekbar_placeholder2);
            layout2.addView(rangeSeekBar2);


            filter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    brandData = adapter.Filterbranddata();
                    int size = brandData.size();
                    size = size - 1;

                    for (int i = 0; i < brandData.size(); i++) {

                        FilterDefaultMultipleListModel abc = brandData.get(i);


                        if (abc.isChecked()) {


                            brand = brand + String.valueOf(abc.getId());

                            brand = brand + ",";

                        }

                    }
                    //if (brand != null && brand.length() > 0 && brand.charAt(brand.length()-1)==',') {
                    if (brand != null && brand.length() > 0 && brand.charAt(brand.length() - 1) == ',') {
                        brand = brand.substring(0, brand.length() - 1);
                    }

                    price = rangeSeekBar1.getSelectedMinValue() + "," + rangeSeekBar1.getSelectedMaxValue();
                    discount = rangeSeekBar2.getSelectedMinValue() + "," + rangeSeekBar2.getSelectedMaxValue();

                    Toast.makeText(FilterActivity.this, brand + "\n" + price + "\n" + discount, Toast.LENGTH_SHORT).show();
                    if (prd_type.equals("0")) {
                        prd_type = "1";
                    }
                    else  if (prd_type.equals("2")) {
                        prd_type = "3";
                    }
String empty="";
                    Intent intent = new Intent(FilterActivity.this, Products.class);
                    intent.putExtra("id", String.valueOf(prd_id));
                    intent.putExtra("sort", sort);
                    intent.putExtra("price", price);
                    if(brand.equals(empty)){
                    intent.putExtra("brand", "0");}
                    else{
                        intent.putExtra("brand", brand);
                    }
                    intent.putExtra("discount", discount);
                    intent.putExtra("type", prd_type);

                    startActivity(intent);


                }
            });


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

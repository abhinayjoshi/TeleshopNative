package com.application.teleshopnative;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.balysv.materialripple.MaterialRippleLayout;
import java.util.regex.Pattern;


public class Products extends BaseActivity {

    int  str2 ;


    private GridView gridView;
    ImageView like;
    MaterialRippleLayout cLayout;
    int flag=1, lFalg=0;
    private GridViewAdapter gridAdapter;
    public static String[] prdDis ;
    public static String[] prdName;
    public static String[] prdOprice;
    public  String[] prdDprice;
    public String[] prdDiscount;
    public static String[] prdStar;
    public static String[] prdID;
    public String[] prdImages;


    String theResult = "";
    String two = "2";




    MaterialRippleLayout l1,l2,l3,l4,l5;

    String type,sort,price,brand,discount,save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String id = bundle.getString("id");
        sort = bundle.getString("sort");
        price = bundle.getString("price");
        brand = bundle.getString("brand");
        discount = bundle.getString("discount");
        type = bundle.getString("type");





   //     Toast.makeText(this, "Products Activity :- " + id, Toast.LENGTH_SHORT).show();
        Toast.makeText(Products.this, "type is : "+type, Toast.LENGTH_SHORT).show();
Log.e("typeproduct",type);

        str2 = Integer.parseInt(id);



        cLayout=(MaterialRippleLayout)findViewById(R.id.clayout);
        gridView = (GridView) findViewById(R.id.gridView);
        like = (ImageView) findViewById(R.id.like);


        MaterialRippleLayout materialRippleLayout = (MaterialRippleLayout)findViewById(R.id.sort);
        materialRippleLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                final Dialog dialog = new Dialog(Products.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog);
                dialog.show();

                l1 = (MaterialRippleLayout)dialog.findViewById(R.id.textView1);
                l2 = (MaterialRippleLayout)dialog.findViewById(R.id.textView2);
                l3 = (MaterialRippleLayout)dialog.findViewById(R.id.textView3);
                l4 = (MaterialRippleLayout)dialog.findViewById(R.id.textView4);
                l5 = (MaterialRippleLayout)dialog.findViewById(R.id.textView5);


                l1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        sort="PD";
                        save=type;
                        type="5";
                        new InsideScreenData().execute();
                        dialog.dismiss();
                    }
                });

                l2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        sort="PA";
                        save=type;
                        type="5";
                        new InsideScreenData().execute();
                        dialog.dismiss();
                    }
                });

                l3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        sort="SD";
                        save=type;
                        type="5";
                        new InsideScreenData().execute();
                        dialog.dismiss();
                    }
                });

                l4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        sort="DaD";
                        save=type;
                        type="5";
                        new InsideScreenData().execute();
                        dialog.dismiss();
                    }
                });

                l5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        sort="DiD";
                        save=type;
                        type="5";
                        new InsideScreenData().execute();
                        dialog.dismiss();

                    }
                });







            }

        });


        MaterialRippleLayout materialRippleLayout2 = (MaterialRippleLayout)findViewById(R.id.filter);
        materialRippleLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Toast.makeText(Products.this, "id pass : "+str2, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(Products.this, FilterActivity.class);
                intent.putExtra("id", str2);
                intent.putExtra("type", type);
                intent.putExtra("sort", sort);
                startActivity(intent);
                finish();



            }
        });


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
            String zero = "0",one="1",three="3";

//            try {


                if(type.equals(zero)) {
                    theResult = new LSVBTBCPSoap().remotereq(str2);
                }

                else if (type.equals(two)){


                    theResult = new SubcatSoap().remotereq(str2);

                }

                else if (type.equals(one)){
                    // theResult = new LSVBTBCPSoap().remotereq(str2);

                     theResult = new FilterDataLSVSoap().remotereq(str2,brand,price,discount,sort);
                    type="0";

                }
                else if (type.equals(three)){

                    theResult = new FilterDataFDLBVSoap().remotereq(str2,brand,price,discount,sort);
                    type="2";

                }
                else{

                    if(save.equals(two)){
                        theResult = new FilterDataFDLBVSoap().remotereq(str2,brand,price,discount,sort);
                        type=save;
                    }else
                    {
                        theResult = new FilterDataLSVSoap().remotereq(str2,brand,price,discount,sort);
                        type=save;
                    }


                }

//            } catch (Exception e) {
//                e.printStackTrace();
//            }

            return theResult;
        }


        @Override
        protected void onPostExecute(String theResult) {
            super.onPostExecute(theResult);


       Toast.makeText(Products.this, theResult, Toast.LENGTH_SHORT).show();



            Log.e("Filterresultpart2",theResult);

if(theResult!="") {
    String fsplit[] = theResult.split(Pattern.quote("^"));

    //data = theResult;


    String parts[] = fsplit[1].split(Pattern.quote("$"));

    Log.e("part length", String.valueOf(parts.length));
    Log.e("part 2 asd1", fsplit[1]);

    prdDis = new String[parts.length];
    prdName = new String[parts.length];
    prdOprice = new String[parts.length];
    prdDprice = new String[parts.length];
    prdDiscount = new String[parts.length];
    prdStar = new String[parts.length];
    prdID = new String[parts.length];
    prdImages = new String[parts.length];


    Log.e("asjdajsdaaa", fsplit[0]);
    for (int i = 0; i < parts.length; i++) {


        //  Toast.makeText(Products.this, parts[i], Toast.LENGTH_SHORT).show();
        String abc[] = parts[i].split(Pattern.quote("#"));

        prdName[i] = abc[2];
        prdDis[i] = abc[3];
        prdOprice[i] = abc[4];
        prdDprice[i] = abc[5];
        prdDiscount[i] = abc[6];
        prdStar[i] = abc[7];
        prdID[i] = abc[0];
        prdImages[i] = abc[1];


    }


    gridAdapter = new GridViewAdapter(Products.this, prdName, prdDis, prdOprice, prdDprice, prdDiscount, prdStar, prdID, prdImages, lFalg);
    gridView.setAdapter(gridAdapter);

    cLayout.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (flag == 1) {
                //gridView.setColumnWidth(400);
                gridView.setNumColumns(1);
                lFalg = 1;
                flag = 0;
                gridAdapter.changeFlag(lFalg);
                gridView.setAdapter(gridAdapter);

            } else {

                //gridView.setColumnWidth(250);
                gridView.setNumColumns(2);
                lFalg = 0;
                flag = 1;
                gridAdapter.changeFlag(lFalg);
                gridView.setAdapter(gridAdapter);

            }


        }
    });

}


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

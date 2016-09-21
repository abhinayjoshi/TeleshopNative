package com.application.teleshopnative;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class SubCatActivity extends BaseActivity {
        ViewPager  pager;
        TabLayout tabLayout;

        String theResult = "";


//    String []tabName;



List<List<SubCatArraylist>> sublist=new ArrayList<>();

        List<SubCatArraylist> datalist=new ArrayList<>();


        String []tabName;

        public static String[][] prdName ;
        public static String[][] prdOprice;
        public static String[][] prdDprice ;
        public static String[] [] prdDiscount ;
        public static String[][] prdStar ;
        public static String[][] prdImages;
        public static String[][] prdId;





//        String []tabName= new String[]{"Men", "Women","others","there","hii"};
//
//public static String[][] prdName = {{"Kanvas Katha Tote(Ecru)", "Butterflies  Bag(Beige)", "Be for Bag Tote(Multicolor)", "Fostelo Hand-held Bag(White)", "YOURS LUGGAGE Hand-held Bag(Maroon)", "Vintage Hand-held Hand-held Bag(NA)", "Kanvas Katha Hand-held Tote(Ecru)", "Butterflies Hand-held Bag(Pink)", "JG Shoppe Hand-held Bag(Red-365)", "Butterflies Hand-held Bag(Beige)", "Butterflies Bag(Peach, Grey)", "Kanvas Katha Tote(Ecru)", "Lavie Messenger Hand-held Bag(Tan)", "Rosemary Hand-held Bag(PEACH)", "Diana Korr Bag(Green)"},
//        { "Butterflies  Bag(Beige)2", "Be for Bag Tote(Multicolor)2", "Fostelo Hand-held Bag(White)2", "YOURS LUGGAGE Hand-held Bag(Maroon)2", "Vintage Hand-held Hand-held Bag(NA)2", "Kanvas Katha Hand-held Tote(Ecru)2", "Butterflies Hand-held Bag(Pink)2", "JG Shoppe Hand-held Bag(Red-365)2", "Butterflies Hand-held Bag(Beige)2", "Butterflies Bag(Peach, Grey)2", "Kanvas Katha Tote(Ecru)2", "Lavie Messenger Hand-held Bag(Tan)2", "Rosemary Hand-held Bag(PEACH)2", "Diana Korr Bag(Green)2"},
//        { "Be for Bag Tote(Multicolor)3", "Fostelo Hand-held Bag(White)3", "YOURS LUGGAGE Hand-held Bag(Maroon)3", "Vintage Hand-held Hand-held Bag(NA)3", "Kanvas Katha Hand-held Tote(Ecru)3", "Butterflies Hand-held Bag(Pink)3", "JG Shoppe Hand-held Bag(Red-365)3", "Butterflies Hand-held Bag(Beige)3", "Butterflies Bag(Peach, Grey)3", "Kanvas Katha Tote(Ecru)3", "Lavie Messenger Hand-held Bag(Tan)3", "Rosemary Hand-held Bag(PEACH)3", "Diana Korr Bag(Green)3"},
//        {  "Fostelo Hand-held Bag(White)4", "YOURS LUGGAGE Hand-held Bag(Maroon)4", "Vintage Hand-held Hand-held Bag(NA)4", "Kanvas Katha Hand-held Tote(Ecru)4", "Butterflies Hand-held Bag(Pink)4", "JG Shoppe Hand-held Bag(Red-465)4", "Butterflies Hand-held Bag(Beige)4", "Butterflies Bag(Peach, Grey)4", "Kanvas Katha Tote(Ecru)4", "Lavie Messenger Hand-held Bag(Tan)4", "Rosemary Hand-held Bag(PEACH)4", "Diana Korr Bag(Green)4"},
//        {  "YOURS LUGGAGE Hand-held Bag(Maroon)5", "Vintage Hand-held Hand-held Bag(NA)5", "Kanvas Katha Hand-held Tote(Ecru)5", "Butterflies Hand-held Bag(Pink)5", "JG Shoppe Hand-held Bag(Red-565)5", "Butterflies Hand-held Bag(Beige)5", "Butterflies Bag(Peach, Grey)5", "Kanvas Katha Tote(Ecru)5", "Lavie Messenger Hand-held Bag(Tan)5", "Rosemary Hand-held Bag(PEACH)5", "Diana Korr Bag(Green)5"}};
//public static String[][] prdOprice = {{"299", "3,299", "399", "1,799", "999", "799", "299", "2,799", "1,420", "2,199", "4,299", "299", "4,490", "999", "3,350"},
//        { "3,2992", "3992", "1,7992", "9992", "7992", "2992", "2,7992", "1,4202", "2,1992", "4,2992", "2992", "4,4902", "9992", "3,3502"},
//        { "3993", "1,7993", "9993", "7993", "2993", "2,7993", "1,4203", "2,1993", "4,2993", "2993", "4,4903", "9993", "3,3502"},
//        { "1,7994", "9994", "7994", "2994", "2,7994", "1,4204", "2,1994", "4,2994", "2994", "4,4904", "9994", "3,3502"},
//        { "9995", "7995", "2995", "2,7995", "1,4205", "2,1995", "4,2995", "2995", "4,4905", "9995", "3,3502"}};
//public static String[][] prdDprice = {{"167", "1,699", "199", "699", "299", "295", "166", "1,499", "695", "1,499", "1,599", "155", "1,751", "499", "1,699"},
//        { "1,6992", "1992", "6992", "2992", "2952", "1662", "1,4992", "6952", "1,4992", "1,5992", "1552", "1,7512", "4992", "1,699"},
//        { "1993", "6993", "2993", "2953", "1663", "1,4993", "6953", "1,4993", "1,5993", "1553", "1,7513", "4993", "1,699"},
//        { "6994", "2994", "2954", "1664", "1,4994", "6954", "1,4994", "1,5994", "1554", "1,7514", "4994", "1,699"},
//        { "2995", "2955", "1665", "1,4995", "6955", "1,4995", "1,5995", "1555", "1,7515", "4995", "1,699"}};
//public static String[] [] prdDiscount = {{"44", "48", "50", "61", "70", "63", "44", "46", "51", "31", "62", "48", "61", "50", "49"},
//        { "482", "502", "612", "702", "632", "442", "462", "512", "312", "622", "482", "612", "502", "49"},
//        { "503", "613", "703", "633", "443", "463", "513", "313", "623", "483", "613", "503", "49"},
//        { "614", "704", "634", "444", "464", "514", "314", "624", "484", "614", "504", "49"},
//        { "705", "635", "445", "465", "515", "315", "625", "485", "615", "505", "49"}};
//public static String[][] prdStar ={{"2.3","3.4","5.0","4.5","1.4","2.2","3.3","2.3","3.4","5.0","4.5","1.4","2.2","3.3","4.5"},
//        {"3.41","5.01","4.51","1.41","2.21","3.31","2.31","3.41","5.01","4.51","1.41","2.21","3.31","4.5"},
//        {"5.02","4.52","1.42","2.22","3.32","2.32","3.42","5.02","4.52","1.42","2.22","3.32","4.5"},
//        {"4.53","1.43","2.23","3.33","2.33","3.43","5.03","4.53","1.43","2.23","3.33","4.5"},
//        {"1.44","2.24","3.34","2.34","3.44","5.04","4.54","1.44","2.24","3.34","4.5"}};
//
//public static int[][] prdImages = {{R.drawable.a1, R.drawable.a2, R.drawable.a3, R.drawable.a4, R.drawable.a5, R.drawable.a6, R.drawable.a7, R.drawable.a8, R.drawable.a9, R.drawable.a10, R.drawable.a11, R.drawable.a4, R.drawable.a1, R.drawable.a2, R.drawable.a3},
//        {R.drawable.a1, R.drawable.a2, R.drawable.a3, R.drawable.a4, R.drawable.a5, R.drawable.a6, R.drawable.a7, R.drawable.a8, R.drawable.a9, R.drawable.a10, R.drawable.a11, R.drawable.a4, R.drawable.a1, R.drawable.a2},
//        {R.drawable.a1, R.drawable.a2, R.drawable.a3, R.drawable.a4, R.drawable.a5, R.drawable.a6, R.drawable.a7, R.drawable.a8, R.drawable.a9, R.drawable.a10, R.drawable.a11, R.drawable.a4, R.drawable.a1},
//        {R.drawable.a1, R.drawable.a2, R.drawable.a3, R.drawable.a4, R.drawable.a5, R.drawable.a6, R.drawable.a7, R.drawable.a8, R.drawable.a9, R.drawable.a10, R.drawable.a11, R.drawable.a4},
//        {R.drawable.a1, R.drawable.a2, R.drawable.a3, R.drawable.a4, R.drawable.a5, R.drawable.a6, R.drawable.a7, R.drawable.a8, R.drawable.a9, R.drawable.a10, R.drawable.a11}};


        int id;

        @Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_cat);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        id = Integer.parseInt(bundle.getString("id"));

        pager= (ViewPager) findViewById(R.id.view_pager);
        tabLayout= (TabLayout) findViewById(R.id.tab_layout);


        new  InsideScreenData().execute();

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
//            try {

                                theResult = new SubCatDataSOAP().remotereq(id);


//            } catch (Exception e) {
//                e.printStackTrace();
//            }

                        return theResult;
                }


                @Override
                protected void onPostExecute(String theResult) {
                        super.onPostExecute(theResult);



                        Log.e("catwithsubcat",theResult);

                        String Fsplit[] = theResult.split(Pattern.quote("^"));
                        tabName=new String[Integer.parseInt(Fsplit[0])];
                         String Sesplit[] = Fsplit[1].split(Pattern.quote("$"));

                        prdName =new String[Sesplit.length][];
                        prdOprice =new String[Sesplit.length][];
                        prdDprice =new String[Sesplit.length][];
                        prdDiscount =new String[Sesplit.length][];
                        prdStar =new String[Sesplit.length][];
                        prdImages =new String[Sesplit.length][];
                        prdId =new String[Sesplit.length][];

                        for(int r1=0;r1<Sesplit.length;r1++){


                                String Thsplit[] = Sesplit[r1].split(Pattern.quote("["));

                                 Log.e("thsplit[0]",Thsplit[0]);

                                tabName[r1] = Thsplit[0];


                                        String Fosplit[] = Thsplit[1].split(Pattern.quote("@"));

                                prdName[r1] = new String[Fosplit.length];
                                prdOprice[r1] = new String[Fosplit.length];
                                prdDprice[r1] = new String[Fosplit.length];
                                prdDiscount[r1] = new String[Fosplit.length];
                                prdStar[r1] = new String[Fosplit.length];
                                prdImages[r1] = new String[Fosplit.length];
                                prdId[r1] = new String[Fosplit.length];


                                for(int r2=0;r2<Fosplit.length;r2++) {


                                        String Fisplit[] = Fosplit[r2].split(Pattern.quote("#"));
                                        prdName[r1][r2]=Fisplit[2];
                                        prdId[r1][r2]=Fisplit[0];
                                        prdOprice[r1][r2]=Fisplit[3];
                                        prdDprice[r1][r2]=Fisplit[4];
                                        prdDiscount[r1][r2]=Fisplit[5];
                                        prdStar[r1][r2]=Fisplit[6];
                                       prdImages[r1][r2]= Fisplit[1];


                                }
    //                                           SubCatArraylist sublist = new SubCatArraylist();
//
//                                                sublist.setId(Fisplit[0]);
//                                                sublist.setImage(Fisplit[1]);
//                                                sublist.setName(Fisplit[2]);
//                                                sublist.setDel(Fisplit[3]);
//                                                sublist.setOprice(Fisplit[4]);
//                                                sublist.setDiscount(Fisplit[6]);
//                                                sublist.setDprice(Fisplit[5]);
//                                                sublist.setRate(Fisplit[7]);
//
  //                                             datalist.add(r3,sublist);



//                                sublist.add(r1,datalist);
                        }










                        FragmentManager manager=getSupportFragmentManager();
                        PagerAdapter adapter=new PagerAdapter(manager,tabName, prdId, prdName, prdOprice,prdDprice, prdDiscount,prdStar,  prdImages) ;


                        pager.setAdapter(adapter);
                        pager.setOffscreenPageLimit(tabName.length);

                        tabLayout.setupWithViewPager(pager);
                        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
                        tabLayout.setTabsFromPagerAdapter(adapter);



                }


                }


        }






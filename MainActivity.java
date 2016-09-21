package com.application.teleshopnative;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.ExpandableDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileSettingDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import android.widget.TextView;

public class MainActivity extends BaseActivity {


    private static final int PROFILE_SETTING = 1;

    //save our header or result
    private AccountHeader headerResult = null;
    private Drawer result = null;

    private IProfile profile;

    private static final String TAG = MainActivity.class.getSimpleName();


    private AppBarLayout mToolbarContainer;
    private int mToolbarHeight;


    CustomPagerAdapter mCustomPagerAdapter;
    ViewPager mViewPager;

    String data;
    int flag1 = 0, flag2 = 0, flag3 = 0, flag4 = 0, flag5 = 0, flag6 = 0, flag7 = 0;


    int layoutOrder[];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //   setContentView(R.layout.activity_main);

        // Handle Toolbar


        //   mToolbarContainer = (AppBarLayout) findViewById(R.id.app_bar);


        //    new HomeScreenData().execute();


        //---------------------------------------------------------------------------------------


        //buildHeader(true, null);
        //result.setHeader(headerResult.getView());
        //headerResult.setDrawer(result);
    }

    @Override
    protected boolean useSearchBar() {
        return true;
    }

    @Override
    protected boolean fixToolBar() {
        return true;
    }


    class HomeScreenData extends AsyncTask<Void, Void, String> {


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
                theResult = new HomeScreenSOAP().remotereq();

            } catch (Exception e) {
                e.printStackTrace();
            }

            return theResult;
        }


        @Override
        protected void onPostExecute(String theResult) {
            super.onPostExecute(theResult);


            data = theResult;
            Log.e("sdlkfjsdijf", theResult);
            initRecyclerView();


            //     String movie_string = db.getAllRating();


        }


    }


    @Override
    public void onResume() {  // After a pause OR at startup
        super.onResume();

        setContentView(R.layout.activity_main);

        mToolbarContainer = (AppBarLayout) findViewById(R.id.app_bar);

        new HomeScreenData().execute();


        //Refresh your stuff here
    }

    private void initRecyclerView() {
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);


        SampleEnumMapAdapter adapter = new SampleEnumMapAdapter();


        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        List<SampleData> dataSet1 = new ArrayList<>();
        List<List<SampleData2>> dataSet2 = new ArrayList<>();
        List<SampleData3> dataSet3 = new ArrayList<>();
        List<SampleData4> dataSet4 = new ArrayList<>();
        List<SampleData5> dataSet5 = new ArrayList<>();
        List<List<SampleData6>> dataSet6 = new ArrayList<>();
        List<SampleData7> dataSet7 = new ArrayList<>();


        String[] parts = data.split(Pattern.quote("$"));

        layoutOrder = new int[parts.length];


        for (int i = 0; i < parts.length; i++) {


            String abc[] = parts[i].split(Pattern.quote("#"));


            if (abc[0].equals("1")) {

                flag1 = 1;

                SampleData data = new SampleData();


                data.id = abc[1];
                data.Title = abc[2];

                // type should be defined here of index "#"
                data.mContent = new ArrayList<DataItems>();

                for (int j = 4; j < abc.length; j++) {

                    String xyz[] = abc[j].split(Pattern.quote("@"));
                    DataItems dataItems = new DataItems();
                    dataItems.id = xyz[0];
                    dataItems.img = xyz[1];
                    dataItems.name = xyz[2];
                    dataItems.dPrice = xyz[3];
                    dataItems.oPrice = xyz[4];
                    dataItems.discount = xyz[5];
                    data.mContent.add(dataItems);
                }
                dataSet1.add(data);
                layoutOrder[i] = Integer.parseInt(abc[0]);
            } else if (abc[0].equals("2")) {


                flag2 = 1;

                List<SampleData2> datapager = new ArrayList<>();
                for (int j = 1; j < abc.length; j++) {

                    String xyz[] = abc[j].split(Pattern.quote("@"));
                    SampleData2 data2 = new SampleData2();
                    data2.id = xyz[0];
                    Log.e("Part 2", xyz[1]);
                    data2.img = xyz[1];
                    datapager.add(data2);

                }
                dataSet2.add(datapager);
                layoutOrder[i] = Integer.parseInt(abc[0]);
            } else if (abc[0].equals("3")) {

                flag3 = 1;
                SampleData3 data3 = new SampleData3();
                data3.id = abc[1];
                data3.url = abc[2];
                data3.mTitle = abc[3];
                Log.e("Part 3", parts[i]);
                data3.mContent = abc[4];
                dataSet3.add(data3);
                layoutOrder[i] = Integer.parseInt(abc[0]);

            } else if (abc[0].equals("4")) {

                flag4 = 1;

                SampleData4 data4 = new SampleData4();
                data4.id = abc[1];
                Log.e("Part 4", parts[i]);
                data4.img = abc[2];
                dataSet4.add(data4);
                layoutOrder[i] = Integer.parseInt(abc[0]);

            } else if (abc[0].equals("5")) {


                flag5 = 1;
                SampleData5 data5 = new SampleData5();
                data5.id = abc[1];
                data5.mImg = abc[2];
                Log.e("Part 5", parts[i]);
                data5.mTitle = abc[3];
                data5.mContent = abc[4];
                dataSet5.add(data5);
                layoutOrder[i] = Integer.parseInt(abc[0]);

            } else if (abc[0].equals("6")) {


                flag6 = 1;

                List<SampleData6> datacat = new ArrayList<>();
                for (int j = 1; j < abc.length; j++) {

                    String xyz[] = abc[j].split(Pattern.quote("@"));
                    SampleData6 data6 = new SampleData6();
                    data6.id = xyz[0];
                    data6.img = xyz[1];
                    Log.e("Part 6", parts[i]);
                    data6.text = xyz[2];
                    datacat.add(data6);

                }
                dataSet6.add(datacat);

                layoutOrder[i] = Integer.parseInt(abc[0]);


            } else if (abc[0].equals("7")) {

                flag7 = 1;

                SampleData7 data = new SampleData7();


                data.id = abc[1];
                data.Title = abc[2];

                // type should be defined here of index "#"
                data.mContent = new ArrayList<DataItems>();

                for (int j = 3; j < abc.length; j++) {

                    String xyz[] = abc[j].split(Pattern.quote("@"));
                    DataItems dataItems = new DataItems();
                    dataItems.id = xyz[0];
                    dataItems.img = xyz[1];
                    dataItems.name = xyz[2];
                    dataItems.dPrice = xyz[3];
                    dataItems.oPrice = xyz[4];
                    dataItems.discount = xyz[5];
                    data.mContent.add(dataItems);
                }
                dataSet7.add(data);
                layoutOrder[i] = Integer.parseInt(abc[0]);

            } else {
                // Log.e("gdfgdfg", String.valueOf(abc[0]));
                layoutOrder[i] = Integer.parseInt(abc[0]); // Database Error Zone. Stay out of this. Its not ur fault. U r awesome.
            }


        }

        adapter.setOrder(layoutOrder);
        if (flag1 == 1)
            adapter.setSample1Data(dataSet1);
        if (flag2 == 1)
            adapter.setSample2Data(dataSet2);
        if (flag3 == 1)
            adapter.setSample3Data(dataSet3);
        if (flag4 == 1)
            adapter.setSample4Data(dataSet4);
        if (flag5 == 1)
            adapter.setSample5Data(dataSet5);
        if (flag6 == 1)
            adapter.setSample6Data(dataSet6);
        if (flag7 == 1)
            adapter.setSample7Data(dataSet7);

        recyclerView.addOnScrollListener(new HidingScrollListener(this) {

            @Override
            public void onMoved(int distance) {
                mToolbarContainer.setTranslationY(-distance);
            }

            @Override
            public void onShow() {
                mToolbarContainer.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2)).start();
            }

            @Override
            public void onHide() {
                mToolbarContainer.animate().translationY(-mToolbarHeight).setInterpolator(new AccelerateInterpolator(2)).start();
            }

        });
    }


    @Override
    public void onBackPressed() {
        //handle the back press :D close the drawer first and if the drawer is closed close the activity
        if (result != null && result.isDrawerOpen()) {
            result.closeDrawer();
        } else {
            super.onBackPressed();
        }
    }

}
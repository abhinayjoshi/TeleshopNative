package com.application.teleshopnative;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;


import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.holder.BadgeStyle;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.ExpandableDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileSettingDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.mikepenz.materialdrawer.model.interfaces.Nameable;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;


public class BaseActivity extends AppCompatActivity {


    private static final int PROFILE_SETTING = 1;

    //save our header or result
    private AccountHeader headerResult = null;
    public Drawer result = null;

    private IProfile profile;

    private static final String TAG = MainActivity.class.getSimpleName();


    AppBarLayout appBarLayout;
    private int mToolbarHeight;


    String data;
    int backFlag = 0;


    int layoutOrder[];

    CoordinatorLayout fullLayout;
    Toolbar toolbar;
    private ActionBarDrawerToggle drawerToggle;


    private Menu menu;
    private Bundle outState;


    @Override
    public void setContentView(@LayoutRes int layoutResID) {


        fullLayout = (CoordinatorLayout) getLayoutInflater().inflate(R.layout.activity_base, null);
        /**
         * {@link FrameLayout} to inflate the child's view. We could also use a {@link android.view.ViewStub}
         */
        FrameLayout activityContainer = (FrameLayout) fullLayout.findViewById(R.id.activity_content);
        getLayoutInflater().inflate(layoutResID, activityContainer, true);

        /**
         * Note that we don't pass the child's layoutId to the parent,
         * instead we pass it our inflated layout.
         */
        super.setContentView(fullLayout);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        toolbar.setLogo(R.drawable.logo);


        appBarLayout = (AppBarLayout) findViewById(R.id.app_bar);

        if (useToolbar()) {
            setSupportActionBar(toolbar);


        } else {

            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.MATCH_PARENT,
                    RelativeLayout.LayoutParams.MATCH_PARENT
            );
            params.setMargins(0, 0, 0, 0);
            activityContainer.setLayoutParams(params);


            appBarLayout.setVisibility(View.GONE);
        }


        setUpNavView();


    }


    protected boolean useToolbar() {
        return true;
    }

    protected boolean useSearchBar() {
        return false;
    }

    protected void setUpNavView() {


        DatabaseHandler db = new DatabaseHandler(getBaseContext());


        User user;
        user = db.getUser();


        profile = new ProfileDrawerItem().withName(user.getName()).withEmail(user.getEmail()).withIcon(getResources().getDrawable(R.mipmap.ic_launcher));


        // Create the AccountHeader        build
        buildHeader(false);


        result = new DrawerBuilder()
                .withActivity(this)
                .withSavedInstance(outState)
                .withAccountHeader(headerResult) //set the AccountHeader we created earlier for the header
                .addDrawerItems(
                        new PrimaryDrawerItem().withName(R.string.drawer_item_home).withIdentifier(50).withIcon(FontAwesome.Icon.faw_home),
                        new DividerDrawerItem())

                .build();


        for (int i = 0; i < db.getNavCount(); i++) {

            NavigationDrawerItem navigationDrawerItem = db.getNavDrawerItem(i + 1);

            ExpandableDrawerItem item1 = new ExpandableDrawerItem().withName(navigationDrawerItem.getName()).withTextColorRes(R.color.accent).withIdentifier(Long.parseLong(navigationDrawerItem.getCatID())).withArrowColorRes(R.color.colorAccent).withSelectable(false);


            if (navigationDrawerItem.getSUBCat().equals("null")) {

                result.addItem(new DividerDrawerItem());

            } else {


                String abc[] = navigationDrawerItem.getSUBCat().split(Pattern.quote("["));

                for (int j = 0; j < abc.length; j++) {

                    String xyz[] = abc[j].split(Pattern.quote("@"));


                    char[] chars = xyz[1].toLowerCase().toCharArray();
                    boolean found = false;
                    for (int k = 0; k < chars.length; k++) {
                        if (!found && Character.isLetter(chars[k])) {
                            chars[k] = Character.toUpperCase(chars[k]);
                            found = true;
                        } else if (Character.isWhitespace(chars[k]) || chars[k] == '.' || chars[k] == '\'') { // You can add other chars here
                            found = false;
                        }
                    }


                    PrimaryDrawerItem item2 = new SecondaryDrawerItem().withName(String.valueOf(chars)).withIdentifier(Long.parseLong(xyz[0])).withLevel(2);
                    item1.withSubItems(item2);
                    item2.withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                        @Override
                        public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {


                        //    Toast.makeText(getBaseContext(), String.valueOf(drawerItem.getIdentifier()), Toast.LENGTH_SHORT).show();



                            Intent intent = new Intent(getBaseContext(), Products.class);
                            intent.putExtra("id", String.valueOf(drawerItem.getIdentifier()));
                          //  Toast.makeText(BaseActivity.this,"id: "+ String.valueOf(drawerItem.getIdentifier()),Toast.LENGTH_LONG).show();
                            intent.putExtra("type", "2");
                            BaseActivity.this.startActivity(intent);
                            return false;


                        }
                    });



                }


                result.addItem(item1);


            }
        }

        result.addItem(new DividerDrawerItem());
        result.addItem(new SecondaryDrawerItem().withName("Offer Zone").withIdentifier(51).withIcon(FontAwesome.Icon.faw_tag).withIconColorRes(R.color.nav).withTextColorRes
                (R.color.nav).withBadge("22").withBadgeStyle(new BadgeStyle().withTextColor(Color.WHITE).withColorRes(R.color.md_red_700)));
        result.addItem(new SecondaryDrawerItem().withName("Refer & Earn").withIdentifier(52).withIcon(FontAwesome.Icon.faw_money).withTextColorRes(R.color.nav).withIconColorRes
                (R.color.nav));
        result.addItem(new SecondaryDrawerItem().withName("My Rewards").withIdentifier(53).withIcon(FontAwesome.Icon.faw_diamond).withTextColorRes(R.color.nav).withIconColorRes
                (R.color.nav));
        result.addItem(new DividerDrawerItem());
        result.addItem(new SecondaryDrawerItem().withName("Notification").withIdentifier(54).withSelectedIconColor(Color.RED).withTextColorRes(R.color.nav).withIconColorRes
                (R.color.nav).withIcon(new IconicsDrawable(this, FontAwesome.Icon.faw_bell)));
        result.addItem(new SecondaryDrawerItem().withName("My Cart").withIdentifier(55).withIcon(FontAwesome.Icon.faw_shopping_cart).withTextColorRes(R.color.nav).withIconColorRes
                (R.color.nav));
        result.addItem(new DividerDrawerItem());
        result.addItem(new SecondaryDrawerItem().withName("My Account").withTextColorRes(R.color.colorPrimary).withIconColorRes(R.color.colorPrimary).withIcon
                (FontAwesome.Icon.faw_cog).withIdentifier(10));
        result.addItem(new SecondaryDrawerItem().withName("My Orders").withIdentifier(56).withIcon(FontAwesome.Icon.faw_github).withTextColorRes(R.color.colorPrimary).withIconColorRes
                (R.color.colorPrimary)
        );
        result.addItem(new DividerDrawerItem());


//        result.getActionBarDrawerToggle().setDrawerIndicatorEnabled(false);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //   getSupportActionBar().setDisplayHomeAsUpEnabled(false);
//        result.getActionBarDrawerToggle().setDrawerIndicatorEnabled(true);
        if (useDrawerToggle()) { // use the hamburger menu

            result.setToolbar(BaseActivity.this, toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            result.getActionBarDrawerToggle().setDrawerIndicatorEnabled(true);
        } else if (useToolbar() && getSupportActionBar() != null) {
            // Use home/back button instead
            backFlag = 1;
            // result.getActionBarDrawerToggle().setDrawerIndicatorEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        if (fixToolBar()) {
            AppBarLayout.LayoutParams params =
                    (AppBarLayout.LayoutParams) toolbar.getLayoutParams();
            params.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL
                    | AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS);

        }

        if (useSearchBar()) {

            ViewStub stub = (ViewStub) findViewById(R.id.viewStub);
            stub.setLayoutResource(R.layout.widget_search_bar);
            View inflated = stub.inflate();
            CardView cardView = (CardView) inflated.findViewById(R.id.search_box_collapsed);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//
//
                    Intent intent = new Intent(BaseActivity.this, SearchActivity.class);
                    startActivity(intent);
                }
            });


        }


    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //add the values which need to be saved from the drawer to the bundle
        outState = result.saveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Toast.makeText(BaseActivity.this, "Clicked!", Toast.LENGTH_SHORT);
                return true;

            case R.id.action_cart:
                Intent intent = new Intent(BaseActivity.this, CartActivity.class);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.base, menu);
        return true;
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


    protected boolean useDrawerToggle() {
        return true;
    }

    protected boolean fixToolBar() {
        return false;
    }


    private void buildHeader(boolean compact) {
        // Create the AccountHeader
        headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.lg)
                .withCompactStyle(compact)
                .addProfiles(
                        profile
                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean current) {
                        //sample usage of the onProfileChanged listener
                        Toast.makeText(getBaseContext(), "Pressed", Toast.LENGTH_SHORT).show();

                        //false if you have not consumed the event and it should close the drawer
                        return false;
                    }
                })
                .build();
    }


}

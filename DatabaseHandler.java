package com.application.teleshopnative;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by INDIA on 7/14/2016.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "TeleshopData";

    // Contacts table name
    private static final String TABLE_NAV_DRAWER = "NavigationDrawer";
    private static final String TABLE_USER = "User";

    // Contacts Table Columns names

    private static final String KEY_NAME = "name";
    private static final String KEY_ID = "id";
    private static final String KEY_CAT_ID = "cat_id";
    private static final String KEY_SUB_CAT = "sub_cat";



    private static final String KEY_EMAIL = "email";
    private static final String KEY_USER = "user_key";
    private static final String KEY_USER_ID = "user_key_id";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_NAVIGATION_DRAWER_TABLE = "CREATE TABLE " + TABLE_NAV_DRAWER + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_CAT_ID + " TEXT," + KEY_SUB_CAT + " TEXT" + ")";
        db.execSQL(CREATE_NAVIGATION_DRAWER_TABLE);

        String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT," + KEY_USER_ID + " TEXT,"
                + KEY_EMAIL + " TEXT," + KEY_USER + " TEXT" + ")";
        db.execSQL(CREATE_USER_TABLE);


    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAV_DRAWER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);

        // Create tables again
        onCreate(db);
    }


    public int getNavCount() {
        int num = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            String QUERY = "SELECT * FROM " + TABLE_NAV_DRAWER;
            Cursor cursor = db.rawQuery(QUERY, null);
            num = cursor.getCount();
            db.close();
            return num;
        } catch (Exception e) {
            Log.e("error", e + "");
        }
        return 0;
    }


    public int getUserCount() {
        int num = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            String QUERY = "SELECT * FROM " + TABLE_USER;
            Cursor cursor = db.rawQuery(QUERY, null);
            num = cursor.getCount();
            db.close();
            return num;
        } catch (Exception e) {
            Log.e("error", e + "");
        }
        return 0;
    }



    public void addNavDrawerItem(int id ,String string, String cat_id, String sub_cat) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, id);
        values.put(KEY_NAME, string);
        values.put(KEY_CAT_ID, cat_id);
        values.put(KEY_SUB_CAT, sub_cat);
        // Movie Name
        // Inserting Row
        db.insert(TABLE_NAV_DRAWER, null, values);
        db.close(); // Closing database connection
    }

    public void addUser(String name, String user_id, String email, String key) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_NAME, name);
        values.put(KEY_USER_ID, user_id);
        values.put(KEY_EMAIL, email);
        values.put(KEY_USER, key);
        // Movie Name
        // Inserting Row
        db.insert(TABLE_USER, null, values);
        db.close(); // Closing database connection
    }

    // Getting single contact


    public void deleteNavDrawerItem() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAV_DRAWER, KEY_ID + " = ?", new String[]{"6"});
        db.close();
    }


    public void deleteUser() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USER,  KEY_ID + " = ?", new String[]{String.valueOf(1)});
        db.close();
    }



    // Getting All
    public List<NavigationDrawerItem> getNavDrawerItems()  {
        List<NavigationDrawerItem> NavList = new ArrayList<NavigationDrawerItem>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAV_DRAWER;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                NavigationDrawerItem navDrawerItem = new NavigationDrawerItem();
                navDrawerItem.setID(Integer.parseInt(cursor.getString(0)));
                navDrawerItem.setName(cursor.getString(1));
                navDrawerItem.setCatID(cursor.getString(2));
                navDrawerItem.setSUBCat(cursor.getString(3));
                // Adding contact to list
                NavList.add(navDrawerItem);
            } while (cursor.moveToNext());
        }

        // return contact list
        return NavList;
    }


    // Updating single navItem
    public void updateNavItems(int id, String string, String cat_id, String sub_cat) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_ID, id);
        values.put(KEY_NAME, string);
        values.put(KEY_CAT_ID, cat_id);
        values.put(KEY_SUB_CAT, sub_cat);


        // updating row
        db.update(TABLE_NAV_DRAWER, values, KEY_ID + " = ?",
                new String[]{String.valueOf(id)});
    }

    public void updateUser(String name, String user_id, String email, String key) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_NAME, name);
        values.put(KEY_USER_ID, user_id);
        values.put(KEY_EMAIL, email);
        values.put(KEY_USER, key);


        // updating row
        db.update(TABLE_USER, values, KEY_ID + " = ?",
                new String[]{"1"});
    }



    // Getting single Nav
    public NavigationDrawerItem getNavDrawerItem(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAV_DRAWER, new String[] { KEY_ID,
                        KEY_NAME, KEY_CAT_ID, KEY_SUB_CAT }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();


        // return contact
        assert cursor != null;
        return new NavigationDrawerItem(cursor.getString(1),
                cursor.getString(2), cursor.getString(3));
    }


    public User getUser() {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_USER, new String[] {
                        KEY_ID, KEY_NAME, KEY_USER_ID, KEY_EMAIL, KEY_USER }, KEY_ID + "=?",
                new String[] { "1" }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();


        // return contact
        assert cursor != null;
        return new User(Integer.parseInt(cursor.getString(2)),cursor.getString(1),
                cursor.getString(3), cursor.getString(4));
    }



}
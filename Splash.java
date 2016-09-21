package com.application.teleshopnative;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.regex.Pattern;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        new SplashData().execute();
    }


    class SplashData extends AsyncTask<Void, Void, String> {


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
                theResult = new NavDrawerSOAP().remotereq();

            } catch (Exception e) {
                e.printStackTrace();
            }

            return theResult;
        }


        @Override
        protected void onPostExecute(String theResult) {
            super.onPostExecute(theResult);


            DatabaseHandler db = new DatabaseHandler(Splash.this);
            String xyz[] ;

            String abc[] = theResult.split(Pattern.quote("$"));


            if (db.getNavCount() == 0) {

                for (int j = 0; j < abc.length; j++) {

                    xyz = abc[j].split(Pattern.quote("#"));





//                    char[] chars = xyz[1].toLowerCase().toCharArray();
//                    boolean found = false;
//                    for (int i = 0; i < chars.length; i++) {
//                        if (!found && Character.isLetter(chars[i])) {
//                            chars[i] = Character.toUpperCase(chars[i]);
//                            found = true;
//                        } else if (Character.isWhitespace(chars[i]) || chars[i] == '.' || chars[i] == '\'') { // You can add other chars here
//                            found = false;
//                        }
//                    }

                    db.addNavDrawerItem(j + 1, xyz[1], xyz[0], xyz[2]);

                }

            } else {

                for (int j = 0; j < abc.length; j++) {

                    xyz = abc[j].split(Pattern.quote("#"));


                    char[] chars = xyz[1].toLowerCase().toCharArray();
                    boolean found = false;
                    for (int i = 0; i < chars.length; i++) {
                        if (!found && Character.isLetter(chars[i])) {
                            chars[i] = Character.toUpperCase(chars[i]);
                            found = true;
                        } else if (Character.isWhitespace(chars[i]) || chars[i] == '.' || chars[i] == '\'') { // You can add other chars here
                            found = false;
                        }
                    }
                    db.updateNavItems(j + 1, String.valueOf(chars), xyz[0], xyz[2]);
                }

            }


            // Toast.makeText(Splash.this, "Values:-" + db.getNavCount(), Toast.LENGTH_SHORT).show();


            Intent i = new Intent(Splash.this, MainActivity.class);
            startActivity(i);


        }


    }

}

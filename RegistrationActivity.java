package com.application.teleshopnative;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;
import java.util.regex.Pattern;

import swarajsaaj.smscodereader.interfaces.OTPListener;
import swarajsaaj.smscodereader.receivers.OtpReader;

public class RegistrationActivity extends AppCompatActivity {


    EditText et_name, et_email, et_password;
    String name, email, mobile, password, otp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);


        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        mobile = bundle.getString("phone");
        otp = bundle.getString("otp");

        et_name = (EditText) findViewById(R.id.editText4);
        et_email = (EditText) findViewById(R.id.editText3);
        et_password = (EditText) findViewById(R.id.editText5);


        Button signup = (Button) findViewById(R.id.register);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                name = String.valueOf(et_name.getText());
                email = String.valueOf(et_email.getText());
                password = String.valueOf(et_password.getText());


                new RegistationScreenData().execute();

            }
        });


    }


    class RegistationScreenData extends AsyncTask<Void, Void, String> {


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
                theResult = new RegisterDataSOAP().remotereq(name, email, mobile, password, otp);

            } catch (Exception e) {
                e.printStackTrace();
            }

            return theResult;
        }


        @Override
        protected void onPostExecute(String theResult) {
            super.onPostExecute(theResult);


            String parts[] = theResult.split(Pattern.quote("!"));
            DatabaseHandler db = new DatabaseHandler(RegistrationActivity.this);


            if (parts[0].equals("error ")) {

                Toast.makeText(RegistrationActivity.this, parts[1], Toast.LENGTH_SHORT).show();
            } else {


                String abc[] = theResult.split(Pattern.quote(";"));

                String id = abc[3];
                String key = abc[1];

                if (db.getUserCount() == 0) {

                    db.addUser( name, id , email, key);
                } else {
                    db.updateUser(name, id, email, key);

                }

                startActivity(new Intent(RegistrationActivity.this, MainActivity.class));


            }


        }
    }


}


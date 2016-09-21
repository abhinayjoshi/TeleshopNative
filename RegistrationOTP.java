package com.application.teleshopnative;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Toast;

public class RegistrationOTP extends AppCompatActivity {

    String phone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_otp);


        final ScrollView mainScrollView = (ScrollView)findViewById(R.id.scrollView);


        final EditText editText = (EditText)findViewById(R.id.editText);
        editText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                mainScrollView.fullScroll(View.FOCUS_DOWN);
                return false;
            }
        });
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                phone = String.valueOf(editText.getText());
                new RegisterSOAP().execute();

            }
        });


    }



    private class RegisterSOAP extends AsyncTask<Void, Void, String>

        {


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
                theResult = new RegisterOTPSOAP().remotereq(phone);

            } catch (Exception e) {
                e.printStackTrace();
            }

            return theResult;
        }


            @Override
            protected void onPostExecute(String theResult) {
            super.onPostExecute(theResult);


            Toast.makeText(RegistrationOTP.this, theResult, Toast.LENGTH_SHORT).show();


                Intent intent = new Intent(RegistrationOTP.this, RegistrationPart2.class);
                intent.putExtra("phone", phone);
                intent.putExtra("otp", theResult);
                startActivity(intent);





        }
        }


    }


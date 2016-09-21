package com.application.teleshopnative;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {
    String user,pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText username = (EditText)findViewById(R.id.editText6);
        final EditText password = (EditText)findViewById(R.id.editText7);

        Button button = (Button) findViewById(R.id.button4);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                user = String.valueOf(username.getText());
                pass = String.valueOf(password.getText());
                new LoginData().execute();

            }
        });
        TextView signup =(TextView) findViewById(R.id.signup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(LoginActivity.this, RegistrationOTP.class);
                startActivity(intent);

            }
        });
    }
    private class LoginData extends AsyncTask<Void, Void, String> {


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

            try {
                theResult = new LoginSOAP().remotereq(user,pass);

            } catch (Exception e) {
                e.printStackTrace();
            }

            return theResult;
        }


        @Override
        protected void onPostExecute(String theResult) {
            super.onPostExecute(theResult);


            String parts[] = theResult.split(Pattern.quote(";"));

            if (parts[0].equals("error")) {

                Toast.makeText(LoginActivity.this, theResult, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(LoginActivity.this, SubCatActivity.class);
                startActivity(intent);

            } else {
                Toast.makeText(LoginActivity.this, theResult, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);

            }




        }
    }
}

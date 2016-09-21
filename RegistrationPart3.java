package com.application.teleshopnative;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrationPart3 extends AppCompatActivity {

    String otp, phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_part3);


        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        phone = bundle.getString("phone");
        otp = bundle.getString("otp");


        Button signup = (Button) findViewById(R.id.button3);

        final EditText editText = (EditText) findViewById(R.id.editText2);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String value = String.valueOf(editText.getText());

                if (otp.equals(value)) {

                    Intent intent = new Intent(RegistrationPart3.this, RegistrationActivity.class);
                    intent.putExtra("phone", phone);
                    intent.putExtra("otp", otp);
                    startActivity(intent);
                } else {

                    Toast.makeText(RegistrationPart3.this, otp, Toast.LENGTH_SHORT).show();
                }


            }
        });


    }
}

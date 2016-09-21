package com.application.teleshopnative;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RegistrationPart2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_part2);


        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        final String phone = bundle.getString("phone");
        final String otp = bundle.getString("otp");



        Button button = (Button) findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegistrationPart2.this, RegistrationPart3.class);
                intent.putExtra("phone", phone);
                intent.putExtra("otp", otp);
                startActivity(intent);


            }
        });

    }
}

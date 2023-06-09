package com.allbank.balancecheck;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class GetStartedActivity extends AppCompatActivity {
    ImageView get_start;
    ImageView login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_started);

        get_start=findViewById(R.id.get_start);
        login=findViewById(R.id.log);


        get_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent= new Intent(GetStartedActivity.this,HomePageActivity.class);
                startActivity(mainIntent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent= new Intent(GetStartedActivity.this,LoginActivity.class);
                startActivity(mainIntent);


            }

        });
    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(GetStartedActivity.this,ExitActivity.class));
    }
}

     
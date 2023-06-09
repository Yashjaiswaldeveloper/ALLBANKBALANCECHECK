package com.allbank.balancecheck;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class LanguageActivity extends AppCompatActivity {
    ImageView lngcontinuebtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);

        lngcontinuebtn=findViewById(R.id.lngcontinuebtn);
        lngcontinuebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LanguageActivity.this,PasswordActivity.class);
                startActivity(intent);
            }
        });
    }
}
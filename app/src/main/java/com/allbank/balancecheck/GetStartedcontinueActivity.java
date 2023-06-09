package com.allbank.balancecheck;

import static com.pesonal.adsdk.AppManage.ADMOB_B;
import static com.pesonal.adsdk.AppManage.FACEBOOK_NB;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.pesonal.adsdk.AppManage;

public class GetStartedcontinueActivity extends AppCompatActivity {
    private ImageButton register_button;
    private RadioGroup radio_group_text_gender;
    private RadioButton radio_button_selected;
    private String mobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_startedcontinue);

        radio_group_text_gender = findViewById(R.id.gendergrp);
        register_button = findViewById(R.id.cont_1);
        radio_group_text_gender.clearCheck();
        mobile = getIntent().getStringExtra("mobilenumber");
        AppManage.getInstance(GetStartedcontinueActivity.this).showNative((ViewGroup) findViewById(R.id.ad_banner), ADMOB_B[0], FACEBOOK_NB[0]);

        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedGenderId = radio_group_text_gender.getCheckedRadioButtonId();
                radio_button_selected = findViewById(selectedGenderId);
                String textGender;

                if (radio_group_text_gender.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(GetStartedcontinueActivity.this, "Please select your gender", Toast.LENGTH_SHORT).show();
                    radio_button_selected.setError("Gender is required");
                    radio_button_selected.requestFocus();
                }else{
                    textGender = radio_button_selected.getText().toString();
                    registerUser(textGender);
                }

            }
        });
    }

    private void registerUser(String textGender) {
        Toast.makeText(this, textGender, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(),NameActivity.class);
        intent.putExtra("mobilenumbe",mobile);
        intent.putExtra("gender", textGender);
        startActivity(intent);
    }
}
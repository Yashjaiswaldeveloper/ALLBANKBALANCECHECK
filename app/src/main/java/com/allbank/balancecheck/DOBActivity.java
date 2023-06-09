package com.allbank.balancecheck;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DOBActivity extends AppCompatActivity {
    ImageView dobcontinuebtn;
    Spinner date,month;

    private String gender;
    private String fullname;
    private String email;
    private String DOB,mobile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dobactivity);

        this.date = (Spinner) findViewById(R.id.date);
        this.month = (Spinner) findViewById(R.id.month);
        dobcontinuebtn=findViewById(R.id.dobcontinuebtn);
        gender = getIntent().getStringExtra("gende");
        fullname = getIntent().getStringExtra("fulname");
        email = getIntent().getStringExtra("email");
        mobile = getIntent().getStringExtra("mobilenum");


        List<String> years = new ArrayList<String>();
        int thisYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = 1950; i <= thisYear; i++) {
            years.add(Integer.toString(i));
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, years);

        List<String> months = new ArrayList<String>();
        for (int i = 1; i <= 12; i++) {
            months.add(Integer.toString(i));
        }
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, months);

        List<String> dates = new ArrayList<String>();
        for (int i = 1; i <= 31; i++) {
            dates.add(Integer.toString(i));
        }
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, dates);

        Spinner year = (Spinner)findViewById(R.id.year);
        year.setAdapter(adapter);
        month.setAdapter(adapter1);
        date.setAdapter(adapter2);

        dobcontinuebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!date.getSelectedItem().toString().trim().isEmpty()) {
                    if (!month.getSelectedItem().toString().trim().isEmpty()) {
                        if (!year.getSelectedItem().toString().trim().isEmpty()) {
                            DOB = date.getSelectedItem().toString()+"/"+month.getSelectedItem().toString()+"/"+year.getSelectedItem().toString();
                            Intent intent = new Intent(getApplicationContext(), CityActivity.class);
                            intent.putExtra("gend", gender);
                            intent.putExtra("fulnam", fullname);
//                            intent.putExtra("mail", email);
                            intent.putExtra("mobilenu", mobile);
                            intent.putExtra("dob", DOB);
                            startActivity(intent);
                        }else {
                            Toast.makeText(DOBActivity.this, "Please select birth year", Toast.LENGTH_SHORT).show();
//                            year.setError("Valid Email is required");
//                            year.requestFocus();
                        }
                    }else {
                        Toast.makeText(DOBActivity.this, "Please select birth month", Toast.LENGTH_SHORT).show();
//                            month.setError("Valid Email is required");
//                            month.requestFocus();
                    }
                }else {
                    Toast.makeText(DOBActivity.this, "Please select birth date", Toast.LENGTH_SHORT).show();
//                            date.setError("Valid Email is required");
//                            date.requestFocus();
                }
            }
        });
    }
}
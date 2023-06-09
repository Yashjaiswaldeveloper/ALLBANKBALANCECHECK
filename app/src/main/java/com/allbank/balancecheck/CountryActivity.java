package com.allbank.balancecheck;

import static com.pesonal.adsdk.AppManage.ADMOB_B;
import static com.pesonal.adsdk.AppManage.ADMOB_N;
import static com.pesonal.adsdk.AppManage.FACEBOOK_N;
import static com.pesonal.adsdk.AppManage.FACEBOOK_NB;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.pesonal.adsdk.AppManage;

import java.util.ArrayList;
import java.util.List;

public class CountryActivity extends AppCompatActivity {

    ImageView countrycontinuebtn;
    private Spinner country_Spinner;

    //    private String gender;
//    private String fullname;
//    private String email, DOB;
    private String mobile;
    CountryItem2 countryItem2;
    String CountryName;
    private ArrayList<CountryItem2> countryList2;
    private CountryAdapter2 mAdapter2;
//    List<String> countries = new ArrayList();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country);
        countrycontinuebtn = findViewById(R.id.countrycontinuebtn);
        country_Spinner = findViewById(R.id.country);
        mobile = getIntent().getStringExtra("mobilenu");
        AppManage.getInstance(CountryActivity.this).showNative((ViewGroup) findViewById(R.id.ad_banner), ADMOB_B[0], FACEBOOK_NB[0]);

        inCountryList();

        mAdapter2 = new CountryAdapter2(this, countryList2);
        country_Spinner.setAdapter(mAdapter2);

//        gender = getIntent().getStringExtra("gend");
//        fullname = getIntent().getStringExtra("fulnam");
//        email = getIntent().getStringExtra("mail");
//        DOB = getIntent().getStringExtra("dob");

        country_Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {

                countryItem2 = (CountryItem2) parent.getItemAtPosition((position));
                CountryName = countryItem2.getCountryName2();
                Toast.makeText(CountryActivity.this, CountryName + " Selected", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        countrycontinuebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!country_Spinner.getSelectedItem().toString().trim().isEmpty()) {
                    Intent intent = new Intent(getApplicationContext(), BanksActivity.class);
                    intent.putExtra("country", CountryName);
                    startActivity(intent);
                }
            }
        });
    }

    private void inCountryList() {

        countryList2 = new ArrayList<>();
        countryList2.add(new CountryItem2(1, "India", R.drawable.india, R.drawable.rupe));
        countryList2.add(new CountryItem2(2, "UK", R.drawable.uk, R.drawable.eur));
        countryList2.add(new CountryItem2(3, "USA", R.drawable.usa, R.drawable.doller));
        countryList2.add(new CountryItem2(4, "Sweden", R.drawable.sweden, R.drawable.kr));
        countryList2.add(new CountryItem2(5, "Australia", R.drawable.australia, R.drawable.doller));
        countryList2.add(new CountryItem2(6, "Canada", R.drawable.canada, R.drawable.doller));
        countryList2.add(new CountryItem2(7, "Japan", R.drawable.japan, R.drawable.yenn));
        countryList2.add(new CountryItem2(8, "Germany", R.drawable.germany, R.drawable.eur));
        countryList2.add(new CountryItem2(9, "Switzerland", R.drawable.switzerland, R.drawable.chf));

    }

//    @Override
//    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//
//
//    }
//
//    @Override
//    public void onNothingSelected(AdapterView<?> adapterView) {
//
//    }
}
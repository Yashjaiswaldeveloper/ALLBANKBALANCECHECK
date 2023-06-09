package com.allbank.balancecheck;

import static com.pesonal.adsdk.AppManage.ADMOB_N;
import static com.pesonal.adsdk.AppManage.FACEBOOK_N;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.pesonal.adsdk.AppManage;

public class CalculatorActivity extends AppCompatActivity {
    Button gst_cal;
    Button emi_cal;
    RelativeLayout native_ads;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        gst_cal=findViewById(R.id.gst_cal);
        emi_cal= findViewById(R.id.emi_cal);
        native_ads= findViewById(R.id.native_ads);
        AppManage.getInstance(CalculatorActivity.this).showNative((ViewGroup) findViewById(R.id.native_ads), ADMOB_N[0], FACEBOOK_N[0]);
        AppManage.getInstance(CalculatorActivity.this).loadInterstitialAd(this);

        gst_cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CalculatorActivity.this, GSTCalcyActivity.class);
                startActivity(intent);


            }
        });

        emi_cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppManage.getInstance(CalculatorActivity.this).showInterstitialAd(CalculatorActivity.this, new AppManage.MyCallback() {
                    public void callbackCall() {
                Intent intent = new Intent(CalculatorActivity.this,EmiCalculator.class);
                startActivity(intent);
                    }
                }, "", AppManage.app_mainClickCntSwAd);
                    }

        });
    }
    public void onBackPressed() {
        AppManage.getInstance(CalculatorActivity.this).showBackPressAd(CalculatorActivity.this, new AppManage.MyCallback() {
            public void callbackCall() {
                CalculatorActivity.super.onBackPressed();
            }
        });
    }

}
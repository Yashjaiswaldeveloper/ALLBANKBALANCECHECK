package com.allbank.balancecheck;


import static com.pesonal.adsdk.AppManage.ADMOB_B;
import static com.pesonal.adsdk.AppManage.FACEBOOK_NB;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.pesonal.adsdk.AppManage;


public class AboutActivity extends AppCompatActivity {
    ImageView arrow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        arrow = findViewById(R.id.arrowback_about);
        AppManage.getInstance(AboutActivity.this).showNative((ViewGroup) findViewById(R.id.ad_banner), ADMOB_B[0], FACEBOOK_NB[0]);
        arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        AppManage.getInstance(AboutActivity.this).showBackPressAd(AboutActivity.this, new AppManage.MyCallback() {
            public void callbackCall() {
                AboutActivity.super.onBackPressed();
            }
        });
    }
}
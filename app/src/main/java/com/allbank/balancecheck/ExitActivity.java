package com.allbank.balancecheck;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.pesonal.adsdk.AppManage;

public class ExitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exit);
        TextView cancel_btn = findViewById(R.id.nobt);
        TextView yes_btn = findViewById(R.id.yesbt);
        AppManage.getInstance(ExitActivity.this).loadInterstitialAd(this);

        cancel_btn.setOnClickListener(v -> ExitActivity.super.onBackPressed());
        yes_btn.setOnClickListener(v -> {
            AppManage.getInstance(ExitActivity.this).showInterstitialAd(ExitActivity.this, new AppManage.MyCallback() {
                public void callbackCall() {
//            stopVpn();
            startActivity(new Intent(ExitActivity.this, ThankyouActivity.class));
            finish();
                }
            }, "", AppManage.app_mainClickCntSwAd);
        });
    }
}
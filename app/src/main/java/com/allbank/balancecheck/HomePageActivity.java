package com.allbank.balancecheck;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.pesonal.adsdk.AppManage;

public class HomePageActivity extends AppCompatActivity {
    ImageView checkbln;
    ImageView ifsc;
    ImageView exchange;
    ImageView calculator;
    ImageView nearbank;
    ImageView atm;
    ImageView setting;
    ImageView about;
    private String country;
    private String bank;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        checkbln = findViewById(R.id.checkbln);
        ifsc = findViewById(R.id.ifsc);
        exchange = findViewById(R.id.exchange);
        calculator = findViewById(R.id.calculator);
        nearbank = findViewById(R.id.nearbank);
        atm = findViewById(R.id.atm);
        setting = findViewById(R.id.setting);
        about = findViewById(R.id.about);

        country = getIntent().getStringExtra("country");
        bank = getIntent().getStringExtra("bankname");


        checkbln.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppManage.getInstance(HomePageActivity.this).showInterstitialAd(HomePageActivity.this, new AppManage.MyCallback() {
                    public void callbackCall() {
                        Intent intent = new Intent(HomePageActivity.this, CheckBalanceActivity.class);
                        startActivity(intent);
                    }
                }, "", AppManage.app_mainClickCntSwAd);
            }
        });
        ifsc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppManage.getInstance(HomePageActivity.this).showInterstitialAd(HomePageActivity.this, new AppManage.MyCallback() {
                    public void callbackCall() {
                        Intent intent = new Intent(HomePageActivity.this, IFSCcodeActivity.class);
                        startActivity(intent);
                    }
                }, "", AppManage.app_mainClickCntSwAd);
            }
        });
        exchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppManage.getInstance(HomePageActivity.this).showInterstitialAd(HomePageActivity.this, new AppManage.MyCallback() {
                    public void callbackCall() {
                        Intent intent = new Intent(HomePageActivity.this, ExchangeActivity.class);
                        startActivity(intent);
                    }
                }, "", AppManage.app_mainClickCntSwAd);
            }
        });
        calculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppManage.getInstance(HomePageActivity.this).showInterstitialAd(HomePageActivity.this, new AppManage.MyCallback() {
                    public void callbackCall() {
                        Intent intent = new Intent(HomePageActivity.this, CalculatorActivity.class);
                        startActivity(intent);
                    }
                }, "", AppManage.app_mainClickCntSwAd);
            }
        });

        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppManage.getInstance(HomePageActivity.this).showInterstitialAd(HomePageActivity.this, new AppManage.MyCallback() {
                    public void callbackCall() {
                        Intent intent = new Intent(HomePageActivity.this, SettingActivity.class);
                        startActivity(intent);
                    }
                }, "", AppManage.app_mainClickCntSwAd);
            }

        });
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppManage.getInstance(HomePageActivity.this).showInterstitialAd(HomePageActivity.this, new AppManage.MyCallback() {
                    public void callbackCall() {
                        Intent intent = new Intent(HomePageActivity.this, AboutActivity.class);
                        startActivity(intent);
                    }
                }, "", AppManage.app_mainClickCntSwAd);
            }
        });

    }

    public void finder(final View view) {
        String find;
        find = "bank";

        try {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("https://www.google.co.in/maps/search/" + find));
            intent.setPackage("com.google.android.apps.maps");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Intent intent2 = new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.maps"));
            intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent2);
        }
    }

    public void finder1(View view) {
        String find2;
        find2 = "atm";
        try {
            Intent intent3 = new Intent("android.intent.action.VIEW", Uri.parse("https://www.google.co.in/maps/search/" + find2));
            intent3.setPackage("com.google.android.apps.maps");
            intent3.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent3);
        } catch (ActivityNotFoundException e2) {
            Intent intent4 = new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.maps"));
            intent4.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent4);
        }
    }

    private void showDialogbox() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);

        LayoutInflater inflater = LayoutInflater.from(this);

        View reg_layout = inflater.inflate(R.layout.exitbox, null);

        final TextView yesbt = reg_layout.findViewById(R.id.yesbt);
        final TextView nobt = reg_layout.findViewById(R.id.nobt);


        dialog.setView(reg_layout);

        yesbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishAffinity();
            }
        });

        nobt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                dialog.dismiss();
                startActivity(new Intent(HomePageActivity.this, HomePageActivity.class));
            }
        });

        dialog.show();
    }
    }


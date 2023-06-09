package com.allbank.balancecheck;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class ThankyouActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thankyou);
    }
    public void onBackPressed() {
 //       stopVpn();
        finishAffinity();
    }
}
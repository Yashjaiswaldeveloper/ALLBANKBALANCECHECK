package com.allbank.balancecheck;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import static com.pesonal.adsdk.AppManage.ADMOB_B;
import static com.pesonal.adsdk.AppManage.ADMOB_N;
import static com.pesonal.adsdk.AppManage.FACEBOOK_N;
import static com.pesonal.adsdk.AppManage.FACEBOOK_NB;

import android.app.SearchManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.pesonal.adsdk.AppManage;
import com.rengwuxian.materialedittext.MaterialEditText;

public class CheckBalanceActivity extends AppCompatActivity {

    LinearLayout ad_banner;
    String bank_name;
    ImageView backbt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_balance);
        ad_banner = findViewById(R.id.ad_banner);
        AppManage.getInstance(CheckBalanceActivity.this).showNative((ViewGroup) findViewById(R.id.ad_banner), ADMOB_B[0], FACEBOOK_NB[0]);

        backbt = findViewById(R.id.backbt);

        backbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }

    public void onBackPressed() {
        super.onBackPressed();
    }

    public void callCheckBal(View view) {
        showDialogbank1();
    }

    public void callMiniStatement(View view) {
        showDialogbank2();
    }

    public void callCustomerCare(View view) {
        showDialogbank3();
    }

    private void showDialogbank1() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Bank");
        dialog.setMessage("Enter Bank Name");

        LayoutInflater inflater = LayoutInflater.from(this);

        View reg_layout = inflater.inflate(R.layout.enterbank, null);

        final MaterialEditText bankedittext1 = reg_layout.findViewById(R.id.bankedittext1);


        dialog.setView(reg_layout);

        dialog.setPositiveButton("Search", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                bank_name = bankedittext1.getText().toString();

                if (TextUtils.isEmpty(bank_name)) {
                    Toast.makeText(CheckBalanceActivity.this, "Please type your bank name...", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
                    intent.putExtra(SearchManager.QUERY, bank_name + " check balance missed call service number");
                    startActivity(intent);
                }
            }
        });

        dialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
//                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void showDialogbank2() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Bank");
        dialog.setMessage("Enter Bank Name");

        LayoutInflater inflater = LayoutInflater.from(this);

        View reg_layout = inflater.inflate(R.layout.enterbank, null);

        final MaterialEditText bankedittext1 = reg_layout.findViewById(R.id.bankedittext1);


        dialog.setView(reg_layout);

        dialog.setPositiveButton("Search", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                bank_name = bankedittext1.getText().toString();

                if (TextUtils.isEmpty(bank_name)) {
                    Toast.makeText(CheckBalanceActivity.this, "Please type your bank name...", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
                    intent.putExtra(SearchManager.QUERY, bank_name + " mini statement missed call service number");
                    startActivity(intent);
                }
            }
        });

        dialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
//                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void showDialogbank3() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Bank");
        dialog.setMessage("Enter Bank Name");

        LayoutInflater inflater = LayoutInflater.from(this);

        View reg_layout = inflater.inflate(R.layout.enterbank, null);

        final MaterialEditText bankedittext1 = reg_layout.findViewById(R.id.bankedittext1);


        dialog.setView(reg_layout);

        dialog.setPositiveButton("Search", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                bank_name = bankedittext1.getText().toString();

                if (TextUtils.isEmpty(bank_name)) {
                    Toast.makeText(CheckBalanceActivity.this, "Please type your bank name...", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
                    intent.putExtra(SearchManager.QUERY, bank_name + " customer care number");
                    startActivity(intent);
                }
            }
        });

        dialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
//                dialog.dismiss();
            }
        });

        dialog.show();
    }
}
//
//    public void onBackPressed() {
//        AppManage.getInstance(CheckBalanceActivity.this).showBackPressAd(CheckBalanceActivity.this, new AppManage.MyCallback() {
//            public void callbackCall() {
//                CheckBalanceActivity.super.onBackPressed();
//            }
//        });
//    }
//}



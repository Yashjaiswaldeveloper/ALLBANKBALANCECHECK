package com.allbank.balancecheck;

import static com.pesonal.adsdk.AppManage.ADMOB_B;
import static com.pesonal.adsdk.AppManage.FACEBOOK_NB;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.pesonal.adsdk.AppManage;
import com.rengwuxian.materialedittext.MaterialEditText;

public class IFSCcodeActivity extends AppCompatActivity {

    ImageView arrow;
    Button serchbank, serchpin, serchifsc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ifsccode);


        arrow = findViewById(R.id.arrowback_ifsc);
        serchbank = findViewById(R.id.button_searcbank);
        serchpin = findViewById(R.id.button_pincode);
        serchifsc = findViewById(R.id.button_serifsc);
        AppManage.getInstance(IFSCcodeActivity.this).showNative((ViewGroup) findViewById(R.id.ad_banner), ADMOB_B[0], FACEBOOK_NB[0]);

        arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(IFSCcodeActivity.this, HomePageActivity.class);
                startActivity(i);
                finish();
            }
        });

        serchbank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogbank();
            }
        });

        serchpin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogpin();
            }
        });

        serchifsc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogifsc();
            }
        });
    }

    private void showDialogifsc() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("IFSC Code");
        dialog.setMessage("Search by IFSC Code");

        LayoutInflater inflater = LayoutInflater.from(this);

        View reg_layout = inflater.inflate(R.layout.enterifsc,null);

        final MaterialEditText ifscedittext = reg_layout.findViewById(R.id.ifscedittext);


        dialog.setView(reg_layout);

        dialog.setPositiveButton("Search", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which){
                String ifsc = ifscedittext.getText().toString();

                if(TextUtils.isEmpty(ifsc)){
                    Toast.makeText(IFSCcodeActivity.this, "Please type your IFSC Code...", Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
                    intent.putExtra(SearchManager.QUERY, ifsc + " details");
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

    private void showDialogpin() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Pincode");
        dialog.setMessage("Search by Pincode");

        LayoutInflater inflater = LayoutInflater.from(this);

        View reg_layout = inflater.inflate(R.layout.enternumber,null);

        final MaterialEditText pincodeedittext = reg_layout.findViewById(R.id.pincodeedittext);
        final MaterialEditText bankedittext = reg_layout.findViewById(R.id.bankedittext);

        dialog.setView(reg_layout);

        dialog.setPositiveButton("Search", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which){
                String pincode = pincodeedittext.getText().toString();
                String enterbank = bankedittext.getText().toString();

                if(!TextUtils.isEmpty(pincode)){
                    if(!TextUtils.isEmpty(enterbank)) {
                        Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
                        intent.putExtra(SearchManager.QUERY, enterbank+" ifsc code in "+pincode);
                        startActivity(intent);
                    }else{
                        Toast.makeText(IFSCcodeActivity.this, "Please type your bank name...", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(IFSCcodeActivity.this, "Please type your pincode...", Toast.LENGTH_SHORT).show();
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

    private void showDialogbank() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Bank");
        dialog.setMessage("Search by Bank Name");

        LayoutInflater inflater = LayoutInflater.from(this);

        View reg_layout = inflater.inflate(R.layout.enterbank,null);

        final MaterialEditText bankedittext1 = reg_layout.findViewById(R.id.bankedittext1);


        dialog.setView(reg_layout);

        dialog.setPositiveButton("Search", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which){
                String bank_name = bankedittext1.getText().toString();

                if(TextUtils.isEmpty(bank_name)){
                    Toast.makeText(IFSCcodeActivity.this, "Please type your bank name...", Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
                    intent.putExtra(SearchManager.QUERY, "ifsc code of "+bank_name + " near me");
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
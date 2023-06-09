package com.allbank.balancecheck;

import static com.pesonal.adsdk.AppManage.ADMOB_B;
import static com.pesonal.adsdk.AppManage.FACEBOOK_NB;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.pesonal.adsdk.AppManage;

import java.io.File;

public class SettingActivity extends AppCompatActivity {
    ImageView backbt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        backbt=findViewById(R.id.backbt);
        AppManage.getInstance(SettingActivity.this).loadInterstitialAd(this);

        AppManage.getInstance(SettingActivity.this).showNative((ViewGroup) findViewById(R.id.ad_banner), ADMOB_B[0], FACEBOOK_NB[0]);

        backbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SettingActivity.super.onBackPressed();
            }
        });
    }

    public void logout(View view) {
        AppManage.getInstance(SettingActivity.this).showInterstitialAd(SettingActivity.this, new AppManage.MyCallback() {
            public void callbackCall() {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                finish();
            }
        }, "", AppManage.app_mainClickCntSwAd);
    }

    public void rateApp(View view)
    {
        try
        {
            Intent rateIntent = rateIntentForUrl("market://details");
            startActivity(rateIntent);
        }
        catch (ActivityNotFoundException e)
        {
            Intent rateIntent = rateIntentForUrl("https://play.google.com/store/apps/details");
            startActivity(rateIntent);
        }
    }

    private Intent rateIntentForUrl(String url)
    {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(String.format("%s?id=%s", url, getPackageName())));
        int flags = Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_MULTIPLE_TASK;
        if (Build.VERSION.SDK_INT >= 21)
        {
            flags |= Intent.FLAG_ACTIVITY_NEW_DOCUMENT;
        }
        else
        {
            //noinspection deprecation
            flags |= Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET;
        }
        intent.addFlags(flags);
        return intent;
    }

//    private void showDialog() {
//        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
//        dialog.setTitle("Feedback");
//        dialog.setMessage("Provide us your valuable feedback");
//
//        LayoutInflater inflater = LayoutInflater.from(this);
//
//        View reg_layout = inflater.inflate(R.layout.send_feedback,null);
//
//        final MaterialEditText edtEmail = reg_layout.findViewById(R.id.edtEmail);
//        final MaterialEditText edtName = reg_layout.findViewById(R.id.edtName);
//        final MaterialEditText edtFeedback = reg_layout.findViewById(R.id.edtFeedback);
//
//        dialog.setView(reg_layout);
//
//        dialog.setPositiveButton("SEND", new DialogInterface.OnClickListener(){
//            @Override
//            public void onClick(DialogInterface dialog, int which){
//
//                if(TextUtils.isEmpty(edtEmail.getText().toString())){
//                    Toast.makeText(SettingActivity.this, "Please type your email...", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                if(TextUtils.isEmpty(edtName.getText().toString())){
//                    Toast.makeText(SettingActivity.this, "Please type your name...", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//
//                if(TextUtils.isEmpty(edtFeedback.getText().toString())){
//                    Toast.makeText(SettingActivity.this, "Feedback field cannot be empty...", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//            }
//        });
//
//        dialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int which) {
////                dialog.dismiss();
//            }
//        });
//
//        dialog.show();
//    }

    public void shareapp(View view) {
//        Intent intent = new Intent("android.intent.action.SEND");
//        intent.setType("text/plain");
//        startActivity(Intent.createChooser(intent, "Share Using"));
        try {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Bank Account Bank Balance");
            String shareMessage= "\nLet me recommend you this application\n\n";
            shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID +"\n\n";
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
            startActivity(Intent.createChooser(shareIntent, "choose one"));
        } catch(Exception e) {
            //e.toString();
        }
    }

    public void about(View view) {
        AppManage.getInstance(SettingActivity.this).showInterstitialAd(SettingActivity.this, new AppManage.MyCallback() {
            public void callbackCall() {
                startActivity(new Intent(SettingActivity.this,AboutActivity.class));
            }
        }, "", AppManage.app_mainClickCntSwAd);
    }

    public void privacypolicy(View view) {

        gotoUrl("https://techiemediaadvertising.blogspot.com/2022/06/techiemedia-inc.html");

    }

//    public void tAndc(View view) {
//
//    }

    private void gotoUrl(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}

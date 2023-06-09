package com.allbank.balancecheck;

import static com.allbank.balancecheck.Constants.CURRENCY_STORED;
import static com.allbank.balancecheck.Constants.PACKAGE_NAME;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.android.play.core.review.ReviewInfo;
import com.google.android.play.core.review.ReviewManager;
import com.google.android.play.core.review.ReviewManagerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class EmiCalculator extends AppCompatActivity {

    String appLink;
    Bitmap bmp;
    TextView date;
    Bitmap dusrabmp;
    double emi;
    String fmonth;
    FrameLayout frameLayout;
    SharedPreferences frontshare;
    int fyear;
    RadioGroup group;
    String interAds;
    double interest;
    EditText interestamount;
    boolean isCalculated;
    double loanamount;
    ReviewManager manger;
    SQLiteDatabase myDatabase;
    String name;
    String nativeAds;
    double period;
    String premiumLink;
    EditText principalamount;
    RadioButton radioButton;
    ReviewInfo reviewInfo;
    Bitmap scalebmp;
    Bitmap scaledusrabmp;
    DatePickerDialog.OnDateSetListener setListener;
    double tinterest;
    String tmonth;
    int todate;
    int tomonth;
    double total;
    TextView totalemi;
    ImageView backbt;
    TextView totalinterest;
    TextView totalpayement;
    TextView totalprincipal;
    int toyear;
    EditText year;

    public void calculate(View view) {
        ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(view.getWindowToken(), 0);
        if (this.principalamount.getText().toString().isEmpty() || this.interestamount.getText().toString().isEmpty() || this.year.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Enter Inputs", 0).show();
            return;
        }
        this.period = Double.parseDouble(this.year.getText().toString());
        RadioButton radioButton2 = (RadioButton) findViewById(this.group.getCheckedRadioButtonId());
        this.radioButton = radioButton2;
        if (radioButton2.getText().toString().equals("year")) {
            this.period = Double.parseDouble(this.year.getText().toString()) * 12.0d;
        } else {
            this.period = Double.parseDouble(this.year.getText().toString());
        }
        if (this.period <= 360.0d && Double.parseDouble(this.interestamount.getText().toString()) <= 50.0d) {
            calculation();
        } else if (this.period > 360.0d) {
            Toast.makeText(this, "tenure should be less than 30 years", 0).show();
        } else {
            Toast.makeText(this, "Interest rate should be less than 50%", 0).show();
        }
    }

    public void calculation() {
        ((NestedScrollView) findViewById(R.id.scroller)).smoothScrollTo(0, 800);
        this.loanamount = Double.parseDouble(this.principalamount.getText().toString());
        double parseDouble = Double.parseDouble(this.interestamount.getText().toString());
        this.interest = parseDouble;
        double d = parseDouble / 1200.0d;
        this.interest = d;
        double pmt = (this.loanamount * d) / (1.0d - Math.pow(d + 1.0d, -this.period));
        double d2 = this.period;
        double d3 = this.loanamount;
        double d4 = (pmt * d2) - d3;
        this.tinterest = d4;
        this.total = d4 + d3;
        double p = Math.pow(this.interest + 1.0d, d2);
        this.emi = ((this.loanamount * this.interest) * p) / (p - 1.0d);
        PrasingTheDouble prasingTheDouble = new PrasingTheDouble(this.loanamount);
        TextView textView = this.totalprincipal;
        textView.setText(prasingTheDouble.getaDouble() + " " + CURRENCY_STORED);
        double d5 = this.tinterest;
        if (((double) ((int) d5)) == d5) {
            this.totalinterest.setText(String.valueOf((int) d5));
        } else {
            this.tinterest = new BigDecimal(this.tinterest).setScale(1, RoundingMode.HALF_UP).doubleValue();
            TextView textView2 = this.totalinterest;
            textView2.setText(this.tinterest + " " + CURRENCY_STORED);
        }
        PrasingTheDouble prasingTheDouble2 = new PrasingTheDouble(this.tinterest);
        TextView textView3 = this.totalinterest;
        textView3.setText(prasingTheDouble2.getaDouble() + " " + CURRENCY_STORED);
        double d6 = this.total;
        if (((double) ((int) d6)) == d6) {
            TextView textView4 = this.totalpayement;
            textView4.setText(((int) d6) + " " + CURRENCY_STORED);
        } else {
            this.total = new BigDecimal(this.total).setScale(1, RoundingMode.HALF_UP).doubleValue();
            TextView textView5 = this.totalpayement;
            textView5.setText(this.total + " " + CURRENCY_STORED);
        }
        PrasingTheDouble prasingTheDouble3 = new PrasingTheDouble(this.total);
        TextView textView6 = this.totalpayement;
        textView6.setText(prasingTheDouble3.getaDouble() + " " + CURRENCY_STORED);
        double d7 = this.emi;
        if (((double) ((int) d7)) == d7) {
            this.totalemi.setText(String.valueOf((int) d7));
        } else {
            this.emi = new BigDecimal(this.emi).setScale(1, RoundingMode.HALF_UP).doubleValue();
            TextView textView7 = this.totalemi;
            textView7.setText(this.emi + " " + CURRENCY_STORED);
        }
        PrasingTheDouble prasingTheDouble4 = new PrasingTheDouble(this.emi);
        TextView textView8 = this.totalemi;
        textView8.setText(prasingTheDouble4.getaDouble() + " " + CURRENCY_STORED);
        double d8 = (double) this.tomonth;
        double d9 = this.period;
        Double.isNaN(d8);
        int fomonth = (int) (d8 + d9);
        int index = (fomonth - 1) / 12;
        this.fyear = this.toyear + index;
        switch (fomonth - (index * 12)) {
            case 1:
                this.fmonth = "Jan";
                break;
            case 2:
                this.fmonth = "Feb";
                break;
            case 3:
                this.fmonth = "Mar";
                break;
            case 4:
                this.fmonth = "April";
                break;
            case 5:
                this.fmonth = "May";
                break;
            case 6:
                this.fmonth = "Jun";
                break;
            case 7:
                this.fmonth = "July";
                break;
            case 8:
                this.fmonth = "Aug";
                break;
            case 9:
                this.fmonth = "Sep";
                break;
            case 10:
                this.fmonth = "Oct";
                break;
            case 11:
                this.fmonth = "Nov";
                break;
            case 12:
                this.fmonth = "Dec";
                break;
        }
        ((TextView) findViewById(R.id.finaldate)).setText(this.todate + " " + this.fmonth + " " + this.fyear);
    }



    public void share(View view) {
        if (this.principalamount.getText().toString().isEmpty() || this.interestamount.getText().toString().isEmpty() || this.year.getText().toString().isEmpty()) {
            Toast.makeText(this, "Enter the value", 0).show();
            return;
        }
        this.period = Double.parseDouble(this.year.getText().toString());
        RadioButton radioButton2 = (RadioButton) findViewById(this.group.getCheckedRadioButtonId());
        this.radioButton = radioButton2;
        if (radioButton2.getText().toString().equals("year")) {
            this.period = Double.parseDouble(this.year.getText().toString()) * 12.0d;
        } else {
            this.period = Double.parseDouble(this.year.getText().toString());
        }
        if (this.period <= 30.0d || Double.parseDouble(this.interestamount.getText().toString()) <= 50.0d) {
            calculation();
            Intent intent = new Intent("android.intent.action.SEND");
            intent.setType("text/plain");
            intent.putExtra("android.intent.extra.SUBJECT", "Bank Account Balance Check");
            intent.putExtra("android.intent.extra.TEXT", "EMI Details-\n \nPrincipal Loan Amount: " + String.valueOf(this.loanamount) + "\nLoan term: " + String.valueOf(this.period) + "\nFirst EMI at: " + String.valueOf(this.todate) + " " + this.tmonth + " " + String.valueOf(this.toyear) + "\n\nMonthly EMI: " + this.emi + "\nTotal Interest: " + String.valueOf(this.tinterest) + "\nTotal payment: " + String.valueOf(this.tinterest + this.loanamount) + "\nLast Loan Date: " + String.valueOf(this.todate) + " " + this.fmonth + " " + String.valueOf(this.fyear) + "\n\nCalculated by 'Bank Account Balance Check'\n" );
            startActivity(Intent.createChooser(intent, "Share Using"));
        } else if (this.period > 30.0d) {
            Toast.makeText(this, "tenure should be less than 30 years", 0).show();
        } else {
            Toast.makeText(this, "Interest rate should be less than 50%", 0).show();
        }
    }

    public void pdf(View view) {
        if (this.principalamount.getText().toString().isEmpty() || this.interestamount.getText().toString().isEmpty() || this.year.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Enter Inputs", 0).show();
            return;
        }
//        if (this.ad.isLoaded()) {
//            this.ad.show();
//        }
//        loadInterstitialAd();
        this.period = Double.parseDouble(this.year.getText().toString());
        RadioButton radioButton2 = (RadioButton) findViewById(this.group.getCheckedRadioButtonId());
        this.radioButton = radioButton2;
        if (radioButton2.getText().toString().equals("year")) {
            this.period = Double.parseDouble(this.year.getText().toString()) * 12.0d;
        } else {
            this.period = Double.parseDouble(this.year.getText().toString());
        }
        if (this.period <= 30.0d || Double.parseDouble(this.interestamount.getText().toString()) <= 50.0d) {
            calculation();
            ActivityCompat.requestPermissions(this, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 0);
            PdfDocument myPdf = new PdfDocument();
            Paint myPaint = new Paint();
            PdfDocument.PageInfo myPageInfo = new PdfDocument.PageInfo.Builder(1200, 2010, 1).create();
            PdfDocument.Page myPage = myPdf.startPage(myPageInfo);
            Canvas canvas = myPage.getCanvas();
            canvas.drawBitmap(this.scalebmp, 0.0f, 0.0f, myPaint);
            myPaint.setTextSize(33.0f);
            myPaint.setTextAlign(Paint.Align.CENTER);
            int selectedd = this.group.getCheckedRadioButtonId();
            this.radioButton = (RadioButton) findViewById(selectedd);
            canvas.drawText(this.loanamount + " " + CURRENCY_STORED, 900.0f, 575.0f, myPaint);
            StringBuilder sb = new StringBuilder();
            PdfDocument.PageInfo pageInfo = myPageInfo;
            sb.append(this.interest * 1200.0d);
            sb.append(" %");
            canvas.drawText(sb.toString(), 900.0f, 700.0f, myPaint);
            canvas.drawText(this.period + "  " + this.radioButton.getText().toString(), 900.0f, 850.0f, myPaint);
            canvas.drawText(this.tinterest + " " + CURRENCY_STORED, 300.0f, 1150.0f, myPaint);
            StringBuilder sb2 = new StringBuilder();
            int i = selectedd;
            sb2.append(this.loanamount);
            sb2.append(" ");
            sb2.append(CURRENCY_STORED);
            canvas.drawText(sb2.toString(), 900.0f, 1150.0f, myPaint);
            canvas.drawText(this.total + " " + CURRENCY_STORED, 600.0f, 1450.0f, myPaint);
            canvas.drawText(this.todate + " " + this.fmonth + " " + this.fyear, 600.0f, 1660.0f, myPaint);
            canvas.drawText(this.todate + " " + this.tmonth + " " + this.toyear, 900.0f, 480.0f, myPaint);
            myPdf.finishPage(myPage);
            PdfDocument.Page myPage2 = myPdf.startPage(new PdfDocument.PageInfo.Builder(1200, 2010, 2).create());
            Canvas canvas2 = myPage2.getCanvas();
            canvas2.drawBitmap(this.scaledusrabmp, 0.0f, 0.0f, myPaint);
            canvas2.drawText(String.valueOf(this.emi) + " " + CURRENCY_STORED, 600.0f, 375.0f, myPaint);
            myPdf.finishPage(myPage2);
            Calendar calendar = Calendar.getInstance();
            File externalStoragePublicDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            File file = new File(externalStoragePublicDirectory, "/Emi" + calendar.get(5) + ".pdf");
            try {
                FileOutputStream out = new FileOutputStream(file);
                myPdf.writeTo(out);
                out.flush();
                out.close();
                myPdf.writeTo(new FileOutputStream(file));
            } catch (Exception e) {
                Log.e("PDF ISSUE", e.getMessage());
            }
            myPdf.close();
            if (file.exists()) {
                Intent intent1 = new Intent("android.intent.action.VIEW");
                intent1.setDataAndType(FileProvider.getUriForFile(this, getApplicationContext().getPackageName() + ".provider", file), "application/pdf");
                intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent1.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                try {
                    startActivity(intent1);
                } catch (ActivityNotFoundException e2) {
                    ActivityNotFoundException activityNotFoundException = e2;
                    Toast.makeText(this, "Download", 0).show();
                }
            }
        } else if (this.period > 30.0d) {
            Toast.makeText(this, "tenure should be less than 30 years", 0).show();
        } else {
            Toast.makeText(this, "Interest rate should be less than 50%", 0).show();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emi_calculator);



        backbt=findViewById(R.id.backbt);


        backbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        ReviewManager create = ReviewManagerFactory.create(this);
        this.manger = create;
        create.requestReviewFlow().addOnCompleteListener((com.google.android.play.core.tasks.OnCompleteListener<ReviewInfo>) task -> {
            if (task.isSuccessful()) {
                EmiCalculator.this.reviewInfo = task.getResult();
                return;
            }
            Toast.makeText(EmiCalculator.this, "Error", 0).show();
        });
        this.frontshare = getApplicationContext().getSharedPreferences(PACKAGE_NAME, 0);
//        this.madview = (AdView) findViewById(R.id.adView);
//        this.madview.loadAd(new AdRequest.Builder().build());
//        final UnifiedNativeAd[] nativeAd = new UnifiedNativeAd[1];
//        new AdLoader.Builder((Context) this, this.nativeAds).forUnifiedNativeAd(new UnifiedNativeAd.OnUnifiedNativeAdLoadedListener() {
//            public void onUnifiedNativeAdLoaded(UnifiedNativeAd unifiedNativeAd) {
//                nativeAd[0] = unifiedNativeAd;
//                UnifiedNativeAdView adview = (UnifiedNativeAdView) emi.this.getLayoutInflater().inflate(R.layout.nativeads, (ViewGroup) null);
//                LinearLayout container = (LinearLayout) emi.this.findViewById(R.id.nativead);
//                container.setVisibility(0);
//                adview.setHeadlineView(adview.findViewById(R.id.heading));
//                adview.setAdvertiserView(adview.findViewById(R.id.advertisername));
//                adview.setBodyView(adview.findViewById(R.id.body));
//                adview.setStarRatingView(adview.findViewById(R.id.start_rating));
//                adview.setMediaView((MediaView) adview.findViewById(R.id.media_view));
//                adview.setCallToActionView(adview.findViewById(R.id.calltoaction));
//                adview.setIconView(adview.findViewById(R.id.adicon));
//                adview.getMediaView().setMediaContent(unifiedNativeAd.getMediaContent());
//                ((TextView) adview.getHeadlineView()).setText(nativeAd[0].getHeadline());
//                if (unifiedNativeAd.getBody() == null) {
//                    adview.getBodyView().setVisibility(4);
//                } else {
//                    ((TextView) adview.getBodyView()).setText(unifiedNativeAd.getBody());
//                    adview.getBodyView().setVisibility(0);
//                }
//                if (unifiedNativeAd.getAdvertiser() == null) {
//                    adview.getAdvertiserView().setVisibility(4);
//                } else {
//                    ((TextView) adview.getAdvertiserView()).setText(unifiedNativeAd.getAdvertiser());
//                    adview.getAdvertiserView().setVisibility(0);
//                }
//                if (unifiedNativeAd.getStarRating() == null) {
//                    adview.getStarRatingView().setVisibility(4);
//                } else {
//                    ((RatingBar) adview.getStarRatingView()).setRating(unifiedNativeAd.getStarRating().floatValue());
//                    adview.getStarRatingView().setVisibility(0);
//                }
//                if (unifiedNativeAd.getIcon() == null) {
//                    adview.getIconView().setVisibility(4);
//                } else {
//                    ((ImageView) adview.getIconView()).setImageDrawable(unifiedNativeAd.getIcon().getDrawable());
//                    adview.getIconView().setVisibility(0);
//                }
//                if (unifiedNativeAd.getCallToAction() == null) {
//                    adview.getCallToActionView().setVisibility(4);
//                } else {
//                    ((Button) adview.getCallToActionView()).setText(unifiedNativeAd.getCallToAction());
//                    adview.getCallToActionView().setVisibility(0);
//                }
//                adview.setNativeAd(unifiedNativeAd);
//                container.removeAllViews();
//                container.addView(adview);
//            }
//        }).build().loadAd(new AdRequest.Builder().build());
//        InterstitialAd interstitialAd = new InterstitialAd(this);
//        this.ad = interstitialAd;
//        interstitialAd.setAdUnitId(this.interAds);
//        this.ad.loadAd(new AdRequest.Builder().build());
        this.bmp = BitmapFactory.decodeResource(getResources(), R.drawable.emidetails);
        this.date = (TextView) findViewById(R.id.date);
        this.scalebmp = Bitmap.createScaledBitmap(this.bmp, 1200, 2010, false);
        Bitmap decodeResource = BitmapFactory.decodeResource(getResources(), R.drawable.emipart);
        this.dusrabmp = decodeResource;
        this.scaledusrabmp = Bitmap.createScaledBitmap(decodeResource, 1200, 2010, false);
        ((LinearLayout) findViewById(R.id.mainlinear)).startAnimation(AnimationUtils.loadAnimation(this, R.anim.slideup));
        SQLiteDatabase openOrCreateDatabase = openOrCreateDatabase("EMI", 0, (SQLiteDatabase.CursorFactory) null);
        this.myDatabase = openOrCreateDatabase;
        openOrCreateDatabase.execSQL("CREATE TABLE IF NOT EXISTS emiTable(name TEXT,principalAmount DOUBLE,interest DOUBLE,tenure DOUBLE,date TEXT,id INTEGER PRIMARY KEY)");
        this.principalamount = (EditText) findViewById(R.id.principalamoount);
        this.interestamount = (EditText) findViewById(R.id.interestamoount);
        this.year = (EditText) findViewById(R.id.tenure);
        this.totalemi = (TextView) findViewById(R.id.emi);
        this.totalinterest = (TextView) findViewById(R.id.totalinterest);
        this.totalprincipal = (TextView) findViewById(R.id.totalprncipal);
        this.totalpayement = (TextView) findViewById(R.id.totalpayement);
        this.frameLayout = (FrameLayout) findViewById(R.id.blur);
        this.group = (RadioGroup) findViewById(R.id.togle);
        Calendar c = Calendar.getInstance();
        this.toyear = c.get(1);
        this.tomonth = c.get(2) + 1;
        this.todate = c.get(5);
        switch (this.tomonth) {
            case 1:
                this.tmonth = "Jan";
                break;
            case 2:
                this.tmonth = "Feb";
                break;
            case 3:
                this.tmonth = "Mar";
                break;
            case 4:
                this.tmonth = "April";
                break;
            case 5:
                this.tmonth = "May";
                break;
            case 6:
                this.tmonth = "Jun";
                break;
            case 7:
                this.tmonth = "July";
                break;
            case 8:
                this.tmonth = "Aug";
                break;
            case 9:
                this.tmonth = "Sep";
                break;
            case 10:
                this.tmonth = "Oct";
                break;
            case 11:
                this.tmonth = "Nov";
                break;
            case 12:
                this.tmonth = "Dec";
                break;
        }
        TextView button = this.date;
        button.setText("First EMI: " + this.todate + " " + this.tmonth + " " + this.toyear);
        this.setListener = new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                EmiCalculator.this.todate = i2;
                EmiCalculator.this.tomonth = i1 + 1;
                EmiCalculator.this.toyear = i;
                switch (EmiCalculator.this.tomonth) {
                    case 1:
                        EmiCalculator.this.tmonth = "Jan";
                        break;
                    case 2:
                        EmiCalculator.this.tmonth = "Feb";
                        break;
                    case 3:
                        EmiCalculator.this.tmonth = "Mar";
                        break;
                    case 4:
                        EmiCalculator.this.tmonth = "April";
                        break;
                    case 5:
                        EmiCalculator.this.tmonth = "May";
                        break;
                    case 6:
                        EmiCalculator.this.tmonth = "Jun";
                        break;
                    case 7:
                        EmiCalculator.this.tmonth = "July";
                        break;
                    case 8:
                        EmiCalculator.this.tmonth = "Aug";
                        break;
                    case 9:
                        EmiCalculator.this.tmonth = "Sep";
                        break;
                    case 10:
                        EmiCalculator.this.tmonth = "Oct";
                        break;
                    case 11:
                        EmiCalculator.this.tmonth = "Nov";
                        break;
                    case 12:
                        EmiCalculator.this.tmonth = "Dec";
                        break;
                }
                TextView button = EmiCalculator.this.date;
                button.setText("First EMI: " + EmiCalculator.this.todate + " " + EmiCalculator.this.tmonth + " " + EmiCalculator.this.toyear);
            }
        };
        String ext = getIntent().getStringExtra("Open");
        if (ext != null) {
//            if (this.ad.isLoaded()) {
//                this.ad.show();
//            }
//            this.ad.loadAd(new AdRequest.Builder().build());
//            openingSaved(Integer.parseInt(ext));
        }
        this.date.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EmiCalculator emi = EmiCalculator.this;
                DatePickerDialog datePickerDialog = new DatePickerDialog(emi, 16973936, emi.setListener, EmiCalculator.this.toyear, EmiCalculator.this.tomonth, EmiCalculator.this.todate);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                datePickerDialog.show();
            }
        });
    }

    public void onMessage() {
        this.frameLayout.setBackgroundColor(Color.parseColor("#00FFFFFF"));
    }

    public void onBackPressed() {
        Statement statement = (Statement) getSupportFragmentManager().findFragmentByTag("TAG_FRAGMENT");
        this.frameLayout.setBackgroundColor(Color.parseColor("#00FFFFFF"));
        int times = this.frontshare.getInt("times", 0);
        if ((this.reviewInfo != null && this.isCalculated && times == 1) || times % 3 == 0) {
            this.manger.launchReviewFlow(this, this.reviewInfo).addOnCompleteListener((com.google.android.play.core.tasks.OnCompleteListener<Void>) task -> task.isSuccessful());
            super.onBackPressed();
        }
    }

    public void historyFunction() {
        Cursor cursor = this.myDatabase.rawQuery("SELECT * FROM emiTable", (String[]) null);
        List<Double> principle = new ArrayList<>();
        List<Integer> id = new ArrayList<>();
        List<String> name2 = new ArrayList<>();
        List<Double> interest2 = new ArrayList<>();
        List<Double> time = new ArrayList<>();
        List<String> dateHistory = new ArrayList<>();
        int idIndex = cursor.getColumnIndex("id");
        int nameIndex = cursor.getColumnIndex(AppMeasurementSdk.ConditionalUserProperty.NAME);
        int principalIndex = cursor.getColumnIndex("principalAmount");
        int interetIndex = cursor.getColumnIndex("interest");
        int tenureIndex = cursor.getColumnIndex("tenure");
        int dateIndex = cursor.getColumnIndex("date");
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            name2.add(cursor.getString(nameIndex));
            id.add(Integer.valueOf(cursor.getInt(idIndex)));
            principle.add(Double.valueOf(cursor.getDouble(principalIndex)));
            interest2.add(Double.valueOf(cursor.getDouble(interetIndex)));
            time.add(Double.valueOf(cursor.getDouble(tenureIndex)));
            dateHistory.add(cursor.getString(dateIndex));
            cursor.moveToNext();
        }
        Dialog history = new Dialog(this);
        history.setContentView(R.layout.history);
        history.setCancelable(true);
        int i = dateIndex;
        int i2 = tenureIndex;
        history.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        history.setCanceledOnTouchOutside(true);
        history.getWindow().setLayout(-2, -1);
        history.getWindow().getAttributes().windowAnimations = 16973826;
        history.show();
        int i3 = interetIndex;
        int i4 = principalIndex;
        int i5 = nameIndex;
        int i6 = idIndex;
        final Dialog history2 = history;
        ((ListView) history2.findViewById(R.id.historyList)).setAdapter(new MyListAdapter(this, name2, principle, dateHistory, id, time, (List<Double>) null));
        ((ImageButton) history2.findViewById(R.id.canceling)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                history2.dismiss();
            }
        });
    }

    public void openingSaved(int position) {
        char c;
        char c2;
        Cursor cursor = this.myDatabase.rawQuery("SELECT * FROM emiTable", null);
        List<Double> principle = new ArrayList<>();
        List<Integer> id = new ArrayList<>();
        List<String> name = new ArrayList<>();
        List<Double> interest = new ArrayList<>();
        List<Double> time = new ArrayList<>();
        List<String> dateHistory = new ArrayList<>();
        int idIndex = cursor.getColumnIndex("id");
        int nameIndex = cursor.getColumnIndex(AppMeasurementSdk.ConditionalUserProperty.NAME);
        int principalIndex = cursor.getColumnIndex("principalAmount");
        int interetIndex = cursor.getColumnIndex("interest");
        int tenureIndex = cursor.getColumnIndex("tenure");
        int dateIndex = cursor.getColumnIndex("date");
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            name.add(cursor.getString(nameIndex));
            id.add(Integer.valueOf(cursor.getInt(idIndex)));
            principle.add(Double.valueOf(cursor.getDouble(principalIndex)));
            interest.add(Double.valueOf(cursor.getDouble(interetIndex)));
            time.add(Double.valueOf(cursor.getDouble(tenureIndex)));
            dateHistory.add(cursor.getString(dateIndex));
            cursor.moveToNext();
        }
        this.principalamount.setText(String.valueOf(principle.get(position)));
        this.year.setText(String.valueOf(time.get(position)));
        this.interestamount.setText(String.valueOf(interest.get(position)));
        this.date.setText(String.valueOf(dateHistory.get(position)));
        String[] dateregex = dateHistory.get(position).split(" ");
        this.todate = Integer.parseInt(dateregex[0]);
        String str = dateregex[1];
        this.tmonth = str;
        switch (str.hashCode()) {
            case 66195:
                if (str.equals("Aug")) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case 68578:
                if (str.equals("Dec")) {
                    c = 11;
                    break;
                }
                c = 65535;
                break;
            case 70499:
                if (str.equals("Feb")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 74231:
                if (str.equals("Jan")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 74851:
                if (str.equals("Jun")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 77118:
                if (str.equals("Mar")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 77125:
                if (str.equals("May")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 78517:
                if (str.equals("Nov")) {
                    c = '\n';
                    break;
                }
                c = 65535;
                break;
            case 79104:
                if (str.equals("Oct")) {
                    c = '\t';
                    break;
                }
                c = 65535;
                break;
            case 83006:
                if (str.equals("Sep")) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            case 2320440:
                if (str.equals("July")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case 63478374:
                if (str.equals("April")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                c2 = 2;
                this.tomonth = 1;
                break;
            case 1:
                c2 = 2;
                this.tomonth = 2;
                break;
            case 2:
                this.tomonth = 3;
                c2 = 2;
                break;
            case 3:
                this.tomonth = 4;
                c2 = 2;
                break;
            case 4:
                this.tomonth = 5;
                c2 = 2;
                break;
            case 5:
                this.tomonth = 6;
                c2 = 2;
                break;
            case 6:
                this.tomonth = 7;
                c2 = 2;
                break;
            case 7:
                this.tomonth = 8;
                c2 = 2;
                break;
            case '\b':
                this.tomonth = 9;
                c2 = 2;
                break;
            case '\t':
                this.tomonth = 10;
                c2 = 2;
                break;
            case '\n':
                this.tomonth = 11;
                c2 = 2;
                break;
            case 11:
                this.tomonth = 12;
                c2 = 2;
                break;
            default:
                c2 = 2;
                break;
        }
        this.toyear = Integer.parseInt(dateregex[c2]);
        this.period = Double.valueOf(this.year.getText().toString()).doubleValue() * 12.0d;
        calculation();
    }


//    public void save(View view) {
//        double periodSql;
//        if (this.principalamount.getText().toString().isEmpty() || this.interestamount.getText().toString().isEmpty() || this.year.getText().toString().isEmpty()) {
//            Toast.makeText(getApplicationContext(), "Enter Inputs", 0).show();
//            return;
//        }
//        this.period = Double.valueOf(this.year.getText().toString()).doubleValue();
//        int selectedid = this.group.getCheckedRadioButtonId();
//        RadioButton radioButton2 = (RadioButton) findViewById(selectedid);
//        this.radioButton = radioButton2;
//        if (radioButton2.getText().toString().equals("year")) {
//            this.period = Double.valueOf(this.year.getText().toString()).doubleValue() * 12.0d;
//        } else {
//            this.period = Double.valueOf(this.year.getText().toString()).doubleValue();
//        }
//        if (this.period > 360.0d || Double.valueOf(this.interestamount.getText().toString()).doubleValue() > 50.0d) {
//            if (this.period > 360.0d) {
//                Toast.makeText(this, "tenure should be less than 30 years", 0).show();
//            } else {
//                Toast.makeText(this, "Interest rate should be less than 50%", 0).show();
//            }
//        } else {
//            calculation();
//            double loanSql = Double.parseDouble(this.principalamount.getText().toString());
//            double interestSql = Double.parseDouble(this.interestamount.getText().toString());
//            String sqlDate = this.todate + " " + this.tmonth + " " + this.toyear;
//            if (this.radioButton.getText().toString().equals("year")) {
//                periodSql = Double.valueOf(this.year.getText().toString()).doubleValue();
//            } else {
//                periodSql = Double.valueOf(this.year.getText().toString()).doubleValue() / 12.0d;
//            }
//            final Dialog entry = new Dialog(this);
//            entry.setContentView(R.layout.file_name);
//            entry.setCancelable(true);
//            entry.getWindow().setBackgroundDrawable(new ColorDrawable(0));
//            entry.setCanceledOnTouchOutside(true);
//            entry.getWindow().setLayout(-2, -2);
//            entry.getWindow().getAttributes().windowAnimations = 16973826;
//            entry.show();
//            ImageButton cancel = (ImageButton) entry.findViewById(R.id.canceling);
//            cancel.setOnClickListener(new View.OnClickListener() {
//                public void onClick(View v) {
//                    entry.dismiss();
//                }
//            });
//            final EditText editText = (EditText) entry.findViewById(R.id.naming);
//            final double d = loanSql;
//            final double d2 = interestSql;
//            ImageButton imageButton = cancel;
//            int i = selectedid;
////            AnonymousClass11 r12 = r0;
//            final double d3 = periodSql;
//            double d4 = loanSql;
//            TextView save = (TextView) entry.findViewById(R.id.save);
//            final String str = sqlDate;
//            Dialog dialog = entry;
//            View.OnClickListener r0 = new View.OnClickListener() {
//                public void onClick(View v) {
//                    EmiCalculator.this.name = editText.getText().toString();
//                    if (EmiCalculator.this.name.isEmpty()) {
//                        EmiCalculator.this.name = "EMPTY";
//                    }
//                    SQLiteDatabase sQLiteDatabase = EmiCalculator.this.myDatabase;
//                    sQLiteDatabase.execSQL("INSERT INTO emiTable(name,principalAmount,interest,tenure,date) VALUES ('" + EmiCalculator.this.name + "'," + d + "," + d2 + "," + d3 + ",'" + str + "')");
//                    entry.dismiss();
//                }
//            };
//            save.setOnClickListener(r0);
//        }
//    }


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.history, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        historyFunction();
        return super.onOptionsItemSelected(item);
    }

}




//    EditText principalamt, interest, loantenure, emidate;
//    ImageView calculate;
//    ImageView backbt;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_emi_calculator);
//        principalamt=findViewById(R.id.principalamt);
//        interest=findViewById(R.id.interest);
//        loantenure=findViewById(R.id.loantenure);
//        emidate=findViewById(R.id.emidate);
//        calculate=findViewById(R.id.calculate);
//        final EditText TI = (EditText) findViewById(R.id.interest_total);
//        final EditText result = (EditText) findViewById(R.id.emi);
//        backbt=findViewById(R.id.backbt);
//
//
//        backbt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                onBackPressed();
//            }
//        });
//
//        calculate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                String st1 = principalamt.getText().toString();
//                String st2 = interest.getText().toString();
//                String st3 = loantenure.getText().toString();
//                if (TextUtils.isEmpty(st1)) {
//                    principalamt.setError("Enter Prncipal Amount");
//                    principalamt.requestFocus();
//                    return;
//                }
//                if (TextUtils.isEmpty(st2)) {
//                    interest.setError("Enter Interest Rate");
//                    interest.requestFocus();
//                    return;
//                }
//                if (TextUtils.isEmpty(st3)) {
//                    loantenure.setError("Enter Years");
//                    loantenure.requestFocus();
//                    return;
//                }
//                float p = Float.parseFloat(st1);
//                float i = Float.parseFloat(st2);
//                float y = Float.parseFloat(st3);
//                float Principal = calPric(p);
//                float Rate = calInt(i);
//                float Months = calMonth(y);
//                float Dvdnt = calDvdnt(Rate, Months);
//                float FD = calFinalDvdnt(Principal, Rate, Dvdnt);
//                float D = calDivider(Dvdnt);
//                float emi = calEmi(FD, D);
//                float TA = calTa(emi, Months);
//                float ti = calTotalInt(TA, Principal);
//                result.setText(String.valueOf(emi));
//                TI.setText(String.valueOf(ti));
//            }
//        });
//    }
//    public float calPric(float p) {
//        return (float)(p);
//    }
//    public float calInt(float i) {
//        return (float)(i / 12 / 100);
//    }
//    public float calMonth(float y) {
//        return (float)(y * 12);
//    }
//    public float calDvdnt(float Rate, float Months) {
//        return (float)(Math.pow(1 + Rate, Months));
//    }
//    public float calFinalDvdnt(float Principal, float Rate, float Dvdnt) {
//        return (float)(Principal * Rate * Dvdnt);
//    }
//    public float calDivider(float Dvdnt) {
//        return (float)(Dvdnt - 1);
//    }
//    public float calEmi(float FD, Float D) {
//        return (float)(FD / D);
//    }
//    public float calTa(float emi, Float Months) {
//        return (float)(emi * Months);
//    }
//    public float calTotalInt(float TA, float Principal) {
//        return (float)(TA - Principal);
//    }
//
//    public void onBackPressed(){
//        super.onBackPressed();
//    }
//}
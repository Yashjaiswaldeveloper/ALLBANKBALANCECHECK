package com.allbank.balancecheck;

import static com.pesonal.adsdk.AppManage.ADMOB_B;
import static com.pesonal.adsdk.AppManage.FACEBOOK_NB;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.pesonal.adsdk.AppManage;

import java.util.ArrayList;

public class ExchangeActivity extends AppCompatActivity {


    ImageView backbt, switchbt;
    EditText enterAmount;
    Button convertbt;
    TextView convertAmount, tv, tv2;
    CountryItem countryItem;
    String clickedCountryName;
    CountryItem countryItemTo;
    String clickedCountryNameTo;

    private ArrayList<CountryItem> countryList;
    private CountryAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exchange);

        Spinner spinner=findViewById(R.id.spinner);
        Spinner spinner3=findViewById(R.id.spinner3);
        AppManage.getInstance(ExchangeActivity.this).showNative((ViewGroup) findViewById(R.id.ad_banner), ADMOB_B[0], FACEBOOK_NB[0]);
        AppManage.getInstance(ExchangeActivity.this).loadInterstitialAd(this);
        switchbt=findViewById(R.id.switchimg);

        backbt=findViewById(R.id.backbt);
        tv=findViewById(R.id.tv1);
//        tv2=findViewById(R.id.tv2);
        convertbt=findViewById(R.id.convertbt);
        enterAmount=findViewById(R.id.edittextone);
        convertAmount=findViewById(R.id.result);
        iniCountryList();

        mAdapter = new CountryAdapter(this,countryList);
        spinner.setAdapter(mAdapter);
        spinner3.setAdapter(mAdapter);

        switchbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int positionSpinner1 = spinner.getSelectedItemPosition() ;
                int positionSpinner2 = spinner3.getSelectedItemPosition() ;
                if (spinner.getAdapter().equals(mAdapter)) {
                    spinner.setAdapter(mAdapter);
                    spinner3.setAdapter(mAdapter);
                } else {
                    spinner.setAdapter(mAdapter);
                    spinner3.setAdapter(mAdapter);
                }
                spinner.setSelection(positionSpinner2);
                spinner3.setSelection(positionSpinner1);
            }
        });


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {

                countryItem = (CountryItem) parent.getItemAtPosition((position));
                clickedCountryName = countryItem.getCountryName();
                tv.setText(clickedCountryName);
                Toast.makeText(ExchangeActivity.this,clickedCountryName + " Selected", Toast.LENGTH_SHORT).show();
//                itemselect();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {

                countryItemTo = (CountryItem) parent.getItemAtPosition((position));
                clickedCountryNameTo = countryItemTo.getCountryName();
                Toast.makeText(ExchangeActivity.this,clickedCountryNameTo + " Selected", Toast.LENGTH_SHORT).show();

//                itemselect();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        convertbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppManage.getInstance(ExchangeActivity.this).showInterstitialAd(ExchangeActivity.this, new AppManage.MyCallback() {
                    public void callbackCall() {

                        Double totalConvertedAmount;
                        Double amount = Double.parseDouble(enterAmount.getText().toString());

                        if (clickedCountryName.equals("Rupee") && clickedCountryNameTo.equals("US Dollar")) {
                            totalConvertedAmount = amount * 81.72;
                            String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
                            convertAmount.setText("" + tot);
                        }
                        if (clickedCountryName.equals("Rupee") && clickedCountryNameTo.equals("Euro")) {
                            totalConvertedAmount = amount * 79.17;
                            String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
                            convertAmount.setText("" + tot);
                        }
                        if (clickedCountryName.equals("Rupee") && clickedCountryNameTo.equals("Yen")) {
                            totalConvertedAmount = amount * 0.56;
                            String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
                            convertAmount.setText("" + tot);
                        }
                        if (clickedCountryName.equals("Rupee") && clickedCountryNameTo.equals("Pound")) {
                            totalConvertedAmount = amount * 88.29;
                            String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
                            convertAmount.setText("" + tot);
                        }
                        if (clickedCountryName.equals("Rupee") && clickedCountryNameTo.equals("Australian Dollar")) {
                            totalConvertedAmount = amount * 52.98;
                            String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
                            convertAmount.setText("" + tot);
                        }
                        if (clickedCountryName.equals("Rupee") && clickedCountryNameTo.equals("Ruble")) {
                            totalConvertedAmount = amount * 1.41;
                            String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
                            convertAmount.setText("" + tot);
                        }
                        if (clickedCountryName.equals("Rupee") && clickedCountryNameTo.equals("Yuan")) {
                            totalConvertedAmount = amount * 11.36;
                            String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
                            convertAmount.setText("" + tot);
                        }
                        if (clickedCountryName.equals("Rupee") && clickedCountryNameTo.equals("South Korean Won")) {
                            totalConvertedAmount = amount * 0.05;
                            String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
                            convertAmount.setText("" + tot);
                        }
                        if (clickedCountryName.equals("Rupee") && clickedCountryNameTo.equals("North Korean Won")) {
                            totalConvertedAmount = amount * 0.09;
                            String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
                            convertAmount.setText("" + tot);
                        }
                        if (clickedCountryName.equals("Rupee") && clickedCountryNameTo.equals("Peso")) {
                            totalConvertedAmount = amount * 4.04;
                            String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
                            convertAmount.setText("" + tot);
                        } if (clickedCountryName.equals("US Dollar") && clickedCountryNameTo.equals("Rupee")) {
                            totalConvertedAmount = amount * 0.012;
                            String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
                            convertAmount.setText("" + tot);
                        }
                        if (clickedCountryName.equals("US Dollar") && clickedCountryNameTo.equals("Euro")) {
                            totalConvertedAmount = amount * 0.96;
                            String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
                            convertAmount.setText("" + tot);
                        }
                        if (clickedCountryName.equals("US Dollar") && clickedCountryNameTo.equals("Yen")) {
                            totalConvertedAmount = amount * 0.0069107977;
                            String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
                            convertAmount.setText("" + tot);
                        }
                        if (clickedCountryName.equals("US Dollar") && clickedCountryNameTo.equals("Pound")) {
                            totalConvertedAmount = amount * 1.08;
                            String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
                            convertAmount.setText("" + tot);
                        }
                        if (clickedCountryName.equals("US Dollar") && clickedCountryNameTo.equals("Australian Dollar")) {
                            totalConvertedAmount = amount * 0.64;
                            String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
                            convertAmount.setText("" + tot);
                        }
                        if (clickedCountryName.equals("US Dollar") && clickedCountryNameTo.equals("Ruble")) {
                            totalConvertedAmount = amount * 0.01;
                            String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
                            convertAmount.setText("" + tot);
                        }
                        if (clickedCountryName.equals("US Dollar") && clickedCountryNameTo.equals("Yuan")) {
                            totalConvertedAmount = amount * 0.13;
                            String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
                            convertAmount.setText("" + tot);
                        }
                        if (clickedCountryName.equals("US Dollar") && clickedCountryNameTo.equals("South Korean Won")) {
                            totalConvertedAmount = amount *0.00069502417;
                            String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
                            convertAmount.setText("" + tot);
                        }
                        if (clickedCountryName.equals("US Dollar") && clickedCountryNameTo.equals("North Korean Won")) {
                            totalConvertedAmount = amount *0.0011110755;
                            String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
                            convertAmount.setText("" + tot);
                        }
                        if (clickedCountryName.equals("US Dollar") && clickedCountryNameTo.equals("Peso")) {
                            totalConvertedAmount = amount * 0.04;
                            String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
                            convertAmount.setText("" + tot);
                        } if (clickedCountryName.equals("Euro") && clickedCountryNameTo.equals("Rupee")) {
                            totalConvertedAmount = amount * 0.01;
                            String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
                            convertAmount.setText("" + tot);
                        }
                        if (clickedCountryName.equals("Euro") && clickedCountryNameTo.equals("US Dollar")) {
                            totalConvertedAmount = amount * 1.03;
                            String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
                            convertAmount.setText("" + tot);
                        }
                        if (clickedCountryName.equals("Euro") && clickedCountryNameTo.equals("Yen")) {
                            totalConvertedAmount = amount * 0.007148747;
                            String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
                            convertAmount.setText("" + tot);
                        }
                        if (clickedCountryName.equals("Euro") && clickedCountryNameTo.equals("Pound")) {
                            totalConvertedAmount = amount * 1.11;
                            String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
                            convertAmount.setText("" + tot);
                        }
                        if (clickedCountryName.equals("Euro") && clickedCountryNameTo.equals("Australian Dollar")) {
                            totalConvertedAmount = amount * 0.66;
                            String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
                            convertAmount.setText("" + tot);
                        }
                        if (clickedCountryName.equals("Euro") && clickedCountryNameTo.equals("Ruble")) {
                            totalConvertedAmount = amount * 0.01;
                            String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
                            convertAmount.setText("" + tot);
                        }
                        if (clickedCountryName.equals("Euro") && clickedCountryNameTo.equals("Yuan")) {
                            totalConvertedAmount = amount * 0.14;
                            String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
                            convertAmount.setText("" + tot);
                        }
                        if (clickedCountryName.equals("Euro") && clickedCountryNameTo.equals("South Korean Won")) {
                            totalConvertedAmount = amount * 0.00071921423;
                            String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
                            convertAmount.setText("" + tot);
                        }
                        if (clickedCountryName.equals("Euro") && clickedCountryNameTo.equals("North Korean Won")) {
                            totalConvertedAmount = amount * 0.0011493089;
                            String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
                            convertAmount.setText("" + tot);
                        }
                        if (clickedCountryName.equals("Euro") && clickedCountryNameTo.equals("Peso")) {
                            totalConvertedAmount = amount * 0.05;
                            String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
                            convertAmount.setText("" + tot);
                        } if (clickedCountryName.equals("Yen") && clickedCountryNameTo.equals("Rupee")) {
                            totalConvertedAmount = amount * 1.7672506;
                            String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
                            convertAmount.setText("" + tot);
                        }
                        if (clickedCountryName.equals("Yen") && clickedCountryNameTo.equals("Euro")) {
                            totalConvertedAmount = amount * 139.88811;
                            String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
                            convertAmount.setText("" + tot);
                        }
                        if (clickedCountryName.equals("Yen") && clickedCountryNameTo.equals("US Dollar")) {
                            totalConvertedAmount = amount * 144.69705;
                            String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
                            convertAmount.setText("" + tot);
                        }
                        if (clickedCountryName.equals("Yen") && clickedCountryNameTo.equals("Pound")) {
                            totalConvertedAmount = amount * 156.2821;
                            String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
                            convertAmount.setText("" + tot);
                        }
                        if (clickedCountryName.equals("Yen") && clickedCountryNameTo.equals("Australian Dollar")) {
                            totalConvertedAmount = amount * 93.553871;
                            String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
                            convertAmount.setText("" + tot);
                        }
                        if (clickedCountryName.equals("Yen") && clickedCountryNameTo.equals("Ruble")) {
                            totalConvertedAmount = amount * 2.505339;
                            String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
                            convertAmount.setText("" + tot);
                        }
                        if (clickedCountryName.equals("Yen") && clickedCountryNameTo.equals("Yuan")) {
                            totalConvertedAmount = amount * 20.107353;
                            String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
                            convertAmount.setText("" + tot);
                        }
                        if (clickedCountryName.equals("Yen") && clickedCountryNameTo.equals("South Korean Won")) {
                            totalConvertedAmount = amount * 0.10057905;
                            String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
                            convertAmount.setText("" + tot);
                        }
                        if (clickedCountryName.equals("Yen") && clickedCountryNameTo.equals("North Korean Won")) {
                            totalConvertedAmount = amount * 0.16079851;
                            String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
                            convertAmount.setText("" + tot);
                        }
                        if (clickedCountryName.equals("Yen") && clickedCountryNameTo.equals("Peso")) {
                            totalConvertedAmount = amount * 7.1677912;
                            String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
                            convertAmount.setText("" + tot);
                        } if (clickedCountryName.equals("Pound") && clickedCountryNameTo.equals("Rupee")) {
                            totalConvertedAmount = amount * 0.011304441;
                            String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
                            convertAmount.setText("" + tot);
                        }
                        if (clickedCountryName.equals("Pound") && clickedCountryNameTo.equals("Euro")) {
                            totalConvertedAmount = amount * 0.89507237;
                            String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
                            convertAmount.setText("" + tot);
                        }
                        if (clickedCountryName.equals("Pound") && clickedCountryNameTo.equals("US Dollar")) {
                            totalConvertedAmount = amount * 0.92488439;
                            String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
                            convertAmount.setText("" + tot);
                        }
                        if (clickedCountryName.equals("Pound") && clickedCountryNameTo.equals("Yen")) {
                            totalConvertedAmount = amount * 0.006391924;
                            String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
                            convertAmount.setText("" + tot);
                        }
                        if (clickedCountryName.equals("Pound") && clickedCountryNameTo.equals("Australian Dollar")) {
                            totalConvertedAmount = amount * 0.59825672;
                            String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
                            convertAmount.setText("" + tot);
                        }
                        if (clickedCountryName.equals("Pound") && clickedCountryNameTo.equals("Ruble")) {
                            totalConvertedAmount = amount * 0.015941283;
                            String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
                            convertAmount.setText("" + tot);
                        }
                        if (clickedCountryName.equals("Pound") && clickedCountryNameTo.equals("Yuan")) {
                            totalConvertedAmount = amount * 0.128485;
                            String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
                            convertAmount.setText("" + tot);
                        }
                        if (clickedCountryName.equals("Pound") && clickedCountryNameTo.equals("South Korean Won")) {
                            totalConvertedAmount = amount * 0.00064295106;
                            String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
                            convertAmount.setText("" + tot);
                        }
                        if (clickedCountryName.equals("Pound") && clickedCountryNameTo.equals("North Korean Won")) {
                            totalConvertedAmount = amount * 0.0010275958;
                            String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
                            convertAmount.setText("" + tot);
                        }
                        if (clickedCountryName.equals("Pound") && clickedCountryNameTo.equals("Peso")) {
                            totalConvertedAmount = amount * 0.045820808;
                            String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
                            convertAmount.setText("" + tot);
                        } if (clickedCountryName.equals("Australian Dollar") && clickedCountryNameTo.equals("Rupee")) {
                            totalConvertedAmount = amount * 0.018887412;
                            String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
                            convertAmount.setText("" + tot);
                        }
                        if (clickedCountryName.equals("Australian Dollar") && clickedCountryNameTo.equals("Euro")) {
                            totalConvertedAmount = amount * 1.49;
                            String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
                            convertAmount.setText("" + tot);
                        }
                        if (clickedCountryName.equals("Australian Dollar") && clickedCountryNameTo.equals("US Dollar")) {
                            totalConvertedAmount = amount * 1.54;
                            String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
                            convertAmount.setText("" + tot);
                        }
                        if (clickedCountryName.equals("Australian Dollar") && clickedCountryNameTo.equals("Yen")) {
                            totalConvertedAmount = amount * 0.010689495;
                            String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
                            convertAmount.setText("" + tot);
                        }
                        if (clickedCountryName.equals("Australian Dollar") && clickedCountryNameTo.equals("Pound")) {
                            totalConvertedAmount = amount * 1.67;
                            String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
                            convertAmount.setText("" + tot);
                        }
                        if (clickedCountryName.equals("Australian Dollar") && clickedCountryNameTo.equals("Ruble")) {
                            totalConvertedAmount = amount * 0.026641402;
                            String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
                            convertAmount.setText("" + tot);
                        }
                        if (clickedCountryName.equals("Australian Dollar") && clickedCountryNameTo.equals("Yuan")) {
                            totalConvertedAmount = amount * 0.21507483;
                            String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
                            convertAmount.setText("" + tot);
                        }
                        if (clickedCountryName.equals("Australian Dollar") && clickedCountryNameTo.equals("South Korean Won")) {
                            totalConvertedAmount = amount * 0.0010752094;
                            String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
                            convertAmount.setText("" + tot);
                        }
                        if (clickedCountryName.equals("Australian Dollar") && clickedCountryNameTo.equals("North Korean Won")) {
                            totalConvertedAmount = amount * 0.0017184803;
                            String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
                            convertAmount.setText("" + tot);
                        }
                        if (clickedCountryName.equals("Australian Dollar") && clickedCountryNameTo.equals("Peso")) {
                            totalConvertedAmount = amount * 0.076610569;
                            String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
                            convertAmount.setText("" + tot);
                        } if (clickedCountryName.equals("Ruble") && clickedCountryNameTo.equals("Rupee")) {
                            totalConvertedAmount = amount * 0.70675701;
                            String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
                            convertAmount.setText("" + tot);
                        }
                        if (clickedCountryName.equals("Ruble") && clickedCountryNameTo.equals("Euro")) {
                            totalConvertedAmount = amount * 55.94;
                            String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
                            convertAmount.setText("" + tot);
                        }
                        if (clickedCountryName.equals("Ruble") && clickedCountryNameTo.equals("US Dollar")) {
                            totalConvertedAmount = amount * 57.86;
                            String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
                            convertAmount.setText("" + tot);
                        }
                        if (clickedCountryName.equals("Ruble") && clickedCountryNameTo.equals("Yen")) {
                            totalConvertedAmount = amount * 0.39982284;
                            String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
                            convertAmount.setText("" + tot);
                        }
                        if (clickedCountryName.equals("Ruble") && clickedCountryNameTo.equals("Pound")) {
                            totalConvertedAmount = amount * 62.46;
                            String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
                            convertAmount.setText("" + tot);
                        }
                        if (clickedCountryName.equals("Ruble") && clickedCountryNameTo.equals("Australian Dollar")) {
                            totalConvertedAmount = amount * 37.37;
                            String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
                            convertAmount.setText("" + tot);
                        }
                        if (clickedCountryName.equals("Ruble") && clickedCountryNameTo.equals("Yuan")) {
                            totalConvertedAmount = amount * 8.03;
                            String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
                            convertAmount.setText("" + tot);
                        }
                        if (clickedCountryName.equals("Ruble") && clickedCountryNameTo.equals("South Korean Won")) {
                            totalConvertedAmount = amount * 0.040110178;
                            String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
                            convertAmount.setText("" + tot);
                        }
                        if (clickedCountryName.equals("Ruble") && clickedCountryNameTo.equals("North Korean Won")) {
                            totalConvertedAmount = amount * 0.064180784;
                            String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
                            convertAmount.setText("" + tot);
                        }
                        if (clickedCountryName.equals("Ruble") && clickedCountryNameTo.equals("Peso")) {
                            totalConvertedAmount = amount * 2.86;
                            String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
                            convertAmount.setText("" + tot);
                        } if (clickedCountryName.equals("Yuan") && clickedCountryNameTo.equals("Rupee")) {
                            totalConvertedAmount = amount * 0.087906807;
                            String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
                            convertAmount.setText("" + tot);
                        }
                        if (clickedCountryName.equals("Yuan") && clickedCountryNameTo.equals("Euro")) {
                            totalConvertedAmount = amount * 6.95;
                            String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
                            convertAmount.setText("" + tot);
                        }
                        if (clickedCountryName.equals("Yuan") && clickedCountryNameTo.equals("US Dollar")) {
                            totalConvertedAmount = amount * 7.20;
                            String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
                            convertAmount.setText("" + tot);
                        }
                        if (clickedCountryName.equals("Yuan") && clickedCountryNameTo.equals("Yen")) {
                            totalConvertedAmount = amount * 0.049744114;
                            String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
                            convertAmount.setText("" + tot);
                        }
                        if (clickedCountryName.equals("Yuan") && clickedCountryNameTo.equals("Pound")) {
                            totalConvertedAmount = amount * 7.76;
                            String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
                            convertAmount.setText("" + tot);
                        }
                        if (clickedCountryName.equals("Yuan") && clickedCountryNameTo.equals("Australian Dollar")) {
                            totalConvertedAmount = amount * 4.65;
                            String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
                            convertAmount.setText("" + tot);
                        }
                        if (clickedCountryName.equals("Yuan") && clickedCountryNameTo.equals("Ruble")) {
                            totalConvertedAmount = amount * 0.12435818;
                            String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
                            convertAmount.setText("" + tot);
                        }
                        if (clickedCountryName.equals("Yuan") && clickedCountryNameTo.equals("South Korean Won")) {
                            totalConvertedAmount = amount * 0.0049998182;
                            String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
                            convertAmount.setText("" + tot);
                        }
                        if (clickedCountryName.equals("Yuan") && clickedCountryNameTo.equals("North Korean Won")) {
                            totalConvertedAmount = amount * 0.0080014705;
                            String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
                            convertAmount.setText("" + tot);
                        }
                        if (clickedCountryName.equals("Yuan") && clickedCountryNameTo.equals("Peso")) {
                            totalConvertedAmount = amount * 0.35607752;
                            String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
                            convertAmount.setText("" + tot);
                        } if (clickedCountryName.equals("South Korean Won") && clickedCountryNameTo.equals("Rupee")) {
                            totalConvertedAmount = amount * 17.58266;
                            String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
                            convertAmount.setText("" + tot);
                        }
                        if (clickedCountryName.equals("South Korean Won") && clickedCountryNameTo.equals("Euro")) {
                            totalConvertedAmount = amount * 1391.8076;
                            String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
                            convertAmount.setText("" + tot);
                        }
                        if (clickedCountryName.equals("South Korean Won") && clickedCountryNameTo.equals("US Dollar")) {
                            totalConvertedAmount = amount * 1439.5563;
                            String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
                            convertAmount.setText("" + tot);
                        }
                        if (clickedCountryName.equals("South Korean Won") && clickedCountryNameTo.equals("Yen")) {
                            totalConvertedAmount = amount * 9.9478858;
                            String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
                            convertAmount.setText("" + tot);
                        }
                        if (clickedCountryName.equals("South Korean Won") && clickedCountryNameTo.equals("Pound")) {
                            totalConvertedAmount = amount * 1552.2845;
                            String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
                            convertAmount.setText("" + tot);
                        }
                        if (clickedCountryName.equals("South Korean Won") && clickedCountryNameTo.equals("Australian Dollar")) {
                            totalConvertedAmount = amount * 930.08658;
                            String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
                            convertAmount.setText("" + tot);
                        }
                        if (clickedCountryName.equals("South Korean Won") && clickedCountryNameTo.equals("Ruble")) {
                            totalConvertedAmount = amount * 24.788181;
                            String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
                            convertAmount.setText("" + tot);
                        }
                        if (clickedCountryName.equals("South Korean Won") && clickedCountryNameTo.equals("Yuan")) {
                            totalConvertedAmount = amount * 199.88542;
                            String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
                            convertAmount.setText("" + tot);
                        }
                        if (clickedCountryName.equals("South Korean Won") && clickedCountryNameTo.equals("North Korean Won")) {
                            totalConvertedAmount = amount * 1.5995429;
                            String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
                            convertAmount.setText("" + tot);
                        }
                        if (clickedCountryName.equals("South Korean Won") && clickedCountryNameTo.equals("Peso")) {
                            totalConvertedAmount = amount * 71.160373;
                            String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
                            convertAmount.setText("" + tot);
                        } if (clickedCountryName.equals("North Korean Won") && clickedCountryNameTo.equals("Rupee")) {
                            totalConvertedAmount = amount * 10.987911;
                            String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
                            convertAmount.setText("" + tot);
                        }
                        if (clickedCountryName.equals("North Korean Won") && clickedCountryNameTo.equals("Euro")) {
                            totalConvertedAmount = amount * 869.83247;
                            String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
                            convertAmount.setText("" + tot);
                        }
                        if (clickedCountryName.equals("North Korean Won") && clickedCountryNameTo.equals("US Dollar")) {
                            totalConvertedAmount = amount * 899.98226;
                            String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
                            convertAmount.setText("" + tot);
                        }
                        if (clickedCountryName.equals("North Korean Won") && clickedCountryNameTo.equals("Yen")) {
                            totalConvertedAmount = amount * 6.2190007;
                            String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
                            convertAmount.setText("" + tot);
                        }
                        if (clickedCountryName.equals("North Korean Won") && clickedCountryNameTo.equals("Pound")) {
                            totalConvertedAmount = amount * 972.40272;
                            String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
                            convertAmount.setText("" + tot);
                        }
                        if (clickedCountryName.equals("North Korean Won") && clickedCountryNameTo.equals("Australian Dollar")) {
                            totalConvertedAmount = amount * 581.72895;
                            String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
                            convertAmount.setText("" + tot);
                        }
                        if (clickedCountryName.equals("North Korean Won") && clickedCountryNameTo.equals("Ruble")) {
                            totalConvertedAmount = amount * 15.523804;
                            String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
                            convertAmount.setText("" + tot);
                        }
                        if (clickedCountryName.equals("North Korean Won") && clickedCountryNameTo.equals("Yuan")) {
                            totalConvertedAmount = amount * 124.96553;
                            String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
                            convertAmount.setText("" + tot);
                        }
                        if (clickedCountryName.equals("North Korean Won") && clickedCountryNameTo.equals("South Korean Won")) {
                            totalConvertedAmount = amount * 0.62517626;
                            String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
                            convertAmount.setText("" + tot);
                        }
                        if (clickedCountryName.equals("North Korean Won") && clickedCountryNameTo.equals("Peso")) {
                            totalConvertedAmount = amount * 44.494157;
                            String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
                            convertAmount.setText("" + tot);
                        } if (clickedCountryName.equals("Peso") && clickedCountryNameTo.equals("Rupee")) {
                            totalConvertedAmount = amount * 0.24697983;
                            String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
                            convertAmount.setText("" + tot);
                        }
                        if (clickedCountryName.equals("Peso") && clickedCountryNameTo.equals("Euro")) {
                            totalConvertedAmount = amount * 19.549434;
                            String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
                            convertAmount.setText("" + tot);
                        }
                        if (clickedCountryName.equals("Peso") && clickedCountryNameTo.equals("US Dollar")) {
                            totalConvertedAmount = amount * 20.22;
                            String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
                            convertAmount.setText("" + tot);
                        }
                        if (clickedCountryName.equals("Peso") && clickedCountryNameTo.equals("Yen")) {
                            totalConvertedAmount = amount * 0.13979372;
                            String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
                            convertAmount.setText("" + tot);
                        }
                        if (clickedCountryName.equals("Peso") && clickedCountryNameTo.equals("Pound")) {
                            totalConvertedAmount = amount * 21.84;
                            String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
                            convertAmount.setText("" + tot);
                        }
                        if (clickedCountryName.equals("Peso") && clickedCountryNameTo.equals("Australian Dollar")) {
                            totalConvertedAmount = amount * 13.06;
                            String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
                            convertAmount.setText("" + tot);
                        }
                        if (clickedCountryName.equals("Peso") && clickedCountryNameTo.equals("Ruble")) {
                            totalConvertedAmount = amount * 0.34825147;
                            String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
                            convertAmount.setText("" + tot);
                        }
                        if (clickedCountryName.equals("Peso") && clickedCountryNameTo.equals("Yuan")) {
                            totalConvertedAmount = amount * 2.80;
                            String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
                            convertAmount.setText("" + tot);
                        }
                        if (clickedCountryName.equals("Peso") && clickedCountryNameTo.equals("South Korean Won")) {
                            totalConvertedAmount = amount * 0.014044832;
                            String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
                            convertAmount.setText("" + tot);
                        }
                        if (clickedCountryName.equals("Peso") && clickedCountryNameTo.equals("North Korean Won")) {
                            totalConvertedAmount = amount * 0.022473992;
                            String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
                            convertAmount.setText("" + tot);
                        }
                    }
                }, "", AppManage.app_mainClickCntSwAd);
            }
        });

        backbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }

//    private void itemselect() {
//
//        tv2.setText("1 " + clickedCountryName + " = " + clickedCountryNameTo);
//
//    }

    private void iniCountryList() {

        countryList =new ArrayList<>();
        countryList.add(new CountryItem("Rupee",R.drawable.rupee));
        countryList.add(new CountryItem("US Dollar",R.drawable.dollar));
        countryList.add(new CountryItem("Euro",R.drawable.euro));
        countryList.add(new CountryItem("Yen",R.drawable.yen));
        countryList.add(new CountryItem("Australian Dollar",R.drawable.dollar));
        countryList.add(new CountryItem("Pound",R.drawable.pound));
        countryList.add(new CountryItem("Ruble",R.drawable.ruble));
        countryList.add(new CountryItem("Yuan",R.drawable.yen));
        countryList.add(new CountryItem("South Korean Won",R.drawable.won));
        countryList.add(new CountryItem("North Korean Won",R.drawable.won));
        countryList.add(new CountryItem("Peso",R.drawable.peso));

    }


    public void onBackPressed(){
        super.onBackPressed();
    }
}



//    ArrayAdapter<CharSequence>adapter2= ArrayAdapter.createFromResource(this, R.array.currency, android.R.layout.list_content);
//        adapter2.setDropDownViewResource(android.R.layout.list_content);
//
//                spinner.setAdapter(adapter2);
//                spinner.setOnItemSelectedListener(this);
//
//                ArrayAdapter<CharSequence>adapter3= ArrayAdapter.createFromResource(this, R.array.currency, android.R.layout.simple_spinner_dropdown_item);
//        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner3.setAdapter(adapter3);
//        spinner3.setOnItemSelectedListener(this);

//@Override
//    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
//        String text = parent.getItemAtPosition(position).toString();
//        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    public void onNothingSelected(AdapterView<?> parent) {
//
//    }

//        Spinner spinner=findViewById(R.id.spinner);
//        Spinner spinner3=findViewById(R.id.spinner3);
//        backbt=findViewById(R.id.backbt);
//        tv=findViewById(R.id.tv1);
//        tv2=findViewById(R.id.tv2);
//        convertbt=findViewById(R.id.convertbt);
//        enterAmount=findViewById(R.id.edittextone);
//        convertAmount=findViewById(R.id.result);
//        iniCountryList();
//
//        mAdapter = new CountryAdapter(this,countryList);
//        spinner.setAdapter(mAdapter);
//        spinner3.setAdapter(mAdapter);
//
//
//        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
//
//                countryItem = (CountryItem) parent.getItemAtPosition((position));
//                clickedCountryName = countryItem.getCountryName();
//                tv.setText(clickedCountryName);
//                Toast.makeText(ExchangeActivity.this,clickedCountryName + " Selected", Toast.LENGTH_SHORT).show();
//                itemselect();
//
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
//
//        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
//
//                countryItemTo = (CountryItem) parent.getItemAtPosition((position));
//                clickedCountryNameTo = countryItemTo.getCountryName();
//                Toast.makeText(ExchangeActivity.this,clickedCountryNameTo + " Selected", Toast.LENGTH_SHORT).show();
//
//                itemselect();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
//
//        convertbt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Double totalConvertedAmount;
//                Double amount = Double.parseDouble(enterAmount.getText().toString());
//
//                if (clickedCountryName.equals("Rupee") && clickedCountryNameTo.equals("US Dollar")) {
//                    totalConvertedAmount = amount * 81.72;
//                    String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
//                    convertAmount.setText("" + tot);
//                }
//                if (clickedCountryName.equals("Rupee") && clickedCountryNameTo.equals("Euro")) {
//                    totalConvertedAmount = amount * 79.17;
//                    String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
//                    convertAmount.setText("" + tot);
//                }
//                if (clickedCountryName.equals("Rupee") && clickedCountryNameTo.equals("Yen")) {
//                    totalConvertedAmount = amount * 0.56;
//                    String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
//                    convertAmount.setText("" + tot);
//                }
//                if (clickedCountryName.equals("Rupee") && clickedCountryNameTo.equals("Pound")) {
//                    totalConvertedAmount = amount * 88.29;
//                    String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
//                    convertAmount.setText("" + tot);
//                }
//                if (clickedCountryName.equals("Rupee") && clickedCountryNameTo.equals("Australian Dollar")) {
//                    totalConvertedAmount = amount * 52.98;
//                    String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
//                    convertAmount.setText("" + tot);
//                }
//                if (clickedCountryName.equals("Rupee") && clickedCountryNameTo.equals("Ruble")) {
//                    totalConvertedAmount = amount * 1.41;
//                    String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
//                    convertAmount.setText("" + tot);
//                }
//                if (clickedCountryName.equals("Rupee") && clickedCountryNameTo.equals("Yuan")) {
//                    totalConvertedAmount = amount * 11.36;
//                    String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
//                    convertAmount.setText("" + tot);
//                }
//                if (clickedCountryName.equals("Rupee") && clickedCountryNameTo.equals("South Korean Won")) {
//                    totalConvertedAmount = amount * 0.05;
//                    String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
//                    convertAmount.setText("" + tot);
//                }
//                if (clickedCountryName.equals("Rupee") && clickedCountryNameTo.equals("North Korean Won")) {
//                    totalConvertedAmount = amount * 0.09;
//                    String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
//                    convertAmount.setText("" + tot);
//                }
//                if (clickedCountryName.equals("Rupee") && clickedCountryNameTo.equals("Peso")) {
//                    totalConvertedAmount = amount * 4.04;
//                    String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
//                    convertAmount.setText("" + tot);
//                } if (clickedCountryName.equals("US Dollar") && clickedCountryNameTo.equals("Rupee")) {
//                    totalConvertedAmount = amount * 0.012;
//                    String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
//                    convertAmount.setText("" + tot);
//                }
//                if (clickedCountryName.equals("US Dollar") && clickedCountryNameTo.equals("Euro")) {
//                    totalConvertedAmount = amount * 0.96;
//                    String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
//                    convertAmount.setText("" + tot);
//                }
//                if (clickedCountryName.equals("US Dollar") && clickedCountryNameTo.equals("Yen")) {
//                    totalConvertedAmount = amount * 0.0069107977;
//                    String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
//                    convertAmount.setText("" + tot);
//                }
//                if (clickedCountryName.equals("US Dollar") && clickedCountryNameTo.equals("Pound")) {
//                    totalConvertedAmount = amount * 1.08;
//                    String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
//                    convertAmount.setText("" + tot);
//                }
//                if (clickedCountryName.equals("US Dollar") && clickedCountryNameTo.equals("Australian Dollar")) {
//                    totalConvertedAmount = amount * 0.64;
//                    String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
//                    convertAmount.setText("" + tot);
//                }
//                if (clickedCountryName.equals("US Dollar") && clickedCountryNameTo.equals("Ruble")) {
//                    totalConvertedAmount = amount * 0.01;
//                    String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
//                    convertAmount.setText("" + tot);
//                }
//                if (clickedCountryName.equals("US Dollar") && clickedCountryNameTo.equals("Yuan")) {
//                    totalConvertedAmount = amount * 0.13;
//                    String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
//                    convertAmount.setText("" + tot);
//                }
//                if (clickedCountryName.equals("US Dollar") && clickedCountryNameTo.equals("South Korean Won")) {
//                    totalConvertedAmount = amount *0.00069502417;
//                    String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
//                    convertAmount.setText("" + tot);
//                }
//                if (clickedCountryName.equals("US Dollar") && clickedCountryNameTo.equals("North Korean Won")) {
//                    totalConvertedAmount = amount *0.0011110755;
//                    String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
//                    convertAmount.setText("" + tot);
//                }
//                if (clickedCountryName.equals("US Dollar") && clickedCountryNameTo.equals("Peso")) {
//                    totalConvertedAmount = amount * 0.04;
//                    String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
//                    convertAmount.setText("" + tot);
//                } if (clickedCountryName.equals("Euro") && clickedCountryNameTo.equals("Rupee")) {
//                    totalConvertedAmount = amount * 0.01;
//                    String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
//                    convertAmount.setText("" + tot);
//                }
//                if (clickedCountryName.equals("Euro") && clickedCountryNameTo.equals("US Dollar")) {
//                    totalConvertedAmount = amount * 1.03;
//                    String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
//                    convertAmount.setText("" + tot);
//                }
//                if (clickedCountryName.equals("Euro") && clickedCountryNameTo.equals("Yen")) {
//                    totalConvertedAmount = amount * 0.007148747;
//                    String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
//                    convertAmount.setText("" + tot);
//                }
//                if (clickedCountryName.equals("Euro") && clickedCountryNameTo.equals("Pound")) {
//                    totalConvertedAmount = amount * 1.11;
//                    String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
//                    convertAmount.setText("" + tot);
//                }
//                if (clickedCountryName.equals("Euro") && clickedCountryNameTo.equals("Australian Dollar")) {
//                    totalConvertedAmount = amount * 0.66;
//                    String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
//                    convertAmount.setText("" + tot);
//                }
//                if (clickedCountryName.equals("Euro") && clickedCountryNameTo.equals("Ruble")) {
//                    totalConvertedAmount = amount * 0.01;
//                    String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
//                    convertAmount.setText("" + tot);
//                }
//                if (clickedCountryName.equals("Euro") && clickedCountryNameTo.equals("Yuan")) {
//                    totalConvertedAmount = amount * 0.14;
//                    String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
//                    convertAmount.setText("" + tot);
//                }
//                if (clickedCountryName.equals("Euro") && clickedCountryNameTo.equals("South Korean Won")) {
//                    totalConvertedAmount = amount * 0.00071921423;
//                    String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
//                    convertAmount.setText("" + tot);
//                }
//                if (clickedCountryName.equals("Euro") && clickedCountryNameTo.equals("North Korean Won")) {
//                    totalConvertedAmount = amount * 0.0011493089;
//                    String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
//                    convertAmount.setText("" + tot);
//                }
//                if (clickedCountryName.equals("Euro") && clickedCountryNameTo.equals("Peso")) {
//                    totalConvertedAmount = amount * 0.05;
//                    String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
//                    convertAmount.setText("" + tot);
//                } if (clickedCountryName.equals("Yen") && clickedCountryNameTo.equals("Rupee")) {
//                    totalConvertedAmount = amount * 1.7672506;
//                    String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
//                    convertAmount.setText("" + tot);
//                }
//                if (clickedCountryName.equals("Yen") && clickedCountryNameTo.equals("Euro")) {
//                    totalConvertedAmount = amount * 139.88811;
//                    String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
//                    convertAmount.setText("" + tot);
//                }
//                if (clickedCountryName.equals("Yen") && clickedCountryNameTo.equals("US Dollar")) {
//                    totalConvertedAmount = amount * 144.69705;
//                    String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
//                    convertAmount.setText("" + tot);
//                }
//                if (clickedCountryName.equals("Yen") && clickedCountryNameTo.equals("Pound")) {
//                    totalConvertedAmount = amount * 156.2821;
//                    String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
//                    convertAmount.setText("" + tot);
//                }
//                if (clickedCountryName.equals("Yen") && clickedCountryNameTo.equals("Australian Dollar")) {
//                    totalConvertedAmount = amount * 93.553871;
//                    String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
//                    convertAmount.setText("" + tot);
//                }
//                if (clickedCountryName.equals("Yen") && clickedCountryNameTo.equals("Ruble")) {
//                    totalConvertedAmount = amount * 2.505339;
//                    String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
//                    convertAmount.setText("" + tot);
//                }
//                if (clickedCountryName.equals("Yen") && clickedCountryNameTo.equals("Yuan")) {
//                    totalConvertedAmount = amount * 20.107353;
//                    String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
//                    convertAmount.setText("" + tot);
//                }
//                if (clickedCountryName.equals("Yen") && clickedCountryNameTo.equals("South Korean Won")) {
//                    totalConvertedAmount = amount * 0.10057905;
//                    String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
//                    convertAmount.setText("" + tot);
//                }
//                if (clickedCountryName.equals("Yen") && clickedCountryNameTo.equals("North Korean Won")) {
//                    totalConvertedAmount = amount * 0.16079851;
//                    String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
//                    convertAmount.setText("" + tot);
//                }
//                if (clickedCountryName.equals("Yen") && clickedCountryNameTo.equals("Peso")) {
//                    totalConvertedAmount = amount * 7.1677912;
//                    String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
//                    convertAmount.setText("" + tot);
//                } if (clickedCountryName.equals("Pound") && clickedCountryNameTo.equals("Rupee")) {
//                    totalConvertedAmount = amount * 0.011304441;
//                    String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
//                    convertAmount.setText("" + tot);
//                }
//                if (clickedCountryName.equals("Pound") && clickedCountryNameTo.equals("Euro")) {
//                    totalConvertedAmount = amount * 0.89507237;
//                    String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
//                    convertAmount.setText("" + tot);
//                }
//                if (clickedCountryName.equals("Pound") && clickedCountryNameTo.equals("US Dollar")) {
//                    totalConvertedAmount = amount * 0.92488439;
//                    String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
//                    convertAmount.setText("" + tot);
//                }
//                if (clickedCountryName.equals("Pound") && clickedCountryNameTo.equals("Yen")) {
//                    totalConvertedAmount = amount * 0.006391924;
//                    String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
//                    convertAmount.setText("" + tot);
//                }
//                if (clickedCountryName.equals("Pound") && clickedCountryNameTo.equals("Australian Dollar")) {
//                    totalConvertedAmount = amount * 0.59825672;
//                    String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
//                    convertAmount.setText("" + tot);
//                }
//                if (clickedCountryName.equals("Pound") && clickedCountryNameTo.equals("Ruble")) {
//                    totalConvertedAmount = amount * 0.015941283;
//                    String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
//                    convertAmount.setText("" + tot);
//                }
//                if (clickedCountryName.equals("Pound") && clickedCountryNameTo.equals("Yuan")) {
//                    totalConvertedAmount = amount * 0.128485;
//                    String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
//                    convertAmount.setText("" + tot);
//                }
//                if (clickedCountryName.equals("Pound") && clickedCountryNameTo.equals("South Korean Won")) {
//                    totalConvertedAmount = amount * 0.00064295106;
//                    String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
//                    convertAmount.setText("" + tot);
//                }
//                if (clickedCountryName.equals("Pound") && clickedCountryNameTo.equals("North Korean Won")) {
//                    totalConvertedAmount = amount * 0.0010275958;
//                    String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
//                    convertAmount.setText("" + tot);
//                }
//                if (clickedCountryName.equals("Pound") && clickedCountryNameTo.equals("Peso")) {
//                    totalConvertedAmount = amount * 0.045820808;
//                    String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
//                    convertAmount.setText("" + tot);
//                } if (clickedCountryName.equals("Australian Dollar") && clickedCountryNameTo.equals("Rupee")) {
//                    totalConvertedAmount = amount * 0.018887412;
//                    String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
//                    convertAmount.setText("" + tot);
//                }
//                if (clickedCountryName.equals("Australian Dollar") && clickedCountryNameTo.equals("Euro")) {
//                    totalConvertedAmount = amount * 1.49;
//                    String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
//                    convertAmount.setText("" + tot);
//                }
//                if (clickedCountryName.equals("Australian Dollar") && clickedCountryNameTo.equals("US Dollar")) {
//                    totalConvertedAmount = amount * 1.54;
//                    String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
//                    convertAmount.setText("" + tot);
//                }
//                if (clickedCountryName.equals("Australian Dollar") && clickedCountryNameTo.equals("Yen")) {
//                    totalConvertedAmount = amount * 0.010689495;
//                    String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
//                    convertAmount.setText("" + tot);
//                }
//                if (clickedCountryName.equals("Australian Dollar") && clickedCountryNameTo.equals("Pound")) {
//                    totalConvertedAmount = amount * 1.67;
//                    String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
//                    convertAmount.setText("" + tot);
//                }
//                if (clickedCountryName.equals("Australian Dollar") && clickedCountryNameTo.equals("Ruble")) {
//                    totalConvertedAmount = amount * 0.026641402;
//                    String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
//                    convertAmount.setText("" + tot);
//                }
//                if (clickedCountryName.equals("Australian Dollar") && clickedCountryNameTo.equals("Yuan")) {
//                    totalConvertedAmount = amount * 0.21507483;
//                    String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
//                    convertAmount.setText("" + tot);
//                }
//                if (clickedCountryName.equals("Australian Dollar") && clickedCountryNameTo.equals("South Korean Won")) {
//                    totalConvertedAmount = amount * 0.0010752094;
//                    String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
//                    convertAmount.setText("" + tot);
//                }
//                if (clickedCountryName.equals("Australian Dollar") && clickedCountryNameTo.equals("North Korean Won")) {
//                    totalConvertedAmount = amount * 0.0017184803;
//                    String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
//                    convertAmount.setText("" + tot);
//                }
//                if (clickedCountryName.equals("Australian Dollar") && clickedCountryNameTo.equals("Peso")) {
//                    totalConvertedAmount = amount * 0.076610569;
//                    String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
//                    convertAmount.setText("" + tot);
//                } if (clickedCountryName.equals("Ruble") && clickedCountryNameTo.equals("Rupee")) {
//                    totalConvertedAmount = amount * 0.70675701;
//                    String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
//                    convertAmount.setText("" + tot);
//                }
//                if (clickedCountryName.equals("Ruble") && clickedCountryNameTo.equals("Euro")) {
//                    totalConvertedAmount = amount * 55.94;
//                    String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
//                    convertAmount.setText("" + tot);
//                }
//                if (clickedCountryName.equals("Ruble") && clickedCountryNameTo.equals("US Dollar")) {
//                    totalConvertedAmount = amount * 57.86;
//                    String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
//                    convertAmount.setText("" + tot);
//                }
//                if (clickedCountryName.equals("Ruble") && clickedCountryNameTo.equals("Yen")) {
//                    totalConvertedAmount = amount * 0.39982284;
//                    String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
//                    convertAmount.setText("" + tot);
//                }
//                if (clickedCountryName.equals("Ruble") && clickedCountryNameTo.equals("Pound")) {
//                    totalConvertedAmount = amount * 62.46;
//                    String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
//                    convertAmount.setText("" + tot);
//                }
//                if (clickedCountryName.equals("Ruble") && clickedCountryNameTo.equals("Australian Dollar")) {
//                    totalConvertedAmount = amount * 37.37;
//                    String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
//                    convertAmount.setText("" + tot);
//                }
//                if (clickedCountryName.equals("Ruble") && clickedCountryNameTo.equals("Yuan")) {
//                    totalConvertedAmount = amount * 8.03;
//                    String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
//                    convertAmount.setText("" + tot);
//                }
//                if (clickedCountryName.equals("Ruble") && clickedCountryNameTo.equals("South Korean Won")) {
//                    totalConvertedAmount = amount * 0.040110178;
//                    String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
//                    convertAmount.setText("" + tot);
//                }
//                if (clickedCountryName.equals("Ruble") && clickedCountryNameTo.equals("North Korean Won")) {
//                    totalConvertedAmount = amount * 0.064180784;
//                    String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
//                    convertAmount.setText("" + tot);
//                }
//                if (clickedCountryName.equals("Ruble") && clickedCountryNameTo.equals("Peso")) {
//                    totalConvertedAmount = amount * 2.86;
//                    String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
//                    convertAmount.setText("" + tot);
//                } if (clickedCountryName.equals("Yuan") && clickedCountryNameTo.equals("Rupee")) {
//                    totalConvertedAmount = amount * 0.087906807;
//                    String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
//                    convertAmount.setText("" + tot);
//                }
//                if (clickedCountryName.equals("Yuan") && clickedCountryNameTo.equals("Euro")) {
//                    totalConvertedAmount = amount * 6.95;
//                    String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
//                    convertAmount.setText("" + tot);
//                }
//                if (clickedCountryName.equals("Yuan") && clickedCountryNameTo.equals("US Dollar")) {
//                    totalConvertedAmount = amount * 7.20;
//                    String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
//                    convertAmount.setText("" + tot);
//                }
//                if (clickedCountryName.equals("Yuan") && clickedCountryNameTo.equals("Yen")) {
//                    totalConvertedAmount = amount * 0.049744114;
//                    String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
//                    convertAmount.setText("" + tot);
//                }
//                if (clickedCountryName.equals("Yuan") && clickedCountryNameTo.equals("Pound")) {
//                    totalConvertedAmount = amount * 7.76;
//                    String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
//                    convertAmount.setText("" + tot);
//                }
//                if (clickedCountryName.equals("Yuan") && clickedCountryNameTo.equals("Australian Dollar")) {
//                    totalConvertedAmount = amount * 4.65;
//                    String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
//                    convertAmount.setText("" + tot);
//                }
//                if (clickedCountryName.equals("Yuan") && clickedCountryNameTo.equals("Ruble")) {
//                    totalConvertedAmount = amount * 0.12435818;
//                    String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
//                    convertAmount.setText("" + tot);
//                }
//                if (clickedCountryName.equals("Yuan") && clickedCountryNameTo.equals("South Korean Won")) {
//                    totalConvertedAmount = amount * 0.0049998182;
//                    String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
//                    convertAmount.setText("" + tot);
//                }
//                if (clickedCountryName.equals("Yuan") && clickedCountryNameTo.equals("North Korean Won")) {
//                    totalConvertedAmount = amount * 0.0080014705;
//                    String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
//                    convertAmount.setText("" + tot);
//                }
//                if (clickedCountryName.equals("Yuan") && clickedCountryNameTo.equals("Peso")) {
//                    totalConvertedAmount = amount * 0.35607752;
//                    String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
//                    convertAmount.setText("" + tot);
//                } if (clickedCountryName.equals("South Korean Won") && clickedCountryNameTo.equals("Rupee")) {
//                    totalConvertedAmount = amount * 17.58266;
//                    String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
//                    convertAmount.setText("" + tot);
//                }
//                if (clickedCountryName.equals("South Korean Won") && clickedCountryNameTo.equals("Euro")) {
//                    totalConvertedAmount = amount * 1391.8076;
//                    String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
//                    convertAmount.setText("" + tot);
//                }
//                if (clickedCountryName.equals("South Korean Won") && clickedCountryNameTo.equals("US Dollar")) {
//                    totalConvertedAmount = amount * 1439.5563;
//                    String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
//                    convertAmount.setText("" + tot);
//                }
//                if (clickedCountryName.equals("South Korean Won") && clickedCountryNameTo.equals("Yen")) {
//                    totalConvertedAmount = amount * 9.9478858;
//                    String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
//                    convertAmount.setText("" + tot);
//                }
//                if (clickedCountryName.equals("South Korean Won") && clickedCountryNameTo.equals("Pound")) {
//                    totalConvertedAmount = amount * 1552.2845;
//                    String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
//                    convertAmount.setText("" + tot);
//                }
//                if (clickedCountryName.equals("South Korean Won") && clickedCountryNameTo.equals("Australian Dollar")) {
//                    totalConvertedAmount = amount * 930.08658;
//                    String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
//                    convertAmount.setText("" + tot);
//                }
//                if (clickedCountryName.equals("South Korean Won") && clickedCountryNameTo.equals("Ruble")) {
//                    totalConvertedAmount = amount * 24.788181;
//                    String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
//                    convertAmount.setText("" + tot);
//                }
//                if (clickedCountryName.equals("South Korean Won") && clickedCountryNameTo.equals("Yuan")) {
//                    totalConvertedAmount = amount * 199.88542;
//                    String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
//                    convertAmount.setText("" + tot);
//                }
//                if (clickedCountryName.equals("South Korean Won") && clickedCountryNameTo.equals("North Korean Won")) {
//                    totalConvertedAmount = amount * 1.5995429;
//                    String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
//                    convertAmount.setText("" + tot);
//                }
//                if (clickedCountryName.equals("South Korean Won") && clickedCountryNameTo.equals("Peso")) {
//                    totalConvertedAmount = amount * 71.160373;
//                    String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
//                    convertAmount.setText("" + tot);
//                } if (clickedCountryName.equals("North Korean Won") && clickedCountryNameTo.equals("Rupee")) {
//                    totalConvertedAmount = amount * 10.987911;
//                    String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
//                    convertAmount.setText("" + tot);
//                }
//                if (clickedCountryName.equals("North Korean Won") && clickedCountryNameTo.equals("Euro")) {
//                    totalConvertedAmount = amount * 869.83247;
//                    String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
//                    convertAmount.setText("" + tot);
//                }
//                if (clickedCountryName.equals("North Korean Won") && clickedCountryNameTo.equals("US Dollar")) {
//                    totalConvertedAmount = amount * 899.98226;
//                    String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
//                    convertAmount.setText("" + tot);
//                }
//                if (clickedCountryName.equals("North Korean Won") && clickedCountryNameTo.equals("Yen")) {
//                    totalConvertedAmount = amount * 6.2190007;
//                    String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
//                    convertAmount.setText("" + tot);
//                }
//                if (clickedCountryName.equals("North Korean Won") && clickedCountryNameTo.equals("Pound")) {
//                    totalConvertedAmount = amount * 972.40272;
//                    String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
//                    convertAmount.setText("" + tot);
//                }
//                if (clickedCountryName.equals("North Korean Won") && clickedCountryNameTo.equals("Australian Dollar")) {
//                    totalConvertedAmount = amount * 581.72895;
//                    String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
//                    convertAmount.setText("" + tot);
//                }
//                if (clickedCountryName.equals("North Korean Won") && clickedCountryNameTo.equals("Ruble")) {
//                    totalConvertedAmount = amount * 15.523804;
//                    String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
//                    convertAmount.setText("" + tot);
//                }
//                if (clickedCountryName.equals("North Korean Won") && clickedCountryNameTo.equals("Yuan")) {
//                    totalConvertedAmount = amount * 124.96553;
//                    String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
//                    convertAmount.setText("" + tot);
//                }
//                if (clickedCountryName.equals("North Korean Won") && clickedCountryNameTo.equals("South Korean Won")) {
//                    totalConvertedAmount = amount * 0.62517626;
//                    String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
//                    convertAmount.setText("" + tot);
//                }
//                if (clickedCountryName.equals("North Korean Won") && clickedCountryNameTo.equals("Peso")) {
//                    totalConvertedAmount = amount * 44.494157;
//                    String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
//                    convertAmount.setText("" + tot);
//                } if (clickedCountryName.equals("Peso") && clickedCountryNameTo.equals("Rupee")) {
//                    totalConvertedAmount = amount * 0.24697983;
//                    String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
//                    convertAmount.setText("" + tot);
//                }
//                if (clickedCountryName.equals("Peso") && clickedCountryNameTo.equals("Euro")) {
//                    totalConvertedAmount = amount * 19.549434;
//                    String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
//                    convertAmount.setText("" + tot);
//                }
//                if (clickedCountryName.equals("Peso") && clickedCountryNameTo.equals("US Dollar")) {
//                    totalConvertedAmount = amount * 20.22;
//                    String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
//                    convertAmount.setText("" + tot);
//                }
//                if (clickedCountryName.equals("Peso") && clickedCountryNameTo.equals("Yen")) {
//                    totalConvertedAmount = amount * 0.13979372;
//                    String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
//                    convertAmount.setText("" + tot);
//                }
//                if (clickedCountryName.equals("Peso") && clickedCountryNameTo.equals("Pound")) {
//                    totalConvertedAmount = amount * 21.84;
//                    String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
//                    convertAmount.setText("" + tot);
//                }
//                if (clickedCountryName.equals("Peso") && clickedCountryNameTo.equals("Australian Dollar")) {
//                    totalConvertedAmount = amount * 13.06;
//                    String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
//                    convertAmount.setText("" + tot);
//                }
//                if (clickedCountryName.equals("Peso") && clickedCountryNameTo.equals("Ruble")) {
//                    totalConvertedAmount = amount * 0.34825147;
//                    String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
//                    convertAmount.setText("" + tot);
//                }
//                if (clickedCountryName.equals("Peso") && clickedCountryNameTo.equals("Yuan")) {
//                    totalConvertedAmount = amount * 2.80;
//                    String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
//                    convertAmount.setText("" + tot);
//                }
//                if (clickedCountryName.equals("Peso") && clickedCountryNameTo.equals("South Korean Won")) {
//                    totalConvertedAmount = amount * 0.014044832;
//                    String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
//                    convertAmount.setText("" + tot);
//                }
//                if (clickedCountryName.equals("Peso") && clickedCountryNameTo.equals("North Korean Won")) {
//                    totalConvertedAmount = amount * 0.022473992;
//                    String tot = String.format("%.2f %s", totalConvertedAmount, clickedCountryNameTo);
//                    convertAmount.setText("" + tot);
//                }
//            }
//        });
//
//        backbt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                onBackPressed();
//            }
//        });
//
//    }
//
//    private void itemselect() {
//
//        tv2.setText("1 " + clickedCountryName + " = " + clickedCountryNameTo);
//
//    }
//
//    private void iniCountryList() {
//
//        countryList =new ArrayList<>();
//        countryList.add(new CountryItem("Rupee",R.drawable.rupee));
//        countryList.add(new CountryItem("US Dollar",R.drawable.dollar));
//        countryList.add(new CountryItem("Euro",R.drawable.euro));
//        countryList.add(new CountryItem("Yen",R.drawable.yen));
//        countryList.add(new CountryItem("Australian Dollar",R.drawable.dollar));
//        countryList.add(new CountryItem("Pound",R.drawable.pound));
//        countryList.add(new CountryItem("Ruble",R.drawable.ruble));
//        countryList.add(new CountryItem("Yuan",R.drawable.yen));
//        countryList.add(new CountryItem("South Korean Won",R.drawable.won));
//        countryList.add(new CountryItem("North Korean Won",R.drawable.won));
//        countryList.add(new CountryItem("Peso",R.drawable.peso));
//
//    }
//
//
//    public void onBackPressed(){
//        super.onBackPressed();
//    }
//}
//
//
//
////    ArrayAdapter<CharSequence>adapter2= ArrayAdapter.createFromResource(this, R.array.currency, android.R.layout.list_content);
////        adapter2.setDropDownViewResource(android.R.layout.list_content);
////
////                spinner.setAdapter(adapter2);
////                spinner.setOnItemSelectedListener(this);
////
////                ArrayAdapter<CharSequence>adapter3= ArrayAdapter.createFromResource(this, R.array.currency, android.R.layout.simple_spinner_dropdown_item);
////        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
////        spinner3.setAdapter(adapter3);
////        spinner3.setOnItemSelectedListener(this);
//
////@Override
////    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
////        String text = parent.getItemAtPosition(position).toString();
////        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
////    }
////
////    @Override
////    public void onNothingSelected(AdapterView<?> parent) {
////
////    }

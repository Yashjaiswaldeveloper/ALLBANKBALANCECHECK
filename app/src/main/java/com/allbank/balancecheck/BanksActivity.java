package com.allbank.balancecheck;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.allbank.balancecheck.bankadapter.AustralianBankAdapter;
import com.allbank.balancecheck.bankadapter.CanadaBankAdapter;
import com.allbank.balancecheck.bankadapter.GermanyBankAdapter;
import com.allbank.balancecheck.bankadapter.IndianBankAdapter;
import com.allbank.balancecheck.bankadapter.JapanBankAdapter;
import com.allbank.balancecheck.bankadapter.SwedenBankAdapter;
import com.allbank.balancecheck.bankadapter.SwitzerlandBankAdapter;
import com.allbank.balancecheck.bankadapter.UKBankAdapter;
import com.allbank.balancecheck.bankadapter.USABankAdapter;

import java.util.ArrayList;

public class BanksActivity extends AppCompatActivity {

    ImageView backbt;
//    Button continueb;
    TextView textviewcountry, textviewcountry2;
    String country;
    String bank;
    ArrayList<BankModel> IndianBanks = new ArrayList<>();
    ArrayList<BankModel> UKBanks = new ArrayList<>();
    ArrayList<BankModel> USABanks = new ArrayList<>();
    ArrayList<BankModel> SwedenBanks = new ArrayList<>();
    ArrayList<BankModel> GermanyBanks = new ArrayList<>();
    ArrayList<BankModel> SwitzerlandBanks = new ArrayList<>();
    ArrayList<BankModel> JapanBanks = new ArrayList<>();
    ArrayList<BankModel> AustralianBanks = new ArrayList<>();
    ArrayList<BankModel> CanadaBanks = new ArrayList<>();

//    FirebaseDatabase firebaseDatabase;
//    DatabaseReference databaseReference;
//    CountryandBank countryandBank;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banks);
        textviewcountry = findViewById(R.id.textviewcountry);
        textviewcountry2 = findViewById(R.id.textviewcountry2);
        backbt = findViewById(R.id.backbt);
//        continueb = findViewById(R.id.continuebutton);
        recyclerView = findViewById(R.id.bankList);

//        firebaseDatabase = FirebaseDatabase.getInstance();
//        databaseReference = firebaseDatabase.getReference("CountryandBank");
//
//        countryandBank = new CountryandBank();

        country = getIntent().getStringExtra("country");

        textviewcountry.setText(country);
        textviewcountry2.setText(country);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

// Indian Banks List
        IndianBanks.add(new BankModel(R.drawable.hdfc, "HDFC Bank"));
        IndianBanks.add(new BankModel(R.drawable.sbi, "State Bank Of India"));
        IndianBanks.add(new BankModel(R.drawable.ubi, "Union Bank Of India"));
        IndianBanks.add(new BankModel(R.drawable.icici, "ICICI Bank"));
        IndianBanks.add(new BankModel(R.drawable.axis, "Axis Bank"));
        IndianBanks.add(new BankModel(R.drawable.kotak, "Kotak Mahindra Bank"));
        IndianBanks.add(new BankModel(R.drawable.boi, "Bank Of India"));
        IndianBanks.add(new BankModel(R.drawable.yesbank, "Yes Bank"));
        IndianBanks.add(new BankModel(R.drawable.pnb, "Punjab National Bank"));
        IndianBanks.add(new BankModel(R.drawable.bob, "Bank Of Baroda"));


        // UK Banks List
        UKBanks.add(new BankModel(R.drawable.hsbc, "HSBC Holdings"));
        UKBanks.add(new BankModel(R.drawable.lloyds, "Lloyds Banking Group"));
        UKBanks.add(new BankModel(R.drawable.natwest, "NatWest Group"));
        UKBanks.add(new BankModel(R.drawable.barclays, "Barclays"));
        UKBanks.add(new BankModel(R.drawable.allica, "Allica Bank"));

        // USA Banks List
        USABanks.add(new BankModel(R.drawable.chase, "JPMorgan Chase"));
        USABanks.add(new BankModel(R.drawable.boa, "Bank Of America"));
        USABanks.add(new BankModel(R.drawable.citibank, "Citigroup"));
        USABanks.add(new BankModel(R.drawable.wellsfargo, "Wells Fargo"));
        USABanks.add(new BankModel(R.drawable.goldmansachs, "Goldman Sachs"));
        USABanks.add(new BankModel(R.drawable.morganstanley, "Morgan Stanley"));
        USABanks.add(new BankModel(R.drawable.usbancorp, "U.S. Bancorp"));
        USABanks.add(new BankModel(R.drawable.pnc, "PNC Financial Services"));
            USABanks.add(new BankModel(R.drawable.truist, "Truist Financial"));

        // Canada Banks List
        CanadaBanks.add(new BankModel(R.drawable.rbc, "Royal Bank Of Canada"));
        CanadaBanks.add(new BankModel(R.drawable.tdbank, "Toronto-Dominion Bank"));
        CanadaBanks.add(new BankModel(R.drawable.scotia, "Bank Of Nova Scotia"));
        CanadaBanks.add(new BankModel(R.drawable.bmo, "Bank Of Montreal"));
        CanadaBanks.add(new BankModel(R.drawable.cibc, "Canadian Imperial"));

        // Sweden Banks List
        SwedenBanks.add(new BankModel(R.drawable.nordea, "Nordea Bank AB."));
        SwedenBanks.add(new BankModel(R.drawable.handelsbanken, "Svenska Handelsbanken"));
        SwedenBanks.add(new BankModel(R.drawable.seb, "Skandinaviska Enskilda Banken"));
        SwedenBanks.add(new BankModel(R.drawable.swedbank, "Swedbank"));
        SwedenBanks.add(new BankModel(R.drawable.carnegic, "Carnegie Investment Bank"));
        SwedenBanks.add(new BankModel(R.drawable.ikano, "Ikano Bank"));
        SwedenBanks.add(new BankModel(R.drawable.forex, "Forex Bank AB."));
        SwedenBanks.add(new BankModel(R.drawable.skandia, "Skandiabanken"));

        // Switzerland Banks List
        SwitzerlandBanks.add(new BankModel(R.drawable.julius, "Julius Baer Group"));
        SwitzerlandBanks.add(new BankModel(R.drawable.vontobel, "Vontobel"));
        SwitzerlandBanks.add(new BankModel(R.drawable.pictet, "Pictet Group"));
        SwitzerlandBanks.add(new BankModel(R.drawable.lombard, "Lombard Odier"));
        SwitzerlandBanks.add(new BankModel(R.drawable.jss, "J. Safra Sarasin"));
        SwitzerlandBanks.add(new BankModel(R.drawable.ubp, "Union Bancaire Privee"));
        SwitzerlandBanks.add(new BankModel(R.drawable.efg, "EFG International"));

        // Germany Banks List
        GermanyBanks.add(new BankModel(R.drawable.bayern, "BayernLB"));
        GermanyBanks.add(new BankModel(R.drawable.bremer, "Bremer Bank"));
        GermanyBanks.add(new BankModel(R.drawable.commerz, "Commerzbank"));
        GermanyBanks.add(new BankModel(R.drawable.consors, "Consorsbank"));
        GermanyBanks.add(new BankModel(R.drawable.dab, "DAB BNP Paribas"));
        GermanyBanks.add(new BankModel(R.drawable.deka, "DekaBank"));
        GermanyBanks.add(new BankModel(R.drawable.deutsche, "Deutsche Bank"));
        GermanyBanks.add(new BankModel(R.drawable.pbb, "Deutsche Pfandbriefbank"));

        // Japan Banks List
        JapanBanks.add(new BankModel(R.drawable.jpb, "Japan Post Bank"));
        JapanBanks.add(new BankModel(R.drawable.mizuho, "Mizuho Bank"));
        JapanBanks.add(new BankModel(R.drawable.mufg, "Mitsubishi UFJ Bank"));
        JapanBanks.add(new BankModel(R.drawable.mitsui, "Mitsui Sumitomo Bank"));
        JapanBanks.add(new BankModel(R.drawable.resona, "Resono Bank"));
        JapanBanks.add(new BankModel(R.drawable.shinsei, "Shinsei Bank"));
        JapanBanks.add(new BankModel(R.drawable.seven, "Seven Bank"));
        JapanBanks.add(new BankModel(R.drawable.prestia, "Prestia"));

        // Australian Banks List
        AustralianBanks.add(new BankModel(R.drawable.nab, "National Australian Bank"));
        AustralianBanks.add(new BankModel(R.drawable.westpac, "Westpac Bank"));
        AustralianBanks.add(new BankModel(R.drawable.boq, "Bank Of Queensland"));
        AustralianBanks.add(new BankModel(R.drawable.macquire, "Macquaire Bank"));
        AustralianBanks.add(new BankModel(R.drawable.bendigo, "Bendigo Bank"));
        AustralianBanks.add(new BankModel(R.drawable.amp, "AMP Bank Ltd"));
        AustralianBanks.add(new BankModel(R.drawable.suncorp, "Suncorp Bank"));

        switch (country) {
            case "India":
                IndianBankAdapter adapter = new IndianBankAdapter(this, IndianBanks);
                recyclerView.setAdapter(adapter);
                break;
            case "UK":
                UKBankAdapter adapter2 = new UKBankAdapter(this, UKBanks);
                recyclerView.setAdapter(adapter2);
                break;
            case "USA":
                USABankAdapter adapter3 = new USABankAdapter(this, USABanks);
                recyclerView.setAdapter(adapter3);
                break;
            case "Canada":
                CanadaBankAdapter adapter4 = new CanadaBankAdapter(this, CanadaBanks);
                recyclerView.setAdapter(adapter4);
                break;
            case "Sweden":
                SwedenBankAdapter adapter5 = new SwedenBankAdapter(this, SwedenBanks);
                recyclerView.setAdapter(adapter5);
                break;
            case "Switzerland":
                SwitzerlandBankAdapter adapter6 = new SwitzerlandBankAdapter(this, SwitzerlandBanks);
                recyclerView.setAdapter(adapter6);
                break;
            case "Germany":
                GermanyBankAdapter adapter7 = new GermanyBankAdapter(this, GermanyBanks);
                recyclerView.setAdapter(adapter7);
                break;
            case "Japan":
                JapanBankAdapter adapter8 = new JapanBankAdapter(this, JapanBanks);
                recyclerView.setAdapter(adapter8);
                break;
            case "Australia":
                AustralianBankAdapter adapter9 = new AustralianBankAdapter(this, AustralianBanks);
                recyclerView.setAdapter(adapter9);
                break;
        }

        backbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
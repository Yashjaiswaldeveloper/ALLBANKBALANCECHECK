package com.allbank.balancecheck;

import static com.pesonal.adsdk.AppManage.ADMOB_B;
import static com.pesonal.adsdk.AppManage.ADMOB_N;
import static com.pesonal.adsdk.AppManage.FACEBOOK_N;
import static com.pesonal.adsdk.AppManage.FACEBOOK_NB;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.pesonal.adsdk.AppManage;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class CityActivity extends AppCompatActivity {
    ImageButton citycontinuebtn;
    private Spinner state;
    private Spinner city;
    private String gender;
    private String fullname;
    private String email;
    private String DOB, mobile;
    String assetName;
    String stat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);
        city = findViewById(R.id.city);
        state = findViewById(R.id.state);
        citycontinuebtn = findViewById(R.id.citycontinuebtn);
        mobile = getIntent().getStringExtra("mobilen");
        gender = getIntent().getStringExtra("gender1");
        fullname = getIntent().getStringExtra("fullname1");
        email = getIntent().getStringExtra("mail");
        DOB = getIntent().getStringExtra("dob1");
        AppManage.getInstance(CityActivity.this).showNative((ViewGroup) findViewById(R.id.ad_banner), ADMOB_B[0], FACEBOOK_NB[0]);

        JSONObject json = loadJsonObjectFromAsset("IndianStates.JSON");
        List<String> istateList = new ArrayList<>();
        try {
            JSONArray refArray = json.getJSONArray("states");
            for (int i = 0; i < refArray.length(); i++) {
                String ref = refArray.getJSONObject(i).getString("name");
                istateList.add(ref);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                stat=adapterView.getItemAtPosition(pos).toString();
                Toast.makeText(CityActivity.this, stat, Toast.LENGTH_SHORT).show();
                JSONObject json2 = loadJsonObjectFromAsset("indiancities.json");
                List<String> icitiesList2 = new ArrayList<>();
                try {
                    JSONArray refArray = json2.getJSONArray(stat);
                    for (int i = 0; i < refArray.length(); i++) {
                        String ref = refArray.getJSONObject(i).getString("name");
                        icitiesList2.add(ref);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(CityActivity.this,android.R.layout.simple_spinner_dropdown_item, icitiesList2);
                city.setAdapter(arrayAdapter2);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, istateList);
        state.setAdapter(arrayAdapter);
        citycontinuebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CityActivity.this, PasswordActivity.class);
                intent.putExtra("gender2", gender);
                intent.putExtra("fullname2", fullname);
//                intent.putExtra("mail2", email);
                intent.putExtra("dob2", DOB);
                intent.putExtra("mobile", mobile);
                startActivity(intent);

            }
        });
    }

    public JSONObject loadJsonObjectFromAsset(String assetName) {
        try {
            String json = loadStringFromAsset(assetName);
            if (json != null)
                return new JSONObject(json);
        } catch (Exception e) {
            Log.e("JsonUtils", e.toString());
        }

        return null;
    }

    private String loadStringFromAsset(String assetName) throws Exception {
        InputStream is = getAssets().open(assetName);
        int size = is.available();
        byte[] buffer = new byte[size];
        is.read(buffer);
        is.close();
        return new String(buffer, "UTF-8");
    }
}

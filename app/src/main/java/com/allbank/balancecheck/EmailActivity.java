package com.allbank.balancecheck;

import static com.pesonal.adsdk.AppManage.ADMOB_B;
import static com.pesonal.adsdk.AppManage.FACEBOOK_NB;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.allbank.balancecheck.DOBActivity;
import com.pesonal.adsdk.AppManage;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class EmailActivity extends AppCompatActivity {
    private ImageView continuebtn;
    private Spinner state;
    private String gender, mobile;
    private String fullname;
    private String DOB;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);
//        state=findViewById(R.id.state);
        continuebtn= findViewById(R.id.continuebtn);
        gender = getIntent().getStringExtra("gende");
        mobile = getIntent().getStringExtra("mobilenumb");
        fullname = getIntent().getStringExtra("fulname");
        DOB = getIntent().getStringExtra("dob1");
        AppManage.getInstance(EmailActivity.this).showNative((ViewGroup) findViewById(R.id.ad_banner), ADMOB_B[0], FACEBOOK_NB[0]);

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

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, istateList);
        state.setAdapter(arrayAdapter);

        continuebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (state. {
                Intent intent = new Intent(EmailActivity.this, CityActivity.class);
                intent.putExtra("gender2", gender);
                intent.putExtra("fullname2", fullname);
                intent.putExtra("dob2", DOB);
                intent.putExtra("mobile", mobile);
                intent.putExtra("state", state.getSelectedItem().toString());
                startActivity(intent);
//                } else {
//                    Toast.makeText(EmailActivity.this, "Please select state...", Toast.LENGTH_SHORT).show();
//                }
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


//        continuebtn.setOnClickListener(new View.OnClickListener() {
//@Override
//public void onClick(View v) {
//        if (!email_edit_txt.getText().toString().trim().isEmpty()) {
//        if (Patterns.EMAIL_ADDRESS.matcher(email_edit_txt.getText().toString()).matches()) {
//        Intent intent = new Intent(getApplicationContext(), DOBActivity.class);
//        intent.putExtra("gende", gender);
//        intent.putExtra("fulname", fullname);
//        intent.putExtra("mobilenum", mobile);
//        intent.putExtra("email", email_edit_txt.getText().toString());
//        startActivity(intent);
//        }else{
//        Toast.makeText(EmailActivity.this, "Please re-enter your Email", Toast.LENGTH_SHORT).show();
//        email_edit_txt.setError("Valid Email is required");
//        email_edit_txt.requestFocus();
//        }
//        }else{
//        Toast.makeText(EmailActivity.this, "Please Enter your Email", Toast.LENGTH_SHORT).show();
//        }
//        }
// });
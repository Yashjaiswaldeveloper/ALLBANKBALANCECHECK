package com.allbank.balancecheck;

import static com.pesonal.adsdk.AppManage.ADMOB_B;
import static com.pesonal.adsdk.AppManage.FACEBOOK_NB;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.pesonal.adsdk.AppManage;

public class NameActivity extends AppCompatActivity {
    private ImageButton con;
    private ImageView login;
    private EditText name_edit_txt;
    private String gender,mobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);
        name_edit_txt=findViewById(R.id.name_edit_txt);
        con=findViewById(R.id.con);
        login= findViewById(R.id.log);
        mobile = getIntent().getStringExtra("mobilenumbe");
        gender = getIntent().getStringExtra("gender");
        AppManage.getInstance(NameActivity.this).showNative((ViewGroup) findViewById(R.id.ad_banner), ADMOB_B[0], FACEBOOK_NB[0]);

        con.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!name_edit_txt.getText().toString().trim().isEmpty()) {
                    Intent intent = new Intent(getApplicationContext(), DOBActivity.class);
                    intent.putExtra("gende", gender);
                    intent.putExtra("mobilenumb",mobile);
                    intent.putExtra("fullname", name_edit_txt.getText().toString());
                    startActivity(intent);
                }else{
                    Toast.makeText(NameActivity.this, "Please Enter your Full Name", Toast.LENGTH_SHORT).show();
                    name_edit_txt.setError("Full Name is required");
                    name_edit_txt.requestFocus();
                }
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(NameActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
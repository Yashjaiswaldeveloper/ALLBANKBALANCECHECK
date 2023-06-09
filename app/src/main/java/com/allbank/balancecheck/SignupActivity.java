package com.allbank.balancecheck;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SignupActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    ImageView code_btn, login;
    EditText enternumber;
    String choosen = "+91";
    Spinner countrycodespinner;
    List<String> countrycodespiner = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        login= findViewById(R.id.log);
        code_btn= findViewById(R.id.code_btn);
        enternumber = findViewById(R.id.input_mobile_number);
        this.countrycodespinner = (Spinner) findViewById(R.id.countrycode);
        this.countrycodespiner.add("+91");
        this.countrycodespiner.add("+44");
        this.countrycodespiner.add("+1");
        this.countrycodespiner.add("+46");
        this.countrycodespiner.add("+41");
        this.countrycodespiner.add("+49");
        this.countrycodespiner.add("+61");
        this.countrycodespiner.add("+1");
        this.countrycodespiner.add("+81");
        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<>(this, 17367048, this.countrycodespiner);
        dataAdapter1.setDropDownViewResource(17367049);
        this.countrycodespinner.setAdapter(dataAdapter1);
        this.countrycodespinner.setOnItemSelectedListener(this);


        code_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!enternumber.getText().toString().trim().isEmpty()){
                    if ((enternumber.getText().toString().trim()).length()==10){

                        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                                choosen + enternumber.getText().toString(),
                                60,
                                TimeUnit.SECONDS,
                                SignupActivity.this,
                                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                    @Override
                                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                                    }

                                    @Override
                                    public void onVerificationFailed(@NonNull FirebaseException e) {
                                        Toast.makeText(SignupActivity.this,e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                        Intent intent = new Intent(getApplicationContext(),OTPActivity.class);
                                        intent.putExtra("mobile",countrycodespinner.getSelectedItem().toString() + enternumber.getText().toString());
                                        intent.putExtra("otp",s);
                                        startActivity(intent);
                                    }
                                }
                        );

                    }else {
                        Toast.makeText(SignupActivity.this, "Please enter correct number", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(SignupActivity.this, "Please enter mobile number", Toast.LENGTH_SHORT).show();
                }
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(SignupActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        this.choosen = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
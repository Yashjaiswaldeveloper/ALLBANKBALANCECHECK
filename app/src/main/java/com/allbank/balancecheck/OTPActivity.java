package com.allbank.balancecheck;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class OTPActivity extends AppCompatActivity {
    ImageView verifycontbtn;
    EditText otp1, otp2, otp3, otp4, otp5, otp6;
    String getotpbe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpactivity);

        otp1=findViewById(R.id.otp_btn1);
        otp2=findViewById(R.id.otp_btn2);
        otp3=findViewById(R.id.otp_btn3);
        otp4=findViewById(R.id.otp_btn4);
        otp5=findViewById(R.id.otp_btn5);
        otp6=findViewById(R.id.otp_btn6);
        verifycontbtn=findViewById(R.id.verifycontbtn);

        TextView textView = findViewById(R.id.get_code);
        textView.setText(String.format(
                "%s",getIntent().getStringExtra("mobile")
        ));

        getotpbe = getIntent().getStringExtra("otp");

        verifycontbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!otp1.getText().toString().trim().isEmpty() && !otp2.getText().toString().trim().isEmpty() && !otp3.getText().toString().trim().isEmpty() && !otp4.getText().toString().trim().isEmpty() && !otp5.getText().toString().trim().isEmpty() && !otp6.getText().toString().trim().isEmpty()){
                    String enterotp = otp1.getText().toString() + otp2.getText().toString() + otp3.getText().toString() + otp4.getText().toString() + otp5.getText().toString() + otp6.getText().toString();

                    if (getotpbe!=null){
                        PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(
                                getotpbe, enterotp
                        );
                        FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()){
                                            Intent intent =new Intent(getApplicationContext(),GetStartedcontinueActivity.class);
                                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                            startActivity(intent);
                                        }else{
                                            Toast.makeText(OTPActivity.this, "Enter Correct OTP", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    }else{
                        Toast.makeText(OTPActivity.this, "Please Check Your Internet Connection", Toast.LENGTH_SHORT).show();
                    }

                    Toast.makeText(OTPActivity.this, "OTP Verify", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(OTPActivity.this, "Please Enter OTP", Toast.LENGTH_SHORT).show();
                }
            }
        });

        enterotpmove();
        findViewById(R.id.resend).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        getIntent().getStringExtra("mobile"),
                        60,
                        TimeUnit.SECONDS,
                        OTPActivity.this,
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                Toast.makeText(OTPActivity.this,e.getMessage(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCodeSent(@NonNull String newotp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                getotpbe=newotp;
                                Toast.makeText(OTPActivity.this, "OTP Resent!!", Toast.LENGTH_SHORT).show();
                            }
                        }
                );
            }
        });
    }

    private void enterotpmove() {
        otp1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                if(!s.toString().trim().isEmpty()){
                    otp2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        otp2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                if(!s.toString().trim().isEmpty()){
                    otp3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        otp3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                if(!s.toString().trim().isEmpty()){
                    otp4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        otp4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                if(!s.toString().trim().isEmpty()){
                    otp5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        otp5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                if(!s.toString().trim().isEmpty()){
                    otp6.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }
}
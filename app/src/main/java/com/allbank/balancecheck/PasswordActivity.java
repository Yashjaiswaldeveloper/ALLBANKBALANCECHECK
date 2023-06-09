package com.allbank.balancecheck;


import static android.content.ContentValues.TAG;

import static com.pesonal.adsdk.AppManage.ADMOB_B;
import static com.pesonal.adsdk.AppManage.FACEBOOK_NB;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.pesonal.adsdk.AppManage;

public class PasswordActivity extends AppCompatActivity {

    ImageView passwordcontinuebtn;
    TextView gotoEmail;
    private String textFullName, textEmail, textDoB, textGender, textMobile, state, city;
    private EditText editTextpassword, editTextconfirmpassword,email_edit_txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        editTextpassword = findViewById(R.id.editTextpassword);
        email_edit_txt = findViewById(R.id.email_edit_txt);
        editTextconfirmpassword = findViewById(R.id.editTextconfirmpassword);
        passwordcontinuebtn = findViewById(R.id.passwordcontinuebtn);
        gotoEmail.setVisibility(View.INVISIBLE);
        textGender = getIntent().getStringExtra("gender1");
        textFullName = getIntent().getStringExtra("fullname1");
//        textEmail = getIntent().getStringExtra("mail2");
        textEmail=email_edit_txt.getText().toString();
        textDoB = getIntent().getStringExtra("dob1");
        state = getIntent().getStringExtra("state");
        city = getIntent().getStringExtra("city");
        textMobile = getIntent().getStringExtra("mobile");
        AppManage.getInstance(PasswordActivity.this).showNative((ViewGroup) findViewById(R.id.ad_banner), ADMOB_B[0], FACEBOOK_NB[0]);

        passwordcontinuebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textPwd = editTextpassword.getText().toString();
                String textConfirmPwd = editTextconfirmpassword.getText().toString();
                if (TextUtils.isEmpty(textEmail)) {
                    Toast.makeText(PasswordActivity.this, "Please enter your Email", Toast.LENGTH_SHORT).show();
                    email_edit_txt.setError("Email is required");
                    email_edit_txt.requestFocus();
                } else if (!Patterns.EMAIL_ADDRESS.matcher(textEmail).matches()) {
                    Toast.makeText(PasswordActivity.this, "Please re-enter your Email", Toast.LENGTH_SHORT).show();
                    email_edit_txt.setError("Valid Email is required");
                    email_edit_txt.requestFocus();
                }else if (TextUtils.isEmpty(textPwd)) {
                    Toast.makeText(PasswordActivity.this, "Please enter your password", Toast.LENGTH_SHORT).show();
                    editTextpassword.setError("Password is required");
                    editTextpassword.requestFocus();
                } else if (textPwd.length() < 6) {
                    Toast.makeText(PasswordActivity.this, "Password should be atleast 6 digits", Toast.LENGTH_SHORT).show();
                    editTextpassword.setError("Password too weak");
                    editTextpassword.requestFocus();
                } else if (TextUtils.isEmpty(textConfirmPwd)) {
                    Toast.makeText(PasswordActivity.this, "Please Confirm your Password", Toast.LENGTH_SHORT).show();
                    editTextconfirmpassword.setError("Password confirmation is reuired");
                    editTextconfirmpassword.requestFocus();
                } else if (!textPwd.equals(textConfirmPwd)) {
                    Toast.makeText(PasswordActivity.this, "Password should be same", Toast.LENGTH_SHORT).show();
                    editTextconfirmpassword.setError("Password confirmation is reuired");
                    editTextconfirmpassword.requestFocus();
                    editTextpassword.clearComposingText();
                    editTextconfirmpassword.clearComposingText();
                }

                registerUser(textFullName, textEmail, textDoB, textGender, textMobile, textPwd,state, city);
            }
        });
    }

    private void registerUser(String textFullName, String textEmail, String textDoB, String textGender, String textMobile, String textPwd, String state, String city) {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.createUserWithEmailAndPassword(textEmail, textPwd).addOnCompleteListener(PasswordActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(PasswordActivity.this, "User Registered Successfully", Toast.LENGTH_SHORT).show();
                    FirebaseUser firebaseUser = auth.getCurrentUser();
                    //Update display name of User
                    UserProfileChangeRequest profileChangeRequest = new UserProfileChangeRequest.Builder().setDisplayName(textFullName).build();
                    firebaseUser.updateProfile(profileChangeRequest);


                    //Enter User Data into the Firebase Realtime Database
                    ReadWriteUserDetails writeUserDetails = new ReadWriteUserDetails (textFullName,textDoB, textGender, textMobile, state, city);

                    //Extracting User reference from Database
                    DatabaseReference referenceProfile = FirebaseDatabase.getInstance().getReference("Registered Users");
                    referenceProfile.child(firebaseUser.getUid()).setValue(writeUserDetails).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Intent intent = new Intent(PasswordActivity.this, CountryActivity.class);
                                //to prevent user from returning back to register activity on pressing back button after registration
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                                finish();
                            } else {
                                try {
                                    throw task.getException();
                                } catch (FirebaseAuthWeakPasswordException e) {
                                    editTextpassword.setError("Your password is too weak. Kindly use a mix of alphabets,numbers and special characters");
                                    editTextpassword.requestFocus();
                                } catch (FirebaseAuthInvalidCredentialsException e) {
                                    email_edit_txt.setError("Your email is Invalid or already in use.Kindly re-enter");
                                    email_edit_txt.requestFocus();
                                } catch (FirebaseAuthUserCollisionException e) {
                                    email_edit_txt.setError("User is already registered with this email. Use another email");
                                    email_edit_txt.requestFocus();
                                } catch (Exception e) {
                                    Log.e(TAG, e.getMessage());
                                    Toast.makeText(PasswordActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    });
                }
            }
        });
    }
}

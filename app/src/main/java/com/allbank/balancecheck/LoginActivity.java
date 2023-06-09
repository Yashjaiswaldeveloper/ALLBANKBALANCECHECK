package com.allbank.balancecheck;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextEmail, editTextPassword;
    TextView forgot_password;
    ImageView login_button,register;
    //    private ProgressBar progress_bar;
    private FirebaseAuth authProfile;
    private static final String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
//        imageView_show_hide_pwd = findViewById(R.id.imageView_show_hide_pwd);
        register = findViewById(R.id.signup);
        forgot_password = findViewById(R.id.forgot_password);
//        google_logo = findViewById(R.id.google_logo);
//        progress_bar = findViewById(R.id.progress_bar);
        authProfile = FirebaseAuth.getInstance();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,NameActivity.class);
                startActivity(intent);
            }
        });
        //Login Button
        login_button = findViewById(R.id.loginbt);
//        imageView_show_hide_pwd.setImageResource(R.drawable.unseen);
        forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText reset_mail = new EditText(v.getContext());
                final AlertDialog.Builder passResetDialog = new AlertDialog.Builder(v.getContext());
                passResetDialog.setTitle("Reset Password");
                passResetDialog.setMessage("Enter your Email ID to Receive Reset Link");
                passResetDialog.setView(reset_mail);
                passResetDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //extract the email and send the rest link
                        String mail = reset_mail.getText().toString();
                        authProfile.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(LoginActivity.this, "Reset Link Sent To Your Email.", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(Exception e) {
                                Toast.makeText(LoginActivity.this, "Error ! Reset Link is Not Sent" + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
                passResetDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //close the dialog box
                    }
                });
                passResetDialog.create().show();
            }
        });
//        imageView_show_hide_pwd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(editTextPassword.getTransformationMethod().equals(HideReturnsTransformationMethod.getInstance())){
//                    editTextPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
//                    imageView_show_hide_pwd.setImageResource(R.drawable.unseen);
//                }else{
//                    editTextPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
//                    imageView_show_hide_pwd.setImageResource(R.drawable.seen);
//                }
//            }
//        });
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textEmail = editTextEmail.getText().toString();
                String textPwd = editTextPassword.getText().toString();
                if (TextUtils.isEmpty(textEmail)) {
                    Toast.makeText(LoginActivity.this, "Please enter your Email", Toast.LENGTH_SHORT).show();
                    editTextEmail.setError("Email is required");
                    editTextEmail.requestFocus();
                } else if (!Patterns.EMAIL_ADDRESS.matcher(textEmail).matches()) {
                    Toast.makeText(LoginActivity.this, "Please re-enter your Email", Toast.LENGTH_SHORT).show();
                    editTextEmail.setError("Valid Email is required");
                    editTextEmail.requestFocus();
                }else if (TextUtils.isEmpty(textPwd)) {
                    Toast.makeText(LoginActivity.this, "Please enter your password", Toast.LENGTH_SHORT).show();
                    editTextPassword.setError("Password is required");
                    editTextPassword.requestFocus();
                }else {
//                    progress_bar.setVisibility(View.VISIBLE);
                    loginUser(textEmail,textPwd);
                }
            }
        });
    }

    private void loginUser(String email, String pwd) {
        authProfile.signInWithEmailAndPassword(email,pwd).addOnCompleteListener(LoginActivity.this,new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    //Get instant of current user
//                    FirebaseUser firebaseUser=authProfile.getCurrentUser();
//                    assert firebaseUser != null;
//                    if (firebaseUser.isEmailVerified()){
                    Toast.makeText(LoginActivity.this, "You are logged in now", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(LoginActivity.this, HomePageActivity.class);
                    startActivity(intent);
//                    }else{
//                        firebaseUser.sendEmailVerification();
//                        authProfile.signOut();
//                        showAlertDialog();
//                    }
                }else{
                    try{
                        throw task.getException();
                    }catch(FirebaseAuthInvalidUserException e){
                        editTextEmail.setError("User does not exists or is no longer valid. Please register again");
                        editTextEmail.requestFocus();
                    }catch(FirebaseAuthInvalidCredentialsException e){
                        editTextPassword.setError("Invalid credentials. Kindly, check and re-enter");
                        editTextPassword.requestFocus();
                    }catch (Exception e){
                        Log.e(TAG,e.getMessage());
                        Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }
                //progress_bar.setVisibility(View.GONE);
            }
        });
    }

    //    private void showAlertDialog() {
//        //Setup AlertBuilder
//        AlertDialog.Builder builder=new AlertDialog.Builder(LoginActivity.this);
//        builder.setTitle("Email not Verified");
//        builder.setMessage("Please Verify your Email now");
//        builder.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                Intent intent=new Intent(Intent.ACTION_MAIN);
//                intent.addCategory(Intent.CATEGORY_APP_EMAIL);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);//To email app in new Window
//                startActivity(intent);
//            }
//        });
//        AlertDialog alertDialog= builder.create();
//
//        //Show the AlertDialog
//        alertDialog.show();
//    }
    //check user is already logged in
//    @Override
//    protected void onStart() {
//        super.onStart();
//        if(authProfile.getCurrentUser()!=null){
//            Toast.makeText(this, "Already logged in", Toast.LENGTH_SHORT).show();
//            //Start the User Profile Activity
//            startActivity(new Intent(LoginActivity.this,HomePageActivity.class));
//            finish();
//        }else{
//            Toast.makeText(this, "You can Login Now", Toast.LENGTH_SHORT).show();
//        }
//    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, GetStartedActivity.class));
    }


//    ImageButton loginbt;
//    ImageView signup;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);
//        loginbt=findViewById(R.id.loginbt);
//        signup=findViewById(R.id.signup);
//
//
//        loginbt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent mainIntent= new Intent(LoginActivity.this,HomePageActivity.class);
//                startActivity(mainIntent);
//            }
//        });
//
//        signup.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent mainIntent= new Intent(LoginActivity.this,SignupActivity.class);
//                startActivity(mainIntent);
//            }
//        });
//    }
//}

















        private void showAlertDialog() {
        //Setup AlertBuilder
        AlertDialog.Builder builder=new AlertDialog.Builder(LoginActivity.this);
        builder.setTitle("Email not Verified");
        builder.setMessage("Please Verify your Email now");
        builder.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent=new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_APP_EMAIL);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);//To email app in new Window
                startActivity(intent);
            }
        });
        AlertDialog alertDialog= builder.create();

        //Show the AlertDialog
        alertDialog.show();
    }
    //check user is already logged in













    //remove it
    @Override
    protected void onStart() {
        super.onStart();
        if(authProfile.getCurrentUser()!=null){
            Toast.makeText(this, "Already logged in", Toast.LENGTH_SHORT).show();
            //Start the User Profile Activity
            startActivity(new Intent(LoginActivity.this,HomePageActivity.class));
            finish();
        }else{
            Toast.makeText(this, "You can Login Now", Toast.LENGTH_SHORT).show();
        }
    }

}


//    ImageButton loginbt;
//    ImageView signup;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);
//        loginbt=findViewById(R.id.loginbt);
//        signup=findViewById(R.id.signup);
//
//
//        loginbt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent mainIntent= new Intent(LoginActivity.this,HomePageActivity.class);
//                startActivity(mainIntent);
//            }
//        });
//
//        signup.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent mainIntent= new Intent(LoginActivity.this,SignupActivity.class);
//                startActivity(mainIntent);
//            }
//        });
//    }
//}



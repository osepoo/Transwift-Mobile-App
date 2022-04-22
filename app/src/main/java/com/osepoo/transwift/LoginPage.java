package com.osepoo.transwift;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginPage extends AppCompatActivity implements View.OnClickListener{

    ImageView loginBanner;
    TextInputEditText etEmailLogin,etPasswordLogin;
    Button loginButton;
    ProgressBar loginProgressBar;
    TextView passwordreset;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        mAuth = FirebaseAuth.getInstance();

        etEmailLogin = (TextInputEditText) findViewById(R.id.loginemail);
        etPasswordLogin = (TextInputEditText) findViewById(R.id.loginpassword);
        loginButton = (Button) findViewById(R.id.loginbutton);
        loginButton.setOnClickListener(this);
        loginBanner = (ImageView) findViewById(R.id.loginbanner1);
        loginBanner.setOnClickListener(this);
        loginProgressBar = (ProgressBar) findViewById(R.id.loginprogressbar);
        passwordreset = (TextView) findViewById(R.id.passreset);
        passwordreset.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.loginbutton:
                loginUser();
                break;
            case R.id.loginbanner1:
                startActivity(new Intent(LoginPage.this,HomePage.class));
                break;
            case R.id.passreset:
                startActivity(new Intent(LoginPage.this,PasswordReset.class));
                break;
        }
    }

    private void loginUser() {
        String email = etEmailLogin.getText().toString().trim();
        String password = etPasswordLogin.getText().toString().trim();

        if(email.isEmpty()){
            etEmailLogin.setError("Email Required");
            etEmailLogin.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmailLogin.setError("Write a correct email");
            etEmailLogin.requestFocus();
            return;
        }
        if(password.isEmpty()){
            etPasswordLogin.setError("Password Required");
            etPasswordLogin.requestFocus();
            return;
        }
        if (password.length() < 6) {
            etPasswordLogin.setError("Password is short, Input the correct length");
            etPasswordLogin.requestFocus();
            return;
        }
        loginProgressBar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                if(user.isEmailVerified()){

                        startActivity(new Intent(LoginPage.this,SecondHome.class));

                }else{
                        user.sendEmailVerification();
                        Toast.makeText(LoginPage.this,"Kindly check yout email address and verify",Toast.LENGTH_LONG).show();
                        loginProgressBar.setVisibility(View.GONE);
                }

                }else{
                    Toast.makeText(LoginPage.this,"Failed, Please check again",Toast.LENGTH_LONG).show();
                    loginProgressBar.setVisibility(View.GONE);
                }
            }
        });
    }
}
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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;


public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView regimg;
    private TextInputEditText etFullName,etEmail,etAge,etPassword;
    private ProgressBar regProgressBar;
    private Button regButton;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        regimg = (ImageView) findViewById(R.id.loginbanner);
        regimg.setOnClickListener(this);

        regButton = (Button) findViewById(R.id.registerbutton);
        regButton.setOnClickListener(this);

        etFullName = (TextInputEditText) findViewById(R.id.registername);
        etEmail = (TextInputEditText) findViewById(R.id.registeremail);
        etAge = (TextInputEditText) findViewById(R.id.registerage);
        etPassword = (TextInputEditText) findViewById(R.id.registerpassword);
        regProgressBar = (ProgressBar) findViewById(R.id.registerprogressbar);



    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.loginbanner:
                startActivity(new Intent(RegisterActivity.this,HomePage.class));
                break;
            case R.id.registerbutton:
                registerUser();
                break;
        }

    }

    private void registerUser() {
        String fullName = etFullName.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String age = etAge.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (fullName.isEmpty()) {
            etFullName.setError("Full name is required");
            etFullName.requestFocus();
            return;
        }
        if (age.isEmpty()) {
            etAge.setError("Age is required");
            etAge.requestFocus();
            return;
        }
        if (email.isEmpty()) {
            etEmail.setError("Email is required");
            etEmail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.setError("Write a correct email");
            etEmail.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            etPassword.setError("Password is required");
            etPassword.requestFocus();
            return;
        }
        if (password.length() < 6) {
            etPassword.setError("Password is short, Input the correct length");
            etPassword.requestFocus();
            return;
        }

        regProgressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            User  user = new User(fullName, age, email);
                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if(task.isSuccessful()){
                                        Toast.makeText(RegisterActivity.this,"Successfully registered",Toast.LENGTH_LONG).show();
                                        regProgressBar.setVisibility(View.GONE);

                                    }else{
                                        Toast.makeText(RegisterActivity.this,"Something's wrong men",Toast.LENGTH_LONG).show();
                                        regProgressBar.setVisibility(View.GONE);
                                    }
                                }
                            });

                        }else{
                            Toast.makeText(RegisterActivity.this,"It's all wrong men" + task.getException().getMessage(),Toast.LENGTH_LONG).show();
                            regProgressBar.setVisibility(View.GONE);
                        }
                    }

                });
    }
}
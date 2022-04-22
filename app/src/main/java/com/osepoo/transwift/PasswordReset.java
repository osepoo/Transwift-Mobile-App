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
import com.google.firebase.auth.FirebaseAuth;

public class PasswordReset extends AppCompatActivity {

    ImageView imgvw;
    TextInputEditText emailforg;
    Button buttonforg;
    ProgressBar progbar;

    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_reset);

        imgvw = (ImageView) findViewById(R.id.loginbanner2);
        emailforg = (TextInputEditText) findViewById(R.id.forgetemail);
        buttonforg = (Button) findViewById(R.id.forgetbutton);
        progbar = (ProgressBar) findViewById(R.id.forgetprogressbar);

        auth = FirebaseAuth.getInstance();

        imgvw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PasswordReset.this,HomePage.class));
            }
        });

        buttonforg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetPassword();
            }
        });
    }
    private void resetPassword() {
        String email = emailforg.getText().toString().trim();

        if(email.isEmpty()){
            emailforg.setError("Email Required");
            emailforg.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailforg.setError("Write a correct email");
            emailforg.requestFocus();
            return;
        }
        progbar.setVisibility(View.VISIBLE);
        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if(task.isSuccessful()){
                    Toast.makeText(PasswordReset.this, "Check your email address", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(PasswordReset.this,LoginPage.class));
                }else{
                    Toast.makeText(PasswordReset.this, "Try again! Something went wrong", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
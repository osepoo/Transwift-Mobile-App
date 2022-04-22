package com.osepoo.transwift;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.material.textfield.TextInputEditText;

public class BookingPage extends AppCompatActivity {

    TextInputEditText editText1,editText2,editText3;
    ImageView imageView1;
    Button button1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_page);

        editText1 = (TextInputEditText) findViewById(R.id.bookingfrom);
        editText2 = (TextInputEditText) findViewById(R.id.bookingdestination);
        editText2 = (TextInputEditText) findViewById(R.id.confirmbookingpassword);
        button1 = (Button) findViewById(R.id.submitbookingbutton);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submituser();
            }
        });

        imageView1 = (ImageView) findViewById(R.id.bookingpageimageview);
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent bookingpagetohomepage = new Intent(BookingPage.this,HomePage.class);
                startActivity(bookingpagetohomepage);
            }
        });

    }

    private void submituser() {
        String carfrom = editText1.getText().toString().trim();
        String destination = editText2.getText().toString().trim();
        String password = editText3.getText().toString().trim();

        if (carfrom.isEmpty()) {
            editText1.setError("This is required");
            editText1.requestFocus();
            return;
        }
        if (destination.isEmpty()) {
            editText2.setError("This is required");
            editText2.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            editText3.setError("Password is required");
            editText3.requestFocus();
            return;
        }
        if (password.length() < 6) {
            editText3.setError("Password is short, Input the correct length");
            editText3.requestFocus();
            return;
        }
    }
}
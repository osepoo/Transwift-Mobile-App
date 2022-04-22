package com.osepoo.transwift;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class DriverDetailsPage extends AppCompatActivity {

    ImageView imageView1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_details_page);

        imageView1 = (ImageView) findViewById(R.id.loginbannerdriverpage);
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent driverpagetohomepage = new Intent(DriverDetailsPage.this,HomePage.class);
                startActivity(driverpagetohomepage);
            }
        });
    }
}
package com.osepoo.transwift;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

public class DrawerPage extends AppCompatActivity {

    ImageView imageView1;
    DrawerLayout drawerLayout;
     NavigationView navigationView;
    Toolbar toolbar;
     ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_page);

        toolbar = (Toolbar) findViewById(R.id.nav_toolbar);
         setSupportActionBar(toolbar);

        imageView1 = (ImageView) findViewById(R.id.loginbannerdrawerpage);
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent drawerpagetohomepage = new Intent(DrawerPage.this,HomePage.class);
                startActivity(drawerpagetohomepage);
            }
        });

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.navigationView);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.open_menu,R.string.close_menu);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                switch (item.getItemId()){

                    case R.id.homemenu:
                        Toast.makeText(DrawerPage.this, "Home Page", Toast.LENGTH_SHORT).show();
                        Intent toHomePage = new Intent(DrawerPage.this,HomePage.class);
                        startActivity(toHomePage);
                        break;

                    case R.id.bookingmenu:
                        Toast.makeText(DrawerPage.this, "Booking Page", Toast.LENGTH_SHORT).show();
                        Intent toBookingPage = new Intent(DrawerPage.this,BookingPage.class);
                        startActivity(toBookingPage);
                        break;

                    case R.id.routemenu:
                        Toast.makeText(DrawerPage.this, "Maps", Toast.LENGTH_SHORT).show();
                        Intent toRoutePage = new Intent(DrawerPage.this,RoutePage.class);
                        startActivity(toRoutePage);
                        break;

                    case R.id.drivermenu:
                        Toast.makeText(DrawerPage.this, "Employee Details Page", Toast.LENGTH_SHORT).show();
                        Intent toDriverdetails = new Intent(DrawerPage.this,DriverDetailsPage.class);
                        startActivity(toDriverdetails);
                        break;

                    case R.id.commsmenu:
                        Toast.makeText(DrawerPage.this, "Direct Hotline", Toast.LENGTH_SHORT).show();
                        Intent toHotlinePage = new Intent(DrawerPage.this,HotlinePage.class);
                        startActivity(toHotlinePage);
                        break;

                    default:
                        startActivity(new Intent(DrawerPage.this,HomePage.class));
                        break;
                }

                return true;
            }
        });
    }

}
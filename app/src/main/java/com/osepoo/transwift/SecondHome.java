package com.osepoo.transwift;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SecondHome extends AppCompatActivity {

    Button todrawe;
    ImageView backhome;

    private FirebaseUser user;
    private DatabaseReference reference;
    private String UserID;

    Button logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_home);

        todrawe = (Button) findViewById(R.id.drawerclass);
        backhome = (ImageView) findViewById(R.id.loginbanner3);

        todrawe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SecondHome.this,DrawerPage.class));
            }
        });

        backhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SecondHome.this,HomePage.class));
            }
        });


        logoutButton = (Button) findViewById(R.id.logoutbutton);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(SecondHome.this,HomePage.class));
            }
        });

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        UserID = user.getUid();

        final TextView greetingsTv = (TextView) findViewById(R.id.greetings);
        final TextView emailTv = (TextView) findViewById(R.id.emailAddress);
        final TextView nameTv = (TextView) findViewById(R.id.namedisplay);
        final TextView ageTv = (TextView) findViewById(R.id.agedisplay);

        reference.child(UserID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                if(userProfile != null){
                    String fullName = userProfile.fullName;
                    String email = userProfile.email;
                    String age = userProfile.age;

                greetingsTv.setText(fullName);
                emailTv.setText(email);
                nameTv.setText(fullName);
                ageTv.setText(age);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(SecondHome.this, "Something wrong happened!", Toast.LENGTH_LONG).show();
            }
        });
    }
}
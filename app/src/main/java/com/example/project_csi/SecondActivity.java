package com.example.project_csi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

public class SecondActivity extends AppCompatActivity {
    ImageButton show,services,specialist;
    Button booknow;
    ImageButton clinichour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        final DrawerLayout drawerLayout=findViewById(R.id.drawer_layout);
        show=findViewById(R.id.show_bar);
        booknow=findViewById(R.id.booknow);
        clinichour=findViewById(R.id.clinichour);
        services=findViewById(R.id.bbb);
        specialist=findViewById(R.id.aaa);
        NavigationView navigationView=findViewById(R.id.navigation_view);
        Menu menu = navigationView.getMenu();
        MenuItem logoutItem = ((Menu) menu).findItem(R.id.logout);
        logoutItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                // Handle logout action here
                finish(); // Finish the activity
                return true; // Indicate that the click has been handled
            }
        });

        booknow.setOnClickListener(View ->{
            Intent i=new Intent(SecondActivity.this,BookNow.class);
            startActivity(i);

        });



        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);

            }
        });


        clinichour.setOnClickListener(View->{
            Intent i=new Intent(SecondActivity.this,CLinicHour.class);
            startActivity(i);

        });

        services.setOnClickListener(View->{
            Intent i=new Intent(SecondActivity.this, Services.class);
            startActivity(i);
        });
        specialist.setOnClickListener(View->{
            Intent i=new Intent(SecondActivity.this, Specialist.class);
            startActivity(i);

        });




    }
}




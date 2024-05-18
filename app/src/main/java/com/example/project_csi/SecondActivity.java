package com.example.project_csi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

public class SecondActivity extends AppCompatActivity {
ImageButton show;
NavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        show=findViewById(R.id.show_bar);
        bottomNavigationView = findViewById(R.id.bar);


        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toggle visibility of BottomNavigationView
                if (bottomNavigationView.getVisibility() == View.VISIBLE) {
                    bottomNavigationView.setVisibility(View.GONE);
                } else {
                    bottomNavigationView.setVisibility(View.VISIBLE);
                }
            }
        });
        bottomNavigationView.setNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.share:
                        // Handle share navigation
                        return true;
                    case R.id.logout:
                        // Handle logout navigation
                        return true;
                    default:
                        return false;
                }
            }
        });
    }
}




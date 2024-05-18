package com.example.project_csi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
EditText user,pass;
Button login,sign_up;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user=findViewById(R.id.email);
        pass=findViewById(R.id.pass);
        login=findViewById(R.id.login);
        sign_up=findViewById(R.id.sign_up);
        sign_up.setOnClickListener(View -> {
            String username = user.getText().toString().trim();
            String password = pass.getText().toString().trim();
            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "You have to fill all inputs required", Toast.LENGTH_SHORT).show();
            } else {

            }
        });
        login.setOnClickListener(View->{
            Intent i=new Intent(MainActivity.this,SecondActivity.class);
            startActivity(i);
            user.setText("");
            pass.setText("");
        });


    }
}
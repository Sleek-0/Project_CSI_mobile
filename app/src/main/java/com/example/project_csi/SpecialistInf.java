package com.example.project_csi;


import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

public class SpecialistInf extends AppCompatActivity {

    TextView details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specialist_inf);

        details = findViewById(R.id.details); // Assuming you have a TextView with id "details"

        Intent i = getIntent();
        int id = i.getIntExtra("id", 0);

        RequestQueue queue = Volley.newRequestQueue(this);

        String url = "https://hassanshadad.000webhostapp.com/getSpecialist.php?id=" + id;

        JsonArrayRequest request = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    JSONObject row = response.getJSONObject(0);
                    int id = row.getInt("id");
                    String phone = row.getString("phone");
                    String information = row.getString("information");
                    sp sp = new sp(id, phone, information);
                    details.setText(sp.toString());
                } catch (Exception ex) {
                    Toast.makeText(SpecialistInf.this, ex.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }, null);

        queue.add(request);
    }
}

class sp {
    private int id;
    private String phone;
    private String information;

    public sp(int id, String phone, String information) {
        this.id = id;
        this.phone = phone;
        this.information = information;
    }

    public int getId() {
        return id;
    }

    public String getPhone() {
        return phone;
    }

    public String getInformation() {
        return information;
    }

    @Override
    public String toString() {
        return "ID: " + id + "\nPhone: " + phone + "\nInformation: " + information;
    }
}
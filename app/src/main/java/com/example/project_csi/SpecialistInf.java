package com.example.project_csi;


import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SpecialistInf extends AppCompatActivity {

    TextView details;
    ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specialist_inf);
        back=findViewById(R.id.letsback);
        back.setOnClickListener(View->{
            finish();
        });

        details = findViewById(R.id.details); // Assuming you have a TextView with id "details"
        Intent i = getIntent();
        int id = i.getIntExtra("id", 0);
        RequestQueue queue = Volley.newRequestQueue(this);

        String url = "https://hassanshadad.000webhostapp.com/getOnespecialist.php?id="+id;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            int id = response.getInt("id");
                            String phone_number = response.getString("phone_number");
                            String information = response.getString("information");
                            sp sp = new sp(id, phone_number, information);
                            details.setText(sp.toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(SpecialistInf.this, "Error parsing JSON", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(SpecialistInf.this, "Error fetching data: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        queue.add(request);
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

        @Override
        public String toString() {
            return  "\nPhone: " + phone + "\nInformation: " + information;
        }
    }
}
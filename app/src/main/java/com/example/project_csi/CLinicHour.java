package com.example.project_csi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CLinicHour extends AppCompatActivity {
ImageButton back;
ListView ls;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinic_hour);
        back=findViewById(R.id.clinicback);
        ls=findViewById(R.id.list_view);
        back.setOnClickListener(View->{
            finish();
        });
        ArrayList<clinichours> clinicHours = new ArrayList<>();

// Assuming you have a proper item layout for the ListView items
        ArrayAdapter<clinichours> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, clinicHours);
        ls.setAdapter(adapter);

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://hassanshadad.000webhostapp.com/getClinichours.php";
        JsonArrayRequest request = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject row = response.getJSONObject(i);
                                String day = row.getString("day");
                                String opening = row.getString("opening");
                                String closing = row.getString("closing");

                                clinichours hours = new clinichours(day, opening, closing);
                                clinicHours.add(hours);
                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(CLinicHour.this, "Error parsing JSON", Toast.LENGTH_SHORT).show();
                            }
                        }
                        adapter.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        Toast.makeText(CLinicHour.this, "Request error", Toast.LENGTH_SHORT).show();
                    }
                });

        queue.add(request);


    }
}
class clinichours {

    public String day;
    public String opening;
    public String closing;

    public clinichours(String day, String opening, String closing) {
        this.day = day;
        this.opening = opening;
        this.closing = closing;
    }
    @Override
    public String toString() {
        return day + " " + opening + " " + closing;
    }
}

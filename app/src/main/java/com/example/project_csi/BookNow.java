package com.example.project_csi;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class BookNow extends AppCompatActivity {
    EditText name,phone,service,date,time;
    Button book;
ImageButton back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_now);

        back=findViewById(R.id.back);
        name=findViewById(R.id.name);
        phone=findViewById(R.id.phone);
        service=findViewById(R.id.service);
        date=findViewById(R.id.date);
        time=findViewById(R.id.time);
        book=findViewById(R.id.book_now);
        date.setText("00/00/2024");
        time.setText("0:00");
        back.setOnClickListener(View->{
            finish();
        });
        book.setOnClickListener(view -> {
            String userName = name.getText().toString().trim();
            String userPhone = phone.getText().toString().trim();
            String userService = service.getText().toString().trim();
            String userDate = date.getText().toString().trim();
            String userTime = time.getText().toString().trim();

            if (userName.isEmpty() || userPhone.isEmpty() || userService.isEmpty() || userDate.isEmpty() || userTime.isEmpty()) {
                Toast.makeText(this, "You have to fill all inputs required", Toast.LENGTH_SHORT).show();
            } else if (userDate.length() > 10) {
                Toast.makeText(this, "Invalid date format", Toast.LENGTH_SHORT).show();
            } else if (userTime.length() > 4) {
                Toast.makeText(this, "Invalid time format", Toast.LENGTH_SHORT).show();
            } else {
                // Proceed with the booking process
                // You can define what happens next, e.g., sending data to a server or saving locally
                makeBooking(userName, userPhone, userService, userDate, userTime);
            }
        });


    }
    private void makeBooking(final String name, final String phone, final String service, final String date, final String time) {
        String url = "https://hassanshadad.000webhostapp.com/booking.php";
        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("BookingResponse", "Response: " + response); // Log the response
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            String message = jsonResponse.getString("message");

                            Toast.makeText(BookNow.this, message, Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(BookNow.this, "Parsing error", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(BookNow.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("name", name);
                params.put("phone", phone);
                params.put("service", service);
                params.put("date", date);
                params.put("time", time);
                return params;
            }
        };

        queue.add(postRequest);
    }

}
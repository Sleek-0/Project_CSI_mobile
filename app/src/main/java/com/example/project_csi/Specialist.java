package com.example.project_csi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
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

public class Specialist extends AppCompatActivity {
    ImageButton back;
    ListView ls;



    int[]images= {
            R.drawable.andrew,
            R.drawable.john,
            R.drawable.monica,
            R.drawable.samantha,
            R.drawable.winston,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specialist);
        back=findViewById(R.id.letback);
        ls=findViewById(R.id.specialist);
        back.setOnClickListener(View->{

            finish();;
        });


        ImageAdapter imageAdapter=new ImageAdapter(this,images);
        ls.setAdapter(imageAdapter);
        RequestQueue queue = Volley.newRequestQueue(this);
        String url="https://hassanshadad.000webhostapp.com/getSpecialist.php";

        JsonArrayRequest request=new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                int[] productID=new int[response.length()];
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject specialistObject = response.getJSONObject(i);
                        int id=specialistObject.getInt("id");
                        String phoneNumber = specialistObject.getString("phone_number");
                        String information = specialistObject.getString("information");
                        productID[i]=id;

                        // Now you can use phoneNumber and information as needed
                    }
                    // Notify adapter after looping through all items
                    imageAdapter.notifyDataSetChanged();
                    ls.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent i=new Intent(Specialist.this,specialistInf.class);
                            i.putExtra("id",productID[position]);
                            startActivity(i);
                        }
                    });

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(Specialist.this, "Error parsing JSON", Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Handle error here
                Toast.makeText(Specialist.this, "Error fetching data: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        queue.add(request);

    }
    }


class ImageAdapter extends BaseAdapter {
    private Context context;
    private int[] images;

    public ImageAdapter(Context context, int[] images) {
        this.context = context;
        this.images = images;
    }

    @Override
    public int getCount() {

        return images.length;
    }

    @Override
    public Object getItem(int position) {
        return images[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        }

        ImageView imageView = convertView.findViewById(R.id.imageView);
        imageView.setImageResource(images[position]);

        return convertView;
    }
}
class specialistInf{
    String phone;
    String information;
}


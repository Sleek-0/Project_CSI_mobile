package com.example.project_csi;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class Services extends AppCompatActivity {
    ListView list;
    ImageButton back;
    int[] images = {
            R.drawable.imagea, // Replace with your actual image names
            R.drawable.imageb,
            R.drawable.imagec,
            R.drawable.imaged,
            // Add more images as needed
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);
        back=findViewById(R.id.backnow);

        list = findViewById(R.id.listv); // Ensure your ListView ID matches
        ImageAdapter adapter = new ImageAdapter(this, images);
        list.setAdapter(adapter);
        back.setOnClickListener(View->{
            finish();;
        });

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
}

package com.example.project_csi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;

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
        ls.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(Specialist.this,SpecialistInf.class);
                intent.putExtra("id",i);
                startActivity(intent);
            }
        });
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


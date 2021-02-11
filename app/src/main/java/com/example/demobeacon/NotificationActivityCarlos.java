package com.example.demobeacon;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class NotificationActivityCarlos extends Activity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification);
        ImageView image = findViewById(R.id.imageView);
        Picasso.get().load("https://s2.wklcdn.com/image_72/2188616/22555187/14357633Master.jpg").resize(1000,562).into(image);
        TextView title = findViewById(R.id.title);
        title.setText("Beacon de Carlos");
        TextView description = findViewById(R.id.description);
        description.setText("Este es el beacon de Carlos");

    }

}

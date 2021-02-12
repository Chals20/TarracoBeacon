package com.example.demobeacon;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class NotificationActivityJudith extends Activity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification);
        ImageView image = findViewById(R.id.imageView);
        Picasso.get().load("https://www.tarragonaturisme.cat/es/monumento/pretorio-y-circo-romano-mht").resize(1000,562).into(image);
        TextView title = findViewById(R.id.title);
        title.setText("Plaça Imperial Tarraco");
        TextView description = findViewById(R.id.description);
        description.setText("La Plaça de la Imperial Tàrraco de Tarragona és una gran intersecció giratòria practicable " +
                "a la qual els vianants poden accedir per un pas de zebra des de la Rambla Nova o des de la Rambla President Lluís Companys.");

    }

}

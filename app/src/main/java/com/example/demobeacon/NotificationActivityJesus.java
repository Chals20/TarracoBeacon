package com.example.demobeacon;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class NotificationActivityJesus extends Activity {
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification);
        ImageView image = findViewById(R.id.imageView);
        Picasso.get().load("https://www.tarragonaturisme.cat/sites/default/files/styles/full_image_with_copyright/public/monument/gallery/25_-_ciutat_-_balco_del_mediterrani_amb_gent.jpg?itok=jaxtc2d1").resize(1000,562).into(image);
        TextView title = findViewById(R.id.title);
        title.setText("Balcó del Mediterrani");
        TextView description = findViewById(R.id.description);
        description.setText("El Balcó del Mediterrani es trobat situat al capdamunt de la Rambla Nova, a uns 40 metres sobre el mar, permeten una visió privilegiada del Mare Nostrum, el port de Tarragona, la platja del Miracle i l'Amfiteatre. +" +
                "Lloc molt estimat pels tarragonins i les tarragonines, on conflueixen tots els qui, passejant, van 'a tocar ferro'. La barana té una forma inconfusible i es diu que tocar-la dóna sort.");

    }
}

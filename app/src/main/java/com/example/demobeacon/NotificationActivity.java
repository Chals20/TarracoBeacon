package com.example.demobeacon;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class NotificationActivity extends Activity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification);
        String string;
        ImageView image = findViewById(R.id.imageView);
        TextView title = findViewById(R.id.title);
        TextView description = findViewById(R.id.description);

        if(savedInstanceState == null){
            Bundle bundle = getIntent().getExtras();
            if(bundle == null){
                string = null;
            } else {
                string = bundle.getString("notificacio");
            }
        } else{
            string = (String)savedInstanceState.getSerializable("notificacio");
        }

        switch (string){
            case "b9407f30-f5f8-466e-aff9-25556b57fe6d631420675":
                Picasso.get().load("https:s2.wklcdn.com/image_72/2188616/22555187/14357633Master.jpg").resize(1000,562).into(image);
                title.setText("Plaça Imperial Tarraco");
                description.setText("La Plaça de la Imperial Tàrraco de Tarragona és una gran intersecció giratòria practicable " +
                        "a la qual els vianants poden accedir per un pas de zebra des de la Rambla Nova o des de la Rambla President Lluís Companys.");
                break;
            case "b9407f30-f5f8-466e-aff9-25556b57fe6d1052732676":
                Picasso.get().load("https://www.tarragonaturisme.cat/sites/default/files/styles/full_image_with_copyright/public/monument/gallery/25_-_ciutat_-_balco_del_mediterrani_amb_gent.jpg?itok=jaxtc2d1").resize(1000,562).into(image);
                title.setText("Balcó del Mediterrani");
                description.setText("El Balcó del Mediterrani es trobat situat al capdamunt de la Rambla Nova, a uns 40 metres sobre el mar, permeten una visió privilegiada del Mare Nostrum, el port de Tarragona, la platja del Miracle i l'Amfiteatre. +" +
                        "Lloc molt estimat pels tarragonins i les tarragonines, on conflueixen tots els qui, passejant, van 'a tocar ferro'. La barana té una forma inconfusible i es diu que tocar-la dóna sort.");
                break;
            case "b9407f30-f5f8-466e-aff9-25556b57fe6d5217649395":
                Picasso.get().load("https://upload.wikimedia.org/wikipedia/commons/e/e7/Roman_circus_of_Tarraco_02.jpg").resize(1000,562).into(image);
                title.setText("Circ Romà");
                description.setText("El circ de Tarraco es trobava emplaçat dins el pomerium de la ciutat. +" +
                        "Situat a la part alta de Tarragona, manté un estat excepcional de conservació.");
                break;
        }



    }

}

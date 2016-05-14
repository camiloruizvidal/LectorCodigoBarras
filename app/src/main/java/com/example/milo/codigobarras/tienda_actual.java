package com.example.milo.codigobarras;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class tienda_actual extends AppCompatActivity {
    ListView tienda;
    String[] Tiendas = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        double longitud = location.getLongitude();
        double latitud = location.getLatitude();
        classtienda Listtienda = new classtienda();
        Tiendas = Listtienda.VerTiendas(longitud, latitud);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tienda_actual);
        tienda = (ListView) findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Tiendas);
        tienda.setAdapter(adapter);
        final tienda_actual esto = this;
        tienda.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(esto, compras.class);
                String TiendaElegida = Tiendas[position];
                i.putExtra("tienda", TiendaElegida);
                startActivity(i);
                finish();
            }
        });
    }
}

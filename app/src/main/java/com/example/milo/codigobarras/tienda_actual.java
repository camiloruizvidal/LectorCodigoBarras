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
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class tienda_actual extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ListView tienda;
        List<String[]> Tiendas = null;
        String[] TiendasName = null;
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling

            return;
        }

        Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tienda_actual);

        classtienda Listtienda = new classtienda();
        //location.getLongitude(), location.getLatitude()
        double Lng = -76.593316197395;
        double Lat = 2.4560236930847;
        Tiendas = Listtienda.VerTiendas(Lng, Lat);
        ListTiendas x = null;
        TiendasName = new String[Tiendas.size()];
        final ListTiendas data[] = new ListTiendas[Tiendas.size()];
        for (int i = 0; i < Tiendas.size(); i++) {
            String[] Temp = Tiendas.get(i);
            x = new ListTiendas(Temp[2], Temp[1], Temp[0]);
            data[i] = x;
        }

        try {
            AdapterTiendas adapter = new AdapterTiendas(this, R.layout.imtes, data);
            tienda = (ListView) findViewById(R.id.listViewTiendas);
            tienda.setAdapter(adapter);
        } catch (Exception e) {
            Toast.makeText(tienda_actual.this, "Error en el adapter [" + e.getMessage() + "]", Toast.LENGTH_SHORT).show();
        }
        /*
        final Context Esto = this;
        tienda.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(Esto, "Producto es " + data[position].NombreTienda, Toast.LENGTH_SHORT).show();
            }
        });
        */
    }

}

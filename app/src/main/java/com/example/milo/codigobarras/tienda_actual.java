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
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class tienda_actual extends AppCompatActivity {
    ListView tienda;
    List<String[]> Tiendas = null;
    String[] TiendasName = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling

                return;
            }

            Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            classtienda Listtienda = new classtienda();

            Tiendas = Listtienda.VerTiendas(location.getLongitude(), location.getLatitude());
            TiendasName = new String[Tiendas.size()];
            for (int i = 0; i < Tiendas.size(); i++) {
                String[] Data = Tiendas.get(i);
                TiendasName[i] = Data[1];
            }
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_tienda_actual);
            tienda = (ListView) findViewById(R.id.listView);

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, TiendasName);
            tienda.setAdapter(adapter);
            final tienda_actual esto = this;
            tienda.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent i = new Intent(esto, compras.class);
                    String TiendaElegida = Tiendas.get(position)[0];
                    Log.e("TiendaElegida", TiendaElegida);
                    i.putExtra("tienda", TiendaElegida);
                    startActivity(i);
                    finish();
                }
            });
        } catch (Exception e) {
            Toast.makeText(tienda_actual.this, "Error inexperado: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}

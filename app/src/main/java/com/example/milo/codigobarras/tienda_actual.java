package com.example.milo.codigobarras;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class tienda_actual extends AppCompatActivity {
    ListView tienda;
    String[] Tiendas = new String[]{"Carulla", "Exito", "El vecino"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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

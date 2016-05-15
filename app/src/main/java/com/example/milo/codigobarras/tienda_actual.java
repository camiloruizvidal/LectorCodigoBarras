package com.example.milo.codigobarras;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.List;

public class tienda_actual extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tienda_actual);

        classgps Loc = new classgps();

        ListView tienda;
        List<String[]> Tiendas = null;
        String[] TiendasName = null;
        classtienda Listtienda = new classtienda();
        Tiendas = Listtienda.VerTiendas(Loc.Longitud, Loc.Latitud);
        TiendasName = new String[Tiendas.size()];
        final ListTiendas data[] = new ListTiendas[Tiendas.size()];
        for (int i = 0; i < Tiendas.size(); i++) {
            ListTiendas x = null;
            String[] Temp = Tiendas.get(i);
            x = new ListTiendas(Temp[2], Temp[1], Temp[0]);
            data[i] = x;
        }

        AdapterTiendas adapter = new AdapterTiendas(this, R.layout.list_tiendas, data);
        tienda = (ListView) findViewById(R.id.listViewTiendas);
        tienda.setAdapter(adapter);
        /**/



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

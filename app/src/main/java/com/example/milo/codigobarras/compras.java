package com.example.milo.codigobarras;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.milo.codigobarras.com.google.zxing.integration.Productos;

import java.util.List;

public class compras extends AppCompatActivity implements View.OnClickListener {

    private String[] ListProductos;
    private ListView listado_productos;
    private TextView TotaltextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compras);
        listado_productos = (ListView) findViewById(R.id.listado_productos);
        Button consultar = (Button) findViewById(R.id.consultarcompras);
        TotaltextView = (TextView) findViewById(R.id.TotaltextView);
        consultar.setOnClickListener(this);
        ConsultarProductos();
    }

    private void ConsultarProductos() {
        Productos pro = new Productos();
        pro.AddContext(this);
        List<String[]> Datos = pro.VerListadoProductos();
        int total = Datos.size();
        items_list data[] = new items_list[total];
        for (int i = 0; i < total; i++) {
            String[] Temp = Datos.get(i);
            new items_list(Temp[2]);
        }
        AdapterItems adapter = new AdapterItems(this, R.layout.imtes, data);
        TotaltextView.setText("$" + pro.PrecioTotal());
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }
}
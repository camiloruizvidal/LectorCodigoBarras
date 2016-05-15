package com.example.milo.codigobarras;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.milo.codigobarras.com.google.zxing.integration.Productos;

import java.text.DecimalFormat;
import java.util.List;

public class compras extends AppCompatActivity implements View.OnClickListener {
    private String CodTIenda;
    private String[] ListProductos;
    private ListView listado_productos;
    private TextView TotaltextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compras);
        Intent intent = getIntent();
        this.CodTIenda=intent.getStringExtra("CodTIenda");
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
        final items_list data[] = new items_list[total];
        for (int i = 0; i < total; i++) {
            String[] Temp = Datos.get(i);
            items_list x = new items_list(Temp[0],Temp[2], Temp[5], Temp[4]);
            data[i] = x;
        }

        AdapterItems adapter = new AdapterItems(this, R.layout.imtes, data);
        listado_productos.setAdapter(adapter);
        DecimalFormat format = new DecimalFormat("'$'#,###,###.##");
        TotaltextView.setText(format.format(pro.PrecioTotal()));
        listado_productos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(compras.this, "Producto es "+data[position].nombre, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(this, MainActivity.class);
        i.putExtra("CodTIenda", this.CodTIenda);
        startActivity(i);
        finish();
    }
}
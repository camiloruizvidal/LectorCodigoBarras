package com.example.milo.codigobarras;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class compras extends AppCompatActivity implements View.OnClickListener {

    private String Tienda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compras);
        Tienda = getIntent().getExtras().getString("tienda");
        Button consultar = (Button) findViewById(R.id.consultarcompras);
        consultar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}

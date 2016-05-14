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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.milo.codigobarras.com.google.zxing.integration.IntentIntegrator;
import com.example.milo.codigobarras.com.google.zxing.integration.IntentResult;
import com.example.milo.codigobarras.com.google.zxing.integration.Productos;

import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button scanBtn, AddBtn, CancelBtn;
    private TextView formatTxt, contentTxt, precio;
    private int CantidadProductos;
    private EditText cantidad;
    Productos pro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        pro = new Productos();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scanBtn = (Button) findViewById(R.id.scan_button);
        AddBtn = (Button) findViewById(R.id.AddBtn);
        CancelBtn = (Button) findViewById(R.id.CancelBtn);

        formatTxt = (TextView) findViewById(R.id.scan_format);
        contentTxt = (TextView) findViewById(R.id.scan_content);
        precio = (TextView) findViewById(R.id.precio);
        cantidad = (EditText) findViewById(R.id.cantiEditText);
        IntentIntegrator scanIntegrator = new IntentIntegrator(this);
        scanIntegrator.initiateScan();

        scanBtn.setOnClickListener(this);
        AddBtn.setOnClickListener(this);
        CancelBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent i;
        switch (view.getId()) {
            case R.id.scan_button:
                IntentIntegrator scanIntegrator = new IntentIntegrator(this);
                scanIntegrator.initiateScan();
                break;
            case R.id.AddBtn:
                AgregarElementos();
                i = new Intent(this, compras.class);
                startActivity(i);
                finish();
                break;
            case R.id.CancelBtn:
                i = new Intent(this, compras.class);
                startActivity(i);
                finish();
                break;
        }
    }

    private void AgregarElementos() {
        pro.AddContext(this);
        pro.cantidad = cantidad.getText().toString();
        pro.AgregarProductoLista();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanningResult != null) {
            conexion Res = new conexion();
            String scanContent = scanningResult.getContents();
            String scanFormat = scanningResult.getFormatName();
            pro.VerProducto(scanContent);
            contentTxt.setText("Producto:" + pro.Nombre + ". " + pro.Descripcion);
            precio.setText("Precio: $" + pro.Precio);
            formatTxt.setText("Formato: " + scanFormat);
        }
    }
}

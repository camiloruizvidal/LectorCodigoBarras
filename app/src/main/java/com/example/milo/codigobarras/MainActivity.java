package com.example.milo.codigobarras;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.milo.codigobarras.com.google.zxing.integration.IntentIntegrator;
import com.example.milo.codigobarras.com.google.zxing.integration.IntentResult;
import com.example.milo.codigobarras.com.google.zxing.integration.Productos;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button scanBtn;
    private TextView formatTxt, contentTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        scanBtn = (Button) findViewById(R.id.scan_button);
        formatTxt = (TextView) findViewById(R.id.scan_format);
        contentTxt = (TextView) findViewById(R.id.scan_content);
        scanBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.scan_button) {
            IntentIntegrator scanIntegrator = new IntentIntegrator(this);
            scanIntegrator.initiateScan();
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanningResult != null) {

            Productos pro = new Productos();
            conexion Res = new conexion();
            String scanContent = scanningResult.getContents();
            String scanFormat = scanningResult.getFormatName();
            pro.VerProducto("1",scanContent);
            String Resultado = Res.Consultar().toString();

            contentTxt.setText("Contenido: " + Resultado);
            formatTxt.setText("Formato: " + scanFormat);
        }
    }
}

package com.example.milo.codigobarras;


import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class classtienda {

    public List<String[]> VerTiendas(double Longitud, double Latitud) {
        List<String[]> tiendas = new ArrayList<>();
        try {
            String Resultado = "";
            conexion Res = new conexion();

            Res.Parametros("Estado", "vertiendas");
            Res.Parametros("Parametros[Longitud]", Double.toString(Longitud));
            Res.Parametros("Parametros[Latitud]", Double.toString(Latitud));
            Resultado = Res.Consultar();

            Resultado = "[ { \"id\": \"c4ca4238a0b923820dcc509a6f75849b\", \"nombre\": \"La casa de Do\\u00f1a Gloria\" ,\"urlimage\":\"http://grivaningenieria.com.co/wp-content/uploads/2015/04/exito-logo.png\"}, { \"id\": \"c4ca4238a0b923820dcc509a6f75849c\", \"nombre\": \"La puerca est\\u00e1 en la pocilga\" ,\"urlimage\":\"http://grivaningenieria.com.co/wp-content/uploads/2015/04/exito-logo.png\" } ]";
            Log.e("resultado", Resultado);
            JSONArray jsonArr = null;
            jsonArr = new JSONArray(Resultado);

            for (int i = 0; i < jsonArr.length(); i++) {
                String temp = jsonArr.getString(i);
                JSONObject jsonObj = new JSONObject(temp);
                String[] Datatiendas = new String[jsonObj.length()];
                Datatiendas[0] = jsonObj.getString("id");
                Datatiendas[1] = jsonObj.getString("nombre");
                Datatiendas[2] = jsonObj.getString("urlimage");
                String[] add=Datatiendas;
                tiendas.add(add);
            }
        } catch (JSONException e) {
            Log.e("===================", e.getLocalizedMessage());
        } finally {
            return tiendas;
        }
    }

}

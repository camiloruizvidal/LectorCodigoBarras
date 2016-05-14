package com.example.milo.codigobarras;


import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class classtienda {

    public String[] VerTiendas(double Longitud, double Latitud) {
        String[] tiendas = {};
        try {
        conexion Res = new conexion();

        Res.Parametros("Estado", "vertiendas");
        Res.Parametros("Longitud", Double.toString(Longitud));
        Res.Parametros("Latitud", Double.toString(Latitud));
        String Resultado = Res.Consultar();
        JSONObject jsonObj = null;
        Log.e("resultado",Resultado);


            jsonObj = new JSONObject(Resultado);
        } catch (JSONException e) {
            Log.e("Pendejo, la embarraste",e.getMessage());
        }
        finally {
        return tiendas;
        }
    }

}

package com.example.milo.codigobarras;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class conexion extends AsyncTask<String, String, String> {

    private List<String[]> paramentros;
    private String Type;
    private String Ruta;
    private String Resultados;

    public conexion() {
        Resultados = "No aparecio nada";
        //Ruta = "http://solucionescrv.com/noniko/rest.php";
        Ruta = "http://192.168.0.26/sis_cod_bar/rest.php";
        //Ruta = "http://192.168.56.1/sis_cod_bar/rest.php";
        paramentros = new ArrayList<>();
        Type = "GET";
    }

    public void Parametros(String Nombre, String Valor) {
        String[] temp = new String[2];
        temp[0] = Nombre;
        temp[1] = Valor;
        paramentros.add(temp);
    }

    private String Parameters(List<String[]> paramentres) {
        String data = "";
        String[] resultado = new String[paramentres.size()];
        int i = 0;
        for (String[] temp : paramentres) {
            resultado[i] = (temp[0] + "=" + temp[1]);
            i++;
        }
        data = implode(resultado, "&");
        return data;
    }

    private String implode(String[] data, String Glue) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < data.length - 1; i++) {
            //data.length - 1 => to not add separator at the end
            if (!data[i].matches(" *")) {//empty string are ""; " "; "  "; and so on
                sb.append(data[i]);
                sb.append(Glue);
            }
        }
        sb.append(data[data.length - 1].trim());
        return sb.toString();
    }


    @Override
    protected String doInBackground(String... params) {
        URL url;
        HttpURLConnection urlConnection = null;
        JSONArray response = new JSONArray();
        String ruta = params[0];
        try {
            String Data = Parameters(this.paramentros);
            if (Data != "") {
                ruta = ruta + "?" + Data;
            }
            url = new URL(ruta);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod(this.Type);

            int responseCode = urlConnection.getResponseCode();
            if (responseCode == 200) {

                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                String Res = readStream(in);
                this.Resultados = "";
                this.Resultados = Res;
            } else {
                this.Resultados = ("Response code:" + responseCode);
            }
        } catch (Exception e) {
            Log.d("Error", e.getMessage());
            this.Resultados = e.getMessage();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return this.Resultados;
    }

    private String readStream(InputStream in) {
        BufferedReader reader = null;
        StringBuffer response = new StringBuffer();
        try {
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return response.toString();
    }

    public String Consultar() {
        try {
            String Resultado = this.execute(this.Ruta).get();
            return Resultado;
        } catch (InterruptedException e) {
            return "";
        } catch (ExecutionException e) {
            return "";
        }
    }
}
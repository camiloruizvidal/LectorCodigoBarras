package com.example.milo.codigobarras.com.google.zxing.integration;

import com.example.milo.codigobarras.conexion;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by MILO on 14/04/2016.
 */
public class Productos {
    public String Descripcion;
    public String Nombre;
    public String Precio;
    public String codigobar;

    public void VerProducto(String numero_cod_barra) {
        String id_tienda = "1";
        conexion Res = new conexion();
        //Ingresando parametros para enviar
        Res.Parametros("id_tienda", id_tienda);
        Res.Parametros("numero_cod_barra", numero_cod_barra);
        //Se ingresaron todos los parametros
        String Resultado = Res.Consultar();
        JSONObject jsonObj = null;
        try {
            jsonObj = new JSONObject(Resultado);
            VerDatosProductos(jsonObj);


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void VerDatosProductos(JSONObject jsonObj) {
        this.Descripcion = "";
        this.Precio = "0";
        this.codigobar = "";
        try {
            String Existe = jsonObj.getString("Existe");
            if (Existe.equals("Si"))
            {

                JSONObject Datos=jsonObj.getJSONObject("datos");
                this.Nombre = Datos.getString("nombre");
                this.Descripcion = Datos.getString("descripcion");
                this.Precio = Datos.getString("precio");
                this.codigobar = Datos.getString("numero_cod_barra");
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}

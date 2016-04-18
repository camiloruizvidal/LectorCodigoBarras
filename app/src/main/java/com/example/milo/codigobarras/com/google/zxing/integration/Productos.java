package com.example.milo.codigobarras.com.google.zxing.integration;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import com.example.milo.codigobarras.conexion;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by MILO on 14/04/2016.
 */
public class Productos {
    public String codigobar;
    public String Nombre;
    public String Descripcion;
    public String Precio;
    public int cantidad;
    private Context context;

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

    public void AgregarProductoLista() {
        conexion_local con2 = new conexion_local(context, "tbl_datos", null, 1);
        ContentValues registro = new ContentValues();
        registro.put("cod_bar", this.codigobar);
        registro.put("nombre", this.Nombre);
        registro.put("descripcion", this.Descripcion);
        registro.put("precio", this.Precio);
        registro.put("precio", this.cantidad);
        try {
            con2.UpdateOrInsert(registro, "Select cod_bar, descripcion, nombre, precio, cantidad from " + con2.getTabla() + " ", " cod_bar='" + this.codigobar + "' ");
        } catch (Exception e) {
            Log.d("Error", e.getMessage());
        }
    }

    private void VerDatosProductos(JSONObject jsonObj) {
        this.Descripcion = "";
        this.Precio = "0";
        this.codigobar = "";
        try {
            String Existe = jsonObj.getString("Existe");
            if (Existe.equals("Si")) {

                JSONObject Datos = jsonObj.getJSONObject("datos");
                this.Nombre = Datos.getString("nombre");
                this.Descripcion = Datos.getString("descripcion");
                this.Precio = Datos.getString("precio");
                this.codigobar = Datos.getString("numero_cod_barra");
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void EliminarProducto(int id_cod_bar) {

    }

    public void AddContext(Context context) {
        this.context = context;
    }

    public double PrecioTotal() {
        double total = 0;
        ArrayList<String[]> Datos = this.VerListadoProductos();
        return total;
    }

    public ArrayList<String[]> VerListadoProductos() {
        conexion_local con2 = new conexion_local(context, "tbl_datos", null, 1);
        ArrayList<String[]> Datos = con2.Records("Select codigo, cod_bar, nombre, descripcion,  precio, cantidad from " + con2.getTabla());
        return Datos;
    }
}

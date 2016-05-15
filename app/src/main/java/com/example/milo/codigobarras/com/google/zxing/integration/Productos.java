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
    public String ref;
    public String nombre;
    public String descripcion;
    public String precio;
    public String cantidad;
    private Context context;

    public void VerProducto(String numero_cod_barra, String CodTIenda) {
        conexion Res = new conexion();
        //Ingresando parametros para enviar
        Res.Parametros("Estado", "BuscarProducto");
        Res.Parametros("Parametros[CodTienda]", CodTIenda);
        Res.Parametros("Parametros[numero_cod_barra]", numero_cod_barra);
        //Se ingresaron todos los parametros
        String Resultado = Res.Consultar();
        JSONObject jsonObj = null;
        try {
            Log.e("BuscarError", Resultado);
            jsonObj = new JSONObject(Resultado);
            VerDatosProductos(jsonObj);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void AgregarProductoLista() {
        conexion_local con2 = new conexion_local(context, "tbl_datos", null, 1);
        ContentValues registro = new ContentValues();
        registro.put("ref", this.ref);
        registro.put("descripcion", this.descripcion);
        registro.put("nombre", this.nombre);
        registro.put("precio", this.precio);
        registro.put("cantidad", this.cantidad);
        try {
            con2.UpdateOrInsert(registro, "Select ref, descripcion, nombre, precio, cantidad from " + con2.getTabla() + " ", " ref='" + this.ref + "' ");
        } catch (Exception e) {
            Log.d("Error", e.getMessage());
        }
    }

    private void VerDatosProductos(JSONObject jsonObj) {


        this.descripcion = "";
        this.precio = "0";
        this.ref = "";
        this.nombre = "";
        try {
            String Existe = jsonObj.getString("Existe");
            if (Existe.equals("Si")) {

                this.nombre = jsonObj.getString("nombre");
                this.descripcion = jsonObj.getString("descripcion");
                this.precio = jsonObj.getString("precio");
                this.ref = jsonObj.getString("ref");
            }

        } catch (JSONException e) {
            Log.d("Error_json", e.getMessage());
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

        for (int i = 0; i < Datos.size(); i++) {
            String[] temp = Datos.get(i);
            total = total + (Double.parseDouble(temp[4]) * Double.parseDouble(temp[5]));
        }
        return total;
    }

    public ArrayList<String[]> VerListadoProductos() {
        conexion_local con2 = new conexion_local(context, "tbl_datos", null, 1);
        ArrayList<String[]> Datos = con2.Records("Select ref, descripcion, nombre, precio, cantidad  from " + con2.getTabla());
        return Datos;
    }

    public ArrayList<String> VerProductoCod(String codigo) {
        conexion_local con2 = new conexion_local(context, "tbl_datos", null, 1);
        ArrayList<String> Datos = con2.Record("Select ref, descripcion, nombre, precio, cantidad from " + con2.getTabla() + " where ref=" + codigo);
        return Datos;
    }
}

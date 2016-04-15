package com.example.milo.codigobarras.com.google.zxing.integration;

import com.example.milo.codigobarras.conexion;

import org.json.JSONObject;

/**
 * Created by MILO on 14/04/2016.
 */
public class Productos {
    public JSONObject VerProducto(String id_tienda, String numero_cod_barra) {

        conexion Res = new conexion();
        //Ingresando parametros para enviar
        Res.Parametros("id_tienda", id_tienda);
        Res.Parametros("numero_cod_barra", numero_cod_barra);
        //Se ingresaron todos los parametros
        String Resultado = Res.Consultar();
        JSONObject jsonObj = new JSONObject();
        return jsonObj;
    }
}

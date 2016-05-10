package com.example.milo.codigobarras;

import java.text.DecimalFormat;

/**
 * Created by MILO on 09/05/2016.
 */
public class items_list {
    public Integer id;
    public String nombre;
    public String cantidad;
    public String precio;
    public String total;

    public items_list() {
        super();
    }

    public items_list(String id, String nombre, String cantidad, String precio) {
        super();
        DecimalFormat format = new DecimalFormat("'$'#,###,###.##");
        this.nombre =nombre;
        this.id=Integer.getInteger(id);
        this.cantidad ="Cantidad: "+ cantidad;
        this.precio ="Precio: "+ format.format(Double.parseDouble(precio));
        this.total="Total: "+format.format((Double.parseDouble(cantidad) * Double.parseDouble(precio)));
    }
}

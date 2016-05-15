package com.example.milo.codigobarras;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by MILO on 09/05/2016.
 */
public class AdapterItems extends ArrayAdapter<items_list> {

    Context context;
    int LayoutResourceId;
    items_list[] data = null;

    public AdapterItems(Context context, int LayoutResourceId, items_list[] data) {
        super(context, LayoutResourceId, data);
        this.context = context;
        this.LayoutResourceId = LayoutResourceId;
        this.data = data;
    }

    public View getView(int position, View ConvertView, ViewGroup parent) {
        View row = ConvertView;
        items_listHolder holder = null;
        if (row == null) {
            LayoutInflater Inflater = ((Activity) context).getLayoutInflater();
            row = Inflater.inflate(LayoutResourceId, parent, false);

            holder = new items_listHolder();
            holder.Producto = (TextView) row.findViewById(R.id.TxVwProducto);
            holder.Precio = (TextView) row.findViewById(R.id.TxVwPrecio);
            holder.Cantidad = (TextView) row.findViewById(R.id.TxVwCantidad);
            holder.Total = (TextView) row.findViewById(R.id.TxVwTotal);
            row.setTag(holder);
        } else {
            holder = (items_listHolder) row.getTag();
        }
        items_list items = data[position];
        holder.Producto.setText(items.nombre);
        holder.Precio.setText(items.precio);
        holder.Cantidad.setText(items.cantidad);
        holder.Total.setText(items.total);
        return row;
    }

    static class items_listHolder {
        TextView Producto;
        TextView Precio;
        TextView Cantidad;
        TextView Total;
    }
}

package com.example.milo.codigobarras;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
public class AdapterTiendas extends ArrayAdapter<ListTiendas> {
    Context context;
    int LayoutResourceId;
    ListTiendas[] data = null;

    public AdapterTiendas(Context context, int LayoutResourceId, ListTiendas[] data) {
        super(context, LayoutResourceId, data);
        this.context = context;
        this.LayoutResourceId = LayoutResourceId;
        this.data = data;
    }

    public View getView(int position, View ConvertView, ViewGroup parent)
    {
        View row = ConvertView;
        classImagesUrl img = new classImagesUrl();
        ListTiendasHolder holder = null;
        if (row == null) {
            LayoutInflater Inflater = ((Activity) context).getLayoutInflater();
            row = Inflater.inflate(LayoutResourceId, parent, false);
            holder = new ListTiendasHolder();
            holder.Tienda = (TextView) row.findViewById(R.id.TextViewNombreTienda);
            holder.ImagenLogo =(ImageView) row.findViewById(R.id.imageViewLogoAlmacen);
            row.setTag(holder);
        } else {
            holder = (ListTiendasHolder) row.getTag();
        }
        ListTiendas items = data[position];
        holder.Tienda.setText(items.NombreTienda);
        //holder.ImagenLogo.setImageBitmap(img.crearImaView(items.UrlImage));
        return row;
    }

    static class ListTiendasHolder {
        TextView Tienda;
        ImageView ImagenLogo;

    }
}

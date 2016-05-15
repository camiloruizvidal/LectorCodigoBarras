package com.example.milo.codigobarras;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

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

    public View getView(int position, View ConvertView, ViewGroup parent) {
        View row = ConvertView;
        ListTiendasHolder holder = null;
        if (row == null) {
            LayoutInflater Inflater = ((Activity) this.context).getLayoutInflater();
            row = Inflater.inflate(this.LayoutResourceId, parent, false);

            holder = new ListTiendasHolder();
            holder.Tienda = (TextView) row.findViewById(R.id.TextViewNombreTienda);
            holder.Logo = (ImageView) row.findViewById(R.id.imageViewLogoAlmacen);
            row.setTag(holder);
        } else {
            holder = (ListTiendasHolder) row.getTag();
        }

        ListTiendas items = data[position];
        holder.Tienda.setText(items.NombreTienda);
        try {
            GetImageTask img = new GetImageTask(holder.Logo);
            holder.Logo.setImageBitmap(img.Buscar(items.UrlImage));
        } catch (Exception e) {
            Log.e("Nombre", e.getMessage());
        } finally {
            return row;
        }
    }

    static class ListTiendasHolder {
        TextView Tienda;
        ImageView Logo;
    }
}

class GetImageTask extends AsyncTask<String, Void, Bitmap> {
    ImageView bmImage;

    public GetImageTask(ImageView bmImage) {
        this.bmImage = bmImage;
    }

    protected Bitmap doInBackground(String... urls) {
        String urldisplay = urls[0];
        Bitmap mIcon11 = null;
        try {
            InputStream in = new java.net.URL(urldisplay).openStream();
            mIcon11 = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return mIcon11;
    }

    protected void onPostExecute(Bitmap result) {
        bmImage.setImageBitmap(result);
    }

    public Bitmap Buscar(String url) {
        Bitmap Imagen = null;
        try {
            Imagen = this.execute(url).get();
            return Imagen;
        } catch (InterruptedException e) {
            return Imagen;
        } catch (ExecutionException e) {
            return Imagen;
        }

    }
}

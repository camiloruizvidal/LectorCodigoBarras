package com.example.milo.codigobarras;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by MILO on 15/05/2016.
 */
public class classImagesUrl {
    public Bitmap crearImaView(String URL_image) {
        URL newurl = null;
        Bitmap mIcon_val = null;
        try {
            newurl = new URL(URL_image);
            mIcon_val = BitmapFactory.decodeStream(newurl.openConnection().getInputStream());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mIcon_val;
    }
}

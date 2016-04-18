package com.example.milo.codigobarras.com.google.zxing.integration;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by MILO on 17/04/2016.
 */
public class conexion_local extends SQLiteOpenHelper {

    private Context context;
    private String name;
    private int version;
    private String tabla;

    public String getTabla() {
        return tabla;
    }

    public conexion_local(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.name = name;
        this.context = context;
        this.version = version;
        this.tabla = " tbl_informacion ";
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + this.tabla + "(codigo integer primary key autoincrement, cod_bar text, descripcion text, nombre text, precio real, cantidad integer)");
    }

    public void Save(ContentValues registro) {
        conexion_local admin = new conexion_local(context, name, null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        bd.insert(this.tabla, null, registro);
        bd.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public ArrayList<String> Record(String sql) {
        conexion_local admin = new conexion_local(this.context, name, null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        Cursor fila = bd.rawQuery(sql, null);
        ArrayList<String> Datos = new ArrayList<String>(0);
        for (int j = 0; j < fila.getCount(); j++)

        {
            fila.moveToNext();
            int columns = fila.getColumnCount();
            for (int i = 0; i < fila.getColumnCount(); i++) {
                Datos.add(fila.getString(i));
            }

        }
        bd.close();
        return Datos;
    }

    public ArrayList<String[]> Records(String sql) {
        ArrayList<String[]> Datos = new ArrayList<String[]>(0);
        try {
            conexion_local admin = new conexion_local(this.context, name, null, 1);
            SQLiteDatabase bd = admin.getWritableDatabase();
            Cursor fila = bd.rawQuery(sql, null);
            String[] temp;
            for (int j = 0; j < fila.getCount(); j++)

            {
                fila.moveToNext();
                temp = null;
                int columns = fila.getColumnCount();
                temp = new String[columns];
                for (int i = 0; i < fila.getColumnCount(); i++) {
                    temp[i] = fila.getString(i);
                }
                Datos.add(temp);

            }
            bd.close();
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
        }
        return Datos;
    }

    public void UpdateOrInsert(ContentValues registro, String Sql, String Where) {
        ArrayList<String[]> Record = this.Records(Sql);
        if (Record.size() > 0) {
            Update(registro, Where);
        } else {
            this.Save(registro);
        }
    }

    public boolean Update(ContentValues registro, String where) {
        if (where != "") {
            conexion_local admin = new conexion_local(context,
                    name, null, 1);
            SQLiteDatabase bd = admin.getWritableDatabase();
            int cant = bd.update(this.tabla, registro, where, null);
            bd.close();
            if (cant == 1)
                return true;
            else
                return false;
        } else {
            return false;
        }
    }
}

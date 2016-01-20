package com.example.clase.practicamusicacontentprovaider.Gestion;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.clase.practicamusicacontentprovaider.BaseDatos.Ayudante;
import com.example.clase.practicamusicacontentprovaider.BaseDatos.Galeria;
import com.example.clase.practicamusicacontentprovaider.tablas.Cancion;

import java.util.ArrayList;
import java.util.List;

public class GestorCanciones {
    private Ayudante abd;
    private SQLiteDatabase bd;

    public GestorCanciones(Context c) {
        abd = new Ayudante(c);
    }
    public void open() {
        bd = abd.getWritableDatabase();
    }

    public void close() {
        abd.close();
    }

    public void insertarCanciones(ArrayList<Cancion> listaCanciones) {
        for (int i = 0; i < listaCanciones.size(); i++) {
            ContentValues values = new ContentValues();
            values.put("nombreCancion", listaCanciones.get(i).getNombreCancion());
            values.put("idDisco", listaCanciones.get(i).getIdDisco());
            bd.insert(Galeria.TablaCanciones.TABLA, null, values);
        }
    }
    public List<Cancion> selectCanciones() {
        List<Cancion> lista;
        lista = new ArrayList<Cancion>();
        Cursor cursor = bd.query(Galeria.TablaCanciones.TABLA, null,
                null, null, null, null, null, null);
        cursor.moveToFirst();
        Cancion cancion;
        while (!cursor.isAfterLast()) {
            cancion = getRowCancion(cursor);
            lista.add(cancion);
            cursor.moveToNext();
        }
        cursor.close();
        if(lista==null){
            lista.add(new Cancion());
            return lista;
        }
        return lista;
    }
    public Cancion getRowCancion(Cursor c) {
        Cancion cancion = new Cancion();
        cancion.setIdCancion(c.getInt(c.getColumnIndex(Galeria.TablaCanciones._ID)));
        cancion.setNombreCancion(c.getString(1));
        cancion.setIdDisco(c.getInt(2));
        return cancion;
    }
    public void borrarTodasCanciones(){
        bd.delete(Galeria.TablaCanciones.TABLA, null,
                null);
    }
}

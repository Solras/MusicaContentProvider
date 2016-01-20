package com.example.clase.practicamusicacontentprovaider.Gestion;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.clase.practicamusicacontentprovaider.BaseDatos.Ayudante;
import com.example.clase.practicamusicacontentprovaider.BaseDatos.Galeria;
import com.example.clase.practicamusicacontentprovaider.tablas.Disco;

import java.util.ArrayList;
import java.util.List;

public class GestorDiscos {

    private Ayudante abd;
    private SQLiteDatabase bd;

    public GestorDiscos(Context c) {
        abd = new Ayudante(c);
    }
    public void open() {
        bd = abd.getWritableDatabase();
    }

    public void close() {
        abd.close();
    }
    //-----------------Principal--------------------------------------------
    public List<Disco> selectDiscos() {
        List<Disco> lista;
        lista = new ArrayList<Disco>();
        Cursor cursor = bd.query(Galeria.TablaDiscos.TABLA, null,
                null, null, null, null, null, null);
        cursor.moveToFirst();
        Disco disco;
        while (!cursor.isAfterLast()) {
            disco= getRowDisco(cursor);
            lista.add(disco);
            cursor.moveToNext();
        }
        cursor.close();
        if(lista==null){
            lista.add(new Disco());
            return lista;
        }
        System.out.println("luisillo" + lista.toString());
        return lista;
    }
    public Disco getRowDisco(Cursor c) {
        Disco disco = new Disco();
        disco.setIdDisco(c.getInt(c.getColumnIndex(Galeria.TablaDiscos._ID)));
        disco.setTiulo(c.getString(1));
        disco.setIdInterprete(c.getInt(2));
        return disco;
    }
    public void borrarTodosDiscos(){
        bd.delete(Galeria.TablaDiscos.TABLA, null,
                null);
    }
    public void insertarDiscos(ArrayList<Disco> listaDiscos) {
        for (int i = 0; i < listaDiscos.size(); i++) {
            ContentValues values = new ContentValues();
            values.put("titulo", listaDiscos.get(i).getTiulo());
            values.put("idInterprete", listaDiscos.get(i).getIdInterprete());
            bd.insert(Galeria.TablaDiscos.TABLA, null, values);
        }
    }
}

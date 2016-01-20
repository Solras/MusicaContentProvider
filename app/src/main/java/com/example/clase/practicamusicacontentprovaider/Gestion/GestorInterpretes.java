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

import com.example.clase.practicamusicacontentprovaider.BaseDatos.Ayudante;
import com.example.clase.practicamusicacontentprovaider.BaseDatos.Galeria;
import com.example.clase.practicamusicacontentprovaider.tablas.Interprete;

import java.util.ArrayList;
import java.util.List;

public class GestorInterpretes {
    private Ayudante abd;
    private SQLiteDatabase bd;

    public GestorInterpretes(Context c) {
        abd = new Ayudante(c);
    }
    public void open() {
        bd = abd.getWritableDatabase();
    }

    public void close() {
        abd.close();
    }
    public void insertarInterpretes(ArrayList<Interprete> listaInterpretes) {
        for (int i = 0; i < listaInterpretes.size(); i++) {
            ContentValues values = new ContentValues();
            values.put("nombreInterprete", listaInterpretes.get(i).getNombre());
            bd.insert(Galeria.TablaInterpretes.TABLA, null, values);
            System.out.println("MIAPP for");
        }
    }
    public List<Interprete> selectInterpretes() {
        List<Interprete> lista;
        lista = new ArrayList<Interprete>();
        Cursor cursor = bd.query(Galeria.TablaDiscos.TABLA, null,
                null, null, null, null, null, null);
        cursor.moveToFirst();
        Interprete interprete;
        while (!cursor.isAfterLast()) {
            interprete= getRowInterprete(cursor);
            lista.add(interprete);
            cursor.moveToNext();
        }
        cursor.close();
        if(lista==null){
            lista.add(new Interprete());
            return lista;
        }
        System.out.println("InterpretesSelect" + lista.toString());
        return lista;
    }
    public Interprete getRowInterprete(Cursor c) {
        Interprete interprete = new Interprete();
        interprete.setIdInterprete(c.getInt(c.getColumnIndex(Galeria.TablaDiscos._ID)));
        interprete.setNombre(c.getString(1));
        return interprete;
    }
    //---------------------------------------------------------------------------
    public void borrarTodosInterpretes(){
        bd.delete(Galeria.TablaInterpretes.TABLA, null,
                null);
    }
}
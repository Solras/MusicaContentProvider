package com.example.clase.practicamusicacontentprovaider.BaseDatos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Ayudante extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "musicaNueva3.sqlite";
    public static final int DATABASE_VERSION = 4;

    public Ayudante(Context context) {
        super(context, DATABASE_NAME, null,
                DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql;
        sql = "create table " + Galeria.TablaCanciones.TABLA +
                " (" + Galeria.TablaCanciones._ID +
                " integer primary key autoincrement, " +
                Galeria.TablaCanciones.NOMBRECANCION + " text, " +
                Galeria.TablaCanciones.IDDISCO + " text) ";
        db.execSQL(sql);
        sql = "create table " + Galeria.TablaDiscos.TABLA +
                " (" + Galeria.TablaDiscos._ID +
                " integer primary key autoincrement, " +
                Galeria.TablaDiscos.TITULO + " text, " +
                Galeria.TablaDiscos.IDINTERPRETE + " text)";
        db.execSQL(sql);
        sql = "create table " + Galeria.TablaInterpretes.TABLA +
                " (" + Galeria.TablaInterpretes._ID +
                " integer primary key autoincrement, " +
                Galeria.TablaInterpretes.NOMBRE + " text)";
        db.execSQL(sql);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql="drop table if exists "+ Galeria.TablaCanciones.TABLA;
        db.execSQL(sql);
        String sql1="drop table if exists "+ Galeria.TablaDiscos.TABLA;
        db.execSQL(sql1);
        String sql2="drop table if exists "+ Galeria.TablaInterpretes.TABLA;
        db.execSQL(sql2);
        onCreate(db);
    }

}


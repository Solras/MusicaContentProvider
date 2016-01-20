package com.example.clase.practicamusicacontentprovaider.tablas;

import android.database.Cursor;

import com.example.clase.practicamusicacontentprovaider.BaseDatos.Galeria;

public class Cancion {
    private String nombreCancion;
    private int idDisco;
    private int idCancion;

    public Cancion() {
    }

    public Cancion(String nombreCancion, int idDisco, int idCancion) {
        this.nombreCancion = nombreCancion;
        this.idDisco = idDisco;
        this.idCancion = idCancion;
    }

    public int getIdCancion() {
        return idCancion;
    }

    public void setIdCancion(int idCancion) {
        this.idCancion = idCancion;
    }

    public String getNombreCancion() {
        return nombreCancion;
    }

    public void setNombreCancion(String nombreCancion) {
        this.nombreCancion = nombreCancion;
    }

    public int getIdDisco() {
        return idDisco;
    }

    public void setIdDisco(int idDisco) {
        this.idDisco = idDisco;
    }

    @Override
    public String toString() {
        return "Cancion{" +
                "nombreCancion='" + nombreCancion + '\'' +
                ", idDisco=" + idDisco +
                ", idcancion=" + idCancion +
                '}';
    }
    public void set(Cursor c){ //A partir del cursor recuperar nombre, apellido y telefono
        this.idCancion = c.getInt(c.getColumnIndex(Galeria.TablaCanciones._ID));
        this.idDisco = c.getInt(c.getColumnIndex(Galeria.TablaCanciones.IDDISCO));
        this.nombreCancion= c.getString(c.getColumnIndex(Galeria.TablaCanciones.NOMBRECANCION));

    }
}

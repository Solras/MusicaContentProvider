package com.example.clase.practicamusicacontentprovaider.tablas;

public class Interprete {
    String nombre;
    int idInterprete;

    public Interprete() {
    }

    public Interprete(String nombre, int idInterprete) {
        this.nombre = nombre;
        this.idInterprete = idInterprete;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdInterprete() {
        return idInterprete;
    }

    public void setIdInterprete(int idInterprete) {
        this.idInterprete = idInterprete;
    }

    @Override
    public String toString() {
        return "Interprete{" +
                "nombre='" + nombre + '\'' +
                ", idInterprete=" + idInterprete +
                '}';
    }
}

package com.example.clase.practicamusicacontentprovaider.tablas;

public class Disco {
    String tiulo;
    int idDisco;
    int idInterprete;

    public Disco(String tiulo, int idDisco, int idInterprete) {
        this.tiulo = tiulo;
        this.idDisco = idDisco;
        this.idInterprete = idInterprete;
    }

    public Disco() {
    }

    public String getTiulo() {
        return tiulo;
    }

    public void setTiulo(String tiulo) {
        this.tiulo = tiulo;
    }

    public int getIdDisco() {
        return idDisco;
    }

    public void setIdDisco(int idDisco) {
        this.idDisco = idDisco;
    }

    public int getIdInterprete() {
        return idInterprete;
    }

    public void setIdInterprete(int idInterprete) {
        this.idInterprete = idInterprete;
    }

    @Override
    public String toString() {
        return "Disco{" +
                "tiulo='" + tiulo + '\'' +
                ", idDisco=" + idDisco +
                ", idInterprete=" + idInterprete +
                '}';
    }
    /*  String tiulo,rutaImagen,tipo;
    int idDisco;
   public Disco() {
    }

    public Disco(String tiulo, String rutaImagen, int idDisco, String tipo) {
        this.tiulo = tiulo;
        this.rutaImagen = rutaImagen;
        this.idDisco = idDisco;
        this.tipo = tipo;
    }

    public String getTiulo() {
        return tiulo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setTiulo(String tiulo) {
        this.tiulo = tiulo;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }

    public int getIdDisco() {
        return idDisco;
    }

    public void setIdDisco(int idDisco) {
        this.idDisco = idDisco;
    }

    @Override
    public String toString() {
        return "Disco{" +
                "tiulo='" + tiulo + '\'' +
                ", rutaImagen='" + rutaImagen + '\'' +
                ", idDisco='" + idDisco + '\'' +
                ", tipo='" + tipo + '\'' +
                '}';
    }*/
}

package com.example.applibrosfirebase20202.models;

import java.io.Serializable;

public class LibroModel implements Serializable {
    private int _id;
    private String titulo;
    private String contenido;

    public LibroModel() {
    }

    public LibroModel(String titulo, String contenido) {
        this.titulo = titulo;
        this.contenido = contenido;
    }

    public LibroModel(int _id, String titulo, String contenido) {
        this._id = _id;
        this.titulo = titulo;
        this.contenido = contenido;
    }

    @Override
    public String toString() {
        return "NotaModel{" +
                "_id=" + _id +
                ", titulo='" + titulo + '\'' +
                ", contenido='" + contenido + '\'' +
                '}';
    }

    public int get_id() {
        return this._id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
}

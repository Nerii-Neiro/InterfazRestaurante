package com.example.demo2.modulos;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Casilla extends Rectangle {

    private String imagen;
    private int numero_imagen;
    private int fila;
    private int columna;

    public Casilla(int tam,String imagen,int numero_imagen,int fila,int columna){
        super(tam,tam, Color.RED);
        this.imagen=imagen;
        this.numero_imagen=numero_imagen;
        this.fila=fila;
        this.columna=columna;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public void setNumero_imagen(int numero_imagen) {
        this.numero_imagen = numero_imagen;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public int getNumero_imagen() {
        return numero_imagen;
    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }

    public String getImagen(){
        return imagen;
    }

}
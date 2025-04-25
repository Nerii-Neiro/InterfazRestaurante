package com.example.demo2.modulos;

public class Producto {
    public int id_producto;
    public String nombre;
    public Double precio;
    public String descripcion;
    public int id_categoria;
    public String imagen;
    public Producto(int id_producto, String nombre, double precio, String descripcion, int id_categoria,String imagen){
        this.id_producto = id_producto;
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
        this.id_categoria = id_categoria;
        this.imagen = imagen;

    }
}

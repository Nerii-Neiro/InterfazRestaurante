package com.example.demo2.modulos;

public class Cliente{
    public int id_cliente;
    public String nombre;
    public String direccion;
    public String telefono;
    public String email;

    public Cliente(int id_cliente, String nombre, String direccion, String telefono, String email) {
        this.id_cliente = id_cliente;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
    }
}
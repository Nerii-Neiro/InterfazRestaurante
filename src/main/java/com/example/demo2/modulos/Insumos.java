package com.example.demo2.modulos;

public class Insumos {
    private int id_insumo;
    private String nombre;
    private int cantidad;
    private String descripcion;
    private String observciones;
    private int id_proveedor;

    public Insumos(int id_insumo, String nombre, int cantidad, String descripcion, String observciones, int id_proveedor) {
        this.id_insumo = id_insumo;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.descripcion = descripcion;
        this.observciones = observciones;
        this.id_proveedor = id_proveedor;
    }

    public int getId_insumo() {
        return id_insumo;
    }

    public void setId_insumo(int id_insumo) {
        this.id_insumo = id_insumo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getObservciones() {
        return observciones;
    }

    public void setObservciones(String observciones) {
        this.observciones = observciones;
    }

    public int getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(int id_proveedor) {
        this.id_proveedor = id_proveedor;
    }
}

package com.example.demo2.modulos;

public class Orden{
    private int id_orden;
    private String fecha;
    private String hora;
    private String descripcion;
    private String notas;
    private int id_cliente;
    private int id_mesa;
    private int id_empleado;
    private double total;

    public Orden(int id_orden, String fecha, String hora, String descripcion, String notas, int id_cliente, int id_mesa, int id_empleado, double total) {
        this.id_orden = id_orden;
        this.fecha = fecha;
        this.hora = hora;
        this.descripcion = descripcion;
        this.notas = notas;
        this.id_cliente = id_cliente;
        this.id_mesa = id_mesa;
        this.id_empleado = id_empleado;
        this.total = total;
    }

    public int getId_orden() {
        return id_orden;
    }

    public void setId_orden(int id_orden) {
        this.id_orden = id_orden;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public int getId_mesa() {
        return id_mesa;
    }

    public void setId_mesa(int id_mesa) {
        this.id_mesa = id_mesa;
    }

    public int getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(int id_empleado) {
        this.id_empleado = id_empleado;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
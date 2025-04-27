package com.example.demo2.modulos;

public class Reservacion {
    private int id_reservacion;
    private String fecha;
    private String hora;
    private String observaciones;

    public Reservacion(int id_reservacion, String fecha, String hora, String observaciones) {
        this.id_reservacion = id_reservacion;
        this.fecha = fecha;
        this.hora = hora;
        this.observaciones = observaciones;
    }

    public int getId_reservacion() {
        return id_reservacion;
    }

    public void setId_reservacion(int id_reservacion) {
        this.id_reservacion = id_reservacion;
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

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}

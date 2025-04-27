package com.example.demo2.modulos;

public class Empleado {
    private int id_empleado;
    private String nombre;
    private String curp;
    private String rfc;
    private double sueldo;
    private String puesto;
    private String telefono;
    private String nss;
    private String horario;
    private String fecha_ingreso;
    private String password;
    private String email;

    public Empleado(int id_empleado, String nombre, String curp, String rfc, double sueldo, String puesto, String telefono, String nss, String horario, String fecha_ingreso, String password, String email) {
        this.id_empleado = id_empleado;
        this.nombre = nombre;
        this.curp = curp;
        this.rfc = rfc;
        this.sueldo = sueldo;
        this.puesto = puesto;
        this.telefono = telefono;
        this.nss = nss;
        this.horario = horario;
        this.fecha_ingreso = fecha_ingreso;
        this.password = password;
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(int id_empleado) {
        this.id_empleado = id_empleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public double getSueldo() {
        return sueldo;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNss() {
        return nss;
    }

    public void setNss(String nss) {
        this.nss = nss;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getFecha_ingreso() {
        return fecha_ingreso;
    }

    public void setFecha_ingreso(String fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }
}

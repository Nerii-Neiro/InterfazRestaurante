package com.example.demo2.modulos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.Statement;

public class EmpleadosDAO {

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

    public void crear_empleado(int id_empleado, String nombre, String curp, String rfc, double sueldo, String puesto, String telefono, String nss, String horario, String fecha_ingreso) {
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
    }


    public void INSERT(){

        String query = "INSERT INTO Empleados(nombre,curp,rfc,sueldo,puesto,telefono,nss,horario,fecha_ingreso) " + "VALUES ('"+nombre+"','"+curp+"','"+rfc+"','"+sueldo+"','"+puesto+"','"+telefono+"','"+nss+"','"+horario+"','"+fecha_ingreso+"')";

        try{
            Statement stm= conexion.connection.createStatement();
            stm.executeUpdate(query);
        }
        catch (Exception e){
            System.out.println("OCURRIO UN PROBLEMA AL CREAR EL ELEMENTO");
            e.printStackTrace();

        }
    }

    public void UPDATE(){
        String query = "UPDATE Empleados SET nombre = '"+nombre+"',curp = '"+curp+"',rfc='"+rfc+"',sueldo='"+sueldo+"',puesto='"+puesto+"',telefono='"+telefono+"',nss='"+nss+"',horario='"+horario+"',fecha_ingreso='"+fecha_ingreso+"' WHERE id_empleado="+id_empleado;
        try{
            Statement stm= conexion.connection.createStatement();
            stm.executeUpdate(query);
        }
        catch (Exception e){
            System.out.println("OCURRIO UN PROBLEMA AL ACTUALIZAR EL ELEMENTO");
            e.printStackTrace();

        }
    }

    public ObservableList<EmpleadosDAO> SELECT(){
        String query = "SELECT * FROM Empleados";
        ObservableList<EmpleadosDAO> lista = FXCollections.observableArrayList();
        EmpleadosDAO temp_empleado;
        try{
            Statement stm = conexion.connection.createStatement();
            ResultSet result = stm.executeQuery(query);
            while(result.next()){
                temp_empleado= new EmpleadosDAO();
                temp_empleado.setId_empleado(result.getInt("id_empleado"));
                temp_empleado.setNombre(result.getString("nombre"));
                temp_empleado.setCurp(result.getString("curp"));
                temp_empleado.setRfc(result.getString("rfc"));
                temp_empleado.setSueldo(result.getDouble("sueldo"));
                temp_empleado.setPuesto(result.getString("puesto"));
                temp_empleado.setTelefono(result.getString("telefono"));
                temp_empleado.setNss(result.getString("nss"));
                temp_empleado.setHorario(result.getString("horario"));
                temp_empleado.setFecha_ingreso(result.getString("fecha_ingreso"));
                lista.add(temp_empleado);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return lista;
    }

    public void DELETE(){
        String query = "DELETE FROM Empleados WHERE id_empleado="+id_empleado;
        try{
            Statement stm= conexion.connection.createStatement();
            stm.executeUpdate(query);
        }
        catch (Exception e){
            System.out.println("OCURRIO UN PROBLEMA AL ELIMINAR EL ELEMENTO (POSIBLEMENTE PORQUE NO EXISTE O PORQUE DEPENDEN DE EL)");
            e.printStackTrace();

        }
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

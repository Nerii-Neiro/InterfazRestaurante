package com.example.demo2.modulos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.Statement;

public class OrdenesDAO {

  private int id_orden;
  private String fecha;
  private String hora;
  private String descripcion;
  private String notas;
  private int id_cliente;
  private int id_mesa;
  private int id_empleado;
  private double total;

    public void crear_orden(int id_orden, String fecha, String hora, String descripcion, String notas, int id_cliente, int id_mesa, int id_empleado, double total) {
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




    public void INSERT(){

        String query = "INSERT INTO Ordenes(fecha,hora,descripcion,notas,id_cliente,id_mesa,id_empleado,total) " + "VALUES ('"+fecha+"','"+hora+"','"+descripcion+"','"+notas+"','"+id_cliente+"','"+id_mesa+"','"+id_empleado+"','"+total+"')";

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
        String query = "UPDATE Ordenes SET fecha = '"+fecha+"',hora = '"+hora+"',descripcion='"+descripcion+"',notas='"+notas+"',id_cliente='"+id_cliente+"',id_mesa='"+id_mesa+"',id_empleado='"+id_empleado+"',total='"+total+"' WHERE id_orden="+id_orden;
        try{
            Statement stm= conexion.connection.createStatement();
            stm.executeUpdate(query);
        }
        catch (Exception e){
            System.out.println("OCURRIO UN PROBLEMA AL ACTUALIZAR EL ELEMENTO");
            e.printStackTrace();

        }
    }

    public ObservableList<OrdenesDAO> SELECT(){
        String query = "SELECT * FROM Ordenes";
        ObservableList<OrdenesDAO> lista = FXCollections.observableArrayList();
        OrdenesDAO temp_orden;
        try{
            Statement stm = conexion.connection.createStatement();
            ResultSet result = stm.executeQuery(query);
            while(result.next()){
                temp_orden= new OrdenesDAO();
                temp_orden.setId_orden(result.getInt("id_orden"));
                temp_orden.setFecha(result.getString("fecha"));
                temp_orden.setHora(result.getString("hora"));
                temp_orden.setDescripcion(result.getString("descripcion"));
                temp_orden.setNotas(result.getString("notas"));
                temp_orden.setId_cliente(result.getInt("id_cliente"));
                temp_orden.setId_mesa(result.getInt("id_mesa"));
                temp_orden.setId_empleado(result.getInt("id_empleado"));
                temp_orden.setTotal(result.getDouble("total"));
                lista.add(temp_orden);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return lista;
    }

    public void DELETE(){
        String query = "DELETE FROM Ordenes WHERE id_orden="+id_orden;
        try{
            Statement stm= conexion.connection.createStatement();
            stm.executeUpdate(query);
        }
        catch (Exception e){
            System.out.println("OCURRIO UN PROBLEMA AL ELIMINAR EL ELEMENTO (POSIBLEMENTE PORQUE NO EXISTE O PORQUE DEPENDEN DE EL)");
            e.printStackTrace();

        }
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

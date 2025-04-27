package com.example.demo2.modulos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.Statement;

public class DetOrdenDAO {

    private int id_detalle_orden;
    private int id_orden;
    private int id_producto;
    private int cantidad;
    private double precio;

    public void crear_det_orden(int id_detalle_orden, int id_orden, int id_producto, int cantidad, double precio) {
        this.id_detalle_orden = id_detalle_orden;
        this.id_orden = id_orden;
        this.id_producto = id_producto;
        this.cantidad = cantidad;
        this.precio = precio;
    }


    public void INSERT(){

        String query = "INSERT INTO DetOrden(id_orden,id_producto,cantidad,precio) " + "VALUES ('"+id_orden+"','"+id_producto+"','"+cantidad+"','"+precio+"')";

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
        String query = "UPDATE DetOrden SET id_orden = '"+id_orden+"',id_producto = '"+id_producto+"',cantidad='"+cantidad+"',precio='"+precio+"' WHERE id_detalle_orden="+id_detalle_orden;
        try{
            Statement stm= conexion.connection.createStatement();
            stm.executeUpdate(query);
        }
        catch (Exception e){
            System.out.println("OCURRIO UN PROBLEMA AL ACTUALIZAR EL ELEMENTO");
            e.printStackTrace();

        }
    }

    public ObservableList<DetOrdenDAO> SELECT(){
        String query = "SELECT * FROM DetOrden";
        ObservableList<DetOrdenDAO> lista = FXCollections.observableArrayList();
        DetOrdenDAO temp_detalle_orden;
        try{
            Statement stm = conexion.connection.createStatement();
            ResultSet result = stm.executeQuery(query);
            while(result.next()){
                temp_detalle_orden= new DetOrdenDAO();
                temp_detalle_orden.setId_detalle_orden(result.getInt("id_detalle_orden"));
                temp_detalle_orden.setId_orden(result.getInt("id_orden"));
                temp_detalle_orden.setId_producto(result.getInt("id_producto"));
                temp_detalle_orden.setCantidad(result.getInt("cantidad"));
                temp_detalle_orden.setPrecio(result.getDouble("precio"));
                lista.add(temp_detalle_orden);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return lista;
    }

    public void DELETE(){
        String query = "DELETE FROM DetOrden WHERE id_detalle_orden="+id_detalle_orden;
        try{
            Statement stm= conexion.connection.createStatement();
            stm.executeUpdate(query);
        }
        catch (Exception e){
            System.out.println("OCURRIO UN PROBLEMA AL ELIMINAR EL ELEMENTO (POSIBLEMENTE PORQUE NO EXISTE O PORQUE DEPENDEN DE EL)");
            e.printStackTrace();

        }
    }


    public int getId_detalle_orden() {
        return id_detalle_orden;
    }

    public void setId_detalle_orden(int id_detalle_orden) {
        this.id_detalle_orden = id_detalle_orden;
    }

    public int getId_orden() {
        return id_orden;
    }

    public void setId_orden(int id_orden) {
        this.id_orden = id_orden;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}

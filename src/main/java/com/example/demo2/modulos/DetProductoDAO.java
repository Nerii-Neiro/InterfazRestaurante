package com.example.demo2.modulos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.Statement;

public class DetProductoDAO {

    private  int id_detalle;
    private  int id_producto;
    private int id_insumo;
    private int cantidad_necesitada;
    private String fecha_caducidad;
    private String observaciones;
    private String descripcion;

    public void crear_producto(int id_detalle, int id_producto, int id_insumo, int cantidad_necesitada, String fecha_caducidad, String observaciones, String descripcion) {
        this.id_detalle = id_detalle;
        this.id_producto = id_producto;
        this.id_insumo = id_insumo;
        this.cantidad_necesitada = cantidad_necesitada;
        this.fecha_caducidad = fecha_caducidad;
        this.observaciones = observaciones;
        this.descripcion = descripcion;
    }


    public void INSERT(){

        String query = "INSERT INTO DetProducto(id_producto,id_insumo,cantidad_necesitada,fecha_caducidad,observaciones,descripcion) " + "VALUES ('"+id_producto+"','"+id_insumo+"','"+cantidad_necesitada+"','"+fecha_caducidad+"','"+observaciones+"','"+descripcion+"')";

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
        String query = "UPDATE DetProducto SET id_producto = '"+id_producto+"',id_insumo = '"+id_insumo+"',cantidad_necesitada='"+cantidad_necesitada+"',fecha_caducidad='"+fecha_caducidad+"',observaciones='"+observaciones+"',descripcion='"+descripcion+"' WHERE id_detalle="+id_detalle;
        try{
            Statement stm= conexion.connection.createStatement();
            stm.executeUpdate(query);
        }
        catch (Exception e){
            System.out.println("OCURRIO UN PROBLEMA AL ACTUALIZAR EL ELEMENTO");
            e.printStackTrace();

        }
    }

    public ObservableList<DetProductoDAO> SELECT(){
        String query = "SELECT * FROM DetProducto";
        ObservableList<DetProductoDAO> lista = FXCollections.observableArrayList();
        DetProductoDAO temp_detalle_producto;
        try{
            Statement stm = conexion.connection.createStatement();
            ResultSet result = stm.executeQuery(query);
            while(result.next()){
                temp_detalle_producto= new DetProductoDAO();
                temp_detalle_producto.setId_detalle(result.getInt("id_detalle"));
                temp_detalle_producto.setId_producto(result.getInt("id_producto"));
                temp_detalle_producto.setId_insumo(result.getInt("id_insumo"));
                temp_detalle_producto.setCantidad_necesitada(result.getInt("cantidad_necesitada"));
                temp_detalle_producto.setObservaciones(result.getString("observaciones"));
                temp_detalle_producto.setDescripcion(result.getString("descripcion"));
                lista.add(temp_detalle_producto);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return lista;
    }

    public void DELETE(){
        String query = "DELETE FROM DetProducto WHERE id_detalle="+id_detalle;
        try{
            Statement stm= conexion.connection.createStatement();
            stm.executeUpdate(query);
        }
        catch (Exception e){
            System.out.println("OCURRIO UN PROBLEMA AL ELIMINAR EL ELEMENTO (POSIBLEMENTE PORQUE NO EXISTE O PORQUE DEPENDEN DE EL)");
            e.printStackTrace();

        }
    }

    public int getId_detalle() {
        return id_detalle;
    }

    public void setId_detalle(int id_detalle) {
        this.id_detalle = id_detalle;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public int getId_insumo() {
        return id_insumo;
    }

    public void setId_insumo(int id_insumo) {
        this.id_insumo = id_insumo;
    }

    public int getCantidad_necesitada() {
        return cantidad_necesitada;
    }

    public void setCantidad_necesitada(int cantidad_necesitada) {
        this.cantidad_necesitada = cantidad_necesitada;
    }

    public String getFecha_caducidad() {
        return fecha_caducidad;
    }

    public void setFecha_caducidad(String fecha_caducidad) {
        this.fecha_caducidad = fecha_caducidad;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}

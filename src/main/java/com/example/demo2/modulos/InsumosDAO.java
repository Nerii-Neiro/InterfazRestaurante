package com.example.demo2.modulos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.Statement;

public class InsumosDAO {

    private int id_insumo;
    private String nombre;
    private int cantidad;
    private String descripcion;
    private String observciones;
    private int id_proveedor;

    public void crear_insumo(int id_insumo, String nombre, int cantidad, String descripcion, String observaciones, int id_proveedor) {
        this.id_insumo = id_insumo;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.descripcion = descripcion;
        this.observciones = observaciones;
        this.id_proveedor = id_proveedor;
    }

    public void INSERT(){

        String query = "INSERT INTO Insumos(nombre,cantidad,descripcion,observciones,id_proveedor) " + "VALUES ('"+nombre+"','"+cantidad+"','"+descripcion+"','"+observciones+"','"+id_proveedor+"')";

        try{
            Statement stm= conexion.connection.createStatement();
            stm.executeUpdate(query);
        }
        catch (Exception e){
            System.out.println("OCURRIO UN PROBLEMA AL CREAR EL INSUMO");
            e.printStackTrace();

        }
    }

    public void UPDATE(){
        String query = "UPDATE Insumos SET nombre = '"+nombre+"',cantidad = '"+cantidad+"',descripcion='"+descripcion+"',observciones='"+observciones+"',id_proveedor='"+id_proveedor+"' WHERE id_insumo="+id_insumo;
        try{
            Statement stm= conexion.connection.createStatement();
            stm.executeUpdate(query);
        }
        catch (Exception e){
            System.out.println("OCURRIO UN PROBLEMA AL ACTUALIZAR EL INSUMO");
            e.printStackTrace();

        }
    }

    public ObservableList<InsumosDAO> SELECT(){
        String query = "SELECT * FROM Insumos";
        ObservableList<InsumosDAO> lista = FXCollections.observableArrayList();
        InsumosDAO temp_insumo;
        try{
            Statement stm = conexion.connection.createStatement();
            ResultSet result = stm.executeQuery(query);
            while(result.next()){
                temp_insumo= new InsumosDAO();
                temp_insumo.setId_insumo(result.getInt("id_insumo"));
                temp_insumo.setNombre(result.getString("nombre"));
                temp_insumo.setCantidad(result.getInt("cantidad"));
                temp_insumo.setDescripcion(result.getString("descripcion"));
                temp_insumo.setObservaciones(result.getString("observciones"));
                temp_insumo.setId_proveedor(result.getInt("id_proveedor"));
                lista.add(temp_insumo);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return lista;
    }

    public void DELETE(){
        String query = "DELETE FROM Insumos WHERE id_insumo="+id_insumo;
        try{
            Statement stm= conexion.connection.createStatement();
            stm.executeUpdate(query);
        }
        catch (Exception e){
            System.out.println("OCURRIO UN PROBLEMA AL ELIMINAR EL INSUMO (POSIBLEMENTE PORQUE NO EXISTE O PORQUE DEPENDEN DE EL)");
            e.printStackTrace();

        }
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

    public String getObservaciones() {
        return observciones;
    }

    public void setObservaciones(String observaciones) {
        this.observciones = observaciones;
    }

    public int getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(int id_proveedor) {
        this.id_proveedor = id_proveedor;
    }
}

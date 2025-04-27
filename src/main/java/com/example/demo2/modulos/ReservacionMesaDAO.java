package com.example.demo2.modulos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.Statement;

public class ReservacionMesaDAO {

    private int id_rm;
    private int id_reservacion;
    private int id_mesa;

    public void crear_resrvacion_mesa(int id_rm, int id_reservacion, int id_mesa) {
        this.id_rm = id_rm;
        this.id_reservacion = id_reservacion;
        this.id_mesa = id_mesa;
    }



    public void INSERT(){

        String query = "INSERT INTO ReservacionMesa(id_reservacion,id_mesa) " + "VALUES ('"+id_reservacion+"','"+id_mesa+"')";

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
        String query = "UPDATE ReservacionMesa SET id_reservacion = '"+id_reservacion+"',id_mesa = '"+id_mesa+"' WHERE id_rm="+id_rm;
        try{
            Statement stm= conexion.connection.createStatement();
            stm.executeUpdate(query);
        }
        catch (Exception e){
            System.out.println("OCURRIO UN PROBLEMA AL ACTUALIZAR EL ELEMENTO");
            e.printStackTrace();

        }
    }

    public ObservableList<ReservacionMesaDAO> SELECT(){
        String query = "SELECT * FROM ReservacionMesa";
        ObservableList<ReservacionMesaDAO> lista = FXCollections.observableArrayList();
        ReservacionMesaDAO temp_rm;
        try{
            Statement stm = conexion.connection.createStatement();
            ResultSet result = stm.executeQuery(query);
            while(result.next()){
                temp_rm = new ReservacionMesaDAO();
                temp_rm.setId_rm(result.getInt("id_rm"));
                temp_rm.setId_reservacion(result.getInt("id_reservacion"));
                temp_rm.setId_mesa(result.getInt("id_mesa"));
                lista.add(temp_rm);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return lista;
    }

    public void DELETE(){
        String query = "DELETE FROM ReservacioneMesa WHERE id_rm="+id_rm;
        try{
            Statement stm= conexion.connection.createStatement();
            stm.executeUpdate(query);
        }
        catch (Exception e){
            System.out.println("OCURRIO UN PROBLEMA AL ELIMINAR EL ELEMENTO (POSIBLEMENTE PORQUE NO EXISTE O PORQUE DEPENDEN DE EL)");
            e.printStackTrace();

        }
    }

    public int getId_rm() {
        return id_rm;
    }

    public void setId_rm(int id_rm) {
        this.id_rm = id_rm;
    }

    public int getId_reservacion() {
        return id_reservacion;
    }

    public void setId_reservacion(int id_reservacion) {
        this.id_reservacion = id_reservacion;
    }

    public int getId_mesa() {
        return id_mesa;
    }

    public void setId_mesa(int id_mesa) {
        this.id_mesa = id_mesa;
    }
}

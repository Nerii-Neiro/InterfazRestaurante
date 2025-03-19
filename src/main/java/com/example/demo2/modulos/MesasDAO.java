package com.example.demo2.modulos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.Statement;

public class MesasDAO {

    private int id_mesa;
    private int capacidad;
    private String tipo;

    public void crear_mesa(int id_mesa, int capacidad, String tipo) {
        this.id_mesa = id_mesa;
        this.capacidad = capacidad;
        this.tipo = tipo;
    }


    public void INSERT(){

        String query = "INSERT INTO Mesas(capacidad,tipo) " + "VALUES ('"+capacidad+"','"+tipo+"')";

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
        String query = "UPDATE Mesas SET capacidad = '"+capacidad+"',tipo = '"+tipo+"' WHERE id_mesa="+id_mesa;
        try{
            Statement stm= conexion.connection.createStatement();
            stm.executeUpdate(query);
        }
        catch (Exception e){
            System.out.println("OCURRIO UN PROBLEMA AL ACTUALIZAR EL ELEMENTO");
            e.printStackTrace();

        }
    }

    public ObservableList<MesasDAO> SELECT(){
        String query = "SELECT * FROM Mesas";
        ObservableList<MesasDAO> lista = FXCollections.observableArrayList();
        MesasDAO temp_mesa;
        try{
            Statement stm = conexion.connection.createStatement();
            ResultSet result = stm.executeQuery(query);
            while(result.next()){
                temp_mesa= new MesasDAO();
                temp_mesa.setId_mesas(result.getInt("id_mesa"));
                temp_mesa.setCapacidad(result.getInt("capacidad"));
                temp_mesa.setTipo(result.getString("tipo"));
                lista.add(temp_mesa);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return lista;
    }

    public void DELETE(){
        String query = "DELETE FROM Mesas WHERE id_mesa="+id_mesa;
        try{
            Statement stm= conexion.connection.createStatement();
            stm.executeUpdate(query);
        }
        catch (Exception e){
            System.out.println("OCURRIO UN PROBLEMA AL ELIMINAR EL ELEMENTO (POSIBLEMENTE PORQUE NO EXISTE O PORQUE DEPENDEN DE EL)");
            e.printStackTrace();

        }
    }

    public int getId_mesas() {
        return id_mesa;
    }

    public void setId_mesas(int id_mesa) {
        this.id_mesa = id_mesa;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}

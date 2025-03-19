package com.example.demo2.modulos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.Statement;

public class ReservacionesDAO {

    private int id_reservacion;
    private String fecha;
    private String hora;
    private String observaciones;

    public void crear_reservaciones(int id_reservacion, String fecha, String hora, String observaciones) {
        this.id_reservacion = id_reservacion;
        this.fecha = fecha;
        this.hora = hora;
        this.observaciones = observaciones;
    }


    public void INSERT(){

        String query = "INSERT INTO Reservaciones(fecha,hora,observaciones) " + "VALUES ('"+fecha+"','"+hora+"','"+observaciones+"')";

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
        String query = "UPDATE Reservaciones SET fecha = '"+fecha+"',hora = '"+hora+"',observaciones='"+observaciones+"' WHERE id_reservacion="+id_reservacion;
        try{
            Statement stm= conexion.connection.createStatement();
            stm.executeUpdate(query);
        }
        catch (Exception e){
            System.out.println("OCURRIO UN PROBLEMA AL ACTUALIZAR EL ELEMENTO");
            e.printStackTrace();

        }
    }

    public ObservableList<ReservacionesDAO> SELECT(){
        String query = "SELECT * FROM Reservaciones";
        ObservableList<ReservacionesDAO> lista = FXCollections.observableArrayList();
        ReservacionesDAO temp_reservacion;
        try{
            Statement stm = conexion.connection.createStatement();
            ResultSet result = stm.executeQuery(query);
            while(result.next()){
                temp_reservacion= new ReservacionesDAO();
                temp_reservacion.setId_reservacion(result.getInt("id_reservacion"));
                temp_reservacion.setFecha(result.getString("fecha"));
                temp_reservacion.setHora(result.getString("hora"));
                temp_reservacion.setObservaciones(result.getString("observaciones"));
                lista.add(temp_reservacion);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return lista;
    }

    public void DELETE(){
        String query = "DELETE FROM Reservaciones WHERE id_reservacion="+id_reservacion;
        try{
            Statement stm= conexion.connection.createStatement();
            stm.executeUpdate(query);
        }
        catch (Exception e){
            System.out.println("OCURRIO UN PROBLEMA AL ELIMINAR EL ELEMENTO (POSIBLEMENTE PORQUE NO EXISTE O PORQUE DEPENDEN DE EL)");
            e.printStackTrace();

        }
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

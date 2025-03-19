package com.example.demo2.modulos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.ResultSet;
import java.sql.Statement;

public class ClientesDAO {

    private int id_cliente;
    private String nombre;
    private String direccion;
    private String telefono;
    private String email;

    public void crear_cliente(String nombre,String direccion,String telefono,String email){
          this.nombre= nombre;
          this.direccion=direccion;
          this.telefono=telefono;
          this.email=email;
  }

    public void INSERT(){

        String query = "INSERT INTO Clientes(nombre,direccion,telefono,email) " + "VALUES ('"+nombre+"','"+direccion+"','"+telefono+"','"+email+"')";

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
        String query = "UPDATE Clientes SET nombre = '"+nombre+"',direccion = '"+direccion+"',telefono='"+telefono+"',email='"+email+"' WHERE id_cliente="+id_cliente;
        try{
            Statement stm= conexion.connection.createStatement();
            stm.executeUpdate(query);
        }
        catch (Exception e){
            System.out.println("OCURRIO UN PROBLEMA AL ACTUALIZAR EL ELEMENTO");
            e.printStackTrace();

        }
    }

    public ObservableList<ClientesDAO> SELECT(){
        String query = "SELECT * FROM Clientes";
        ObservableList<ClientesDAO> lista = FXCollections.observableArrayList();
        ClientesDAO temp_cliente;
        try{
            Statement stm = conexion.connection.createStatement();
            ResultSet result = stm.executeQuery(query);
            while(result.next()){
               temp_cliente= new ClientesDAO();
               temp_cliente.setId_cliente(result.getInt("id_cliente"));
               temp_cliente.setDireccion(result.getString("direccion"));
               temp_cliente.setNombre(result.getString("nombre"));
               temp_cliente.setTelefono(result.getString("telefono"));
               temp_cliente.setEmail(result.getString("email"));
               lista.add(temp_cliente);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return lista;
    }

    public void DELETE(){
        String query = "DELETE FROM Clientes WHERE id_cliente="+id_cliente;
        try{
            Statement stm= conexion.connection.createStatement();
            stm.executeUpdate(query);
        }
        catch (Exception e){
            System.out.println("OCURRIO UN PROBLEMA AL ELIMINAR EL ELEMENTO (POSIBLEMENTE PORQUE NO EXISTE O PORQUE DEPENDEN DE EL)");
            e.printStackTrace();

        }
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

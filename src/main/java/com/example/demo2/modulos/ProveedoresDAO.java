package com.example.demo2.modulos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.Statement;

public class ProveedoresDAO {

   private int id_proveedor;
   private String nombre;
   private String telefono;
   private String direccion;
   private String email;
   private String nota;

    public void crear_proveedor(int id_proveedor, String nombre, String telefono, String direccion, String email, String nota) {
        this.id_proveedor = id_proveedor;
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.email = email;
        this.nota = nota;
    }


    public void INSERT(){

            String query = "INSERT INTO Proveedores(nombre,telefono,direccion,email,nota) " + "VALUES ('"+nombre+"','"+telefono+"','"+direccion+"','"+email+"','"+nota+"')";

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
        String query = "UPDATE Proveedores SET nombre = '"+nombre+"',telefono = '"+telefono+"',direccion='"+direccion+"',email='"+email+"',nota='"+nota+"' WHERE id_proveedor="+id_proveedor;
        try{
            Statement stm= conexion.connection.createStatement();
            stm.executeUpdate(query);
        }
        catch (Exception e){
            System.out.println("OCURRIO UN PROBLEMA AL ACTUALIZAR EL ELEMENTO");
            e.printStackTrace();

        }
    }

    public ObservableList<ProveedoresDAO> SELECT(){
        String query = "SELECT * FROM Proveedores";
        ObservableList<ProveedoresDAO> lista = FXCollections.observableArrayList();
        ProveedoresDAO temp_producto;
        try{
            Statement stm = conexion.connection.createStatement();
            ResultSet result = stm.executeQuery(query);
            while(result.next()){
                temp_producto= new ProveedoresDAO();
                temp_producto.setId_proveedor(result.getInt("id_proveedor"));
                temp_producto.setNombre(result.getString("nombre"));
                temp_producto.setTelefono(result.getString("telefono"));
                temp_producto.setDireccion(result.getString("direccion"));
                temp_producto.setEmail(result.getString("email"));
                temp_producto.setNota(result.getString("nota"));
                lista.add(temp_producto);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return lista;
    }

    public void DELETE(){
        String query = "DELETE FROM Proveedores WHERE id_proveedor="+id_proveedor;
        try{
            Statement stm= conexion.connection.createStatement();
            stm.executeUpdate(query);
        }
        catch (Exception e){
            System.out.println("OCURRIO UN PROBLEMA AL ELIMINAR EL ELEMENTO (POSIBLEMENTE PORQUE NO EXISTE O PORQUE DEPENDEN DE EL)");
            e.printStackTrace();

        }
    }


    public int getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(int id_proveedor) {
        this.id_proveedor = id_proveedor;
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }
}

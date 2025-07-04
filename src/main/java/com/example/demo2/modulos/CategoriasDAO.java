package com.example.demo2.modulos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CategoriasDAO {

    private int id_categoria;
    private String nombre;
    private String descripcion;

    private String imagen;

    public void crear_categoria(String nombre,String descripcion,String imagen){
        this.nombre= nombre;
        this.descripcion=descripcion;
        this.imagen=imagen;
    }


    public void INSERT(){

        String query = "INSERT INTO Categorias(nombre,descripcion,imagen) " + "VALUES ('"+nombre+"','"+descripcion+"','"+imagen+"')";

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
        String query = "UPDATE Categorias SET nombre = '"+nombre+"',descripcion = '"+descripcion+"', imagen='"+imagen+"' WHERE id_categoria="+id_categoria;
        try{
            Statement stm= conexion.connection.createStatement();
            stm.executeUpdate(query);
        }
        catch (Exception e){
            System.out.println("OCURRIO UN PROBLEMA AL ACTUALIZAR EL ELEMENTO");
            e.printStackTrace();

        }
    }

    public ObservableList<CategoriasDAO> SELECT(){
        String query = "SELECT * FROM Categorias";
        ObservableList<CategoriasDAO> lista = FXCollections.observableArrayList();
        CategoriasDAO temp_categoria;
        try{
            Statement stm = conexion.connection.createStatement();
            ResultSet result = stm.executeQuery(query);
            while(result.next()){
                temp_categoria= new CategoriasDAO();
                temp_categoria.setId_categoria(result.getInt("id_categoria"));
                temp_categoria.setNombre(result.getString("nombre"));
                temp_categoria.setDescripcion(result.getString("descripcion"));
                temp_categoria.setImagen(result.getString("imagen"));
                lista.add(temp_categoria);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return lista;
    }

    public List<Categoria> Obtener_Categorias(){
        String query = "SELECT * FROM Categorias";
        List<Categoria> lista= new ArrayList<>();
         try{
             Statement stmt = conexion.connection.createStatement();
             ResultSet result = stmt.executeQuery(query);
             while(result.next()){
                lista.add(new Categoria(result.getInt("id_categoria"),result.getString("nombre"),result.getString("descripcion"),result.getString("imagen")));
             }
         }
         catch(Exception e){}



       return lista;
    }



    public void DELETE(){
        String query = "DELETE FROM Categorias WHERE id_categoria="+id_categoria;
        try{
            Statement stm= conexion.connection.createStatement();
            stm.executeUpdate(query);
        }
        catch (Exception e){
            System.out.println("OCURRIO UN PROBLEMA AL ELIMINAR EL ELEMENTO (POSIBLEMENTE PORQUE NO EXISTE O PORQUE DEPENDEN DE EL)");
            e.printStackTrace();

        }
    }


    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}


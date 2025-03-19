package com.example.demo2.modulos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.Statement;

public class ProductosDAO {

    private int id_producto;
    private String nombre;
    private double precio;
    private String descripcion;
    private int id_categoria;

    public void crear_producto(int id_producto, String nombre, double precio, String descripcion, int id_categoria) {
        this.id_producto = id_producto;
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
        this.id_categoria = id_categoria;
    }


    public void INSERT(){

        String query = "INSERT INTO Productos(nombre,precio,descripcion,id_categoria) " + "VALUES ('"+nombre+"','"+precio+"','"+descripcion+"','"+id_categoria+"')";

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
        String query = "UPDATE Categorias SET nombre = '"+nombre+"',precio = '"+precio+"',descripcion='"+descripcion+"',id_categoria='"+id_categoria+"' WHERE id_producto="+id_producto;
        try{
            Statement stm= conexion.connection.createStatement();
            stm.executeUpdate(query);
        }
        catch (Exception e){
            System.out.println("OCURRIO UN PROBLEMA AL ACTUALIZAR EL ELEMENTO");
            e.printStackTrace();

        }
    }

    public ObservableList<ProductosDAO> SELECT(){
        String query = "SELECT * FROM Productos";
        ObservableList<ProductosDAO> lista = FXCollections.observableArrayList();
        ProductosDAO temp_producto;
        try{
            Statement stm = conexion.connection.createStatement();
            ResultSet result = stm.executeQuery(query);
            while(result.next()){
                temp_producto= new ProductosDAO();
                temp_producto.setId_producto(result.getInt("id_producto"));
                temp_producto.setNombre(result.getString("nombre"));
                temp_producto.setPrecio(result.getDouble("precio"));
                temp_producto.setDescripcion(result.getString("descripcion"));
                temp_producto.setId_categoria(result.getInt("id_categoria"));
                lista.add(temp_producto);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return lista;
    }

    public void DELETE(){
        String query = "DELETE FROM Productos WHERE id_producto="+id_producto;
        try{
            Statement stm= conexion.connection.createStatement();
            stm.executeUpdate(query);
        }
        catch (Exception e){
            System.out.println("OCURRIO UN PROBLEMA AL ELIMINAR EL ELEMENTO (POSIBLEMENTE PORQUE NO EXISTE O PORQUE DEPENDEN DE EL)");
            e.printStackTrace();

        }
    }


    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }
}

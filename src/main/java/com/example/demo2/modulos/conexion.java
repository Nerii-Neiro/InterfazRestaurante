package com.example.demo2.modulos;
//recent ichiniwa
import java.sql.Connection;
import java.sql.DriverManager;


public class conexion {

    //PARAMETROS PARA LA CONEXION CON LA BASE DE DATOS
    private static String DB = "db_restaurante";
    private static String USER = "db_restaurante";
    private static String PWD = "123";
    private static String HOST = "localhost";
    private static String PORT = "3306";
    public static Connection connection;


    public conexion(){
        createConecction();
    }

    public void createConecction(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://"+HOST+":"+PORT+"/"+DB,USER,PWD);
            System.out.println("CONEXION EXITOSA (RESTAURANTE)");
        }
        catch(Exception e){
            System.out.println("CONEXION ERRONEA CON (RESTAURANTE):");
            e.printStackTrace();
        }
    }


}

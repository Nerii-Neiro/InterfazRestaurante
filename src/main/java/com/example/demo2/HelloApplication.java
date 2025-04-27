package com.example.demo2;

import com.example.demo2.modulos.*;
import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;

public class HelloApplication extends Application {

    public static conexion con;

    @Override
    public void start(Stage stage) throws IOException {
        con = new conexion();
        VentanaLogin login = new VentanaLogin(); //Para iniciar el login
        login.show(); //Mostrarlo

    }

    public static void main(String[] args) {
        launch();
    }
}








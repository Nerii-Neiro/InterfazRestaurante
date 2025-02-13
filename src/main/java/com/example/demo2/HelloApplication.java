package com.example.demo2;


import com.example.demo2.vistas.CalculadoraV;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import javafx.scene.layout.HBox;


public class HelloApplication extends Application {

    private MenuBar menu_principal;
    private HBox contenedor;
    private Menu competencia1;
    private Menu competencia2;
    private MenuItem calculadora;
    private Scene escena;


    @Override
    public void start(Stage stage) throws IOException {
        init_menu(stage);

    }

    public void init_menu(Stage stage){

        stage.setTitle("PRACTICAS DE TOPICOS DE PROGRAMACION");
        calculadora = new MenuItem("MI CALCULADORA");
        calculadora.setOnAction(event -> new CalculadoraV());
        competencia1= new Menu("COMPETENCIA 1");
        competencia1.getItems().addAll(calculadora);
        competencia2 = new Menu("COMPETENCIA 2");
        menu_principal = new MenuBar();
        menu_principal.getMenus().addAll(competencia1,competencia2);
        contenedor = new HBox(menu_principal);
        escena = new Scene(contenedor,500,200);
        escena.getStylesheets().add(getClass().getResource("/css/interfacecustom.css").toString());
        stage.setScene(escena);
        stage.show();
        stage.setMaximized(true);


    }


    public static void main(String[] args) {
        launch();
    }
}








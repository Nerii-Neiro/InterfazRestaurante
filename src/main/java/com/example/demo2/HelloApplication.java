package com.example.demo2;

import com.example.demo2.Componenetes.Hilo;
import com.example.demo2.modulos.conexion;
import com.example.demo2.vistas.*;
import javafx.geometry.Pos;
import org.kordamp.bootstrapfx.scene.layout.Panel;
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

import java.io.IOException;
import javafx.scene.layout.HBox;


public class HelloApplication extends Application {

    private MenuBar menu_principal;
    private HBox contenedor;
    private Menu competencia1;
    private Menu competencia2;
    private MenuItem calculadora;
    private MenuItem restaurante_item;
    private Scene escena;
    public static conexion con;
    private MenuItem rompecabezas_item;

    private MenuItem carrera;

    @Override
    public void start(Stage stage) throws IOException {
        con = new conexion();
        init_menu(stage);

    }

    public void init_menu(Stage stage){
        carrera=new MenuItem("CARRERA(HILOS)");
        carrera.setOnAction(event->{
            new celaya();
//            System.out.println("CARRERA:START");
//           Hilo hilo1= new Hilo("DELICIAS");
//           Hilo hilo2= new Hilo("LAURELES");
//           Hilo hilo3= new Hilo("SAN JUAN");
//           Hilo hilo4= new Hilo("MONTE BLANCO");
//           hilo1.start();
//           hilo2.start();
//           hilo3.start();
//           hilo4.start();
        });
        rompecabezas_item= new MenuItem("ROMPECABEZAS");
        rompecabezas_item.setOnAction(event -> new Ventana_eleccion());
        restaurante_item=new MenuItem("RESTAURANTE");
        restaurante_item.setOnAction(event -> new vista_clientes());
        stage.setTitle("PRACTICAS DE TOPICOS DE PROGRAMACION");
        calculadora = new MenuItem("MI CALCULADORA");
        calculadora.setOnAction(event -> new CalculadoraV());
        competencia1= new Menu("COMPETENCIA 1");
        competencia1.getItems().addAll(calculadora,restaurante_item,rompecabezas_item);
        competencia2 = new Menu("COMPETENCIA 2");
        competencia2.getItems().addAll(carrera);
        menu_principal = new MenuBar();
        menu_principal.getMenus().addAll(competencia1,competencia2);
        contenedor = new HBox(menu_principal);
        contenedor.setSpacing(20);
        contenedor.setPadding(new Insets( 25));
        contenedor.setAlignment(Pos.BASELINE_LEFT);
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








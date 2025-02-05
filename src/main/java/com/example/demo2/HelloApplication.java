package com.example.demo2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.scene.layout.HBox;


//elements horizoltaes hbox
//elementos vertcals vbox
//con grid pane se utiliza primero de columna-renglon

public class HelloApplication extends Application {


    private MenuBar menu_principal;
    private HBox contenedor;
    private Menu competencia1;
    private Menu competencia2;
    private MenuItem calculadora;
    private Scene escena;



    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("EJEMPLO DE VENTANA");
        calculadora = new MenuItem("MI CALCULADORA");
        competencia1 =new Menu("COMPETENCIA 1");
        competencia1.getItems().addAll(calculadora);
        menu_principal = new MenuBar();
        menu_principal.getMenus().addAll(competencia1,competencia2);
        contenedor = new HBox(menu_principal);
        escena = new Scene(contenedor,500,200);
        stage.setScene(escena);
        stage.show();
        stage.setMaximized(true);

    }


    public static void main(String[] args) {
        launch();
    }
}



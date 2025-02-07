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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
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
        init_menu(stage);

    }

    public void init_menu(Stage stage){

        Key boton_uno= new Key("+");
        GridPane panel_teclado= new GridPane();
        GridPane.setRowIndex(boton_uno, 0);
        GridPane.setColumnIndex(boton_uno, 1);
        Label label = new Label("jnuuhu");
        GridPane.setConstraints(label, 2, 0);
        panel_teclado.getChildren().addAll(boton_uno, label);
        StackPane root = new StackPane();

        stage.setTitle("PRACTICAS DE TOPICOS DE PROGRAMACION");
        calculadora = new MenuItem("MI CALCULADORA");
        competencia1= new Menu("COMPETENCIA 1");
        competencia1.getItems().addAll(calculadora);
        competencia2 = new Menu("COMPETENCIA 2");
        menu_principal = new MenuBar();
        menu_principal.getMenus().addAll(competencia1,competencia2);
        contenedor = new HBox(menu_principal);
        root.getChildren().addAll(panel_teclado, contenedor);
        escena = new Scene(root,500,200);

        stage.setScene(escena);
        stage.show();
        stage.setMaximized(true);


    }




    public static void main(String[] args) {
        launch();
    }
}

class Calculadora extends Stage{

    private Display panel;
    private Keypad teclado;

    public Calculadora(){
        panel = new Display();
        teclado = new Keypad();

    }

}

class Display{

}
class Keypad {
    private GridPane panel_teclado;
    private Key boton_uno;

    public Keypad(){
        boton_uno= new Key("+");
        panel_teclado= new GridPane();
        GridPane.setRowIndex(boton_uno, 0);
        GridPane.setColumnIndex(boton_uno, 1);
        Label label = new Label();
        GridPane.setConstraints(label, 2, 0);
        panel_teclado.getChildren().addAll(boton_uno, label);
    }
}

class Key extends Button{
    private String simbolo;

    public Key(String simbolo){
        this.simbolo=simbolo;
    }
    public String get_simbolo(){
        return this.simbolo;
    }
}




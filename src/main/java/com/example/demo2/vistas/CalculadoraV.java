package com.example.demo2.vistas;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CalculadoraV extends Stage {

    //ELEMENTOS
    private Display display;
    private Keypad teclado;

    //CONTROL
    private Scene escena;
    private VBox contenedor_padre;


    public CalculadoraV(){
        GUI_CALC();
        this.setScene(escena);
        this.setTitle("CALCULADORA VER 1.0");
        this.show();
    }

    public void GUI_CALC(){
        display = new Display();
        teclado = new Keypad();
        contenedor_padre = new VBox(display,teclado);
        contenedor_padre.setSpacing(10);
        contenedor_padre.setPadding(new Insets(10));
        escena = new Scene(contenedor_padre,200,200);
    }
}

class Display extends TextField {
    public Display(){
        super("0");
        this.setEditable(false);
        this.setAlignment(Pos.BASELINE_RIGHT);
    }
}

class Keypad extends GridPane {

    private Key[][] botones;


    public Keypad(){
        botones = new Key[5][4];
        String simbolos[] = {"A","C","%","X","*","7","8","9","/","4","5","6","+","1","2","3","-",".","0","="};
        int pos=0;
        this.setHgap(10);
        this.setVgap(10);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4 ; j++) {
                botones[i][j] = new Key(simbolos[pos]);
                botones[i][j].setPrefSize(75,75);
                this.add(botones[i][j],j,i);
                pos++;
            }
        }


    }
}

class Key extends Button {
    private String simbolo;

    public Key(String simbolo){
        super(simbolo);
        this.simbolo=simbolo;
    }
    public String get_simbolo(){
        return this.simbolo;
    }
}
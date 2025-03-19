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
import java.util.*;


public class CalculadoraV extends Stage{

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
        teclado = new Keypad(display);
        contenedor_padre = new VBox(display,teclado);
        contenedor_padre.setSpacing(10);
        contenedor_padre.setPadding(new Insets(10));
        escena = new Scene(contenedor_padre,250,300);
        escena.getStylesheets().add(getClass().getResource("/css/custom.css").toString());
        //System.out.println(getClass().getResource("/css/custom.css").toString());
    }
}

class Display extends TextField {
    public Display(){
        super("0");
        this.getStyleClass().add("display-fondo");
        this.setEditable(false);
        this.setAlignment(Pos.BASELINE_RIGHT);
    }
}

class Keypad extends GridPane {

    private Key[][] botones;
    private Display txtDisplay;
    private Reconocedor proceso;
    private int operando=1;
    private boolean is_complete = false;


    public Keypad(Display txtDispay){
        this.txtDisplay=txtDispay;
        proceso= new Reconocedor();
        botones = new Key[5][4];
        String simbolos[] = {"n!","Clr","%","rz","7","8","9","/","4","5","6","+","1","2","3","-",".","0","=","*"};
        int pos=0;
        this.setHgap(10);
        this.setVgap(10);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4 ; j++) {
                botones[i][j] = new Key(simbolos[pos]);
                if((pos+1)<5){
                    botones[i][j].getStyleClass().add("botones-azules");
                }
                else if((pos+1)%4==0){
                    botones[i][j].getStyleClass().add("botones-rojos");
                }
                else{
                    botones[i][j].getStyleClass().add("botones-negros");
                }
                int finalPos = pos;
                botones[i][j].setOnAction(event -> EventosKeypad(simbolos[finalPos]));
                botones[i][j].setPrefSize(75,75);
                this.add(botones[i][j],j,i);
                pos++;
            }
        }


    }

    public void EventosKeypad(String simbolo){

        // RECONOCEDOR

        if (is_complete==true){
            operando=2;
        }
        else{
            operando = 1;
        }

        is_complete=proceso.Reconocer_Simbolo(simbolo,operando,txtDisplay);

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

class Reconocedor {
    private double resultado=0;
    private String operando_1="0";
    private String operando_2="0";
    private String operator="0";
    private boolean is_punto = false;

    private boolean other=false;

    public void limpiar_display(Display txtDisplay){
        txtDisplay.clear();
        txtDisplay.appendText("0");
        resultado=0;
        operator="0";
        is_punto=false;
        operando_1="0";
        operando_2="0";
        other=false;
    }
    public void limpiar_operando(Display txtDisplay,int operando){
        if(operando==1){
            resultado=0;
            operando_1="0";
            operando_2="0";
            operator="0";
            is_punto=false;
        }
        else{
            resultado=0;
            operando_2="0";
            is_punto=false;
        }
        txtDisplay.clear();
        txtDisplay.appendText("0");

    }

    public double calcFactorial(double n){

        if(n==0 || n==1){
            return 1;
        }
        else if(n>999){
            return 0;
        }
        return n * calcFactorial(n-1);
    }

    public void mostrar_resultado(Display txtDisplay){

            int is_error=0;
            String error="";
            boolean checar_punto=true;


            checar_punto =operando_1.matches("\\d+(\\.\\d+)?");
            if(checar_punto==false){operando_1=operando_1+"0";}
            checar_punto=operando_2.matches("\\d+(\\.\\d+)?");
            if(checar_punto==false){operando_2=operando_2+"0";}


            double temp_op_1 =Double.parseDouble(operando_1);
            double temp_op_2= Double.parseDouble(operando_2);



            if(operator.equals("0")){resultado=temp_op_1;}
            else {
                if (operator.equals("+")) {
                    resultado = temp_op_1 + temp_op_2;
                }
                else if(operator=="n!"){
                    if(temp_op_1<0){
                        resultado=0;
                        is_error=1;
                        error="ErrFactNegativo.";
                    }
                    else{
                        int n = (int) temp_op_1;
                        resultado=calcFactorial(n);
                    }


                } else if(operator.equals("rz")){
                    if(temp_op_1<0){
                        resultado=0;
                        is_error=1;
                        error="Err.NumeroImg.";
                    }
                    else{
                        resultado=Math.sqrt(temp_op_1);
                    }
                } else if (operator.equals("-")) {
                    resultado = temp_op_1 - temp_op_2;
                } else if (operator.equals("*")) {
                    resultado = temp_op_1 * temp_op_2;
                } else if(operator=="%"){
                    resultado= temp_op_1%temp_op_2;
                } else {
                    if (temp_op_2 == 0) {
                        is_error = 1;
                        resultado = 0;
                        error = "Err.Division/Zero";
                    } else {
                        resultado = temp_op_1 / temp_op_2;
                    }
                }
            }

            resultado = Math.round(resultado * 100.0) / 100.0;

            txtDisplay.clear();
            if(is_error==1){
                txtDisplay.appendText(error);
                operando_1="0";
            }
            else{
                txtDisplay.appendText(""+resultado);
                operando_1=String.valueOf(resultado);
            }
            operando_2="0";
            resultado=0;
            operator="0";
            is_punto=false;

            other=true;
    }

    public boolean Reconocer_Simbolo(String simbolo,int operando,Display txtDisplay){

        boolean is_complete=false;
        switch (simbolo){
            case "n!":
                if(operator.equals("0")) {
                    //txtDisplay.appendText(simbolo);
                    operator="n!";
                    is_punto=false;
                    mostrar_resultado(txtDisplay);
                }
                is_complete=false;
                break;
            case "Clr":
                limpiar_display(txtDisplay);
                is_complete=false;
                break;
            case "%":
                if(operator.equals("0")) {
                    txtDisplay.appendText(simbolo);
                    operator="%";
                    is_punto=false;
                }
                is_complete=true;
                break;
            case "rz":
                if(operator.equals("0")) {
                    //txtDisplay.appendText(simbolo);
                    operator="rz";
                    is_punto=false;
                    mostrar_resultado(txtDisplay);
                }
                is_complete=false;

                break;
            case "/":
                if(operator.equals("0")) {
                    txtDisplay.appendText(simbolo);
                    operator="/";
                    is_punto=false;
                }
                is_complete=true;
                break;
            case "+":
                if(operator.equals("0")) {
                    txtDisplay.appendText(simbolo);
                    operator="+";
                    is_punto=false;
                }
                is_complete=true;
                break;
            case "-":
                if(operator.equals("0")) {
                    txtDisplay.appendText(simbolo);
                    operator="-";
                    is_punto=false;
                }
                is_complete=true;
                break;
            case "*":
                if(operator.equals("0")) {
                    txtDisplay.appendText(simbolo);
                    operator="*";
                    is_punto=false;
                }
                is_complete=true;
                break;
            case "=":
                mostrar_resultado(txtDisplay);
                //is_complete=true;
                is_complete=false;
                break;
            default:
                if(operando==1){

                    if(other==true){
                        limpiar_display(txtDisplay);
                        txtDisplay.clear();
                        operando_1="";
                        other=false;
                    }

                    if(operando_1.equals("0")){
                        txtDisplay.clear();
                        operando_1="";
                    }
                    if(simbolo.equals(".") && !is_punto) {
                        operando_1 += simbolo;
                        txtDisplay.appendText(simbolo);
                        is_punto = true;
                    } else if (!simbolo.equals(".")) {
                        operando_1 += simbolo;
                        txtDisplay.appendText(simbolo);
                    }

                    is_complete=false;
                }
                else{
                    if(operando_2.equals("0")){
                        //txtDisplay.clear();
                        operando_2="";
                    }
                    if(simbolo.equals(".") && !is_punto) {
                        operando_2 += simbolo;
                        txtDisplay.appendText(simbolo);
                        is_punto = true;
                    } else if (!simbolo.equals(".")) {
                        operando_2 += simbolo;
                        txtDisplay.appendText(simbolo);
                    }
                    is_complete=true;
                }

        }
        return is_complete;
    }
}


package com.example.demo2.vistas;

import com.example.demo2.modulos.ClientesDAO;
import com.example.demo2.modulos.EmpleadosDAO;
import com.example.demo2.modulos.ReservacionesDAO;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class reservaciones extends Stage {



    private Scene escena;

    private Label txtrobservaciones,txtfecha,txthora;
    private VBox contenedor_padre;
    private Button enviar;

    private TextField observaciones;
    private TextField fecha;
    private TextField hora;

    private ReservacionesDAO objeto;
    private Label title;
    private Button salir;
    private boolean option;
    private String title_;

    public boolean no_poder;

    private TableView<ReservacionesDAO> tabla_reservaciones;

    public reservaciones(TableView<ReservacionesDAO> tabla_reservaciones,ReservacionesDAO objeto_dao,String title_){
        this.title_=title_;
        this.tabla_reservaciones=tabla_reservaciones;
        CREAR_GUI();
        if(objeto_dao==null){
            objeto =new ReservacionesDAO();
            option=false;
            LocalDate fecha_actual = LocalDate.now();
            LocalTime hora_actual = LocalTime.now();
            DateTimeFormatter formatofecha = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            DateTimeFormatter formatohora = DateTimeFormatter.ofPattern("HH:mm:ss");
            String fecha_mandar= fecha_actual.format(formatofecha);
            String hora_mandar =hora_actual.format(formatohora);
            this.fecha.setText(fecha_mandar);
            this.hora.setText(hora_mandar);
            hora.setEditable(true);
            fecha.setEditable(true);
        }
        else{
            option=true;
            objeto=objeto_dao;
            this.fecha.setText(objeto.getFecha());
            this.hora.setText(objeto.getHora());
            hora.setEditable(false);
            fecha.setEditable(false);
            this.observaciones.setText(objeto.getObservaciones());
        }
        //objeto = (objeto_dao==null)? new ClientesDAO():this.objeto;
        this.setTitle("Registrar Reservacion");
        this.setScene(escena);
        this.showAndWait();
    }

    private void CREAR_GUI(){
        this.salir=new Button("Cancelar");
        this.title =new Label(this.title_);



        txtfecha=new Label("Fecha:");
        fecha=new TextField();
        txthora=new Label("Hora:");
        hora=new TextField();
        txtrobservaciones=new Label("Observaciones:");
        observaciones=new TextField();


        salir.setOnAction(event->{
            no_poder=false;
            this.close();
        });
        enviar=new Button("Guardar");
        enviar.setOnAction(event->{

            //p

            no_poder=true;
            objeto.setFecha(fecha.getText());
            objeto.setHora(hora.getText());
            objeto.setObservaciones(observaciones.getText());
            //

            if(option==true){
                objeto.UPDATE();
            }
            else{
                objeto.INSERT();
            }

            //ACTUALIZAR LA TABLA DE CLIENTES
            this.tabla_reservaciones.setItems(objeto.SELECT());
            this.tabla_reservaciones.refresh();
            this.close();
            //
        });


        contenedor_padre=new VBox(title,txtfecha,fecha,txthora,hora,txtrobservaciones,observaciones,enviar,salir);
        contenedor_padre.setSpacing(20);
        contenedor_padre.setPadding(new Insets(20));
        contenedor_padre.setAlignment(Pos.CENTER);
        escena=new Scene(contenedor_padre,370,700);
        escena.getStylesheets().add(getClass().getResource("/css/estilo_restaurante.css").toString());

        fecha.getStyleClass().add("display-fondo");
        hora.getStyleClass().add("display-fondo");
        observaciones.getStyleClass().add("display-fondo");


        this.enviar.getStyleClass().add("botones-negros");
        this.title.getStyleClass().add("title_space");
        this.salir.getStyleClass().add("botones-rojos");
    }

}




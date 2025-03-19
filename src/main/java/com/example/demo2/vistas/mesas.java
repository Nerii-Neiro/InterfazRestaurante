package com.example.demo2.vistas;

import com.example.demo2.modulos.ClientesDAO;
import com.example.demo2.modulos.EmpleadosDAO;
import com.example.demo2.modulos.MesasDAO;
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

public class mesas extends Stage {



    private Scene escena;

    private Label txtcapacidad,txttipo;
    private VBox contenedor_padre;
    private Button enviar;

    private TextField capacidad;
    private TextField tipo;

    private MesasDAO objeto;
    private Label title;
    private Button salir;
    private boolean option;
    private String title_;

    private TableView<MesasDAO> tabla_mesas;

    public mesas(TableView<MesasDAO> tabla_mesas,MesasDAO objeto_dao,String title_){
        this.title_=title_;
        this.tabla_mesas=tabla_mesas;
        CREAR_GUI();
        if(objeto_dao==null){
            objeto =new MesasDAO();
            option=false;
        }
        else{
            option=true;
            objeto=objeto_dao;
            this.capacidad.setText(String.valueOf(objeto.getCapacidad()));
            this.tipo.setText(objeto.getTipo());
        }
        //objeto = (objeto_dao==null)? new ClientesDAO():this.objeto;
        this.setTitle("Registrar Mesa");
        this.setScene(escena);
        this.show();
    }

    private void CREAR_GUI(){
        this.salir=new Button("Cancelar");
        this.title =new Label(this.title_);



        txtcapacidad=new Label("Capacidad:");
        capacidad=new TextField();
        txttipo=new Label("Tipo:");
        tipo=new TextField();

        salir.setOnAction(event->{
            this.close();
        });
        enviar=new Button("Guardar");
        enviar.setOnAction(event->{

            //
            objeto.setCapacidad(Integer.parseInt(capacidad.getText()));
            objeto.setTipo(tipo.getText());
            //

            if(option==true){
                objeto.UPDATE();
            }
            else{
                objeto.INSERT();
            }

            //ACTUALIZAR LA TABLA DE CLIENTES
            this.tabla_mesas.setItems(objeto.SELECT());
            this.tabla_mesas.refresh();
            this.close();
            //
        });


        contenedor_padre=new VBox(title,txtcapacidad,capacidad,txttipo,tipo,enviar,salir);
        contenedor_padre.setSpacing(20);
        contenedor_padre.setPadding(new Insets(20));
        contenedor_padre.setAlignment(Pos.CENTER);
        escena=new Scene(contenedor_padre,370,700);
        escena.getStylesheets().add(getClass().getResource("/css/estilo_restaurante.css").toString());

        capacidad.getStyleClass().add("display-fondo");
        tipo.getStyleClass().add("display-fondo");

        this.enviar.getStyleClass().add("botones-negros");
        this.title.getStyleClass().add("title_space");
        this.salir.getStyleClass().add("botones-rojos");
    }

}




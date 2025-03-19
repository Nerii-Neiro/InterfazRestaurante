package com.example.demo2.vistas;

import com.example.demo2.modulos.ClientesDAO;
import com.example.demo2.modulos.EmpleadosDAO;
import com.example.demo2.modulos.ReservacionMesaDAO;
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

public class reservacionmesa extends Stage {



    private Scene escena;

    private Label txtidreservacion,txtidmesa;
    private VBox contenedor_padre;
    private Button enviar;

    private TextField idreservacion;
    private TextField idmesa;

    private ReservacionMesaDAO objeto;
    private Label title;
    private Button salir;
    private boolean option;
    private String title_;

    private TableView<ReservacionMesaDAO> table_rm;

    public reservacionmesa(TableView<ReservacionMesaDAO> table_rm,ReservacionMesaDAO objeto_dao,String title_){
        this.title_=title_;
        this.table_rm=table_rm;
        CREAR_GUI();
        if(objeto_dao==null){
            objeto =new ReservacionMesaDAO();
            option=false;
        }
        else{
            option=true;
            objeto=objeto_dao;
            this.idreservacion.setText(String.valueOf(objeto.getId_reservacion()));
            this.idmesa.setText(String.valueOf(objeto.getId_mesa()));
        }
        //objeto = (objeto_dao==null)? new ClientesDAO():this.objeto;
        this.setTitle("Registrar Reservacion-Mesa");
        this.setScene(escena);
        this.show();
    }

    private void CREAR_GUI(){
        this.salir=new Button("Cancelar");
        this.title =new Label(this.title_);

        txtidreservacion=new Label("ID_Reservacion:");
        idreservacion=new TextField();
        txtidmesa=new Label("ID_Mesa:");
        idmesa=new TextField();

        salir.setOnAction(event->{
            this.close();
        });
        enviar=new Button("Guardar");
        enviar.setOnAction(event->{

            //
            objeto.setId_reservacion(Integer.parseInt(idreservacion.getText()));
            objeto.setId_mesa(Integer.parseInt(idmesa.getText()));
            //

            if(option==true){
                objeto.UPDATE();
            }
            else{
                objeto.INSERT();
            }

            //ACTUALIZAR LA TABLA DE CLIENTES
            this.table_rm.setItems(objeto.SELECT());
            this.table_rm.refresh();
            this.close();
            //
        });


        contenedor_padre=new VBox(title,txtidreservacion,idreservacion,txtidmesa,idmesa,enviar,salir);
        contenedor_padre.setSpacing(20);
        contenedor_padre.setPadding(new Insets(20));
        contenedor_padre.setAlignment(Pos.CENTER);
        escena=new Scene(contenedor_padre,370,700);
        escena.getStylesheets().add(getClass().getResource("/css/estilo_restaurante.css").toString());

        idreservacion.getStyleClass().add("display-fondo");
        idmesa.getStyleClass().add("display-fondo");



        this.enviar.getStyleClass().add("botones-negros");
        this.title.getStyleClass().add("title_space");
        this.salir.getStyleClass().add("botones-rojos");
    }

}




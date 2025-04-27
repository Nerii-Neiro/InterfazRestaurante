package com.example.demo2.vistas;

import com.example.demo2.modulos.ClientesDAO;
import com.example.demo2.modulos.EmpleadosDAO;
import com.example.demo2.modulos.OrdenesDAO;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ordenes extends Stage {


    private Scene escena;

    private Label txtfecha,txthora,txtdescripcion,txtnotas,txtidcliente,txtidmesa,txtidempleado,txttotal;
    private VBox contenedor_padre;
    private Button enviar;

    private TextField fecha;
    private TextField hora;
    private TextField descripcion;
    private TextField notas;
    private TextField idcliente;
    private TextField idmesa;
    private TextField idempleado;
    private TextField total;

    private OrdenesDAO objeto;
    private Label title;
    private Button salir;
    private boolean option;
    private String title_;

    private TableView<OrdenesDAO> tabla_ordenes;

    public ordenes(TableView<OrdenesDAO> tabla_ordenes,OrdenesDAO objeto_dao,String title_){
        this.title_=title_;
        this.tabla_ordenes=tabla_ordenes;
        CREAR_GUI();
        if(objeto_dao==null){
            objeto =new OrdenesDAO();
            option=false;
        }
        else{
            option=true;
            objeto=objeto_dao;
            this.fecha.setText(objeto.getFecha());
            this.hora.setText(objeto.getHora());
            this.descripcion.setText(objeto.getDescripcion());
            this.notas.setText(objeto.getNotas());
            this.idempleado.setText(Integer.toString(objeto.getId_empleado()));
            this.idcliente.setText(Integer.toString(objeto.getId_cliente()));
            this.idmesa.setText(Integer.toString(objeto.getId_mesa()));
            this.total.setText(Double.toHexString(objeto.getTotal()));
        }
        //objeto = (objeto_dao==null)? new ClientesDAO():this.objeto;
        this.setTitle("Registrar Orden");
        this.setScene(escena);
        this.show();
    }

    private void CREAR_GUI(){
        this.salir=new Button("Cancelar");
        this.title =new Label(this.title_);


        txtfecha=new Label("Fecha:");
        fecha=new TextField();
        txthora=new Label("Hora:");
        hora=new TextField();
        txtdescripcion=new Label("Descripcion:");
        descripcion=new TextField();
        txtnotas=new Label("Notas:");
        notas=new TextField();
        txtidcliente=new Label("ID_Cliente:");
        idcliente=new TextField();
        txtidmesa=new Label("ID_Mesa:");
        idmesa=new TextField();
        txtidempleado=new Label("ID_Empleado:");
        idempleado=new TextField();
        txttotal=new Label("Total:");
        total=new TextField();

        salir.setOnAction(event->{
            this.close();
        });
        enviar=new Button("Guardar");
        enviar.setOnAction(event->{

            //
            objeto.setFecha(fecha.getText());
            objeto.setHora(hora.getText());
            objeto.setDescripcion(descripcion.getText());
            objeto.setNotas(notas.getText());
            objeto.setId_cliente(Integer.parseInt(idcliente.getText()));
            objeto.setId_mesa(Integer.parseInt(idmesa.getText()));
            objeto.setId_empleado(Integer.parseInt(idempleado.getText()));
            objeto.setTotal(Double.parseDouble(total.getText()));
            //

            if(option==true){
                objeto.UPDATE();
            }
            else{
                objeto.INSERT();
            }

            //ACTUALIZAR LA TABLA DE CLIENTES
            this.tabla_ordenes.setItems(objeto.SELECT());
            this.tabla_ordenes.refresh();
            this.close();
            //
        });

        VBox g1 = new VBox(txtfecha,fecha);
        VBox g2 = new VBox(txthora,hora);
        HBox contt = new HBox(g1,g2);
        VBox g3 = new VBox(txtidcliente,idcliente);
        VBox g4 = new VBox(txtidmesa,idmesa);
        HBox contt_2 = new HBox(g3,g4);

        contenedor_padre=new VBox(title,contt,txtdescripcion,descripcion,txtnotas,notas,contt_2,txtidempleado,idempleado,txttotal,total,enviar,salir);
        contenedor_padre.setSpacing(6);
        contenedor_padre.setPadding(new Insets(20));
        contenedor_padre.setAlignment(Pos.CENTER);
        escena=new Scene(contenedor_padre,600,700);
        escena.getStylesheets().add(getClass().getResource("/css/estilo_restaurante.css").toString());

        fecha.getStyleClass().add("display-fondo");
        hora.getStyleClass().add("display-fondo");
        descripcion.getStyleClass().add("display-fondo");
        notas.getStyleClass().add("display-fondo");
        idcliente.getStyleClass().add("display-fondo");
        idmesa.getStyleClass().add("display-fondo");
        idempleado.getStyleClass().add("display-fondo");
        total.getStyleClass().add("display-fondo");


        this.enviar.getStyleClass().add("botones-negros");
        this.title.getStyleClass().add("title_space");
        this.salir.getStyleClass().add("botones-rojos");
    }

}




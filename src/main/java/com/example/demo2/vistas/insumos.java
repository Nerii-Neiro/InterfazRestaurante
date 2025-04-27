package com.example.demo2.vistas;

import com.example.demo2.modulos.ClientesDAO;
import com.example.demo2.modulos.InsumosDAO;
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

public class insumos extends Stage {



    private Scene escena;

    private Label txtnombre,txtcantidad,txtdescripcion,txtobservaciones,txtidproveedor;
    private VBox contenedor_padre;
    private Button enviar;

    private TextField nombre;
    private TextField cantidad;
    private TextField descripcion;
    private TextField observaciones;
    private TextField idproveedor;


    private InsumosDAO objeto;
    private Label title;
    private Button salir;
    private boolean option;
    private String title_;

    private TableView<InsumosDAO> tabla_insumos;

    public insumos(TableView<InsumosDAO> tabla_insumos,InsumosDAO objeto_dao,String title_){
        this.title_=title_;
        this.tabla_insumos=tabla_insumos;
        CREAR_GUI();
        if(objeto_dao==null){
            objeto =new InsumosDAO();
            option=false;
        }
        else{
            option=true;
            objeto=objeto_dao;


            this.nombre.setText(objeto.getNombre());
            this.cantidad.setText(String.valueOf(objeto.getCantidad()));
            this.descripcion.setText(objeto.getDescripcion());
            this.observaciones.setText(objeto.getObservaciones());
            this.idproveedor.setText(String.valueOf(objeto.getId_proveedor()));
        }
        //objeto = (objeto_dao==null)? new ClientesDAO():this.objeto;
        this.setTitle("Registrar Insumo");
        this.setScene(escena);
        this.show();
    }

    private void CREAR_GUI(){
        this.salir=new Button("Cancelar");
        this.title =new Label(this.title_);


        txtnombre=new Label("Nombre:");
        nombre=new TextField();
        txtcantidad=new Label("Cantidad:");
        cantidad=new TextField();
        txtdescripcion=new Label("Descripcion:");
        descripcion=new TextField();
        txtobservaciones=new Label("Observaciones:");
        observaciones=new TextField();
        txtidproveedor=new Label("Id Proveedor:");
        idproveedor=new TextField();


        salir.setOnAction(event->{
            this.close();
        });
        enviar=new Button("Guardar");
        enviar.setOnAction(event->{


            //
            objeto.setNombre(nombre.getText());
            objeto.setCantidad(Integer.parseInt(cantidad.getText()));
            objeto.setDescripcion(descripcion.getText());
            objeto.setObservaciones(observaciones.getText());
            objeto.setId_proveedor(Integer.parseInt(idproveedor.getText()));
            //

            if(option==true){
                objeto.UPDATE();
            }
            else{
                objeto.INSERT();
            }

            //ACTUALIZAR LA TABLA DE CLIENTES
            this.tabla_insumos.setItems(objeto.SELECT());
            this.tabla_insumos.refresh();
            this.close();
            //
        });

        contenedor_padre=new VBox(title,txtnombre,nombre,txtcantidad,cantidad,txtdescripcion,descripcion,txtobservaciones,observaciones,txtidproveedor,idproveedor,enviar,salir);
        contenedor_padre.setSpacing(5);
        contenedor_padre.setPadding(new Insets(20));
        contenedor_padre.setAlignment(Pos.CENTER);
        escena=new Scene(contenedor_padre,370,700);
        escena.getStylesheets().add(getClass().getResource("/css/estilo_restaurante.css").toString());

        nombre.getStyleClass().add("display-fondo");
        cantidad.getStyleClass().add("display-fondo");
        descripcion.getStyleClass().add("display-fondo");
        observaciones.getStyleClass().add("display-fondo");
        idproveedor.getStyleClass().add("display-fondo");

        enviar.getStyleClass().add("botones-negros");
        this.title.getStyleClass().add("title_space");
        this.salir.getStyleClass().add("botones-rojos");
    }

}




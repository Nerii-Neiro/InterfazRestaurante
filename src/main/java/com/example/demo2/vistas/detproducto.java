package com.example.demo2.vistas;

import com.example.demo2.modulos.ClientesDAO;
import com.example.demo2.modulos.DetProductoDAO;
import com.example.demo2.modulos.EmpleadosDAO;
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

public class detproducto extends Stage {



    private Scene escena;

    private Label txtidproducto,txtidinsumo,txtcantidad,txtfecha,txtobservaciones,txtdecripcion;
    private VBox contenedor_padre;
    private Button enviar;

    private TextField idproducto;
    private TextField idinsumo;
    private TextField cantidad_necesitada;
    private TextField fecha_caducidad;
    private TextField observaciones;
    private TextField descripcion;


    private DetProductoDAO objeto;
    private Label title;
    private Button salir;
    private boolean option;
    private String title_;

    private TableView<DetProductoDAO> tabla_detproducto;

    public detproducto(TableView<DetProductoDAO> tabla_detproducto,DetProductoDAO objeto_dao,String title_){
        this.title_=title_;
        this.tabla_detproducto=tabla_detproducto;
        CREAR_GUI();
        if(objeto_dao==null){
            objeto =new DetProductoDAO();
            option=false;
        }
        else{
            option=true;
            objeto=objeto_dao;
            this.idproducto.setText(String.valueOf(objeto.getId_producto()));
            this.idinsumo.setText(String.valueOf(objeto.getId_insumo()));
            this.cantidad_necesitada.setText(String.valueOf(objeto.getCantidad_necesitada()));
            this.fecha_caducidad.setText(objeto.getFecha_caducidad());
            this.observaciones.setText(objeto.getObservaciones());
            this.descripcion.setText(objeto.getDescripcion());
        }
        //objeto = (objeto_dao==null)? new ClientesDAO():this.objeto;
        this.setTitle("Registrar DetProducto");
        this.setScene(escena);
        this.show();
    }

    private void CREAR_GUI(){
        this.salir=new Button("Cancelar");
        this.title =new Label(this.title_);

        txtidproducto=new Label("ID_Poducto:");
        idproducto=new TextField();
        txtidinsumo=new Label("ID_Insumo:");
        idinsumo=new TextField();
        txtcantidad=new Label("Cantidad:");
        cantidad_necesitada=new TextField();
        txtfecha=new Label("Fecha Caducidad:");
        fecha_caducidad=new TextField();
        txtobservaciones=new Label("Observaciones:");
        observaciones=new TextField();
        txtdecripcion=new Label("Descripcion:");
        descripcion=new TextField();

        salir.setOnAction(event->{
            this.close();
        });
        enviar=new Button("Guardar");
        enviar.setOnAction(event->{

            //
            objeto.setId_producto(Integer.parseInt(idproducto.getText()));
            objeto.setId_insumo(Integer.parseInt(idinsumo.getText()));
            objeto.setCantidad_necesitada(Integer.parseInt(cantidad_necesitada.getText()));
            objeto.setFecha_caducidad(fecha_caducidad.getText());
            objeto.setObservaciones(observaciones.getText());
            objeto.setDescripcion(descripcion.getText());
            //

            if(option==true){
                objeto.UPDATE();
            }
            else{
                objeto.INSERT();
            }

            //ACTUALIZAR LA TABLA DE CLIENTES
            this.tabla_detproducto.setItems(objeto.SELECT());
            this.tabla_detproducto.refresh();
            this.close();
            //
        });


        contenedor_padre=new VBox(title,txtidproducto,idproducto,txtidinsumo,idinsumo,txtcantidad,cantidad_necesitada,txtfecha,fecha_caducidad,txtobservaciones,observaciones,txtdecripcion,descripcion,enviar,salir);
        contenedor_padre.setSpacing(20);
        contenedor_padre.setPadding(new Insets(20));
        contenedor_padre.setAlignment(Pos.CENTER);
        escena=new Scene(contenedor_padre,370,700);
        escena.getStylesheets().add(getClass().getResource("/css/estilo_restaurante.css").toString());

        idproducto.getStyleClass().add("display-fondo");
        idinsumo.getStyleClass().add("display-fondo");
        cantidad_necesitada.getStyleClass().add("display-fondo");
        fecha_caducidad.getStyleClass().add("display-fondo");
        observaciones.getStyleClass().add("display-fondo");
        descripcion.getStyleClass().add("display-fondo");


        this.enviar.getStyleClass().add("botones-negros");
        this.title.getStyleClass().add("title_space");
        this.salir.getStyleClass().add("botones-rojos");
    }

}




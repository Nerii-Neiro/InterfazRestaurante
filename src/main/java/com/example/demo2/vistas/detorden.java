package com.example.demo2.vistas;

import com.example.demo2.modulos.ClientesDAO;
import com.example.demo2.modulos.DetOrdenDAO;
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

public class detorden extends Stage {



    private Scene escena;

    private Label txtidorden,txtidproducto,txtcantidad,txtprecio;
    private VBox contenedor_padre;
    private Button enviar;

    private TextField idorden;
    private TextField idproducto;
    private TextField cantidad;
    private TextField precio;

    private DetOrdenDAO objeto;
    private Label title;
    private Button salir;
    private boolean option;
    private String title_;

    private TableView<DetOrdenDAO> tabla_detorden;

    public detorden(TableView<DetOrdenDAO> tabla_detorden,DetOrdenDAO objeto_dao,String title_){
        this.title_=title_;
        this.tabla_detorden=tabla_detorden;
        CREAR_GUI();
        if(objeto_dao==null){
            objeto =new DetOrdenDAO();
            option=false;
        }
        else{
            option=true;
            objeto=objeto_dao;
            this.idorden.setText(String.valueOf(objeto.getId_orden()));
            this.idproducto.setText(String.valueOf(objeto.getId_producto()));
            this.cantidad.setText(String.valueOf(objeto.getCantidad()));
            this.precio.setText(String.valueOf(objeto.getPrecio()));

        }
        //objeto = (objeto_dao==null)? new ClientesDAO():this.objeto;
        this.setTitle("Registrar DetOrden");
        this.setScene(escena);
        this.show();
    }

    private void CREAR_GUI(){
        this.salir=new Button("Cancelar");
        this.title =new Label(this.title_);

        txtidorden=new Label("ID_Orden:");
        idorden=new TextField();
        txtidproducto=new Label("ID_Producto:");
        idproducto=new TextField();
        txtcantidad=new Label("Cantidad:");
        cantidad=new TextField();
        txtprecio=new Label("Precio:");
        precio=new TextField();

        salir.setOnAction(event->{
            this.close();
        });
        enviar=new Button("Guardar");
        enviar.setOnAction(event->{

            //
            objeto.setId_orden(Integer.parseInt(idorden.getText()));
            objeto.setId_producto(Integer.parseInt(idproducto.getText()));
            objeto.setCantidad(Integer.parseInt(cantidad.getText()));
            objeto.setPrecio(Double.parseDouble(precio.getText()));
            //

            if(option==true){
                objeto.UPDATE();
            }
            else{
                objeto.INSERT();
            }

            //ACTUALIZAR LA TABLA DE CLIENTES
            this.tabla_detorden.setItems(objeto.SELECT());
            this.tabla_detorden.refresh();
            this.close();
            //
        });

        contenedor_padre=new VBox(title,txtidorden,idorden,txtidproducto,idproducto,txtcantidad,cantidad,txtprecio,precio,enviar,salir);
        contenedor_padre.setSpacing(20);
        contenedor_padre.setPadding(new Insets(20));
        contenedor_padre.setAlignment(Pos.CENTER);
        escena=new Scene(contenedor_padre,370,700);
        escena.getStylesheets().add(getClass().getResource("/css/estilo_restaurante.css").toString());

        idorden.getStyleClass().add("display-fondo");
        idproducto.getStyleClass().add("display-fondo");
        cantidad.getStyleClass().add("display-fondo");
        precio.getStyleClass().add("display-fondo");

        this.enviar.getStyleClass().add("botones-negros");
        this.title.getStyleClass().add("title_space");
        this.salir.getStyleClass().add("botones-rojos");
    }

}




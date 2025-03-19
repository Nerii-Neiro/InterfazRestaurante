package com.example.demo2.vistas;

import com.example.demo2.modulos.CategoriasDAO;
import com.example.demo2.modulos.ClientesDAO;
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

public class categoria extends Stage {

    private Scene escena;

    private Label txtnombre,txtdireccion;
    private VBox contenedor_padre;
    private Button enviar;
    private TextField nombre;
    private TextField direccion;
    private CategoriasDAO objeto;
    private Label title;
    private Button salir;
    private boolean option;
    private String title_;

    private TableView<CategoriasDAO> tabla_categorias;

    public categoria(TableView<CategoriasDAO> tabla_categorias,CategoriasDAO objeto_dao,String title_){
        this.title_=title_;
        this.tabla_categorias=tabla_categorias;
        CREAR_GUI();
        if(objeto_dao==null){
            objeto =new CategoriasDAO();
            option=false;
        }
        else{
            option=true;
            objeto=objeto_dao;
            this.nombre.setText(objeto.getNombre());
            this.direccion.setText(objeto.getDescripcion());
        }
        //objeto = (objeto_dao==null)? new ClientesDAO():this.objeto;
        this.setTitle("Registrar Categoria");
        this.setScene(escena);
        this.show();
    }

    private void CREAR_GUI(){
        this.salir=new Button("Cancelar");
        this.title =new Label(this.title_);
        txtnombre=new Label("Nombre:");
        nombre=new TextField();
        txtdireccion=new Label("Descripcion:");
        direccion=new TextField();
        salir.setOnAction(event->{
            this.close();
        });
        enviar=new Button("Guardar");
        enviar.setOnAction(event->{
            //
            objeto.setNombre(nombre.getText());
            objeto.setDescripcion(direccion.getText());
            //

            if(option==true){
                objeto.UPDATE();
            }
            else{
                objeto.INSERT();
            }

            //ACTUALIZAR LA TABLA DE CLIENTES
            this.tabla_categorias.setItems(objeto.SELECT());
            this.tabla_categorias.refresh();
            this.close();
            //
        });
        contenedor_padre=new VBox(title,txtnombre,nombre,txtdireccion,direccion,enviar,salir);
        contenedor_padre.setSpacing(20);
        contenedor_padre.setPadding(new Insets(20));
        contenedor_padre.setAlignment(Pos.CENTER);
        escena=new Scene(contenedor_padre,370,700);
        escena.getStylesheets().add(getClass().getResource("/css/estilo_restaurante.css").toString());
        nombre.getStyleClass().add("display-fondo");
        direccion.getStyleClass().add("display-fondo");
        enviar.getStyleClass().add("botones-negros");
        this.title.getStyleClass().add("title_space");
        this.salir.getStyleClass().add("botones-rojos");
    }

}




package com.example.demo2.vistas;

import com.example.demo2.modulos.ClientesDAO;
import com.example.demo2.modulos.EmpleadosDAO;
import com.example.demo2.modulos.ProductosDAO;
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

public class productos extends Stage {



    private Scene escena;

    private Label txtnombre,txtprecio,txtdescripcion,txtidcategoria;
    private VBox contenedor_padre;
    private Button enviar;

    private TextField nombre;
    private TextField precio;
    private TextField descripcion;
    private TextField idcategoria;

    private ProductosDAO objeto;
    private Label title;
    private Button salir;
    private boolean option;
    private String title_;

    private TableView<ProductosDAO> tabla_productos;

    public productos(TableView<ProductosDAO> tabla_productos,ProductosDAO objeto_dao,String title_){
        this.title_=title_;
        this.tabla_productos=tabla_productos;
        CREAR_GUI();
        if(objeto_dao==null){
            objeto =new ProductosDAO();
            option=false;
        }
        else{
            option=true;
            objeto=objeto_dao;
            this.nombre.setText(objeto.getNombre());
            this.precio.setText(String.valueOf(objeto.getPrecio()));
            this.descripcion.setText(objeto.getDescripcion());
            this.idcategoria.setText(String.valueOf(objeto.getId_categoria()));
        }
        //objeto = (objeto_dao==null)? new ClientesDAO():this.objeto;
        this.setTitle("Registrar Producto");
        this.setScene(escena);
        this.show();
    }

    private void CREAR_GUI(){
        this.salir=new Button("Cancelar");
        this.title =new Label(this.title_);



        txtnombre=new Label("Nombre:");
        nombre=new TextField();
        txtprecio=new Label("Precio:");
        precio=new TextField();
        txtdescripcion=new Label("Descripcion:");
        descripcion=new TextField();
        txtidcategoria=new Label("ID_Categoria:");
        idcategoria=new TextField();

        salir.setOnAction(event->{
            this.close();
        });
        enviar=new Button("Guardar");
        enviar.setOnAction(event->{

            //
            objeto.setNombre(nombre.getText());
            objeto.setPrecio(Double.parseDouble(precio.getText()));
            objeto.setDescripcion(descripcion.getText());
            objeto.setId_categoria(Integer.parseInt(idcategoria.getText()));
            //

            if(option==true){
                objeto.UPDATE();
            }
            else{
                objeto.INSERT();
            }

            //ACTUALIZAR LA TABLA DE CLIENTES
            this.tabla_productos.setItems(objeto.SELECT());
            this.tabla_productos.refresh();
            this.close();
            //
        });


        contenedor_padre=new VBox(title,txtnombre,nombre,txtprecio,precio,txtdescripcion,descripcion,txtidcategoria,idcategoria,enviar,salir);
        contenedor_padre.setSpacing(20);
        contenedor_padre.setPadding(new Insets(20));
        contenedor_padre.setAlignment(Pos.CENTER);
        escena=new Scene(contenedor_padre,370,700);
        escena.getStylesheets().add(getClass().getResource("/css/estilo_restaurante.css").toString());

        nombre.getStyleClass().add("display-fondo");
        precio.getStyleClass().add("display-fondo");
        descripcion.getStyleClass().add("display-fondo");
        idcategoria.getStyleClass().add("display-fondo");



        this.enviar.getStyleClass().add("botones-negros");
        this.title.getStyleClass().add("title_space");
        this.salir.getStyleClass().add("botones-rojos");
    }

}




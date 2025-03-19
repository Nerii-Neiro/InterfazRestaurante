package com.example.demo2.vistas;

import com.example.demo2.modulos.ClientesDAO;
import com.example.demo2.modulos.EmpleadosDAO;
import com.example.demo2.modulos.ProveedoresDAO;
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

public class proveedores extends Stage {



    private Scene escena;

    private Label txtnombre,txttelefono,txtdireccion,txtemail,txtnota;
    private VBox contenedor_padre;
    private Button enviar;

    private TextField nombre;
    private TextField telefono;
    private TextField direccion;
    private TextField email;
    private TextField nota;

    private ProveedoresDAO objeto;
    private Label title;
    private Button salir;
    private boolean option;
    private String title_;

    private TableView<ProveedoresDAO> tabla_proveedores;

    public proveedores(TableView<ProveedoresDAO> tabla_proveedores,ProveedoresDAO objeto_dao,String title_){
        this.title_=title_;
        this.tabla_proveedores=tabla_proveedores;
        CREAR_GUI();
        if(objeto_dao==null){
            objeto =new ProveedoresDAO();
            option=false;
        }
        else{
            option=true;
            objeto=objeto_dao;
            this.nombre.setText(objeto.getNombre());
            this.telefono.setText(objeto.getDireccion());
            this.direccion.setText(objeto.getDireccion());
            this.email.setText(objeto.getEmail());
            this.nota.setText(objeto.getNota());
        }
        //objeto = (objeto_dao==null)? new ClientesDAO():this.objeto;
        this.setTitle("Registrar Proveedor");
        this.setScene(escena);
        this.show();
    }

    private void CREAR_GUI(){
        this.salir=new Button("Cancelar");
        this.title =new Label(this.title_);



        txtnombre=new Label("Nombre:");
        nombre=new TextField();
        txttelefono=new Label("Telefono:");
        telefono=new TextField();
        txtdireccion=new Label("Direccion:");
        direccion=new TextField();
        txtemail=new Label("Correo Electronico:");
        email=new TextField();
        txtnota=new Label("Nota:");
        nota=new TextField();

        salir.setOnAction(event->{
            this.close();
        });
        enviar=new Button("Guardar");
        enviar.setOnAction(event->{

            //
            objeto.setNombre(nombre.getText());
            objeto.setTelefono(telefono.getText());
            objeto.setDireccion(direccion.getText());
            objeto.setEmail(email.getText());
            objeto.setNota(nota.getText());
            //

            if(option==true){
                objeto.UPDATE();
            }
            else{
                objeto.INSERT();
            }

            //ACTUALIZAR LA TABLA DE CLIENTES
            this.tabla_proveedores.setItems(objeto.SELECT());
            this.tabla_proveedores.refresh();
            this.close();
            //
        });


        contenedor_padre=new VBox(title,txtnombre,nombre,txttelefono,telefono,txtdireccion,direccion,txtemail,email,txtnota,nota,enviar,salir);
        contenedor_padre.setSpacing(20);
        contenedor_padre.setPadding(new Insets(20));
        contenedor_padre.setAlignment(Pos.CENTER);
        escena=new Scene(contenedor_padre,370,700);
        escena.getStylesheets().add(getClass().getResource("/css/estilo_restaurante.css").toString());

        nombre.getStyleClass().add("display-fondo");
        telefono.getStyleClass().add("display-fondo");
        email.getStyleClass().add("display-fondo");
        direccion.getStyleClass().add("display-fondo");
        nota.getStyleClass().add("display-fondo");


        this.enviar.getStyleClass().add("botones-negros");
        this.title.getStyleClass().add("title_space");
        this.salir.getStyleClass().add("botones-rojos");
    }

}




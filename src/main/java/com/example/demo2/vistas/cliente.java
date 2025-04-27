package com.example.demo2.vistas;

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

public class cliente extends Stage {

    private Scene escena;

    private Label txtnombre,txtdireccion,txttelefono,txtemail;
    private VBox contenedor_padre;
    private Button enviar;
    private TextField nombre;
    private TextField direccion;
    private TextField telefono;
    private TextField email;
    private ClientesDAO objeto;
    private Label title;
    private Button salir;
    private boolean option;
    private String title_;

    public boolean no_poder;

    private TableView<ClientesDAO> tabla_clientes;

    public cliente(TableView<ClientesDAO> tabla_clientes,ClientesDAO objeto_dao,String title_){
        this.title_=title_;
        this.tabla_clientes=tabla_clientes;
        CREAR_GUI();
        if(objeto_dao==null){
            objeto =new ClientesDAO();
            option=false;
        }
        else{
            option=true;
            objeto=objeto_dao;
            this.nombre.setText(objeto.getNombre());
            this.direccion.setText(objeto.getDireccion());
            this.telefono.setText(objeto.getTelefono());
            this.email.setText(objeto.getEmail());
        }
        //objeto = (objeto_dao==null)? new ClientesDAO():this.objeto;
        this.setTitle("Registrar Cliente");
        this.setScene(escena);
        this.showAndWait();
    }

    private void CREAR_GUI(){
        this.salir=new Button("Cancelar");
        this.title =new Label(this.title_);
        txtnombre=new Label("Nombre:");
        nombre=new TextField();
        txtdireccion=new Label("Direccion:");
        direccion=new TextField();
        txttelefono=new Label("Telefono:");
        telefono=new TextField();
        txtemail=new Label("Correo Electronico:");
        email=new TextField();
        salir.setOnAction(event->{
            no_poder=false;
            this.close();
        });
        enviar=new Button("Guardar");
        enviar.setOnAction(event->{
            //
            no_poder=true;
            objeto.setNombre(nombre.getText());
            objeto.setDireccion(direccion.getText());
            objeto.setTelefono(telefono.getText());
            objeto.setEmail(email.getText());
            //

            if(option==true){
                objeto.UPDATE();
            }
            else{
                objeto.INSERT();
            }

            //ACTUALIZAR LA TABLA DE CLIENTES
            this.tabla_clientes.setItems(objeto.SELECT());
            this.tabla_clientes.refresh();
            this.close();
            //
        });
        contenedor_padre=new VBox(title,txtnombre,nombre,txtdireccion,direccion,txttelefono,telefono,txtemail,email,enviar,salir);
        contenedor_padre.setSpacing(20);
        contenedor_padre.setPadding(new Insets(20));
        contenedor_padre.setAlignment(Pos.CENTER);
        escena=new Scene(contenedor_padre,370,700);
        escena.getStylesheets().add(getClass().getResource("/css/estilo_restaurante.css").toString());
        nombre.getStyleClass().add("display-fondo");
        direccion.getStyleClass().add("display-fondo");
        telefono.getStyleClass().add("display-fondo");
        email.getStyleClass().add("display-fondo");
        enviar.getStyleClass().add("botones-negros");
        this.title.getStyleClass().add("title_space");
        this.salir.getStyleClass().add("botones-rojos");
    }

}




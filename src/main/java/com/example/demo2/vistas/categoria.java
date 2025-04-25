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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;

public class categoria extends Stage {

    private Scene escena;

    private ImageView imagenView;
    private Button boton_cargar;
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
    private String rutaIMAGEN="";

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

    private void cargar_imagen(){
        FileChooser escoger= new FileChooser();
        escoger.setTitle("Seleecionar Imagen");
        escoger.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Imagenes","*.png","*.jpg","*jpeg","*.gif")
        );

        File selectedFile = escoger.showOpenDialog(this);
        if (selectedFile != null) {
            try {
                // Mostrar la imagen en el ImageView
                Image image = new Image(selectedFile.toURI().toString());
                imagenView.setImage(image);

                this.rutaIMAGEN=selectedFile.getName();
                Path destinoCarpeta = Paths.get("imagenes");
                Files.createDirectories(destinoCarpeta);

                Path destinoArchivo = destinoCarpeta.resolve(this.rutaIMAGEN);
                Files.copy(selectedFile.toPath(), destinoArchivo, StandardCopyOption.REPLACE_EXISTING);

                String rutaRelativa= "imagenes/"+rutaIMAGEN;
                rutaIMAGEN=rutaRelativa;
                System.out.println("Imagen guardada en: " + rutaIMAGEN);

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }


    private void CREAR_GUI(){
        this.boton_cargar= new Button("Cargar");
        this.imagenView= new ImageView();
        imagenView.setFitHeight(70);
        imagenView.setFitWidth(70);
        imagenView.setPreserveRatio(true);
        this.boton_cargar.setOnAction(event->{
            cargar_imagen();
        });
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
            if(rutaIMAGEN.isEmpty()){
                rutaIMAGEN="/imagenes/categorias.png";
                objeto.setImagen(rutaIMAGEN);
            }
            else{
                objeto.setImagen(rutaIMAGEN);
            }
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
        contenedor_padre=new VBox(imagenView,boton_cargar,title,txtnombre,nombre,txtdireccion,direccion,enviar,salir);
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
        this.boton_cargar.getStyleClass().add("botones-rojos");
    }

}




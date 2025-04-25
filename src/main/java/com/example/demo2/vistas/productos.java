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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class productos extends Stage {



    private Scene escena;
    private ImageView imagenView;
    private Button boton_cargar;
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
    private String rutaIMAGEN="";

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
            this.tabla_productos.setItems(objeto.SELECT());
            this.tabla_productos.refresh();
            this.close();
            //
        });


        contenedor_padre=new VBox(imagenView,boton_cargar,title,txtnombre,nombre,txtprecio,precio,txtdescripcion,descripcion,txtidcategoria,idcategoria,enviar,salir);
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




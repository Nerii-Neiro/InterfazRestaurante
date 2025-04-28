package com.example.demo2.vistas;


import com.example.demo2.modulos.*;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.util.StringConverter;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;
import org.w3c.dom.stylesheets.LinkStyle;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class GestionInsumos extends Stage {

    Scene escena_principal;
    VBox contenedor;
    GridPane cuadricula_productos;
    ProductosDAO cat_productos;
    InsumosDAO cat_insumos;
    List<Producto> productos_lista;
    VBox contenedor_cuadricula_productos;
    Button salir ;

    Label ver_prod ;

    List<Insumos> lista_insumos;

    ObservableList<Insumos> observable_lista;
    ComboBox<Insumos> comboBox;

    int producto_seleccionado=0;

        public GestionInsumos(){
            GUI();
            this.setTitle("SISTEMA DE RESTAURANTE");
            this.setScene(escena_principal);
            this.setMaximized(true);
            this.show();
        }

        public void GUI(){
            cat_insumos= new InsumosDAO();
            lista_insumos = cat_insumos.Obtener_Insumos();

            observable_lista= FXCollections.observableArrayList(lista_insumos);
            comboBox=new ComboBox<>(observable_lista);
            comboBox.setConverter(new StringConverter<>() {
                @Override
                public String toString(Insumos insumo){
                    return (insumo!=null)?insumo.getNombre():"";
                }

                @Override
                public Insumos fromString(String string) {
                    return null;
                }
            });
            salir = new Button("Cerrar");
            salir.setOnAction(e -> {this.close();});
            salir.getStyleClass().add("botones-exit");
            cat_productos= new ProductosDAO();
            productos_lista = cat_productos.Obtener_Productos();
            cuadricula_productos = new GridPane();
            cuadricula_productos.setHgap(35);
            cuadricula_productos.setVgap(20);
            cuadricula_productos.setAlignment(Pos.CENTER);
            cargar_productos();
            contenedor_cuadricula_productos = new VBox(new Label("Productos:"),cuadricula_productos);
            contenedor_cuadricula_productos.setSpacing(10);
            contenedor_cuadricula_productos.setPadding(new Insets( 10));
            contenedor_cuadricula_productos.setAlignment(Pos.TOP_CENTER);
            contenedor_cuadricula_productos.getStyleClass().add("fondo_interf");
            ScrollPane scroll_2 = new ScrollPane(contenedor_cuadricula_productos);
            //scroll_2.setPrefHeight(400);
            scroll_2.setFitToWidth(true);
            scroll_2.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);


            Button asignar_boton=new Button("Asignar");
            asignar_boton.setOnAction(event -> {
                Insumos selecion = comboBox.getSelectionModel().getSelectedItem();
                if(selecion!= null && producto_seleccionado!=0){
                    System.out.println("ss");
//                    private  int id_detalle;
//                    private  int id_producto;
//                    private int id_insumo;
//                    private int cantidad_necesitada;
//                    private String fecha_caducidad;
//                    private String observaciones;
//                    private String descripcion;
                    LocalDate fecha_actual = LocalDate.now();
                    LocalTime hora_actual = LocalTime.now();
                    DateTimeFormatter formatofecha = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    DateTimeFormatter formatohora = DateTimeFormatter.ofPattern("HH:mm:ss");
                    String fecha_mandar= fecha_actual.format(formatofecha);
                    String hora_mandar =hora_actual.format(formatohora);

                    VentanaNotas notes = new VentanaNotas();
                    String observaciones_mandar=notes.ret_DESC();
                    String notas_mandar =notes.ret_NOTAS();

                    DetProductoDAO orden = new DetProductoDAO();
                    orden.crear_producto(0,producto_seleccionado,selecion.getId_insumo(),1,fecha_mandar,observaciones_mandar,notas_mandar);
                    orden.INSERT();
                    new InformeGeneral("Se asigno Insumo a Producto");
                }
                else{
                    System.out.println("no");
                    new InformeGeneral("No se puede asignar. \nFalta seleccionar Producto o Insumo");
                }
            });
            asignar_boton.getStyleClass().add("botones-media");
            if  (producto_seleccionado==0){
                ver_prod=new Label("Na");
            }
            else{
                ver_prod=new Label(String.valueOf(producto_seleccionado));
            }
            HBox contenedor_xy= new HBox(new Label("Producto Seleccionado: "),ver_prod);
            contenedor_xy.setSpacing(10);
            contenedor_xy.setPadding(new Insets( 10));
            contenedor_xy.setAlignment(Pos.TOP_CENTER);
            contenedor_xy.getStyleClass().add("display-fondo2");

            HBox contenedor_x= new HBox(contenedor_xy,comboBox,asignar_boton);
            contenedor_x.setSpacing(10);
            contenedor_x.setPadding(new Insets( 10));
            contenedor_x.setAlignment(Pos.TOP_CENTER);
            contenedor_x.getStyleClass().add("display-fondo2");

            contenedor=new VBox(new Label("Gestion de Insumos:"),contenedor_x,scroll_2,salir);
            contenedor.setSpacing(10);
            contenedor.setPadding(new Insets( 10));
            contenedor.setAlignment(Pos.TOP_CENTER);
            contenedor.getStyleClass().add("display-fondo2");
            escena_principal = new Scene(contenedor,500,200);
            escena_principal.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
            escena_principal.getStylesheets().add(getClass().getResource("/css/estilo_principal.css").toString());
        }

        public void refresh(){
            ver_prod.setText("");
            if  (producto_seleccionado==0){
                ver_prod.setText("Na");
            }
            else{
                ver_prod.setText(String.valueOf(producto_seleccionado));
            }
        }

    public void cargar_productos(){
        cuadricula_productos.getChildren().clear();

        int tamano3 = 40;
        String imagen3="/imagenes/categorias.png";
        int columna3 = 0;
        int fila3=0;

        for (Producto producto: productos_lista) {
            Casilla casilla = new Casilla(tamano3,imagen3,0,0,0);
            String romp=producto.imagen;//ruta de la imagen
            String NOMBRE= producto.nombre;
            Label texto= new Label(NOMBRE);
            ImageView img = new ImageView(new File(romp).toURI().toString());
            img.setFitWidth(tamano3*2);
            img.setFitHeight(tamano3*2);
            StackPersonal stack = new StackPersonal(casilla,img);
            stack.setOnMouseClicked(event -> {
                System.out.println("Producto-Selecionado: "+producto.id_producto);
                producto_seleccionado=producto.id_producto;
                refresh();
            });
            VBox optical_csilla= new VBox(stack,texto);
            optical_csilla.setAlignment(Pos.CENTER);
            optical_csilla.getStyleClass().add("optical");
            cuadricula_productos.add(optical_csilla,columna3,fila3);
            columna3++;
            if (columna3>2){
                columna3 =0;
                fila3++;
            }

        }
    }
    }



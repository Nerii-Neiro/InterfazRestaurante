package com.example.demo2;

import com.example.demo2.modulos.*;
import com.example.demo2.modulos.CategoriasDAO.*;
import com.example.demo2.vistas.*;
import javafx.beans.value.ObservableListValue;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import org.kordamp.bootstrapfx.BootstrapFX;
import org.kordamp.bootstrapfx.scene.layout.Panel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.io.IOException;
import java.util.List;

import javafx.scene.layout.HBox;





public class HelloApplication extends Application {


    public static conexion con;
    private Label titl_s ;
    private HBox titulo_sistema ;

    private HBox contennedor_apartados;

    private Button boton_outro;
    private VBox contenedor;
    private Scene escena_principal;

    private double s_total= 0.0;
    private int cantidad_productos=0;


    private HBox contenedor_uno;
    private HBox contenedor_dos;
    private HBox contenedor_tres;
    private VBox contendor_cuatro;
    private VBox contenedor_cinco;

    private VBox contenedor_cate;

    private Label titulo_orden;
    private Button boton_orden;
    private Label titulo_reservacion;
    private Button boton_reservacion;
    private Label titulo_categorias;
    private GridPane cuadricula;
    private ArrayList<StackPane> categorias_pan;
    private ArrayList<Label> titulos;
    private Button boton_refresh;
    private Button boton_iniciar_ventana_elementos;
    private Label total;
    private Label cntidad_productos;
    private VentanaELementos vantana_elementos;
    private Label titulo_administrador;
    private Button boton_salir;
    private Button boton_login;

    private Button boton_ver_insumos;
    private Button boton_opcion_insumos;
    private VentanaInsumos ventana_insumos;


    private CategoriasDAO categrias_lista;

    @Override
    public void start(Stage stage) throws IOException {
        con = new conexion();
        init_menu(stage);

    }


    public void init_menu(Stage stage) {

        cuadricula = new GridPane();
        cuadricula.setHgap(35);
        cuadricula.setVgap(20);
        cuadricula.setAlignment(Pos.CENTER);
        titl_s= new Label(" Sistema DE Restaurante SDD Systems. VER-0.01");
        titulo_sistema =  new HBox(titl_s);


        contennedor_apartados = new HBox(15);
        for(int iterator =0 ;iterator<12;iterator++){
            Button bton = new Button("Example");
            ImageView img = new ImageView(getClass().getResource("/imagenes/categorias.png").toString());
            img.setFitWidth(30);
            img.setFitHeight(30);
            bton.setGraphic(img);
            bton.setContentDisplay(ContentDisplay.TOP);
            bton.getStyleClass().add("botones-alta");
            contennedor_apartados.getChildren().add(bton);
        }




        categrias_lista= new CategoriasDAO();
        List<Categoria> lista;
        lista = categrias_lista.Obtener_Categorias();

        int tamano = 72;
        String imagen="/imagenes/categorias.png";
        int columna = 0;
        int fila=0;

        for (Categoria cat: lista){
            Casilla casilla = new Casilla(tamano,imagen,0,0,0);
            String romp="/imagenes/categorias.png";//ruta de la imagen
            String NOMBRE= cat.nombre;
            Label texto= new Label(NOMBRE);
            ImageView img = new ImageView(getClass().getResource(romp).toString());
            img.setFitWidth(tamano);
            img.setFitHeight(tamano);
            StackPersonal stack = new StackPersonal(casilla,img);
            stack.setOnMouseClicked(event -> {
                System.out.println("press");
            });
            VBox optical_csilla= new VBox(stack,texto);
            optical_csilla.setAlignment(Pos.CENTER);
            optical_csilla.getStyleClass().add("optical");
            cuadricula.add(optical_csilla,columna,fila);
            columna++;
            if (columna>4){
                columna =0;
                fila++;
            }

        }


        //REVISAR LA CANTIDAD DE CATEORIAS QUE EXISTEN
        categorias_pan = new ArrayList<>();
        titulos= new ArrayList<>();
        //close






        titulo_orden = new Label(" ORDEN: ");
        titulo_orden.getStyleClass().add("display-fondo");
        titulo_reservacion= new Label(" RESERVACION: ");
        titulo_reservacion.getStyleClass().add("display-fondo");
        boton_orden= new Button("Nueva");
        ImageView img_orden = new ImageView(getClass().getResource("/imagenes/categorias.png").toString());
        img_orden.setFitWidth(30);
        img_orden.setFitHeight(30);
        boton_orden.setGraphic(img_orden);
        boton_orden.setContentDisplay(ContentDisplay.LEFT);
        boton_orden.getStyleClass().add("botones-baja");
        boton_reservacion = new Button("Nueva");
        ImageView img_reservacion = new ImageView(getClass().getResource("/imagenes/categorias.png").toString());
        img_reservacion.setFitWidth(30);
        img_reservacion.setFitHeight(30);
        boton_reservacion.setGraphic(img_reservacion);
        boton_reservacion.setContentDisplay(ContentDisplay.LEFT);
        boton_reservacion.getStyleClass().add("botones-baja");
        boton_orden.setOnAction(event->{

        });
        boton_reservacion.setOnAction(event->{

        });
        contenedor_dos = new HBox(titulo_orden,boton_orden,titulo_reservacion,boton_reservacion);
        contenedor_dos.setSpacing(20);
        contenedor_dos.setPadding(new Insets(25));
        contenedor_dos.setAlignment(Pos.BASELINE_CENTER);
        contenedor_dos.getStyleClass().add("display-fondo2");


        total = new Label("Total $: "+ s_total);
        cntidad_productos= new Label ("Cantidad P: "+cantidad_productos);
        boton_refresh= new Button("Refresh");
        ImageView img_refresh = new ImageView(getClass().getResource("/imagenes/categorias.png").toString());
        img_refresh.setFitWidth(30);
        img_refresh.setFitHeight(30);
        boton_refresh.setGraphic(img_refresh);
        boton_refresh.setContentDisplay(ContentDisplay.LEFT);
        boton_refresh.getStyleClass().add("botones-media");

        boton_iniciar_ventana_elementos = new Button ("Ver Mas");
        ImageView img_elementos = new ImageView(getClass().getResource("/imagenes/categorias.png").toString());
        img_elementos.setFitWidth(30);
        img_elementos.setFitHeight(30);
        boton_iniciar_ventana_elementos.setGraphic(img_elementos);
        boton_iniciar_ventana_elementos.setContentDisplay(ContentDisplay.LEFT);
        boton_iniciar_ventana_elementos.getStyleClass().add("botones-media");

        boton_refresh.setOnAction(event -> {

        });
        boton_iniciar_ventana_elementos.setOnAction(event->{

        });

        boton_outro= new Button("Other");
        ImageView img_outro = new ImageView(getClass().getResource("/imagenes/categorias.png").toString());
        img_outro.setFitWidth(30);
        img_outro.setFitHeight(30);
        boton_outro.setGraphic(img_outro);
        boton_outro.setContentDisplay(ContentDisplay.LEFT);
        boton_outro.getStyleClass().add("botones-media");
        boton_outro.setOnAction(event->{});
        contenedor_uno = new HBox(new Label(" Opciones Orden:  "),total,cntidad_productos,boton_refresh,boton_outro,boton_iniciar_ventana_elementos);
        contenedor_uno.setSpacing(20);
        contenedor_uno.setPadding(new Insets(25));
        contenedor_uno.getStyleClass().add("display-fondo2");

        titulo_categorias= new Label("CATEGORIAS:");
        contenedor_cate = new VBox(titulo_categorias,cuadricula,contenedor_uno);
        contenedor_cate.setSpacing(20);
        contenedor_cate.setPadding(new Insets(25));
        contenedor_cate.setAlignment(Pos.CENTER);
        contenedor_cate.getStyleClass().add("display-fondo2");

        titulo_administrador= new Label("ADMINISTRADOR:");
        boton_login= new Button("Login");
        boton_salir= new Button("Salir");
        boton_login.setOnAction(event-> {

        });
        boton_salir.setOnAction(event->{

        });

        boton_ver_insumos= new Button("Ver");
        boton_opcion_insumos= new Button("Opciones");
        boton_ver_insumos.setOnAction(event->{});
        boton_opcion_insumos.setOnAction(event->{});

        contenedor_cinco= new VBox(titulo_administrador,boton_login,boton_salir);
        contenedor_cinco.setPrefSize(600,400);
        contenedor_cinco.setSpacing(20);
        contenedor_cinco.setPadding(new Insets(25));
        contenedor_cinco.setAlignment(Pos.CENTER);
        contenedor_cinco.getStyleClass().add("display-fondo2");
        contendor_cuatro= new VBox(new Label("INSUMOS: "),boton_ver_insumos,boton_opcion_insumos);
        contendor_cuatro.setPrefSize(300,400);
        contendor_cuatro.setSpacing(20);
        contendor_cuatro.setPadding(new Insets(25));
        contendor_cuatro.setAlignment(Pos.CENTER);
        contendor_cuatro.getStyleClass().add("display-fondo2");
        contenedor_tres = new HBox(contenedor_cinco,contendor_cuatro);
        contenedor_tres.getStyleClass().add("display-fondo2");
        contenedor=new VBox(titulo_sistema,contennedor_apartados,contenedor_dos,contenedor_cate,contenedor_tres);
        contenedor.setSpacing(10);
        contenedor.setPadding(new Insets( 10));
        contenedor.setAlignment(Pos.TOP_CENTER);
        escena_principal = new Scene(contenedor,500,200);
        escena_principal.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        escena_principal.getStylesheets().add(getClass().getResource("/css/estilo_principal.css").toString());

        //CSS PARA LOS DIFERENTES PARA LOS ELEMENTOS VISUALES
        titulo_sistema.getStyleClass().add("display-fondo");
        //

        //CREAR PARTE DINFO DE LA VENTANA O STAGE
        stage.setTitle("SISTEMA DE RESTAURANTE");
        stage.setScene(escena_principal);
        stage.setMaximized(true);
        stage.show();

    }


    public static void main(String[] args) {
        launch();
    }
}








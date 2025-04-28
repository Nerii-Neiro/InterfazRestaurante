package com.example.demo2;

import com.example.demo2.Componenetes.BotonPersonal;
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

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.*;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

import javafx.scene.layout.HBox;


public class VentanaPrincipal extends Stage {

//    public static conexion con;
    private Label titl_s ;
    private HBox titulo_sistema ;

    private int TIPO_EMP=0;
    private String NOMBRE_EMPLOYE;


    private String curp;
    private String rfc;
    private double sueldo;
    private String puesto;
    private String telefono;
    private String nss;
    private String horario;
    private String fecha_ingreso;
    private String password;
    private String email;

    private String correo_s ;
    EmpleadosDAO emp= new EmpleadosDAO();
    private HBox contennedor_apartados;
    private Button boton_all_productos;
    private Button boton_outro;
    private VBox contenedor;
    private Scene escena_principal;

    private double s_total= 0.0;
    private int cantidad_productos=0;
//tesd
    private Label mesa_field;

    private VBox productos_interno;
    private VBox conteneor_productos;
    private ScrollPane scroll_3;


    private HBox contenedor_padre_m;
    private VBox contenedor_mesas;
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
    private GridPane cuadricula_mesas;
    private GridPane cuadricula_productos;
    private ArrayList<StackPane> categorias_pan;
    private ArrayList<Label> titulos;
    private Button boton_refresh;
    private Button boton_iniciar_ventana_elementos;
    private Label total;
    private Label cntidad_productos;
    private Label NOM_CLIENTE;
    private VentanaELementos vantana_elementos;
    private Label titulo_administrador;
    private Button boton_salir;
    private Button boton_login;
    private List<Producto> productos_lista;
    private Button boton_ver_insumos;
    private Button boton_opcion_insumos;
    private VentanaInsumos ventana_insumos;


    private int cantidad_orden_productos=0;

    private int categoria_seleccionada=0;
    private int mesa_seleccionada=0;
    private int producto_seleccionado=0;
    private CategoriasDAO categrias_lista;
    private MesasDAO cat_mesas;
    private ProductosDAO cat_productos;
    private ClientesDAO cat_clientes;

        //INFORMACION NECESARIA PARA PODER GENERAR LA ORDEN
    public int id_empleado_orden=1;//POR DEFECTO PERO ESTE DEBERIA CAMBIAR PARA EL QUE SE LOGUEE
    private int id_cliente_orden=0;
    private List<ProductosDAO> lista_productos_orden;
        //CLSO

    public VentanaPrincipal(int id_employesr){
        this.id_empleado_orden=id_employesr;
        checar_si_es_admin();
        cat_clientes= new ClientesDAO();

        lista_productos_orden= new ArrayList<>();

        boton_all_productos= new Button("Ver*Todo");
        ImageView img_boton_all = new ImageView(getClass().getResource("/imagenes/ver-mas.png").toString());
        img_boton_all.setFitWidth(35);
        img_boton_all.setFitHeight(35);
        boton_all_productos.setGraphic(img_boton_all);
        boton_all_productos.setContentDisplay(ContentDisplay.TOP);
        boton_all_productos.getStyleClass().add("botones-alta");
        boton_all_productos.setOnAction(event->{
            categoria_seleccionada=0;
            System.out.println("id:" + categoria_seleccionada);
            cargar_productos_categoria();
        });



        cuadricula = new GridPane();
        cuadricula.setHgap(35);
        cuadricula.setVgap(20);
        cuadricula.setAlignment(Pos.CENTER);
        titl_s= new Label(" Sistema DE Restaurante SDD Systems. VER-0.01");
        Label tit_empleado = new Label(" LOGIN--->          EmpleadoID: "+id_empleado_orden+" Nombre: "+NOMBRE_EMPLOYE);
        titulo_sistema =  new HBox(titl_s,tit_empleado);

        System.out.println(email);
        contennedor_apartados = new HBox(15);
        String alterm="";
        if(TIPO_EMP==1){
            String[] nombres = {"Clientes","Categorias","Empleados","Insumos","Mesas","Ordenes","Productos","Proveedores","Reservaciones","DetOrden","DetProd","R:M"};
            for(int iterator =0 ;iterator<12;iterator++){
                BotonPersonal bton = new BotonPersonal(nombres[iterator]);
                switch(iterator){
                    case 0:
                        alterm="/imagenes/clientes.png";
                        bton.setOnAction(event->{new vista_clientes(this);});
                        break;
                    case 1:
                        alterm="/imagenes/categorias.png";
                        bton.setOnAction(event->{new vista_categorias(this);});
                        break;
                    case 2:
                        alterm="/imagenes/empleados.png";
                        bton.setOnAction(event->{new vista_empleados(this);});
                        break;
                    case 3:
                        alterm="/imagenes/insumos.png";
                        bton.setOnAction(event->{new vista_insumos(this);});
                        break;
                    case 4:
                        alterm="/imagenes/juego-de-mesa.png";
                        bton.setOnAction(event->{new vista_mesas(this);});
                        break;
                    case 5:
                        alterm="/imagenes/ordenes.png";
                        bton.setOnAction(event->{new vista_ordenes(this);});
                        break;
                    case 6:
                        alterm="/imagenes/productos.png";
                        bton.setOnAction(event->{new vista_productos(this);});
                        break;
                    case 7:
                        alterm="/imagenes/proveedor.png";
                        bton.setOnAction(event->{new vista_proveedores(this);});
                        break;
                    case 8:
                        alterm="/imagenes/reservacion.png";
                        bton.setOnAction(event->{new vista_reservaciones(this);});
                        break;
                    case 9:
                        alterm="/imagenes/detalleorden.png";
                        bton.setOnAction(event->{new vista_detorden(this);});
                        break;
                    case 10:
                        alterm="/imagenes/detalleproducto.png";
                        bton.setOnAction(event->{new vista_detproducto(this);});
                        break;
                    case 11:
                        alterm="/imagenes/reservacionmesa.png";
                        bton.setOnAction(event->{new vista_reservacionmesa(this);});
                        break;

                }
                ImageView img = new ImageView(getClass().getResource(alterm).toString());
                img.setFitWidth(30);
                img.setFitHeight(30);
                bton.setGraphic(img);
                bton.setContentDisplay(ContentDisplay.TOP);
                bton.getStyleClass().add("botones-alta");
                contennedor_apartados.getChildren().add(bton);
            }
            Button boton_graficas = new Button("Graficas");
            boton_graficas.setOnAction(event->{new Graficas();});
            boton_graficas.getStyleClass().add("botones-alta");
            contennedor_apartados.getChildren().add(boton_graficas);
        }

        cat_productos= new ProductosDAO();
        productos_lista = cat_productos.Obtener_Productos();


        categrias_lista= new CategoriasDAO();
        List<Categoria> lista;
        lista = categrias_lista.Obtener_Categorias();

        cuadricula.add(boton_all_productos,0,0);

        int tamano = 72;
        String imagen="imagenes/categorias.png";
        int columna = 1;
        int fila=0;

        for (Categoria cat: lista){
            Casilla casilla = new Casilla(tamano,imagen,0,0,0);
            String romp=cat.imagen;//ruta de la imagen
            String NOMBRE= cat.nombre;
            Label texto= new Label(NOMBRE);
            ImageView img = new ImageView(new File(romp).toURI().toString());
            img.setFitWidth(tamano*1.4);
            img.setFitHeight(tamano*1.4);
            StackPersonal stack = new StackPersonal(casilla,img);
            stack.setOnMouseClicked(event -> {
                categoria_seleccionada=cat.id_categoria;
                System.out.println("id:" + categoria_seleccionada);
                cargar_productos_categoria();
            });
            VBox optical_csilla= new VBox(stack,texto);
            optical_csilla.setAlignment(Pos.CENTER);
            optical_csilla.getStyleClass().add("optical");
            cuadricula.add(optical_csilla,columna,fila);
            columna++;
            if (columna>5){
                columna =0;
                fila++;
            }

        }


        //REVISAR LA CANTIDAD DE CATEORIAS QUE EXISTEN
        categorias_pan = new ArrayList<>();
        titulos= new ArrayList<>();
        //close




        Label titulo_salir_op = new Label("Otras Opciones:");
        Button boton_de_salir = new Button("Salir Sistema");
        boton_de_salir.getStyleClass().add("botones-exit");
        boton_de_salir.setOnAction(event -> {
            this.close();
            System.exit(0);
        });


        titulo_orden = new Label(" ORDEN: ");
        titulo_orden.getStyleClass().add("display-fondo");
        titulo_reservacion= new Label(" RESERVACION: ");
        titulo_reservacion.getStyleClass().add("display-fondo");
        boton_orden= new Button("Generar");
        ImageView img_orden = new ImageView(getClass().getResource("/imagenes/agregar.png").toString());
        img_orden.setFitWidth(30);
        img_orden.setFitHeight(30);
        boton_orden.setGraphic(img_orden);
        boton_orden.setContentDisplay(ContentDisplay.LEFT);
        boton_orden.getStyleClass().add("botones-baja");
        boton_reservacion = new Button("Generar");
        ImageView img_reservacion = new ImageView(getClass().getResource("/imagenes/agregar.png").toString());
        img_reservacion.setFitWidth(30);
        img_reservacion.setFitHeight(30);
        boton_reservacion.setGraphic(img_reservacion);
        boton_reservacion.setContentDisplay(ContentDisplay.LEFT);
        boton_reservacion.getStyleClass().add("botones-baja");
        boton_orden.setOnAction(event->{
            crear_orden_mesa();
        });
        boton_reservacion.setOnAction(event->{
            crear_reservacion_mesa();
        });
        contenedor_dos = new HBox(titulo_orden,boton_orden,titulo_reservacion,boton_reservacion,titulo_salir_op,boton_de_salir);
        contenedor_dos.setSpacing(20);
        contenedor_dos.setPadding(new Insets(25));
        contenedor_dos.setAlignment(Pos.BASELINE_CENTER);
        contenedor_dos.getStyleClass().add("display-fondo2");

        if(mesa_seleccionada==0){
            mesa_field = new Label("Mesa: "+ "NA");
        }
        else{
            mesa_field = new Label("Mesa: "+ mesa_seleccionada);
        }
        mesa_field.setStyle("-fx-font-size: 15px; -fx-background-color: #2f8000;");
        total = new Label("Total $: "+ s_total);
        total.setStyle("-fx-font-size: 15px; -fx-background-color: #808000;");
        cntidad_productos= new Label ("Cantidad P: "+cantidad_productos);
        cntidad_productos.setStyle("-fx-font-size: 15px; -fx-background-color: #af0a0a;");
        NOM_CLIENTE= new Label ("Cliente: "+ "NA");
        NOM_CLIENTE.setStyle("-fx-font-size: 15px; -fx-background-color: #af0a0a;");
        boton_refresh= new Button("Refresh");
        ImageView img_refresh = new ImageView(getClass().getResource("/imagenes/actualizar.png").toString());
        img_refresh.setFitWidth(30);
        img_refresh.setFitHeight(30);
        boton_refresh.setGraphic(img_refresh);
        boton_refresh.setContentDisplay(ContentDisplay.LEFT);
        boton_refresh.getStyleClass().add("botones-oden");

        boton_iniciar_ventana_elementos = new Button ("Ver+");
        ImageView img_elementos = new ImageView(getClass().getResource("/imagenes/ver-mas.png").toString());
        img_elementos.setFitWidth(30);
        img_elementos.setFitHeight(30);
        boton_iniciar_ventana_elementos.setGraphic(img_elementos);
        boton_iniciar_ventana_elementos.setContentDisplay(ContentDisplay.LEFT);
        boton_iniciar_ventana_elementos.getStyleClass().add("botones-oden");

        boton_refresh.setOnAction(event -> {
            id_cliente_orden=0;
            NOM_CLIENTE.setText("");
            NOM_CLIENTE.setText("Cliente: "+"NA");
            lista_productos_orden.clear();
            s_total=0;
            cantidad_productos=0;
            mesa_seleccionada=0;
            total.setText("");
            cntidad_productos.setText("");
            mesa_field.setText("");
            total.setText("Total $: "+ s_total);
            cntidad_productos.setText("Cantidad P: "+cantidad_productos);
            mesa_field.setText("Mesa: "+"NA");
            new InformeGeneral("Datos Reseteados con Exito!!");
        });
        boton_iniciar_ventana_elementos.setOnAction(event->{
            new VentanaELementos(id_empleado_orden,NOMBRE_EMPLOYE);
        });

        boton_outro= new Button("Otro");
        ImageView img_outro = new ImageView(getClass().getResource("/imagenes/agregar-producto.png").toString());
        img_outro.setFitWidth(30);
        img_outro.setFitHeight(30);
        boton_outro.setGraphic(img_outro);
        boton_outro.setContentDisplay(ContentDisplay.LEFT);
        boton_outro.getStyleClass().add("botones-oden");
        boton_outro.setOnAction(event->{

        });
        contenedor_uno = new HBox(new Label("Ord:"),total,cntidad_productos,mesa_field,NOM_CLIENTE,boton_refresh,boton_outro,boton_iniciar_ventana_elementos);
        contenedor_uno.setSpacing(20);
        contenedor_uno.setPadding(new Insets(25));
        contenedor_uno.getStyleClass().add("display-fondo2");
        titulo_categorias= new Label("CATEGORIAS:");
        VBox categorias_interno = new VBox(titulo_categorias,cuadricula);
        categorias_interno.setSpacing(20);
        categorias_interno.setPadding(new Insets(25));
        categorias_interno.setAlignment(Pos.CENTER);
        categorias_interno.getStyleClass().add("fondo_interf");
        ScrollPane scroll_2 = new ScrollPane(categorias_interno);
        //scroll_2.setPrefHeight(400);
        scroll_2.setFitToWidth(true);
        scroll_2.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);


        contenedor_cate = new VBox(scroll_2,contenedor_uno);
        contenedor_cate.setSpacing(20);
        contenedor_cate.setPadding(new Insets(25));
        contenedor_cate.setAlignment(Pos.CENTER);
        contenedor_cate.setPrefSize(1100,600);
        contenedor_cate.getStyleClass().add("display-fondo2");

        titulo_administrador= new Label("ADMINISTRADOR:");
        boton_login= new Button("Informacion Empleado");
        boton_salir= new Button("Cerrar Session");
        boton_login.setOnAction(event-> {
//            private String curp;
//            private String rfc;
//            private double sueldo;
//            private String puesto;
//            private String telefono;
//            private String nss;
//            private String horario;
//            private String fecha_ingreso;
//            private String password;
//            private String email;
            new InformeGeneral("EmpleadoID: "+id_empleado_orden +"\n"+
                                        "NOMBRE: "+NOMBRE_EMPLOYE +"\n"+
                                        "RFC: "+rfc +"\n"+
                                        "SUELDO: "+sueldo +"\n"+
                                        "PUESTO: "+puesto +"\n"+
                                        "TELEFONO: "+telefono +"\n"+
                                        "NSS: "+nss +"\n"+
                                        "HORARIO: "+horario +"\n"+
                                        "FECHA INGRESO: "+fecha_ingreso +"\n"+
                                        "PASSWORD: "+password +"\n"+
                                        "CORREO: "+email +"\n"+
                                        "CURP: "+curp +"\n"
                    );
        });
        boton_salir.setOnAction(event->{
            this.close();
            new  VentanaLogin();
        });
    boton_login.getStyleClass().add("botones-exit");
    boton_salir.getStyleClass().add("botones-exit");
        boton_ver_insumos= new Button("Ver");
        boton_opcion_insumos= new Button("Añadir Insumo");
        boton_ver_insumos.setOnAction(event->{
            new GestionInsumos();
        });
        boton_opcion_insumos.setOnAction(event->{
             insumos ventana_insumos =  new insumos(new TableView<>(),null,"Añadir Insumo");
        });

        boton_ver_insumos.getStyleClass().add("botones-exit");
        boton_opcion_insumos.getStyleClass().add("botones-exit");

        contenedor_cinco= new VBox(titulo_administrador,boton_login,boton_salir);
        contenedor_cinco.setPrefSize(300,400);
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

        //CONTANEDOR DE LOS PRODUCTOS
        //espacio para las mesas
        cuadricula_productos = new GridPane();
        cuadricula_productos.setHgap(35);
        cuadricula_productos.setVgap(20);
        cuadricula_productos.setAlignment(Pos.CENTER);


        int tamano3 = 40;
        String imagen3="/imagenes/productos.png";
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
            });
            VBox optical_csilla= new VBox(stack,texto);
            optical_csilla.setAlignment(Pos.CENTER);
            optical_csilla.getStyleClass().add("optical");
            cuadricula_productos.add(optical_csilla,columna3,fila3);
            columna3++;
            if (columna3>3){
                columna3 =0;
                fila3++;
            }

        }
        //cloes

        productos_interno = new VBox(new Label("Productos:"),cuadricula_productos);
        productos_interno.setSpacing(20);
        productos_interno.setPadding(new Insets(25));
        productos_interno.setAlignment(Pos.CENTER);
        productos_interno.getStyleClass().add("fondo_interf");
        scroll_3 = new ScrollPane(productos_interno);
        //scroll_3.setPrefHeight(400);
        scroll_3.setFitToWidth(true);
        scroll_3.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        conteneor_productos = new VBox(scroll_3);
        conteneor_productos.setSpacing(10);
        conteneor_productos.setPadding(new Insets( 10));
        conteneor_productos.setAlignment(Pos.TOP_CENTER);
        conteneor_productos.setPrefSize(500,400);
        conteneor_productos.getStyleClass().add("display-fondo2");


        //CONTENEDOR DE LOS BOTONES
        Button bton_mas = new Button();
        ImageView img_b_mas = new ImageView(getClass().getResource("/imagenes/agregar.png").toString());
        img_b_mas.setFitWidth(30);
        img_b_mas.setFitHeight(30);
        bton_mas.setGraphic(img_b_mas);
        bton_mas.setContentDisplay(ContentDisplay.TOP);
        bton_mas.getStyleClass().add("botones-alta2");
        bton_mas.setStyle("-fx-border-radius: 0px;");
        bton_mas.setOnAction(event->{
            Producto temp_prodx= cat_productos.producto_crear(producto_seleccionado);
            if(temp_prodx!=null){
                ProductosDAO producto= new ProductosDAO();
                producto.crear_producto(temp_prodx.id_producto,temp_prodx.nombre,temp_prodx.precio,temp_prodx.descripcion,temp_prodx.id_categoria,temp_prodx.imagen);
                cargar_producto_en_orden(producto);
            }
            else{
                System.out.println("No se pudo Cargar Producto");
            }
        });

        Button bton_menos = new Button();
        ImageView img_b_menos = new ImageView(getClass().getResource("/imagenes/boton-menos.png").toString());
        img_b_menos.setFitWidth(30);
        img_b_menos.setFitHeight(30);
        bton_menos.setGraphic(img_b_menos);
        bton_menos.setContentDisplay(ContentDisplay.TOP);
        bton_menos.getStyleClass().add("botones-alta2");
        bton_menos.setStyle("-fx-border-radius: 0px;");
        bton_menos.setOnAction(event->{
            quitar_producto_en_orden();
        });

        Button bton_ds = new Button();
        ImageView img_b_ds = new ImageView(getClass().getResource("/imagenes/boton-eliminar.png").toString());
        img_b_ds.setFitWidth(30);
        img_b_ds.setFitHeight(30);
        bton_ds.setGraphic(img_b_ds);
        bton_ds.setContentDisplay(ContentDisplay.TOP);
        bton_ds.getStyleClass().add("botones-alta2");
        bton_ds.setStyle("-fx-border-radius: 0px;");
        bton_ds.setOnAction(event->{
            limpiar_producto_en_orden();
        });


        HBox contenedor_botones_extras = new HBox(bton_mas,bton_menos,bton_ds);
        contenedor_botones_extras.setSpacing(10);
        contenedor_botones_extras.setPadding(new Insets( 10));
        contenedor_botones_extras.setAlignment(Pos.TOP_CENTER);
        //contenedor_botones_extras.setPrefSize(500,400);
        contenedor_botones_extras.getStyleClass().add("display-fondo2");

        Label ichi = new Label("Opciones Productos");
        ichi.setStyle("-fx-font-size: 14px; -fx-background-color: rgba(172,171,171,0.71);");
        VBox botones_extra_internos = new VBox(ichi,contenedor_botones_extras);
        botones_extra_internos.setSpacing(20);
        botones_extra_internos.setPadding(new Insets(25));
        botones_extra_internos.setAlignment(Pos.CENTER);
        botones_extra_internos.getStyleClass().add("fondo_interf");


        //CLOSE


        contenedor_tres = new HBox(contenedor_cinco,contendor_cuatro,conteneor_productos,botones_extra_internos);
        contenedor_tres.getStyleClass().add("display-fondo2");


        //espacio para las mesas
        cuadricula_mesas = new GridPane();
        cuadricula_mesas.setHgap(35);
        cuadricula_mesas.setVgap(20);
        cuadricula_mesas.setAlignment(Pos.CENTER);
        cat_mesas= new MesasDAO();
        List<Mesa> mesas_lista;
        mesas_lista = cat_mesas.Obtener_Mesas();

        int tamano2 = 40;
        String imagen2="/imagenes/categorias.png";
        int columna2 = 0;
        int fila2=0;

        for (Mesa mesa: mesas_lista){
            Casilla casilla = new Casilla(tamano2,imagen2,0,0,0);
            String romp="/imagenes/juego-de-mesa.png";//ruta de la imagen
            int NOMBRE= mesa.id_mesa;
            Label texto= new Label("Mesa: "+NOMBRE);
            ImageView img = new ImageView(getClass().getResource(romp).toString());
            img.setFitWidth(tamano2*2);
            img.setFitHeight(tamano2*2);
            StackPersonal stack = new StackPersonal(casilla,img);
            stack.setOnMouseClicked(event -> {
                asignar_una_mesa(mesa);
            });
            VBox optical_csilla= new VBox(stack,texto);
            optical_csilla.setAlignment(Pos.CENTER);
            optical_csilla.getStyleClass().add("optical2");
            if (columna2>=2){
                columna2 =0;
                fila2++;
            }
            cuadricula_mesas.add(optical_csilla,columna2,fila2);
            columna2++;
        }
        //cloes


            VBox mesas_interno = new VBox(new Label("Mesas:"),cuadricula_mesas);
            mesas_interno.setSpacing(20);
            mesas_interno.setPadding(new Insets(25));
            mesas_interno.setAlignment(Pos.CENTER);
            mesas_interno.getStyleClass().add("fondo_interf");
        ScrollPane scroll_1 = new ScrollPane(mesas_interno);
        //scroll_1.setPrefHeight(400);
        scroll_1.setFitToWidth(true);
        scroll_1.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        contenedor_mesas = new VBox(scroll_1);
        contenedor_mesas.setSpacing(10);
        contenedor_mesas.setPadding(new Insets( 10));
        contenedor_mesas.setAlignment(Pos.TOP_CENTER);
        contenedor_mesas.setPrefSize(500,400);
        contenedor_mesas.getStyleClass().add("display-fondo2");

        contenedor_padre_m= new HBox(contenedor_mesas,contenedor_cate);
        contenedor_padre_m.setSpacing(10);
        contenedor_padre_m.setPadding(new Insets( 10));
        contenedor_padre_m.setAlignment(Pos.TOP_CENTER);
        contenedor=new VBox(titulo_sistema,contennedor_apartados,contenedor_dos,contenedor_padre_m,contenedor_tres);
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
        this.setTitle("SISTEMA DE RESTAURANTE");
        this.setScene(escena_principal);
        this.setMaximized(true);
        this.show();
    }

    public void crear_reservacion_mesa(){
        if(mesa_seleccionada!=0){
            reservaciones ventana_reservaciones = new reservaciones(new TableView<>(),null,"Nueva Reservacion:");
            if (ventana_reservaciones.no_poder){
                System.out.println("PROCSSANDO....");
                ReservacionMesaDAO reserva = new ReservacionMesaDAO();
                ReservacionesDAO red = new ReservacionesDAO();
                Reservacion sers = red.reservacion_crear();
                reserva.crear_resrvacion_mesa(0,sers.getId_reservacion(),mesa_seleccionada);
                reserva.INSERT();
                String clave = UUID.randomUUID().toString().replace("-", "").substring(0,8);
                new InformeGeneral("SE CREO LA RESERVCION CON CLAVE: "+clave);
            }
            else{
                new InformeGeneral("NO CREO LA RESERVCION");
                System.out.println("NO SE HIZO NADA");
            }
        }
        else{
            new InformeGeneral("No se selecciono una mesa para Reservar!!");
        }
    }


    public void crear_orden_mesa(){
        //    //INFORMACION NECESARIA PARA PODER GENERAR LA ORDEN
//    private int id_empleado_orden=1;//POR DEFECTO PERO ESTE DEBERIA CAMBIAR PARA EL QUE SE LOGUEE
//    private int id_cliente_orden=0;
//    private String notas_orden="";
//    private String descripcion_orden="";
//    private Date fecha_de_orden;
//    private Time hora_orden;
//    private List<ProductosDAO> lista_productos_orden;
//    //CLSO

        if(id_empleado_orden!=0 && id_cliente_orden!=0 && mesa_seleccionada!=0 && !lista_productos_orden.isEmpty() && cantidad_productos !=0){
            LocalDate fecha_actual = LocalDate.now();
            LocalTime hora_actual = LocalTime.now();
            DateTimeFormatter formatofecha = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            DateTimeFormatter formatohora = DateTimeFormatter.ofPattern("HH:mm:ss");
            String fecha= fecha_actual.format(formatofecha);
            String hora =hora_actual.format(formatohora);
            VentanaNotas ven_notas = new VentanaNotas();
            String descripcion = ven_notas.ret_DESC();
            String notas = ven_notas.ret_NOTAS();

            OrdenesDAO ORDEN = new OrdenesDAO();
            ORDEN.crear_orden(0,fecha,hora,descripcion,notas,id_cliente_orden,mesa_seleccionada,id_empleado_orden,s_total);
            ORDEN.INSERT();
            System.out.println("ORDEN CREADA");




            Orden orden = ORDEN.orden_crear();
            for(ProductosDAO producto : lista_productos_orden){
                DetOrdenDAO detalle_ = new DetOrdenDAO();
                detalle_.crear_det_orden(0,orden.getId_orden(),producto.getId_producto(),1,producto.getPrecio());
                detalle_.INSERT();
            }

            System.out.println("DET ORDEN INSERTADA");

            String id_em = String.valueOf(id_empleado_orden);
            String id_mes = String.valueOf(mesa_seleccionada);
            String nomb_c = NOM_CLIENTE.getText();



            new InformeOrden(1);

            Tickets ticket = new Tickets(this,nomb_c,correo_s,id_em,id_mes,lista_productos_orden,s_total,fecha,hora);
            ticket.generarTicketTXT();

            lista_productos_orden.clear();
            s_total=0;
            cantidad_productos=0;
            total.setText("");
            cntidad_productos.setText("");
            total.setText("Total $: "+ s_total);
            cntidad_productos.setText("Cantidad P: "+cantidad_productos);
            new InformeGeneral("El ticket se genero Correctamente");
        }
        else{
            new InformeOrden(0);
        }
    }


    public void asignar_una_mesa(Mesa mesa){
        if(!mesa.get_ocupada()){
            cliente ventana_cliente = new cliente(new TableView<>(),null,"Nuevo Cliente:");
            if (ventana_cliente.no_poder){
                System.out.println("PROCSSANDO..");
                Cliente cli = cat_clientes.cliente_crear();
                correo_s=cli.email;
                mesa.set_cliente(cli.id_cliente);
                mesa.set_name_client(cli.nombre);
                mesa.set_ocupada(true);
                id_cliente_orden= cli.id_cliente;
                NOM_CLIENTE.setText("");
                NOM_CLIENTE.setText("Cliente: "+cli.nombre);
                System.out.println("Cliente id: "+mesa.get_cliente_asignado());
                System.out.println("Mesa-seleccionada: " + mesa.id_mesa);
                mesa_seleccionada=mesa.id_mesa;
                mesa_field.setText("");
                mesa_field.setText("Mesa: "+mesa_seleccionada);
                new InformeGeneral("Mesa Asignada Correctamente!!");

            }
            else{
                System.out.println("NO SE HIZO NADA");
                new InformeGeneral("No se pudo asignar mesa Inserte datos Antes \n e intente nuevamente");
            }
        }
        else{
            VentanaLiberarMesa ventana = new VentanaLiberarMesa();
            if(!ventana.opcion){
                System.out.println("Mesa-seleccionada: " + mesa.id_mesa);
                mesa_seleccionada=mesa.id_mesa;
                id_cliente_orden=mesa.get_cliente_asignado();
                NOM_CLIENTE.setText("");
                NOM_CLIENTE.setText("Cliente: "+mesa.get_cliente_asignado_nombre());
                mesa_field.setText("");
                mesa_field.setText("Mesa: "+mesa_seleccionada);
                new InformeGeneral("Mesa Selecionada para Acciones!!");
            }
            else{
                mesa.set_ocupada(false);
                mesa.set_cliente(0);
                System.out.println("LIBERADA!!");
                mesa_seleccionada=0;
                id_cliente_orden=0;
                NOM_CLIENTE.setText("");
                NOM_CLIENTE.setText("Cliente: "+"NA");
                mesa_field.setText("");
                mesa_field.setText("Mesa: "+"NA");
                new InformeGeneral("Mesa Liberada!!");
            }
        }
    }




    public void refresh_labels_orden(){
        total.setText("");
        cntidad_productos.setText("");
        total.setText("Total $: "+ s_total);
        cntidad_productos.setText("Cantidad P: "+cantidad_productos);
    }

    public void cargar_producto_en_orden(ProductosDAO producto){
        System.out.println(producto.getNombre());
        lista_productos_orden.add(producto);
        cantidad_productos+=1;
        s_total+=producto.getPrecio();
        System.out.println("Producto Agregado"+cantidad_productos);
        refresh_labels_orden();
    }

    public void quitar_producto_en_orden(){
        if(!lista_productos_orden.isEmpty()){
            ProductosDAO temp_proc = lista_productos_orden.getLast();
            s_total-=temp_proc.getPrecio();
            lista_productos_orden.removeLast();
            System.out.println("Producto quitado");
            cantidad_productos-=1;
            refresh_labels_orden();
        }
    }

    public void limpiar_producto_en_orden(){
        lista_productos_orden.clear();
        cantidad_productos=0;
        s_total=0;
        System.out.println("Lista Limpia");
        refresh_labels_orden();
        new InformeGeneral("Lista de Productos Reseteada con Exito!!");
    }

    public void checar_si_es_admin(){
        Empleado emp_t = emp.empleado_crear(id_empleado_orden);
        if ((emp_t.getEmail().equals("admin@gmail.com"))){
            TIPO_EMP=1;
            password=emp_t.getPassword();
        }
        else{
            TIPO_EMP=0;
            password="*******";
        }
        NOMBRE_EMPLOYE=emp_t.getNombre();

        curp=emp_t.getCurp();
        rfc=emp_t.getRfc();
        sueldo=emp_t.getSueldo();
        puesto=emp_t.getPuesto();
        telefono=emp_t.getTelefono();
        nss=emp_t.getNss();
        horario=emp_t.getHorario();
        fecha_ingreso=emp_t.getFecha_ingreso();
        email=emp_t.getEmail();

    }






    public void cargar_productos_categoria(){
        cuadricula_productos.getChildren().clear();
        productos_lista.clear();

        if(categoria_seleccionada!=0){
            productos_lista = cat_productos.Obtener_Productos_por_id(categoria_seleccionada);
        }
        else{
            productos_lista = cat_productos.Obtener_Productos();
        }

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

package com.example.demo2.vistas;

import com.example.demo2.Componenetes.ButtonCell;
import com.example.demo2.Componenetes.ButtonCell_reservaciones;
import com.example.demo2.VentanaPrincipal;
import com.example.demo2.modulos.ClientesDAO;
import com.example.demo2.modulos.ReservacionesDAO;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

public class vista_reservaciones extends Stage{


    private ToolBar menu;
    private TableView<ReservacionesDAO> table_reservaciones;
    private VBox contenedor_padre;
    private Scene escena;
    private Button agregar ;
    private Button salir;
    private Button solo_salir;
    private int id_employ;
    private VentanaPrincipal ventana;
    public vista_reservaciones(VentanaPrincipal ventana){
        id_employ=ventana.id_empleado_orden;
        CREAR_UI();
        this.ventana=ventana;
        this.setTitle("LISTA DE RESERVACIONES");
        this.setScene(escena);
        this.show();
    }

    private void CREAR_UI(){
        this.salir=new Button("SaliryActualizar");

        this.solo_salir=new Button("Solo Salir");
        this.solo_salir.setOnAction(event->{
            this.close();
        });
        this.solo_salir.getStyleClass().add("botones-rojos");

        this.table_reservaciones=new TableView<>();
        this.agregar=  new Button();
        ImageView img = new ImageView(getClass().getResource("/imagenes/n_usuario.png").toString());
        img.setFitWidth(50);
        img.setFitHeight(50);
        this.agregar.setGraphic(img);
        this.menu= new ToolBar();

        Button categorias= new Button("Categorias");
        Button clientes= new Button("Clientes");
        Button empleados= new Button("Empleados");
        Button insumos= new Button("Insumos");
        Button mesas= new Button("Mesas");
        Button ordenes= new Button("Ordenes");
        Button productos= new Button("Productos");
        Button proveedores= new Button("Proveedores");
        Button reservaciones= new Button("Reservaciones");

        Button detorden= new Button("DetOrden");
        Button detproducto= new Button("DetProducto");
        Button reservacionmesa= new Button("Reservacion-Mesa");


        categorias.setOnAction(event->{this.close();new vista_categorias(this.ventana);});
        clientes.setOnAction(event->{this.close();new vista_clientes(this.ventana);});
        empleados.setOnAction(event->{this.close();new vista_empleados(this.ventana);});
        insumos.setOnAction(event->{this.close();new vista_insumos(this.ventana);});
        mesas.setOnAction(event->{this.close();new vista_mesas(this.ventana);});
        ordenes.setOnAction(event->{this.close();new vista_ordenes(this.ventana);});
        productos.setOnAction(event->{this.close();new vista_productos(this.ventana);});
        proveedores.setOnAction(event->{this.close();new vista_proveedores(this.ventana);});
        reservaciones.setOnAction(event->{this.close();new vista_reservaciones(this.ventana);});
        detorden.setOnAction(event->{this.close();new vista_detorden(this.ventana);});
        detproducto.setOnAction(event->{this.close();new vista_detproducto(this.ventana);});
        reservacionmesa.setOnAction(event->{this.close();new vista_reservacionmesa(this.ventana);});

        menu.getItems().addAll(categorias,clientes,empleados,insumos,mesas,ordenes,productos,proveedores,reservaciones,detorden,detproducto,reservacionmesa);


        this.salir.setOnAction(event->{
            this.close();
            if(ventana!=null){
                ventana.close();
            }
            new VentanaPrincipal(id_employ);
        });
        this.agregar.setOnAction(event->{
            new reservaciones(table_reservaciones,null,"Nueva Reservacion:");
        });
        create_table();
        this.contenedor_padre= new VBox(menu,agregar,salir,solo_salir,table_reservaciones);
        this.contenedor_padre.setSpacing(30);
        contenedor_padre.setPadding(new Insets(20));
        this.contenedor_padre.setAlignment(Pos.CENTER);
        this.escena=new Scene(contenedor_padre,1350,600);
        this.escena.getStylesheets().add(getClass().getResource("/css/estilo_restaurante.css").toString());
        this.agregar.getStyleClass().add("botones-negros");
        this.salir.getStyleClass().add("botones-rojos");
    }

    private void create_table(){
        ReservacionesDAO objeto_reservaciones = new ReservacionesDAO();
        TableColumn<ReservacionesDAO,Integer> table_id = new TableColumn<>("ID_Reservacion");
        table_id.setCellValueFactory(new PropertyValueFactory<>("id_reservacion"));
        TableColumn<ReservacionesDAO,String> table_fecha = new TableColumn<>("Fecha");
        table_fecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        TableColumn<ReservacionesDAO,String> table_hora = new TableColumn<>("Hora");
        table_hora.setCellValueFactory(new PropertyValueFactory<>("hora"));
        TableColumn<ReservacionesDAO,String> table_observaciones = new TableColumn<>("Observaciones");
        table_observaciones.setCellValueFactory(new PropertyValueFactory<>("observaciones"));

        table_id.setPrefWidth(250);
        table_fecha.setPrefWidth(250);
        table_hora.setPrefWidth(250);
        table_observaciones.setPrefWidth(250);

        TableColumn<ReservacionesDAO,String> editar_ = new TableColumn<>("Editar");
        editar_.setCellFactory(new Callback<TableColumn<ReservacionesDAO, String>, TableCell<ReservacionesDAO, String>>() {
            @Override
            public TableCell<ReservacionesDAO, String> call(TableColumn<ReservacionesDAO, String> clientesDAOStringTableColumn) {
                return new ButtonCell_reservaciones("Editar");
            }
        });
        TableColumn<ReservacionesDAO,String> eliminar_ = new TableColumn<>("Eliminar");
        eliminar_.setCellFactory(new Callback<TableColumn<ReservacionesDAO, String>, TableCell<ReservacionesDAO, String>>() {
            @Override
            public TableCell<ReservacionesDAO, String> call(TableColumn<ReservacionesDAO, String> clientesDAOStringTableColumn) {
                return new ButtonCell_reservaciones("Eliminar");
            }
        });

        this.table_reservaciones.getColumns().addAll(table_id,table_fecha,table_hora,table_observaciones,editar_,eliminar_);
        this.table_reservaciones.setItems(objeto_reservaciones.SELECT());
    }

}

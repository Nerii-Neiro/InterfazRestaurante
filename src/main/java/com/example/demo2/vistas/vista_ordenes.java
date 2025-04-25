package com.example.demo2.vistas;

import com.example.demo2.Componenetes.ButtonCell;
import com.example.demo2.Componenetes.ButtonCell_ordenes;
import com.example.demo2.VentanaPrincipal;
import com.example.demo2.modulos.ClientesDAO;
import com.example.demo2.modulos.OrdenesDAO;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

public class vista_ordenes extends Stage{


    private ToolBar menu;
    private TableView<OrdenesDAO> table_ordenes;
    private VBox contenedor_padre;
    private Scene escena;
    private Button agregar ;
    private Button salir;

    public vista_ordenes(){
        CREAR_UI();
        this.setTitle("LISTA DE ORDENES");
        this.setScene(escena);
        this.show();
    }

    private void CREAR_UI(){
        this.salir=new Button("Salir");
        this.table_ordenes=new TableView<>();
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


        categorias.setOnAction(event->{this.close();new vista_categorias();});
        clientes.setOnAction(event->{this.close();new vista_clientes();});
        empleados.setOnAction(event->{this.close();new vista_empleados();});
        insumos.setOnAction(event->{this.close();new vista_insumos();});
        mesas.setOnAction(event->{this.close();new vista_mesas();});
        ordenes.setOnAction(event->{this.close();new vista_ordenes();});
        productos.setOnAction(event->{this.close();new vista_productos();});
        proveedores.setOnAction(event->{this.close();new vista_proveedores();});
        reservaciones.setOnAction(event->{this.close();new vista_reservaciones();});
        detorden.setOnAction(event->{this.close();new vista_detorden();});
        detproducto.setOnAction(event->{this.close();new vista_detproducto();});
        reservacionmesa.setOnAction(event->{this.close();new vista_reservacionmesa();});

        menu.getItems().addAll(categorias,clientes,empleados,insumos,mesas,ordenes,productos,proveedores,reservaciones,detorden,detproducto,reservacionmesa);


        this.salir.setOnAction(event->{
            this.close();
            new VentanaPrincipal();
        });
        this.agregar.setOnAction(event->{
            new ordenes(table_ordenes,null,"Nueva Orden:");
        });
        create_table();
        this.contenedor_padre= new VBox(menu,agregar,salir,table_ordenes);
        this.contenedor_padre.setSpacing(30);
        contenedor_padre.setPadding(new Insets(20));
        this.contenedor_padre.setAlignment(Pos.CENTER);
        this.escena=new Scene(contenedor_padre,1350,600);
        this.escena.getStylesheets().add(getClass().getResource("/css/estilo_restaurante.css").toString());
        this.agregar.getStyleClass().add("botones-negros");
        this.salir.getStyleClass().add("botones-rojos");
    }

    private void create_table(){
        OrdenesDAO objeto_ordenes = new OrdenesDAO();
        TableColumn<OrdenesDAO,Integer> table_id = new TableColumn<>("ID_Orden");
        table_id.setCellValueFactory(new PropertyValueFactory<>("id_orden"));
        TableColumn<OrdenesDAO,String> table_fecha = new TableColumn<>("Fecha");
        table_fecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        TableColumn<OrdenesDAO,String> table_hora = new TableColumn<>("Hora");
        table_hora.setCellValueFactory(new PropertyValueFactory<>("hora"));
        TableColumn<OrdenesDAO,String> table_descripcion = new TableColumn<>("Descripcion");
        table_descripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        TableColumn<OrdenesDAO,String> table_notas = new TableColumn<>("Notas");
        table_notas.setCellValueFactory(new PropertyValueFactory<>("notas"));
        TableColumn<OrdenesDAO,String> table_idcliente = new TableColumn<>("ID_Cliente");
        table_idcliente.setCellValueFactory(new PropertyValueFactory<>("id_cliente"));
        TableColumn<OrdenesDAO,String> table_idmesa = new TableColumn<>("ID_Mesa");
        table_idmesa.setCellValueFactory(new PropertyValueFactory<>("id_mesa"));
        TableColumn<OrdenesDAO,String> table_idempleado = new TableColumn<>("ID_Empleado");
        table_idempleado.setCellValueFactory(new PropertyValueFactory<>("id_empleado"));
        TableColumn<OrdenesDAO,String> table_total = new TableColumn<>("Total");
        table_total.setCellValueFactory(new PropertyValueFactory<>("total"));

        table_id.setPrefWidth(250);
        table_fecha.setPrefWidth(250);
        table_hora.setPrefWidth(250);
        table_descripcion.setPrefWidth(250);
        table_notas.setPrefWidth(250);
        table_idcliente.setPrefWidth(250);
        table_idmesa.setPrefWidth(250);
        table_idempleado.setPrefWidth(250);
        table_total.setPrefWidth(250);

        TableColumn<OrdenesDAO,String> editar_ = new TableColumn<>("Editar");
        editar_.setCellFactory(new Callback<TableColumn<OrdenesDAO, String>, TableCell<OrdenesDAO, String>>() {
            @Override
            public TableCell<OrdenesDAO, String> call(TableColumn<OrdenesDAO, String> clientesDAOStringTableColumn) {
                return new ButtonCell_ordenes("Editar");
            }
        });
        TableColumn<OrdenesDAO,String> eliminar_ = new TableColumn<>("Eliminar");
        eliminar_.setCellFactory(new Callback<TableColumn<OrdenesDAO, String>, TableCell<OrdenesDAO, String>>() {
            @Override
            public TableCell<OrdenesDAO, String> call(TableColumn<OrdenesDAO, String> clientesDAOStringTableColumn) {
                return new ButtonCell_ordenes("Eliminar");
            }
        });

        this.table_ordenes.getColumns().addAll(table_id,table_fecha,table_hora,table_descripcion,table_notas,table_idcliente,table_idmesa,table_idempleado,table_total,editar_,eliminar_);
        this.table_ordenes.setItems(objeto_ordenes.SELECT());
    }

}

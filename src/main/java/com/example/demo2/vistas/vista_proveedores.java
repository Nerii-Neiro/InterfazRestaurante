package com.example.demo2.vistas;

import com.example.demo2.Componenetes.ButtonCell;
import com.example.demo2.Componenetes.ButtonCell_proveedores;
import com.example.demo2.VentanaPrincipal;
import com.example.demo2.modulos.ClientesDAO;
import com.example.demo2.modulos.ProveedoresDAO;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

public class vista_proveedores extends Stage{


    private ToolBar menu;
    private TableView<ProveedoresDAO> table_proveedores;
    private VBox contenedor_padre;
    private Scene escena;
    private Button agregar ;
    private Button salir;

    public vista_proveedores(){
        CREAR_UI();
        this.setTitle("LISTA DE PROVEEDORES");
        this.setScene(escena);
        this.show();
    }

    private void CREAR_UI(){
        this.salir=new Button("Salir");
        this.table_proveedores=new TableView<>();
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
            new proveedores(table_proveedores,null,"Nuevo Proveedor:");
        });
        create_table();
        this.contenedor_padre= new VBox(menu,agregar,salir,table_proveedores);
        this.contenedor_padre.setSpacing(30);
        contenedor_padre.setPadding(new Insets(20));
        this.contenedor_padre.setAlignment(Pos.CENTER);
        this.escena=new Scene(contenedor_padre,1350,600);
        this.escena.getStylesheets().add(getClass().getResource("/css/estilo_restaurante.css").toString());
        this.agregar.getStyleClass().add("botones-negros");
        this.salir.getStyleClass().add("botones-rojos");
    }

    private void create_table(){
        ProveedoresDAO objeto_proveedores = new ProveedoresDAO();
        TableColumn<ProveedoresDAO,Integer> table_id = new TableColumn<>("ID_Proveedor");
        table_id.setCellValueFactory(new PropertyValueFactory<>("id_proveedor"));
        TableColumn<ProveedoresDAO,String> table_nombre = new TableColumn<>("Nombre");
        table_nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        TableColumn<ProveedoresDAO,String> table_telefono = new TableColumn<>("Telefono");
        table_telefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        TableColumn<ProveedoresDAO,String> table_direccion = new TableColumn<>("Direccion");
        table_direccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        TableColumn<ProveedoresDAO,String> table_email = new TableColumn<>("Correo Electronico");
        table_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        TableColumn<ProveedoresDAO,String> table_nota = new TableColumn<>("Nota");
        table_nota.setCellValueFactory(new PropertyValueFactory<>("nota"));


        table_id.setPrefWidth(250);
        table_nombre.setPrefWidth(250);
        table_telefono.setPrefWidth(250);
        table_direccion.setPrefWidth(250);
        table_email.setPrefWidth(250);
        table_nota.setPrefWidth(250);



        TableColumn<ProveedoresDAO,String> editar_ = new TableColumn<>("Editar");
        editar_.setCellFactory(new Callback<TableColumn<ProveedoresDAO, String>, TableCell<ProveedoresDAO, String>>() {
            @Override
            public TableCell<ProveedoresDAO, String> call(TableColumn<ProveedoresDAO, String> clientesDAOStringTableColumn) {
                return new ButtonCell_proveedores("Editar");
            }
        });
        TableColumn<ProveedoresDAO,String> eliminar_ = new TableColumn<>("Eliminar");
        eliminar_.setCellFactory(new Callback<TableColumn<ProveedoresDAO, String>, TableCell<ProveedoresDAO, String>>() {
            @Override
            public TableCell<ProveedoresDAO, String> call(TableColumn<ProveedoresDAO, String> clientesDAOStringTableColumn) {
                return new ButtonCell_proveedores("Eliminar");
            }
        });

        this.table_proveedores.getColumns().addAll(table_id,table_nombre,table_telefono,table_direccion,table_email,table_nota,editar_,eliminar_);
        this.table_proveedores.setItems(objeto_proveedores.SELECT());
    }

}

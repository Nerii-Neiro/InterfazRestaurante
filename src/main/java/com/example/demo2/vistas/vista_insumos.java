package com.example.demo2.vistas;

import com.example.demo2.Componenetes.ButtonCell;
import com.example.demo2.Componenetes.ButtonCell_insumos;
import com.example.demo2.modulos.ClientesDAO;
import com.example.demo2.modulos.InsumosDAO;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

public class vista_insumos extends Stage{


    private ToolBar menu;
    private TableView<InsumosDAO> table_insumos;
    private VBox contenedor_padre;
    private Scene escena;
    private Button agregar ;
    private Button salir;

    public vista_insumos(){
        CREAR_UI();
        this.setTitle("LISTA DE CLIENTES");
        this.setScene(escena);
        this.show();
    }

    private void CREAR_UI(){
        this.salir=new Button("Salir");
        this.table_insumos=new TableView<>();
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
        });
        this.agregar.setOnAction(event->{
            new insumos(table_insumos,null,"Nuevo Insumo:");
        });
        create_table();
        this.contenedor_padre= new VBox(menu,agregar,salir,table_insumos);
        this.contenedor_padre.setSpacing(30);
        contenedor_padre.setPadding(new Insets(20));
        this.contenedor_padre.setAlignment(Pos.CENTER);
        this.escena=new Scene(contenedor_padre,1350,600);
        this.escena.getStylesheets().add(getClass().getResource("/css/estilo_restaurante.css").toString());
        this.agregar.getStyleClass().add("botones-negros");
        this.salir.getStyleClass().add("botones-rojos");
    }

    private void create_table(){


        InsumosDAO objeto_clientes = new InsumosDAO();
        TableColumn<InsumosDAO,Integer> table_id = new TableColumn<>("ID_Insumo");
        table_id.setCellValueFactory(new PropertyValueFactory<>("id_insumo"));
        TableColumn<InsumosDAO,String> table_nombre = new TableColumn<>("Nombre");
        table_nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        TableColumn<InsumosDAO,String> table_cantidad = new TableColumn<>("Cantidad");
        table_cantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        TableColumn<InsumosDAO,String> table_descripcion = new TableColumn<>("Descripcion");
        table_descripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        TableColumn<InsumosDAO,String> table_observaciones = new TableColumn<>("Observaciones");
        table_observaciones.setCellValueFactory(new PropertyValueFactory<>("observaciones"));
        TableColumn<InsumosDAO,String> table_idproveedor = new TableColumn<>("ID_Proveedor");
        table_idproveedor.setCellValueFactory(new PropertyValueFactory<>("id_proveedor"));

        table_id.setPrefWidth(250);
        table_nombre.setPrefWidth(250);
        table_cantidad.setPrefWidth(250);
        table_descripcion.setPrefWidth(250);
        table_observaciones.setPrefWidth(250);
        table_idproveedor.setPrefWidth(250);

        TableColumn<InsumosDAO,String> editar_ = new TableColumn<>("Editar");
        editar_.setCellFactory(new Callback<TableColumn<InsumosDAO, String>, TableCell<InsumosDAO, String>>() {
            @Override
            public TableCell<InsumosDAO, String> call(TableColumn<InsumosDAO, String> clientesDAOStringTableColumn) {
                return new ButtonCell_insumos("Editar");
            }
        });
        TableColumn<InsumosDAO,String> eliminar_ = new TableColumn<>("Eliminar");
        eliminar_.setCellFactory(new Callback<TableColumn<InsumosDAO, String>, TableCell<InsumosDAO, String>>() {
            @Override
            public TableCell<InsumosDAO, String> call(TableColumn<InsumosDAO, String> clientesDAOStringTableColumn) {
                return new ButtonCell_insumos("Eliminar");
            }
        });


        this.table_insumos.getColumns().addAll(table_id,table_nombre,table_cantidad,table_descripcion,table_observaciones,table_idproveedor,editar_,eliminar_);
        this.table_insumos.setItems(objeto_clientes.SELECT());
    }

}

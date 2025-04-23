package com.example.demo2.vistas;

import com.example.demo2.Componenetes.ButtonCell;
import com.example.demo2.Componenetes.ButtonCell_productos;
import com.example.demo2.modulos.ClientesDAO;
import com.example.demo2.modulos.ProductosDAO;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

public class vista_productos extends Stage{


    private ToolBar menu;
    private TableView<ProductosDAO> table_productos;
    private VBox contenedor_padre;
    private Scene escena;
    private Button agregar ;
    private Button salir;

    public vista_productos(){
        CREAR_UI();
        this.setTitle("LISTA DE PRODUCTOS");
        this.setScene(escena);
        this.show();
    }

    private void CREAR_UI(){
        this.salir=new Button("Salir");
        this.table_productos=new TableView<>();
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
            new productos(table_productos,null,"Nuevo Producto:");
        });
        create_table();
        this.contenedor_padre= new VBox(menu,agregar,salir,table_productos);
        this.contenedor_padre.setSpacing(30);
        contenedor_padre.setPadding(new Insets(20));
        this.contenedor_padre.setAlignment(Pos.CENTER);
        this.escena=new Scene(contenedor_padre,1350,600);
        this.escena.getStylesheets().add(getClass().getResource("/css/estilo_restaurante.css").toString());
        this.agregar.getStyleClass().add("botones-negros");
        this.salir.getStyleClass().add("botones-rojos");
    }

    private void create_table(){
        ProductosDAO objeto_clientes = new ProductosDAO();
        TableColumn<ProductosDAO,Integer> table_id = new TableColumn<>("ID_Producto");
        table_id.setCellValueFactory(new PropertyValueFactory<>("id_producto"));
        TableColumn<ProductosDAO,String> table_nombre = new TableColumn<>("Nombre");
        table_nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        TableColumn<ProductosDAO,String> table_direccion = new TableColumn<>("Precio");
        table_direccion.setCellValueFactory(new PropertyValueFactory<>("precio"));
        TableColumn<ProductosDAO,String> table_telefono = new TableColumn<>("Descripcion");
        table_telefono.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        TableColumn<ProductosDAO,String> table_email = new TableColumn<>("Id_Categoria");
        table_email.setCellValueFactory(new PropertyValueFactory<>("id_categoria"));

        table_id.setPrefWidth(250);
        table_nombre.setPrefWidth(250);
        table_direccion.setPrefWidth(250);
        table_telefono.setPrefWidth(250);
        table_email.setPrefWidth(250);

        TableColumn<ProductosDAO,String> editar_ = new TableColumn<>("Editar");
        editar_.setCellFactory(new Callback<TableColumn<ProductosDAO, String>, TableCell<ProductosDAO, String>>() {
            @Override
            public TableCell<ProductosDAO, String> call(TableColumn<ProductosDAO, String> clientesDAOStringTableColumn) {
                return new ButtonCell_productos("Editar");
            }
        });
        TableColumn<ProductosDAO,String> eliminar_ = new TableColumn<>("Eliminar");
        eliminar_.setCellFactory(new Callback<TableColumn<ProductosDAO, String>, TableCell<ProductosDAO, String>>() {
            @Override
            public TableCell<ProductosDAO, String> call(TableColumn<ProductosDAO, String> clientesDAOStringTableColumn) {
                return new ButtonCell_productos("Eliminar");
            }
        });

        this.table_productos.getColumns().addAll(table_id,table_nombre,table_direccion,table_telefono,table_email,editar_,eliminar_);
        this.table_productos.setItems(objeto_clientes.SELECT());
    }

}

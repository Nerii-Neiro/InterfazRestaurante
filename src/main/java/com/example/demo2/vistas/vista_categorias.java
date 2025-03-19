package com.example.demo2.vistas;

import com.example.demo2.Componenetes.ButtonCell_categoria;
import com.example.demo2.Componenetes.ButtonCell_categoria;
import com.example.demo2.modulos.CategoriasDAO;
import com.example.demo2.modulos.ClientesDAO;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

public class vista_categorias extends Stage{


    private ToolBar menu;
        private TableView<CategoriasDAO> table_categarias;
    private VBox contenedor_padre;
    private Scene escena;
    private Button agregar ;
    private Button salir;

    public vista_categorias(){
        CREAR_UI();
        this.setTitle("LISTA DE CATEGORIAS");
        this.setScene(escena);
        this.show();
    }

    private void CREAR_UI(){
        this.salir=new Button("Salir");
        this.table_categarias=new TableView<>();
        this.agregar=  new Button();
        ImageView img = new ImageView(getClass().getResource("/imagenes/categorias.png").toString());
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
            new categoria(table_categarias,null,"Nueva Categoria:");
        });
        create_table();
        this.contenedor_padre= new VBox(menu,agregar,salir,table_categarias);
        this.contenedor_padre.setSpacing(30);
        contenedor_padre.setPadding(new Insets(20));
        this.contenedor_padre.setAlignment(Pos.CENTER);
        this.escena=new Scene(contenedor_padre,1200,600);
        this.escena.getStylesheets().add(getClass().getResource("/css/estilo_restaurante.css").toString());
        this.agregar.getStyleClass().add("botones-negros");
        this.salir.getStyleClass().add("botones-rojos");
    }

    private void create_table(){
        CategoriasDAO objeto_categorias = new CategoriasDAO();
        TableColumn<CategoriasDAO,Integer> table_id = new TableColumn<>("ID_Categoria");
        table_id.setCellValueFactory(new PropertyValueFactory<>("id_categoria"));
        TableColumn<CategoriasDAO,String> table_nombre = new TableColumn<>("Nombre");
        table_nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        TableColumn<CategoriasDAO,String> table_direccion = new TableColumn<>("Descripcion");
        table_direccion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));

        table_nombre.setPrefWidth(200);
        table_direccion.setPrefWidth(500);

        TableColumn<CategoriasDAO,String> editar_ = new TableColumn<>("Editar");
        editar_.setCellFactory(new Callback<TableColumn<CategoriasDAO, String>, TableCell<CategoriasDAO, String>>() {
            @Override
            public TableCell<CategoriasDAO, String> call(TableColumn<CategoriasDAO, String> clientesDAOStringTableColumn) {
                return new ButtonCell_categoria("Editar");
            }
        });
        TableColumn<CategoriasDAO,String> eliminar_ = new TableColumn<>("Eliminar");
        eliminar_.setCellFactory(new Callback<TableColumn<CategoriasDAO, String>, TableCell<CategoriasDAO, String>>() {
            @Override
            public TableCell<CategoriasDAO, String> call(TableColumn<CategoriasDAO, String> clientesDAOStringTableColumn) {
                return new ButtonCell_categoria("Eliminar");
            }
        });


        this.table_categarias.getColumns().addAll(table_id,table_nombre,table_direccion,editar_,eliminar_);
        this.table_categarias.setItems(objeto_categorias.SELECT());
    }

}

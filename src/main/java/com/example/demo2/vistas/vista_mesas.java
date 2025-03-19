package com.example.demo2.vistas;

import com.example.demo2.Componenetes.ButtonCell;
import com.example.demo2.Componenetes.ButtonCell_mesas;
import com.example.demo2.modulos.ClientesDAO;
import com.example.demo2.modulos.MesasDAO;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

public class vista_mesas extends Stage{


    private ToolBar menu;
    private TableView<MesasDAO> table_mesas;
    private VBox contenedor_padre;
    private Scene escena;
    private Button agregar ;
    private Button salir;

    public vista_mesas(){
        CREAR_UI();
        this.setTitle("LISTA DE MESAS");
        this.setScene(escena);
        this.show();
    }

    private void CREAR_UI(){
        this.salir=new Button("Salir");
        this.table_mesas=new TableView<>();
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
            new mesas(table_mesas,null,"Nueva Mesa:");
        });
        create_table();
        this.contenedor_padre= new VBox(menu,agregar,salir,table_mesas);
        this.contenedor_padre.setSpacing(30);
        contenedor_padre.setPadding(new Insets(20));
        this.contenedor_padre.setAlignment(Pos.CENTER);
        this.escena=new Scene(contenedor_padre,1200,600);
        this.escena.getStylesheets().add(getClass().getResource("/css/estilo_restaurante.css").toString());
        this.agregar.getStyleClass().add("botones-negros");
        this.salir.getStyleClass().add("botones-rojos");
    }

    private void create_table(){
        MesasDAO objeto_mesa = new MesasDAO();
        TableColumn<MesasDAO,Integer> table_id_jj = new TableColumn<>("ID_Mesa");
        table_id_jj.setCellValueFactory(new PropertyValueFactory<>("id_mesas"));
        TableColumn<MesasDAO,String> table_capacidad = new TableColumn<>("Capacidad");
        table_capacidad.setCellValueFactory(new PropertyValueFactory<>("capacidad"));
        TableColumn<MesasDAO,String> table_tipo = new TableColumn<>("Tipo");
        table_tipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));

        TableColumn<MesasDAO,String> editar_ = new TableColumn<>("Editar");
        editar_.setCellFactory(new Callback<TableColumn<MesasDAO, String>, TableCell<MesasDAO, String>>() {
            @Override
            public TableCell<MesasDAO, String> call(TableColumn<MesasDAO, String> clientesDAOStringTableColumn) {
                return new ButtonCell_mesas("Editar");
            }
        });
        TableColumn<MesasDAO,String> eliminar_ = new TableColumn<>("Eliminar");
        eliminar_.setCellFactory(new Callback<TableColumn<MesasDAO, String>, TableCell<MesasDAO, String>>() {
            @Override
            public TableCell<MesasDAO, String> call(TableColumn<MesasDAO, String> clientesDAOStringTableColumn) {
                return new ButtonCell_mesas("Eliminar");
            }
        });

        this.table_mesas.getColumns().addAll(table_id_jj,table_capacidad,table_tipo,editar_,eliminar_);
        this.table_mesas.setItems(objeto_mesa.SELECT());
    }

}

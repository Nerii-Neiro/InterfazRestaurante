package com.example.demo2.vistas;

import com.example.demo2.Componenetes.ButtonCell;
import com.example.demo2.Componenetes.ButtonCell_empleados;
import com.example.demo2.Componenetes.ButtonCell_insumos;
import com.example.demo2.modulos.ClientesDAO;
import com.example.demo2.modulos.EmpleadosDAO;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

public class vista_empleados extends Stage{


    private ToolBar menu;
    private TableView<EmpleadosDAO> table_empleados;
    private VBox contenedor_padre;
    private Scene escena;
    private Button agregar ;
    private Button salir;

    public vista_empleados(){
        CREAR_UI();
        this.setTitle("LISTA DE EMPLEADOS");
        this.setScene(escena);
        this.show();
    }

    private void CREAR_UI(){
        this.salir=new Button("Salir");
        this.table_empleados=new TableView<>();
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
            new empleados(table_empleados,null,"Nuevo Empleado:");
        });
        create_table();
        this.contenedor_padre= new VBox(menu,agregar,salir,table_empleados);
        this.contenedor_padre.setSpacing(30);
        contenedor_padre.setPadding(new Insets(20));
        this.contenedor_padre.setAlignment(Pos.CENTER);
        this.escena=new Scene(contenedor_padre,1350,600);
        this.escena.getStylesheets().add(getClass().getResource("/css/estilo_restaurante.css").toString());
        this.agregar.getStyleClass().add("botones-negros");
        this.salir.getStyleClass().add("botones-rojos");
    }

    private void create_table(){
        EmpleadosDAO objeto_empleados = new EmpleadosDAO();


        TableColumn<EmpleadosDAO,Integer> table_id = new TableColumn<>("ID_Empleado");
        table_id.setCellValueFactory(new PropertyValueFactory<>("id_empleado"));
        TableColumn<EmpleadosDAO,String> table_nombre = new TableColumn<>("Nombre");
        table_nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        TableColumn<EmpleadosDAO,String> table_curp = new TableColumn<>("Curp");
        table_curp.setCellValueFactory(new PropertyValueFactory<>("curp"));
        TableColumn<EmpleadosDAO,String> table_rfc = new TableColumn<>("RFC");
        table_rfc.setCellValueFactory(new PropertyValueFactory<>("rfc"));
        TableColumn<EmpleadosDAO,String> table_sueldo = new TableColumn<>("Sueldo");
        table_sueldo.setCellValueFactory(new PropertyValueFactory<>("sueldo"));
        TableColumn<EmpleadosDAO,String> table_puesto = new TableColumn<>("Puesto");
        table_puesto.setCellValueFactory(new PropertyValueFactory<>("puesto"));
        TableColumn<EmpleadosDAO,String> table_telefono = new TableColumn<>("Telefono");
        table_telefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        TableColumn<EmpleadosDAO,String> table_nss = new TableColumn<>("NSS");
        table_nss.setCellValueFactory(new PropertyValueFactory<>("nss"));
        TableColumn<EmpleadosDAO,String> table_horario = new TableColumn<>("Horario");
        table_horario.setCellValueFactory(new PropertyValueFactory<>("horario"));
        TableColumn<EmpleadosDAO,String> table_fecha = new TableColumn<>("Fecha Ingreso");
        table_fecha.setCellValueFactory(new PropertyValueFactory<>("fecha_ingreso"));

        table_id.setPrefWidth(250);
        table_nombre.setPrefWidth(250);
        table_curp.setPrefWidth(250);
        table_rfc.setPrefWidth(250);
        table_sueldo.setPrefWidth(250);
        table_puesto.setPrefWidth(250);
        table_telefono.setPrefWidth(250);
        table_nss.setPrefWidth(250);
        table_horario.setPrefWidth(250);
        table_fecha.setPrefWidth(250);

        TableColumn<EmpleadosDAO,String> editar_ = new TableColumn<>("Editar");
        editar_.setCellFactory(new Callback<TableColumn<EmpleadosDAO, String>, TableCell<EmpleadosDAO, String>>() {
            @Override
            public TableCell<EmpleadosDAO, String> call(TableColumn<EmpleadosDAO, String> clientesDAOStringTableColumn) {
                return new ButtonCell_empleados("Editar");
            }
        });
        TableColumn<EmpleadosDAO,String> eliminar_ = new TableColumn<>("Eliminar");
        eliminar_.setCellFactory(new Callback<TableColumn<EmpleadosDAO, String>, TableCell<EmpleadosDAO, String>>() {
            @Override
            public TableCell<EmpleadosDAO, String> call(TableColumn<EmpleadosDAO, String> clientesDAOStringTableColumn) {
                return new ButtonCell_empleados("Eliminar");
            }
        });


        this.table_empleados.getColumns().addAll(table_id,table_nombre,table_curp,table_rfc,table_sueldo,table_puesto,table_telefono,table_nss,table_horario,table_fecha,editar_,eliminar_);
        this.table_empleados.setItems(objeto_empleados.SELECT());
    }

}

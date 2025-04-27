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


public class VentanaELementos extends Stage {
    private String Nombre_empleado;
    private int id_empleado;
    private TableView<OrdenesDAO> table_ordenes;
    private VBox contenedor_padre;
    private Scene escena;
    private Button agregar ;
    private Button solo_salir;

    private VentanaPrincipal ventana;
    public VentanaELementos(int id_empleado ,String Nombre_empleado){
        this.id_empleado=id_empleado;
        this.Nombre_empleado=Nombre_empleado;
        CREAR_UI();
        this.ventana=ventana;
        this.setTitle("LISTA DE ORDENES");
        this.setScene(escena);
        this.showAndWait();
    }

    private void CREAR_UI(){


        this.solo_salir=new Button("Salir");
        this.solo_salir.setOnAction(event->{
            this.close();
        });
        this.solo_salir.getStyleClass().add("botones-exit");

        this.table_ordenes=new TableView<>();
        this.agregar=  new Button();
        ImageView img = new ImageView(getClass().getResource("/imagenes/n_usuario.png").toString());
        img.setFitWidth(50);
        img.setFitHeight(50);
        this.agregar.setGraphic(img);



        this.agregar.setOnAction(event->{
            new ordenes(table_ordenes,null,"Nueva Orden:");
        });
        create_table();
        this.contenedor_padre= new VBox(new Label("Empleado: "+id_empleado + " Nombre: "+Nombre_empleado),solo_salir,table_ordenes);
        this.contenedor_padre.setSpacing(30);
        contenedor_padre.setPadding(new Insets(20));
        this.contenedor_padre.setAlignment(Pos.CENTER);
        this.escena=new Scene(contenedor_padre,1350,600);
        this.escena.getStylesheets().add(getClass().getResource("/css/estilo_restaurante.css").toString());
        this.agregar.getStyleClass().add("botones-negros");

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



        this.table_ordenes.getColumns().addAll(table_id,table_fecha,table_hora,table_descripcion,table_notas,table_idcliente,table_idmesa,table_idempleado,table_total);
        this.table_ordenes.setItems(objeto_ordenes.SELECT_uno(id_empleado));
    }

}






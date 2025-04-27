package com.example.demo2.vistas;

import com.example.demo2.modulos.ClientesDAO;
import com.example.demo2.modulos.EmpleadosDAO;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class empleados extends Stage {



    private Scene escena;

    private Label txtnombre,txtcurp,txtrfc,txtsueldo,txtpuesto,txttelefono,txtnss,txthorario,txtfechaingreso;
    private Label txtemail,txtpassword;
    private VBox contenedor_padre;
    private Button enviar;

    private TextField password;
    private TextField email;
    private TextField curp;
    private TextField rfc;
    private TextField sueldo;
    private TextField puesto;
    private TextField telefono;
    private TextField nss;
    private TextField horario;
    private TextField fecha_ingreso;
    private TextField nombre;

    private EmpleadosDAO objeto;
    private Label title;
    private Button salir;
    private boolean option;
    private String title_;

    private TableView<EmpleadosDAO> tabla_empleados;

    public empleados(TableView<EmpleadosDAO> tabla_clientes,EmpleadosDAO objeto_dao,String title_){
        this.title_=title_;
        this.tabla_empleados=tabla_clientes;
        CREAR_GUI();
        if(objeto_dao==null){
            objeto =new EmpleadosDAO();
            option=false;
        }
        else{
            option=true;
            objeto=objeto_dao;
            this.nombre.setText(objeto.getNombre());
            this.rfc.setText(objeto.getRfc());
            this.sueldo.setText(String.valueOf(objeto.getSueldo()));
            this.puesto.setText(objeto.getPuesto());
            this.telefono.setText(objeto.getTelefono());
            this.nss.setText(objeto.getNss());
            this.horario.setText(objeto.getHorario());
            this.fecha_ingreso.setText(objeto.getFecha_ingreso());
            this.curp.setText(objeto.getCurp());

            this.email.setText(objeto.getEmail());
            this.password.setText(objeto.getPassword());
        }
        //objeto = (objeto_dao==null)? new ClientesDAO():this.objeto;
        this.setTitle("Registrar Empleado");
        this.setScene(escena);
        this.show();
    }

    private void CREAR_GUI(){
        this.salir=new Button("Cancelar");
        this.title =new Label(this.title_);



        txtnombre=new Label("Nombre:");
        nombre=new TextField();
        txtcurp=new Label("Curp:");
        curp=new TextField();
        txtrfc=new Label("RFC:");
        rfc=new TextField();
        txtsueldo=new Label("Sueldo:");
        sueldo=new TextField();
        txtpuesto=new Label("Puesto:");
        puesto=new TextField();
        txttelefono=new Label("Telefono:");
        telefono=new TextField();
        txtnss=new Label("Nss:");
        nss=new TextField();
        txthorario=new Label("Horario:");
        horario=new TextField();
        txtfechaingreso=new Label("Fecha Ingreso:");
        fecha_ingreso=new TextField();

        txtemail=new Label("Correo:");
        email=new TextField();
        txtpassword=new Label("Contraseña:");
        password=new TextField();

        salir.setOnAction(event->{
            this.close();
        });
        enviar=new Button("Guardar");
        enviar.setOnAction(event->{

            //
            objeto.setNombre(nombre.getText());
            objeto.setCurp(curp.getText());
            objeto.setRfc(rfc.getText());
            objeto.setSueldo(Double.parseDouble(sueldo.getText()));
            objeto.setPuesto(puesto.getText());
            objeto.setTelefono(telefono.getText());
            objeto.setFecha_ingreso(fecha_ingreso.getText());
            objeto.setNss(nss.getText());
            objeto.setHorario(horario.getText());

            objeto.setEmail(email.getText());
            objeto.setPassword(password.getText());
            //

            if(option==true){
                objeto.UPDATE();
            }
            else{
                objeto.INSERT();
            }

            //ACTUALIZAR LA TABLA DE CLIENTES
            this.tabla_empleados.setItems(objeto.SELECT());
            this.tabla_empleados.refresh();
            this.close();
            //
        });


        VBox g1 = new VBox(txtnombre,nombre);
        VBox g2 = new VBox(txtcurp,curp);
        HBox contt = new HBox(g1,g2);
        VBox g3 = new VBox(txtrfc,rfc);
        VBox g4 = new VBox(txtsueldo,sueldo);
        HBox contt_2 = new HBox(g3,g4);
        VBox g5 = new VBox(txtpuesto,puesto);
        VBox g6 = new VBox(txttelefono,telefono);
        HBox contt_3 = new HBox(g5,g6);

        VBox g7 = new VBox(txtnss,nss);
        VBox g8 = new VBox(txthorario,horario);
        HBox contt_4 = new HBox(g7,g8);
        VBox g9 = new VBox(txtemail,email);
        VBox g10 = new VBox(txtpassword,password);
        HBox contt_5 = new HBox(g9,g10);

        contenedor_padre=new VBox(title,contt,contt_2,contt_3,contt_4,contt_5,txtfechaingreso,fecha_ingreso,enviar,salir);
        contenedor_padre.setSpacing(5);
        contenedor_padre.setPadding(new Insets(20));
        contenedor_padre.setAlignment(Pos.CENTER);
        escena=new Scene(contenedor_padre,500,700);
        escena.getStylesheets().add(getClass().getResource("/css/estilo_restaurante.css").toString());

        nombre.getStyleClass().add("display-fondo");
        curp.getStyleClass().add("display-fondo");
        rfc.getStyleClass().add("display-fondo");
        sueldo.getStyleClass().add("display-fondo");
        puesto.getStyleClass().add("display-fondo");
        telefono.getStyleClass().add("display-fondo");
        nss.getStyleClass().add("display-fondo");
        horario.getStyleClass().add("display-fondo");
        fecha_ingreso.getStyleClass().add("display-fondo");

        email.getStyleClass().add("display-fondo");
        password.getStyleClass().add("display-fondo");


        this.enviar.getStyleClass().add("botones-negros");
        this.title.getStyleClass().add("title_space");
        this.salir.getStyleClass().add("botones-rojos");
    }

}




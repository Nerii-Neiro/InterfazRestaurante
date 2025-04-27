package com.example.demo2.Componenetes;

import com.example.demo2.modulos.ClientesDAO;
import com.example.demo2.modulos.ReservacionesDAO;
import com.example.demo2.vistas.cliente;
import com.example.demo2.vistas.reservaciones;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;

import java.util.Optional;

public class ButtonCell_reservaciones extends TableCell<ReservacionesDAO, String> {

    private Button celda_buton;
    private String alabel;
    private String estilo_1;
    private String estilo_2;

    public ButtonCell_reservaciones(String a_label){
        this.alabel=a_label;
        celda_buton = new Button(this.alabel);
        estilo_1="-fx-text-fill: white;-fx-background-color: #dd2f05;-fx-font-size: 23;-fx-font-weight: bolder;-fx-font-family: sans-serif;-fx-border-radius: 20;-fx-background-radius: 20;-fx-border-color: white;-fx-border-width: 3px;-fx-padding: 5px 10px 5px 10px;";
        estilo_2="-fx-text-fill: white;-fx-background-color: #2028f8;-fx-font-size: 23;-fx-font-weight: bolder;-fx-font-family: sans-serif;-fx-border-radius: 20;-fx-background-radius: 20;-fx-border-color: white;-fx-border-width: 3px;-fx-padding: 5px 10px 5px 10px;";
        if(a_label.equals("Editar")){
            celda_buton.setStyle(estilo_2);

        }
        else{
            celda_buton.setStyle(estilo_1);
        }
        celda_buton.setOnAction(event->{
            ReservacionesDAO objeto = this.getTableView().getItems().get(this.getIndex());
           // ClientesDAO objeto = this.getTableView().getItems().get(this.getIndex());
            if(a_label.equals("Editar")){
                //PASO PARA EDITAR
                new reservaciones(this.getTableView(),objeto,"Modificar Reservacion:");
            }
            else{
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Mensaje del Sistema");
                alert.setContentText("¿DESEAS ELIMINAR ESTE ELEMENTO?");
                Optional<ButtonType> opcion = alert.showAndWait();
                if(opcion.get()==ButtonType.OK){
                    objeto.DELETE();
                }
            }
            this.getTableView().setItems(objeto.SELECT());
            this.getTableView().refresh();
        });
    }

    private void eliminar_void(){

    }

    @Override
    protected void updateItem(String item,boolean empty){
        super.updateItem(item,empty);
        if(!empty){
            this.setGraphic(celda_buton);
        }
    }


}


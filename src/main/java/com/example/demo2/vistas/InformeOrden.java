package com.example.demo2.vistas;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;


public class InformeOrden extends Stage {

    private VBox contenedor;
    private Scene escena_principal;
    private Label desc;
    private Button boton_liberar;

    public InformeOrden(int opcion) {


        if (opcion==0){
             desc = new Label("No se pudo crear la Orden");
             boton_liberar= new Button("Cerrar");
        }
        else{
             desc = new Label("LA ORDEN SE CREO CON EXITO!! \n Se generara el ticket");
             boton_liberar= new Button("Generar Ticket");
        }

        boton_liberar.setOnAction(event -> {this.close();});
        boton_liberar.getStyleClass().add("botones-alta");

        desc.setStyle("-fx-font-size: 12px; -fx-background-color: #698000;");
        contenedor=new VBox(desc,boton_liberar);
        contenedor.setSpacing(10);
        contenedor.setPadding(new Insets( 10));
        contenedor.setAlignment(Pos.TOP_CENTER);
        escena_principal = new Scene(contenedor,700,200);
        escena_principal.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        escena_principal.getStylesheets().add(getClass().getResource("/css/estilo_principal.css").toString());
        this.setTitle("SISTEMA DE RESTAURANTE");
        this.setScene(escena_principal);
        this.showAndWait();
    }



}

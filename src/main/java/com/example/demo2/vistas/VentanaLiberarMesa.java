package com.example.demo2.vistas;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;


public class VentanaLiberarMesa extends Stage {

    public boolean opcion;
    private VBox contenedor;
    private Scene escena_principal;
    private Button boton_liberar;
    private Button boton_aignar;

    public VentanaLiberarMesa() {
       this.opcion=false;
       boton_liberar= new Button("Liberar");
       boton_liberar.setOnAction(event -> {this.opcion=true;this.close();});
       boton_liberar.getStyleClass().add("botones-media");
       boton_aignar=new Button("Asignar");
       boton_aignar.setOnAction(event -> {this.opcion=false;this.close();});
        boton_aignar.getStyleClass().add("botones-alta");
        Label desc = new Label("La mesa esta ocupada desea liberarla o para asignar orden");
        desc.setStyle("-fx-font-size: 11px; -fx-background-color: #2f8000;");
       contenedor=new VBox(desc,boton_liberar,boton_aignar);
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

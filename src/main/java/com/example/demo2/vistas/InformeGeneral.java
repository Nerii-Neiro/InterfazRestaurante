package com.example.demo2.vistas;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;


public class InformeGeneral extends Stage {

    private VBox contenedor;
    private Scene escena_principal;
    private Label desc;
    private Button boton_liberar;

    public InformeGeneral(String Message) {
        boton_liberar= new Button("Cerrar");
        boton_liberar.setOnAction(event -> {this.close();});
        boton_liberar.getStyleClass().add("botones-alta");
        desc = new Label(Message);
        desc.setStyle("-fx-font-size: 14px; -fx-background-color: #698000;");
        contenedor=new VBox(desc,boton_liberar);
        contenedor.setSpacing(10);
        contenedor.setPadding(new Insets( 10));
        contenedor.setAlignment(Pos.TOP_CENTER);
        escena_principal = new Scene(contenedor,700,500);
        escena_principal.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        escena_principal.getStylesheets().add(getClass().getResource("/css/estilo_principal.css").toString());
        this.setTitle("Sistema Informacion");
        this.setScene(escena_principal);
        this.showAndWait();
    }



}


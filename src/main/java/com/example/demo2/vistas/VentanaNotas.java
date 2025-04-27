package com.example.demo2.vistas;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;


public class VentanaNotas extends Stage {

    private VBox contenedor;
    private Scene escena_principal;
    private Label desc;
    private Label desc2;
    private Button boton_liberar;

    private String DESCRIPCION;
    private String NOTAS;

    private final TextField desc_f;
    private final TextField notas_f;

    public VentanaNotas() {
        desc_f =new TextField();
        desc_f.setPromptText("Escribe algo(opcional).......");
        desc_f.getStyleClass().add("display-fondo2");
        notas_f =new TextField();
        notas_f.setPromptText("Escribe algo(opcional).......");
        notas_f.getStyleClass().add("display-fondo2");
        boton_liberar= new Button("Enviar");
        boton_liberar.setOnAction(event -> {
            DESCRIPCION=desc_f.getText();
            NOTAS=notas_f.getText();
            this.close();
        });
        boton_liberar.getStyleClass().add("botones-alta");

        desc = new Label("Descripcion:");
        desc.setStyle("-fx-font-size: 12px; -fx-background-color: #698000;");
        desc2 = new Label("Notas:");
        desc2.setStyle("-fx-font-size: 12px; -fx-background-color: #698000;");
        contenedor=new VBox(desc,desc_f,desc2,notas_f,boton_liberar);
        contenedor.setSpacing(10);
        contenedor.setPadding(new Insets( 10));
        contenedor.setAlignment(Pos.TOP_CENTER);
        escena_principal = new Scene(contenedor,700,200);
        escena_principal.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        escena_principal.getStylesheets().add(getClass().getResource("/css/estilo_principal.css").toString());
        this.setTitle("INGRESA DATOS");
        this.setScene(escena_principal);
        this.showAndWait();
    }

    public String ret_DESC(){
        return DESCRIPCION;
    }

    public String ret_NOTAS(){
        return NOTAS;
    }



}

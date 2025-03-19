package com.example.demo2.vistas;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;

public class Ventana_eleccion extends Stage {

    private Scene escena_principal;
    private VBox panel_botones;
    private TextField text_dificultad;
    private Button dif_alta;
    private Button dif_media;
    private Button dif_baja;
    private Button iniciar;
    private int eleccion;
    private Label text_dif;
    private rompecabezas romp;
    private Button salir;

    public Ventana_eleccion(){
        INIT_VENTANA();
        this.setScene(escena_principal);
        this.setTitle("ROMPECABEZAS JINWING CONTADOR VER.1.0");
        this.show();
    }

    public void INIT_VENTANA(){
        this.salir = new Button("Salir");
        this.salir.setOnAction(event -> {
            this.close();
            System.exit(0);
        });
        this.text_dif=new Label("Dificultad de Juego:");
        this.eleccion = 1;
        this.iniciar=new Button("Comenzar");
        iniciar.setOnAction(event -> {evento_ventana();});
        this.text_dificultad=new TextField("BAJA");
        this.text_dificultad.setAlignment(Pos.BASELINE_CENTER);
        this.text_dificultad.setEditable(false);
        dif_alta= new Button("ALTA: 16*16");
        dif_media = new Button("MEDIA: 8*8");
        dif_baja=new Button("BAJA: 4*4");
        dif_alta.setOnAction(event->put_eleccion(3));
        dif_media.setOnAction(event->put_eleccion(2));
        dif_baja.setOnAction(event->put_eleccion(1));
        panel_botones=new VBox(text_dif,text_dificultad,dif_alta,dif_media,dif_baja,iniciar,salir);
        panel_botones.setSpacing(25);
        panel_botones.setPadding(new Insets(20));
        panel_botones.setAlignment(Pos.CENTER);
        escena_principal= new Scene(panel_botones,350,500);
        escena_principal.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        escena_principal.getStylesheets().add(getClass().getResource("/css/estilo_rompecabezas.css").toString());
        //estilo
        text_dificultad.getStyleClass().add("display-fondo");
        dif_alta.getStyleClass().add("botones-alta");
        dif_media.getStyleClass().add("botones-media");
        dif_baja.getStyleClass().add("botones-baja");
        iniciar.getStyleClass().add("botones-negros");
        //
    }

    private void evento_ventana(){
        romp=new rompecabezas(eleccion);
        this.close();
    }

    private void put_eleccion(int eleccion){
        this.eleccion=eleccion;
        System.out.println("LA ELECION FUE: "+eleccion);
        this.text_dificultad.clear();
        if(eleccion==3){
            this.text_dificultad.appendText("ALTA");
        }
        else if(eleccion==2){
            this.text_dificultad.appendText("MEDIA");
        }
        else{
            this.text_dificultad.appendText("BAJA");
        }

    }

    public int getEleccion(){
        return this.eleccion;
    }
}


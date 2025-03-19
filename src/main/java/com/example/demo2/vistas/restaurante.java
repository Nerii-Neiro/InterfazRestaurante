package com.example.demo2.vistas;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;
import org.kordamp.bootstrapfx.scene.layout.Panel;

import java.sql.Connection;
import java.sql.DriverManager;



public class restaurante extends Stage {
        private Panel_opciones panel_principal;
        private Scene escena;


        public restaurante(){
            GUI_RESTAURANTE();
            this.setTitle("RESTAURANTE LA PAZ");
            this.setScene(escena);
            this.show();
        }

        private void GUI_RESTAURANTE(){
            panel_principal= new Panel_opciones("Tacos el Miguel");
            escena= new Scene(panel_principal,300,200);
            escena.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
            escena.getStylesheets().add(getClass().getResource("/css/estilo_restaurante.css").toString());
        }


}

class Panel_opciones extends Panel {
    public Panel_opciones(String title){
        super(title);
        this.getStyleClass().add("text_root");
    }

}

package com.example.demo2.vistas;

import com.example.demo2.Componenetes.Hilo;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.ArrayList;

public class celaya extends Stage {

    private Scene escena;
    private VBox contentenor_padre;
    private GridPane panel;
    private Button iniciar;
    private String nobres_rutas[]={"RUTA A: ","RUTA B: ","RUTA C: ","RUTA D: ","RUTA E: "};
    private Label[] lbl_rutas;
    private ProgressBar[] pg_rutas;
    private Hilo[] rutas_hilos;

    public celaya(){
        CREAR_GUI();
        this.setTitle("CALLES DE CELAYA");
        this.setScene(escena);
        this.show();
    }

    private void CREAR_GUI() {
        this.iniciar= new Button("Iniciar");
        pg_rutas= new ProgressBar[5];
        lbl_rutas = new Label[5];
        rutas_hilos= new Hilo[5];
        panel =new GridPane();
        for(int iterator_fila=0;iterator_fila<pg_rutas.length;iterator_fila++){
                lbl_rutas[iterator_fila]= new Label(nobres_rutas[iterator_fila]);
                lbl_rutas[iterator_fila].getStyleClass().add("paralabel");
                pg_rutas[iterator_fila]= new ProgressBar(0);
                rutas_hilos[iterator_fila] = new Hilo(nobres_rutas[iterator_fila],pg_rutas[iterator_fila]);
                panel.add(lbl_rutas[iterator_fila],0,iterator_fila);
                panel.add(pg_rutas[iterator_fila],1,iterator_fila);
        }

        contentenor_padre = new VBox(panel,iniciar);
        contentenor_padre.setSpacing(30);
        contentenor_padre.setPadding(new Insets(30));
        escena = new Scene(contentenor_padre,400,200);
        escena.getStylesheets().add(getClass().getResource("/css/custom.css").toString());
        this.iniciar.getStyleClass().add("botones-negros");

        this.iniciar.setOnAction(e -> {
            reset_prgores(pg_rutas);
            nuevos_hilos(rutas_hilos, nobres_rutas, pg_rutas);
            for (int i = 0; i < pg_rutas.length; i++) {
                if (!rutas_hilos[i].isAlive()) {
                    rutas_hilos[i].start();
                }
            }
        });

    }

    private void nuevos_hilos(Hilo[] rutas_hilos, String[] nobres_rutas, ProgressBar[] pg_rutas) {
        for (int iterator_fila = 0; iterator_fila < pg_rutas.length; iterator_fila++) {
            if (rutas_hilos[iterator_fila] == null || !rutas_hilos[iterator_fila].isAlive()) {
                rutas_hilos[iterator_fila] = new Hilo(nobres_rutas[iterator_fila], pg_rutas[iterator_fila]);
            }
        }
    }

    private void reset_prgores(ProgressBar[] pg_rutas){
        for(ProgressBar pg : pg_rutas){
            pg.setProgress(0.0);
        }
    }

}




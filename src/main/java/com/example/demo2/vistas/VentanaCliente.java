package com.example.demo2.vistas;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class VentanaCliente extends Stage {

    public VentanaCliente() {
        inicializar();
    }

    private void inicializar() {
        Button reservarButton = new Button("Hacer Reservación");
        Button facturaButton = new Button("Solicitar Factura");
        Button salirButton = new Button("Salir");

        reservarButton.setOnAction(e -> hacerReservacion());
        facturaButton.setOnAction(e -> solicitarFactura());
        salirButton.setOnAction(e -> this.close());

        VBox vbox = new VBox(15, reservarButton, facturaButton, salirButton);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(20));
        vbox.getStyleClass().add("vbox-cliente");

        Scene scene = new Scene(vbox, 400, 300);
        scene.getStylesheets().add(getClass().getResource("/css/estilos.css").toExternalForm());

        this.setTitle("Panel de Cliente");
        this.setScene(scene);
    }

    private void hacerReservacion() {
        // Aquí puedes abrir la ventana de reservación
        System.out.println("Reservación solicitada.");
    }

    private void solicitarFactura() {
        // Aquí puedes abrir la ventana de facturación
        System.out.println("Factura solicitada.");
    }
}


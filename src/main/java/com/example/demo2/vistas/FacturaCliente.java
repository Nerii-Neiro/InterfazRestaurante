package com.example.demo2.vistas;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FacturaCliente extends Stage {

    public FacturaCliente() {
        inicializar();
    }

    private void inicializar() {
        Label nombreLabel = new Label("Nombre o Razón Social:");
        TextField nombreField = new TextField();

        Label rfcLabel = new Label("RFC:");
        TextField rfcField = new TextField();

        Label correoLabel = new Label("Correo para envío:");
        TextField correoField = new TextField();

        Button solicitarButton = new Button("Solicitar Factura");
        solicitarButton.setOnAction(e -> solicitarFactura(nombreField.getText(), rfcField.getText(), correoField.getText()));

        VBox vbox = new VBox(15, nombreLabel, nombreField, rfcLabel, rfcField, correoLabel, correoField, solicitarButton);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(20));
        vbox.getStyleClass().add("vbox-cliente");

        Scene scene = new Scene(vbox, 400, 400);
        scene.getStylesheets().add(getClass().getResource("/css/estilos.css").toExternalForm());

        this.setTitle("Solicitud de Factura");
        this.setScene(scene);
    }

    private void solicitarFactura(String nombre, String rfc, String correo) {
        if (nombre.isEmpty() || rfc.isEmpty() || correo.isEmpty()) {
            mostrarAlerta("Error", "Todos los campos son obligatorios.");
            return;
        }

        // Aquí deberías guardar en base de datos la solicitud de factura
        System.out.println("Factura solicitada para " + nombre + " (" + rfc + "), enviar a: " + correo);
        mostrarAlerta("Éxito", "Factura solicitada correctamente.");
        this.close();
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}

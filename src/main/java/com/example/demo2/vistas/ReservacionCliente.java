package com.example.demo2.vistas;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ReservacionCliente extends Stage {

    public ReservacionCliente() {
        inicializar();
    }

    private void inicializar() {
        Label fechaLabel = new Label("Fecha de reservación:");
        DatePicker fechaPicker = new DatePicker();

        Label personasLabel = new Label("Número de personas:");
        TextField personasField = new TextField();

        Button confirmarButton = new Button("Confirmar Reservación");
        confirmarButton.setOnAction(e -> confirmarReservacion(fechaPicker.getValue(), personasField.getText()));

        VBox vbox = new VBox(15, fechaLabel, fechaPicker, personasLabel, personasField, confirmarButton);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(20));
        vbox.getStyleClass().add("vbox-cliente");

        Scene scene = new Scene(vbox, 400, 300);
        scene.getStylesheets().add(getClass().getResource("/css/estilos.css").toExternalForm());

        this.setTitle("Reservación");
        this.setScene(scene);
    }

    private void confirmarReservacion(java.time.LocalDate fecha, String personas) {
        if (fecha == null || personas.isEmpty()) {
            mostrarAlerta("Error", "Todos los campos son obligatorios.");
            return;
        }

        try {
            int numPersonas = Integer.parseInt(personas);
            // Aquí deberías guardar en base de datos la reservación
            System.out.println("Reservación para " + numPersonas + " personas el " + fecha);
            mostrarAlerta("Éxito", "Reservación realizada correctamente.");
            this.close();
        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "Número de personas inválido.");
        }
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}

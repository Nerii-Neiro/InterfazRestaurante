package com.example.demo2.vistas;

import com.example.demo2.modulos.ReservacionesDAO;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ReservacionCliente extends Stage {

    private DatePicker fechaPicker;
    private TextField personasField;
    private ComboBox<String> horaComboBox;

    public ReservacionCliente() {
        inicializar();
    }

    private void inicializar() {
        Label fechaLabel = new Label("Fecha de reservación:");
        fechaPicker = new DatePicker();

        Label horaLabel = new Label("Hora de reservación:");
        horaComboBox = new ComboBox<>();
        // Horarios ejemplo: de 10 AM a 10 PM
        for (int i = 10; i <= 22; i++) {
            horaComboBox.getItems().add(String.format("%02d:00", i));
            horaComboBox.getItems().add(String.format("%02d:30", i));
        }

        Label personasLabel = new Label("Número de personas:");
        personasField = new TextField();

        Button confirmarButton = new Button("Confirmar Reservación");
        confirmarButton.setOnAction(e -> confirmarReservacion());

        VBox vbox = new VBox(15, fechaLabel, fechaPicker, horaLabel, horaComboBox, personasLabel, personasField, confirmarButton);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(20));
        vbox.getStyleClass().add("vbox-cliente");

        Scene scene = new Scene(vbox, 400, 400);
        scene.getStylesheets().add(getClass().getResource("/css/estilo_principal.css").toString());

        this.setTitle("Reservación");
        this.setScene(scene);
    }

    private void confirmarReservacion() {
        if (fechaPicker.getValue() == null || personasField.getText().isEmpty() || horaComboBox.getValue() == null) {
            mostrarAlerta("Error", "Todos los campos son obligatorios.");
            return;
        }

        try {
            int numPersonas = Integer.parseInt(personasField.getText());

            ReservacionesDAO reservacion = new ReservacionesDAO();
            String observaciones = "Número de personas: " + numPersonas;
            reservacion.crear_reservaciones(0, fechaPicker.getValue().toString(), horaComboBox.getValue(), observaciones);

            reservacion.INSERT();

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

package com.example.demo2.vistas;

import com.example.demo2.modulos.conexion;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import com.example.demo2.modulos.ClientesDAO; // Asegúrate que esté bien el paquete

import java.sql.ResultSet;
import java.sql.Statement;


public class RegistroCliente extends Stage {

    private TextField nombreField;
    private TextField direccionField;
    private TextField telefonoField;
    private TextField emailField;
    private PasswordField passwordField;

    public RegistroCliente() {
        inicializar();
    }

    private void inicializar() {
        Label nombreLabel = new Label("Nombre:");
        nombreField = new TextField();

        Label direccionLabel = new Label("Dirección:");
        direccionField = new TextField();

        Label telefonoLabel = new Label("Teléfono:");
        telefonoField = new TextField();

        Label emailLabel = new Label("Correo electrónico:");
        emailField = new TextField();

        Label passwordLabel = new Label("Password");
        passwordField = new PasswordField();

        Button registrarButton = new Button("Registrar");
        registrarButton.setOnAction(e -> registrarCliente());

        VBox vbox = new VBox(15, nombreLabel, nombreField,
                direccionLabel, direccionField,
                telefonoLabel, telefonoField,
                emailLabel, emailField,
                passwordLabel, passwordField,
                registrarButton);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(20));
        vbox.getStyleClass().add("vbox-cliente");

        Scene scene = new Scene(vbox, 400, 500);
        scene.getStylesheets().add(getClass().getResource("/css/estilos.css").toExternalForm());

        this.setTitle("Registro de Cliente");
        this.setScene(scene);
    }


    private boolean correoExiste(String email) {
        String query = "SELECT COUNT(*) FROM Clientes WHERE email = '" + email + "'";
        try {
            Statement stm = conexion.connection.createStatement();
            ResultSet rs = stm.executeQuery(query);
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0; // Si count > 0, ya existe el correo
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false; // Si hubo error, asumimos que no existe
    }

    private void registrarCliente() {
        String nombre = nombreField.getText();
        String direccion = direccionField.getText();
        String telefono = telefonoField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();

        if (nombre.isEmpty() || direccion.isEmpty() || telefono.isEmpty() || email.isEmpty() || password.isEmpty()) {
            mostrarAlerta("Error", "Todos los campos son obligatorios.");
            return;
        }

        if (correoExiste(email)) {
            mostrarAlerta("Error", "El correo ya está registrado. Usa otro correo.");
            return;
        }

        ClientesDAO cliente = new ClientesDAO();
        cliente.crear_cliente(nombre, direccion, telefono, email, password);
        cliente.INSERT();
        mostrarAlerta("Éxito", "Cliente registrado correctamente.");
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

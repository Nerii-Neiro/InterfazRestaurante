package com.example.demo2;

import com.example.demo2.vistas.RegistroCliente;
import com.example.demo2.vistas.VentanaCliente;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.sql.*;

public class VentanaLogin extends Stage {
    private int id_employes;
    private TextField correoField;
    private PasswordField contraseñaField;

    public VentanaLogin() {
        inicializar();
    }

    private void inicializar() {
        Label correoLabel = new Label("Correo electrónico:");
        correoField = new TextField();

        Label contraseñaLabel = new Label("Contraseña:");
        contraseñaField = new PasswordField();

        Button loginButton = new Button("Iniciar Sesión");
        Button registroButton = new Button("Registrarse");

        loginButton.setOnAction(e -> login());
        registroButton.setOnAction(e -> abrirRegistro());

        VBox vbox = new VBox(10, correoLabel, correoField, contraseñaLabel, contraseñaField, loginButton, registroButton);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(20));

        Scene scene = new Scene(vbox, 400, 300);
        scene.getStylesheets().add(getClass().getResource("/css/estilo_principal.css").toString());
        this.setScene(scene);
        this.setTitle("Login - Restaurante");
        this.show();
    }

    private void login() {
        String correo = correoField.getText();
        String contraseña = contraseñaField.getText();

        try {
            Connection conn = HelloApplication.con.connection;  // Usamos la conexión global

            // Primero buscar en empleados
            PreparedStatement stmtEmpleado = conn.prepareStatement(
                    "SELECT id_empleado FROM Empleados WHERE email = ? AND password = ?");
            stmtEmpleado.setString(1, correo);
            stmtEmpleado.setString(2, contraseña);

            ResultSet rsEmpleado = stmtEmpleado.executeQuery();
            if (rsEmpleado.next()) {
                id_employes=rsEmpleado.getInt("id_empleado");
                // Si encontró en empleados, es administrador
                abrirAdministrador();
                this.close();
                return;
            }

            // Luego buscar en clientes
            PreparedStatement stmtCliente = conn.prepareStatement(
                    "SELECT id_cliente FROM Clientes WHERE email = ? AND password = ?");
            stmtCliente.setString(1, correo);
            stmtCliente.setString(2, contraseña);

            ResultSet rsCliente = stmtCliente.executeQuery();
            if (rsCliente.next()) {
                // Si encontró en clientes, es cliente
                abrirCliente();
                this.close();
                return;
            }

            // Si no encontró en ninguno
            mostrarAlerta("Error", "Correo o contraseña incorrectos.");

        } catch (SQLException ex) {
            ex.printStackTrace();
            mostrarAlerta("Error de conexión", "No se pudo conectar a la base de datos.");
        }
    }


    private void abrirCliente() {
        VentanaCliente clienteWindow = new VentanaCliente();
        clienteWindow.show();
    }

    private void abrirAdministrador() {
        VentanaPrincipal adminWindow = new VentanaPrincipal(id_employes);
        adminWindow.show();
    }

    private void abrirRegistro() {
        RegistroCliente registroWindow = new RegistroCliente();
        registroWindow.show();
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}

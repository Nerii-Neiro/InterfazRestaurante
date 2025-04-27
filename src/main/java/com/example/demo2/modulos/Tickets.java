package com.example.demo2.modulos;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.util.List;

public class Tickets {

    private Stage stage;
    private String nombreCliente;
    private String correoCliente;
    private String nombreEmpleado;

    private String numeroMesa;
    private List<ProductosDAO> productos;
    private double total;
    private String fechaOrden;
    private String HoraOrden;

    public Tickets(Stage stage, String nombreCliente, String correoCliente, String nombreEmpleado, String numeroMesa, List<ProductosDAO> productos, double total, String fechaHoraOrden,String HoraOrden) {
        this.stage = stage;
        this.nombreCliente = nombreCliente;
        this.correoCliente = correoCliente;
        this.nombreEmpleado = nombreEmpleado;
        this.numeroMesa = numeroMesa;
        this.productos = productos;
        this.total = total;
        this.fechaOrden = fechaHoraOrden;
        this.HoraOrden= HoraOrden;
    }

    public void generarTicketTXT() {

        LocalDate fechaActual = LocalDate.now();
        LocalTime horaActual = LocalTime.now();

        String fechaHoraActual = fechaActual.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String horaActualStr = horaActual.format(DateTimeFormatter.ofPattern("HH:mm:ss"));

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Guardar Ticket como TXT");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivo de Texto (*.txt)", "*.txt"));
        File file = fileChooser.showSaveDialog(stage);
        
        if (file != null) {
            if (!file.getName().toLowerCase().endsWith(".txt")) {
                file = new File(file.getAbsolutePath() + ".txt");
            }

            try (FileWriter writer = new FileWriter(file)) {
                writer.write("=== Ticket de Compra ===\n\n");
                writer.write("Fecha y Hora Actual: " + fechaHoraActual + " " + horaActualStr + "\n");
                writer.write("Fecha y Hora de Orden: " + fechaOrden + " " +HoraOrden + "\n\n");
                writer.write("Cliente: " + nombreCliente + "\n");
                writer.write("Correo: " + correoCliente + "\n");
                writer.write("Empleado: " + nombreEmpleado + "\n");
                writer.write("Mesa: " + numeroMesa + "\n\n");
                
                writer.write("Productos:\n");
                writer.write(" *** Nombre - Cantidad - Precio - ID:\n");
                for (ProductosDAO producto : productos) {
                    writer.write(" --> " + producto.getNombre() + " " + "1 " + producto.getPrecio() +" "+ producto.getId_producto() +"\n");
                }

                writer.write("\nTotal: $" + String.format("%.2f", total) + "\n");
                writer.write("============================\n");

                System.out.println("Ticket TXT creado en: " + file.getAbsolutePath());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

package com.example.demo2.vistas;

import com.example.demo2.modulos.DetOrdenDAO;
import com.example.demo2.modulos.OrdenesDAO;
import com.example.demo2.modulos.ProductosDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Graficas extends Stage {

    private TableView<ProductoVendido> tablaProductosMasVendidos;
    private TableView<VentaDiaria> tablaVentasPorDia;
    private TableView<EmpleadoVentas> tablaEmpleadosConMasVentas;
    
    private BarChart<String, Number> graficaProductos;
    private BarChart<String, Number> graficaVentas;
    private BarChart<String, Number> graficaEmpleados;


    public Graficas() {
        this.setTitle("Estadísticas y Gráficas");
    
        // Inicializar gráficas
        final CategoryAxis xAxisProductos = new CategoryAxis();
        final NumberAxis yAxisProductos = new NumberAxis();
        xAxisProductos.setLabel("Productos");
        yAxisProductos.setLabel("Cantidad Vendida");
        graficaProductos = new BarChart<>(xAxisProductos, yAxisProductos);
        graficaProductos.setTitle("Productos Más Vendidos");
        
        final CategoryAxis xAxisVentas = new CategoryAxis();
        final NumberAxis yAxisVentas = new NumberAxis();
        xAxisVentas.setLabel("Fecha");
        yAxisVentas.setLabel("Total Ventas");
        graficaVentas = new BarChart<>(xAxisVentas, yAxisVentas);
        graficaVentas.setTitle("Ventas por Día");
        
        final CategoryAxis xAxisEmpleados = new CategoryAxis();
        final NumberAxis yAxisEmpleados = new NumberAxis();
        xAxisEmpleados.setLabel("Empleados");
        yAxisEmpleados.setLabel("Ventas Realizadas");
        graficaEmpleados = new BarChart<>(xAxisEmpleados, yAxisEmpleados);
        graficaEmpleados.setTitle("Empleados con Más Ventas");
    
        // Crear las tablas
        tablaProductosMasVendidos = crearTablaProductos();
        tablaVentasPorDia = crearTablaVentas();
        tablaEmpleadosConMasVentas = crearTablaEmpleados();
    
        // Cargar datos
        cargarDatosProductosMasVendidos();
        cargarDatosVentasPorDia();
        cargarDatosEmpleadosConMasVentas();
    
        Button exportButton = new Button("Exportar a PDF");
        exportButton.getStyleClass().add("botones-exit");
        exportButton.setOnAction(e -> {
            try {
                exportTablesToPDF(this);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    
        Button closeButton = new Button("Cerrar");
        closeButton.getStyleClass().add("botones-exit");
        closeButton.setOnAction(e -> this.close());
    
        HBox buttonBox = new HBox(10, exportButton, closeButton);
        buttonBox.setPadding(new Insets(10));
    
        // Secciones con título y contenido
        Label labelProductos = new Label("Productos Más Vendidos");
        labelProductos.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");
        VBox seccionProductos = new VBox(10, labelProductos, graficaProductos, tablaProductosMasVendidos);
        
        Label labelVentas = new Label("Ventas por Día");
        labelVentas.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");
        VBox seccionVentas = new VBox(10, labelVentas, graficaVentas, tablaVentasPorDia);
        
        Label labelEmpleados = new Label("Empleados con Más Ventas");
        labelEmpleados.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");
        VBox seccionEmpleados = new VBox(10, labelEmpleados, graficaEmpleados, tablaEmpleadosConMasVentas);
    
        VBox vbox = new VBox(10, buttonBox, seccionProductos, seccionVentas, seccionEmpleados);
        vbox.setSpacing(20);
        vbox.setPadding(new Insets(10));
        vbox.getStyleClass().add("fondo_interf");
        ScrollPane scroll_2 = new ScrollPane(vbox);
        //scroll_2.setPrefHeight(400);
        scroll_2.setFitToWidth(true);
        scroll_2.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
    
        Scene scene = new Scene(scroll_2, 800, 900);
        scene.getStylesheets().add(getClass().getResource("/css/estilo_principal.css").toString());
        this.setScene(scene);
        this.show();
    }
    
    private void cargarDatosProductosMasVendidos() {
        // Obtener datos de productos vendidos
        DetOrdenDAO detOrdenDAO = new DetOrdenDAO();
        ObservableList<DetOrdenDAO> detallesOrdenes = detOrdenDAO.SELECT();
        
        // Agrupar por producto y sumar cantidades
        Map<Integer, Integer> ventasPorProducto = new HashMap<>();
        Map<Integer, String> nombresProductos = new HashMap<>();
        
        for (DetOrdenDAO detalle : detallesOrdenes) {
            int idProducto = detalle.getId_producto();
            int cantidad = detalle.getCantidad();
            
            ventasPorProducto.put(idProducto, ventasPorProducto.getOrDefault(idProducto, 0) + cantidad);
            
            // Obtener nombre del producto
            ProductosDAO productoDAO = new ProductosDAO();
            productoDAO.setId_producto(idProducto);
            ObservableList<ProductosDAO> productos = productoDAO.SELECT();
            if (!productos.isEmpty()) {
                nombresProductos.put(idProducto, productos.get(0).getNombre());
            }
        }
        
        // Ordenar por cantidad vendida (mayor a menor)
        Map<Integer, Integer> productosOrdenados = ventasPorProducto.entrySet()
                .stream()
                .sorted(Map.Entry.<Integer, Integer>comparingByValue().reversed())
                .limit(10) // Mostrar solo los 10 más vendidos
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
        
        // Añadir a la tabla y la gráfica
        ObservableList<ProductoVendido> productosList = FXCollections.observableArrayList();
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Cantidad Vendida");
        
        for (Map.Entry<Integer, Integer> entry : productosOrdenados.entrySet()) {
            int idProducto = entry.getKey();
            int cantidadVendida = entry.getValue();
            String nombreProducto = nombresProductos.getOrDefault(idProducto, "Producto " + idProducto);
            
            productosList.add(new ProductoVendido(idProducto, nombreProducto, cantidadVendida));
            series.getData().add(new XYChart.Data<>(nombreProducto, cantidadVendida));
        }
        
        tablaProductosMasVendidos.setItems(productosList);
        graficaProductos.getData().add(series);
    }
    
    private void cargarDatosVentasPorDia() {
        // Obtener datos de ventas por día
        OrdenesDAO ordenesDAO = new OrdenesDAO();
        ObservableList<OrdenesDAO> ordenes = ordenesDAO.SELECT();
        
        // Agrupar por fecha y sumar totales
        Map<String, Double> ventasPorDia = new HashMap<>();
        
        for (OrdenesDAO orden : ordenes) {
            String fecha = orden.getFecha();
            double total = orden.getTotal();
            
            ventasPorDia.put(fecha, ventasPorDia.getOrDefault(fecha, 0.0) + total);
        }
        
        // Ordenar por fecha
        Map<String, Double> ventasOrdenadas = ventasPorDia.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .limit(10) // Mostrar solo los últimos 10 días
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
        
        // Añadir a la tabla y la gráfica
        ObservableList<VentaDiaria> ventasList = FXCollections.observableArrayList();
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Total Ventas");
        
        for (Map.Entry<String, Double> entry : ventasOrdenadas.entrySet()) {
            String fecha = entry.getKey();
            double total = entry.getValue();
            
            ventasList.add(new VentaDiaria(fecha, total));
            series.getData().add(new XYChart.Data<>(fecha, total));
        }
        
        tablaVentasPorDia.setItems(ventasList);
        graficaVentas.getData().add(series);
    }
    
    private void cargarDatosEmpleadosConMasVentas() {
        // Obtener datos de ventas por empleado
        OrdenesDAO ordenesDAO = new OrdenesDAO();
        ObservableList<OrdenesDAO> ordenes = ordenesDAO.SELECT();
        
        // Agrupar por empleado y contar órdenes
        Map<Integer, Integer> ventasPorEmpleado = new HashMap<>();
        
        for (OrdenesDAO orden : ordenes) {
            int idEmpleado = orden.getId_empleado();
            ventasPorEmpleado.put(idEmpleado, ventasPorEmpleado.getOrDefault(idEmpleado, 0) + 1);
        }
        
        // Ordenar por cantidad de ventas (mayor a menor)
        Map<Integer, Integer> empleadosOrdenados = ventasPorEmpleado.entrySet()
                .stream()
                .sorted(Map.Entry.<Integer, Integer>comparingByValue().reversed())
                .limit(10) // Mostrar solo los 10 con más ventas
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
        
        // Añadir a la tabla y la gráfica
        ObservableList<EmpleadoVentas> empleadosList = FXCollections.observableArrayList();
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Ventas Realizadas");
        
        for (Map.Entry<Integer, Integer> entry : empleadosOrdenados.entrySet()) {
            int idEmpleado = entry.getKey();
            int cantidadVentas = entry.getValue();
            
            empleadosList.add(new EmpleadoVentas(idEmpleado, "Empleado " + idEmpleado, cantidadVentas));
            series.getData().add(new XYChart.Data<>("Empleado " + idEmpleado, cantidadVentas));
        }
        
        tablaEmpleadosConMasVentas.setItems(empleadosList);
        graficaEmpleados.getData().add(series);
    }

    private TableView<ProductoVendido> crearTablaProductos() {
        TableView<ProductoVendido> tabla = new TableView<>();
        
        TableColumn<ProductoVendido, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("idProducto"));
        
        TableColumn<ProductoVendido, String> nombreCol = new TableColumn<>("Producto");
        nombreCol.setCellValueFactory(new PropertyValueFactory<>("nombreProducto"));
        nombreCol.setPrefWidth(250);
        
        TableColumn<ProductoVendido, Integer> cantidadCol = new TableColumn<>("Cantidad Vendida");
        cantidadCol.setCellValueFactory(new PropertyValueFactory<>("cantidadVendida"));
        
        tabla.getColumns().addAll(idCol, nombreCol, cantidadCol);
        tabla.setPrefHeight(200);
        
        return tabla;
    }
    
    private TableView<VentaDiaria> crearTablaVentas() {
        TableView<VentaDiaria> tabla = new TableView<>();
        
        TableColumn<VentaDiaria, String> fechaCol = new TableColumn<>("Fecha");
        fechaCol.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        fechaCol.setPrefWidth(200);
        
        TableColumn<VentaDiaria, Double> totalCol = new TableColumn<>("Total Ventas");
        totalCol.setCellValueFactory(new PropertyValueFactory<>("totalVentas"));
        totalCol.setPrefWidth(200);
        
        tabla.getColumns().addAll(fechaCol, totalCol);
        tabla.setPrefHeight(200);
        
        return tabla;
    }
    
    private TableView<EmpleadoVentas> crearTablaEmpleados() {
        TableView<EmpleadoVentas> tabla = new TableView<>();
        
        TableColumn<EmpleadoVentas, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("idEmpleado"));
        
        TableColumn<EmpleadoVentas, String> nombreCol = new TableColumn<>("Empleado");
        nombreCol.setCellValueFactory(new PropertyValueFactory<>("nombreEmpleado"));
        nombreCol.setPrefWidth(250);
        
        TableColumn<EmpleadoVentas, Integer> ventasCol = new TableColumn<>("Ventas Realizadas");
        ventasCol.setCellValueFactory(new PropertyValueFactory<>("ventasRealizadas"));
        
        tabla.getColumns().addAll(idCol, nombreCol, ventasCol);
        tabla.setPrefHeight(200);
        
        return tabla;
    }

    private void exportTablesToPDF(Stage stage) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivo PDF (*.pdf)", "*.pdf"));
        File file = fileChooser.showSaveDialog(stage);
    
        if (file != null) {
            PDDocument document = new PDDocument();
            PDPage page = new PDPage();
            document.addPage(page);
    
            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 14);
            contentStream.beginText();
            contentStream.newLineAtOffset(50, 750);
    
            // Título del reporte
            contentStream.showText("INFORME DE ESTADÍSTICAS DEL RESTAURANTE");
            contentStream.newLineAtOffset(0, -30);
            
            // Fecha actual
            contentStream.setFont(PDType1Font.HELVETICA, 12);
            contentStream.showText("Fecha de generación: " + LocalDate.now());
            contentStream.newLineAtOffset(0, -30);
    
            // Productos más vendidos
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
            contentStream.showText("PRODUCTOS MÁS VENDIDOS:");
            contentStream.newLineAtOffset(0, -20);
            contentStream.setFont(PDType1Font.HELVETICA, 10);
            writeProductosTableToPDF(contentStream, tablaProductosMasVendidos);
    
            // Ventas por día
            contentStream.newLineAtOffset(0, -40);
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
            contentStream.showText("VENTAS POR DÍA:");
            contentStream.newLineAtOffset(0, -20);
            contentStream.setFont(PDType1Font.HELVETICA, 10);
            writeVentasTableToPDF(contentStream, tablaVentasPorDia);
    
            // Empleados con más ventas
            contentStream.newLineAtOffset(0, -40);
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
            contentStream.showText("EMPLEADOS CON MÁS VENTAS:");
            contentStream.newLineAtOffset(0, -20);
            contentStream.setFont(PDType1Font.HELVETICA, 10);
            writeEmpleadosTableToPDF(contentStream, tablaEmpleadosConMasVentas);
    
            contentStream.endText();
            contentStream.close();
    
            document.save(file);
            document.close();
    
            System.out.println("PDF guardado en: " + file.getAbsolutePath());
        }
    }
    
    private void writeProductosTableToPDF(PDPageContentStream contentStream, TableView<ProductoVendido> tabla) throws IOException {
        for (ProductoVendido producto : tabla.getItems()) {
            contentStream.showText(producto.getIdProducto() + " - " + producto.getNombreProducto() + 
                                  ": " + producto.getCantidadVendida() + " unidades");
            contentStream.newLineAtOffset(0, -15);
        }
    }
    
    private void writeVentasTableToPDF(PDPageContentStream contentStream, TableView<VentaDiaria> tabla) throws IOException {
        for (VentaDiaria venta : tabla.getItems()) {
            contentStream.showText("Fecha: " + venta.getFecha() + " - Total: $" + String.format("%.2f", venta.getTotalVentas()));
            contentStream.newLineAtOffset(0, -15);
        }
    }
    
    private void writeEmpleadosTableToPDF(PDPageContentStream contentStream, TableView<EmpleadoVentas> tabla) throws IOException {
        for (EmpleadoVentas empleado : tabla.getItems()) {
            contentStream.showText(empleado.getIdEmpleado() + " - " + empleado.getNombreEmpleado() + 
                                  ": " + empleado.getVentasRealizadas() + " ventas");
            contentStream.newLineAtOffset(0, -15);
        }
    }

    // Clases modelo para las tablas
    public static class ProductoVendido {
        private int idProducto;
        private String nombreProducto;
        private int cantidadVendida;
    
        public ProductoVendido(int idProducto, String nombreProducto, int cantidadVendida) {
            this.idProducto = idProducto;
            this.nombreProducto = nombreProducto;
            this.cantidadVendida = cantidadVendida;
        }
    
        public int getIdProducto() {
            return idProducto;
        }
    
        public String getNombreProducto() {
            return nombreProducto;
        }
    
        public int getCantidadVendida() {
            return cantidadVendida;
        }
    }
    
    public static class VentaDiaria {
        private String fecha;
        private double totalVentas;
    
        public VentaDiaria(String fecha, double totalVentas) {
            this.fecha = fecha;
            this.totalVentas = totalVentas;
        }
    
        public String getFecha() {
            return fecha;
        }
    
        public double getTotalVentas() {
            return totalVentas;
        }
    }
    
    public static class EmpleadoVentas {
        private int idEmpleado;
        private String nombreEmpleado;
        private int ventasRealizadas;
    
        public EmpleadoVentas(int idEmpleado, String nombreEmpleado, int ventasRealizadas) {
            this.idEmpleado = idEmpleado;
            this.nombreEmpleado = nombreEmpleado;
            this.ventasRealizadas = ventasRealizadas;
        }
    
        public int getIdEmpleado() {
            return idEmpleado;
        }
    
        public String getNombreEmpleado() {
            return nombreEmpleado;
        }
    
        public int getVentasRealizadas() {
            return ventasRealizadas;
        }
    }
}

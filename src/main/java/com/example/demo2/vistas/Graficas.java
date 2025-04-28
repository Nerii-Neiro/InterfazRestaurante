package com.example.demo2.vistas;

import com.example.demo2.modulos.DetOrdenDAO;
import com.example.demo2.modulos.OrdenesDAO;
import com.example.demo2.modulos.ProductosDAO;
import com.example.demo2.modulos.EmpleadosDAO; // Nuevo import necesario
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
        scroll_2.setFitToWidth(true);
        scroll_2.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        Scene scene = new Scene(scroll_2, 800, 900);
        scene.getStylesheets().add(getClass().getResource("/css/estilo_principal.css").toString());
        this.setScene(scene);
        this.show();
    }

    private void cargarDatosProductosMasVendidos() {
        DetOrdenDAO detOrdenDAO = new DetOrdenDAO();
        ObservableList<DetOrdenDAO> detallesOrdenes = detOrdenDAO.SELECT();

        // Agrupar por producto y sumar cantidades
        Map<Integer, Integer> ventasPorProducto = new HashMap<>();
        Map<Integer, String> nombresProductos = new HashMap<>();

        // Primero obtener todos los productos únicos para minimizar consultas a la base de datos
        ProductosDAO productoDAO = new ProductosDAO();
        ObservableList<ProductosDAO> todosProductos = productoDAO.SELECT();
        Map<Integer, String> mapaNombresProductos = todosProductos.stream()
                .collect(Collectors.toMap(ProductosDAO::getId_producto, ProductosDAO::getNombre));

        for (DetOrdenDAO detalle : detallesOrdenes) {
            int idProducto = detalle.getId_producto();
            int cantidad = detalle.getCantidad();

            ventasPorProducto.put(idProducto, ventasPorProducto.getOrDefault(idProducto, 0) + cantidad);

            // Obtener nombre del producto del mapa
            nombresProductos.put(idProducto, mapaNombresProductos.getOrDefault(idProducto, "Producto " + idProducto));
        }

        // Ordenar por cantidad vendida (mayor a menor)
        Map<Integer, Integer> productosOrdenados = ventasPorProducto.entrySet()
                .stream()
                .sorted(Map.Entry.<Integer, Integer>comparingByValue().reversed())
                .limit(10)
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
            String nombreProducto = nombresProductos.get(idProducto);

            productosList.add(new ProductoVendido(idProducto, nombreProducto, cantidadVendida));
            series.getData().add(new XYChart.Data<>(nombreProducto, cantidadVendida));
        }

        tablaProductosMasVendidos.setItems(productosList);
        graficaProductos.getData().clear(); // Limpiar datos anteriores
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
                .limit(10)
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
        graficaVentas.getData().clear();
        graficaVentas.getData().add(series);
    }

    private void cargarDatosEmpleadosConMasVentas() {
        OrdenesDAO ordenesDAO = new OrdenesDAO();
        ObservableList<OrdenesDAO> ordenes = ordenesDAO.SELECT();

        // Agrupar por empleado y contar órdenes
        Map<Integer, Integer> ventasPorEmpleado = new HashMap<>();
        Map<Integer, String> nombresEmpleados = new HashMap<>();

        // Primero obtener todos los empleados para minimizar consultas
        EmpleadosDAO empleadoDAO = new EmpleadosDAO();
        ObservableList<EmpleadosDAO> todosEmpleados = empleadoDAO.SELECT();
        Map<Integer, String> mapaNombresEmpleados = todosEmpleados.stream()
                .collect(Collectors.toMap(EmpleadosDAO::getId_empleado, e -> e.getNombre()));

        for (OrdenesDAO orden : ordenes) {
            int idEmpleado = orden.getId_empleado();
            ventasPorEmpleado.put(idEmpleado, ventasPorEmpleado.getOrDefault(idEmpleado, 0) + 1);

            // Obtener nombre del empleado del mapa
            nombresEmpleados.put(idEmpleado, mapaNombresEmpleados.getOrDefault(idEmpleado, "Empleado " + idEmpleado));
        }

        // Ordenar por cantidad de ventas (mayor a menor)
        Map<Integer, Integer> empleadosOrdenados = ventasPorEmpleado.entrySet()
                .stream()
                .sorted(Map.Entry.<Integer, Integer>comparingByValue().reversed())
                .limit(10)
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
            String nombreEmpleado = nombresEmpleados.get(idEmpleado);

            empleadosList.add(new EmpleadoVentas(idEmpleado, nombreEmpleado, cantidadVentas));
            series.getData().add(new XYChart.Data<>(nombreEmpleado, cantidadVentas));
        }

        tablaEmpleadosConMasVentas.setItems(empleadosList);
        graficaEmpleados.getData().clear();
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

            // Configuración inicial
            float margin = 50;
            float yPosition = 750;
            float tableWidth = page.getMediaBox().getWidth() - 2 * margin;
            float rowHeight = 20;
            float cellMargin = 5;

            // Título del reporte
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 14);
            contentStream.beginText();
            contentStream.newLineAtOffset(margin, yPosition);
            contentStream.showText("INFORME DE ESTADÍSTICAS DEL RESTAURANTE");
            contentStream.endText();
            yPosition -= 30;

            // Fecha actual
            contentStream.setFont(PDType1Font.HELVETICA, 12);
            contentStream.beginText();
            contentStream.newLineAtOffset(margin, yPosition);
            contentStream.showText("Fecha de generación: " + LocalDate.now());
            contentStream.endText();
            yPosition -= 40;

            // Productos más vendidos
            drawTableTitle(contentStream, "PRODUCTOS MÁS VENDIDOS:", margin, yPosition);
            yPosition -= 25;
            drawProductosTable(contentStream, tablaProductosMasVendidos, margin, yPosition, tableWidth, rowHeight, cellMargin);
            yPosition -= (tablaProductosMasVendidos.getItems().size() + 1) * rowHeight + 20;

            // Ventas por día
            drawTableTitle(contentStream, "VENTAS POR DÍA:", margin, yPosition);
            yPosition -= 25;
            drawVentasTable(contentStream, tablaVentasPorDia, margin, yPosition, tableWidth, rowHeight, cellMargin);
            yPosition -= (tablaVentasPorDia.getItems().size() + 1) * rowHeight + 20;

            // Empleados con más ventas
            drawTableTitle(contentStream, "EMPLEADOS CON MÁS VENTAS:", margin, yPosition);
            yPosition -= 25;
            drawEmpleadosTable(contentStream, tablaEmpleadosConMasVentas, margin, yPosition, tableWidth, rowHeight, cellMargin);

            contentStream.close();
            document.save(file);
            document.close();

            System.out.println("PDF guardado en: " + file.getAbsolutePath());
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

    private void drawTableTitle(PDPageContentStream contentStream, String title, float x, float y) throws IOException {
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
        contentStream.beginText();
        contentStream.newLineAtOffset(x, y);
        contentStream.showText(title);
        contentStream.endText();
    }

    private void drawProductosTable(PDPageContentStream contentStream, TableView<ProductoVendido> tabla,
                                    float x, float y, float tableWidth, float rowHeight, float cellMargin) throws IOException {
        final int numCols = 3;
        final float colWidth = tableWidth / numCols;

        // Dibujar encabezados
        String[] headers = {"ID", "Producto", "Cantidad Vendida"};
        drawTableRow(contentStream, x, y, colWidth, rowHeight, cellMargin, headers, true);
        y -= rowHeight;

        // Dibujar filas de datos
        for (ProductoVendido producto : tabla.getItems()) {
            String[] rowData = {
                    String.valueOf(producto.getIdProducto()),
                    producto.getNombreProducto(),
                    String.valueOf(producto.getCantidadVendida())
            };
            drawTableRow(contentStream, x, y, colWidth, rowHeight, cellMargin, rowData, false);
            y -= rowHeight;
        }
    }

    private void drawVentasTable(PDPageContentStream contentStream, TableView<VentaDiaria> tabla,
                                 float x, float y, float tableWidth, float rowHeight, float cellMargin) throws IOException {
        final int numCols = 2;
        final float colWidth = tableWidth / numCols;

        // Dibujar encabezados
        String[] headers = {"Fecha", "Total Ventas"};
        drawTableRow(contentStream, x, y, colWidth, rowHeight, cellMargin, headers, true);
        y -= rowHeight;

        // Dibujar filas de datos
        for (VentaDiaria venta : tabla.getItems()) {
            String[] rowData = {
                    venta.getFecha(),
                    String.format("$%.2f", venta.getTotalVentas())
            };
            drawTableRow(contentStream, x, y, colWidth, rowHeight, cellMargin, rowData, false);
            y -= rowHeight;
        }
    }

    private void drawEmpleadosTable(PDPageContentStream contentStream, TableView<EmpleadoVentas> tabla,
                                    float x, float y, float tableWidth, float rowHeight, float cellMargin) throws IOException {
        final int numCols = 3;
        final float colWidth = tableWidth / numCols;

        // Dibujar encabezados
        String[] headers = {"ID", "Empleado", "Ventas Realizadas"};
        drawTableRow(contentStream, x, y, colWidth, rowHeight, cellMargin, headers, true);
        y -= rowHeight;

        // Dibujar filas de datos
        for (EmpleadoVentas empleado : tabla.getItems()) {
            String[] rowData = {
                    String.valueOf(empleado.getIdEmpleado()),
                    empleado.getNombreEmpleado(),
                    String.valueOf(empleado.getVentasRealizadas())
            };
            drawTableRow(contentStream, x, y, colWidth, rowHeight, cellMargin, rowData, false);
            y -= rowHeight;
        }
    }

    private void drawTableRow(PDPageContentStream contentStream, float x, float y,
                              float colWidth, float rowHeight, float cellMargin,
                              String[] text, boolean isHeader) throws IOException {
        // Dibujar las líneas de la tabla
        contentStream.setLineWidth(0.5f);

        // Línea horizontal superior
        contentStream.moveTo(x, y);
        contentStream.lineTo(x + colWidth * text.length, y);
        contentStream.stroke();

        // Línea horizontal inferior
        contentStream.moveTo(x, y - rowHeight);
        contentStream.lineTo(x + colWidth * text.length, y - rowHeight);
        contentStream.stroke();

        // Líneas verticales
        for (int i = 0; i <= text.length; i++) {
            contentStream.moveTo(x + i * colWidth, y);
            contentStream.lineTo(x + i * colWidth, y - rowHeight);
            contentStream.stroke();
        }

        // Escribir el texto en las celdas
        contentStream.setFont(isHeader ? PDType1Font.HELVETICA_BOLD : PDType1Font.HELVETICA, 10);
        for (int i = 0; i < text.length; i++) {
            String cellText = text[i];
            if (cellText.length() > 20) { // Truncar texto muy largo
                cellText = cellText.substring(0, 17) + "...";
            }

            contentStream.beginText();
            contentStream.newLineAtOffset(x + i * colWidth + cellMargin, y - rowHeight + cellMargin);
            contentStream.showText(cellText);
            contentStream.endText();
        }
    }

}

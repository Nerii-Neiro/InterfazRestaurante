package com.example.demo2.vistas;


import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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

public class Graficas extends Stage {

    private TableView<Person> table1;
    private TableView<Person> table2;
    private TableView<Person> table3;


    public Graficas() {
        this.setTitle("Tablas/Graficas");


        table1 = createTable();
        table2 = createTable();
        table3 = createTable();


        table1.getItems().addAll(new Person("Juan", "Pérez"), new Person("Ana", "Gómez"));
        table2.getItems().addAll(new Person("Luis", "Ramírez"), new Person("María", "López"));
        table3.getItems().addAll(new Person("Carlos", "Fernández"), new Person("Laura", "Martínez"));


        Button exportButton = new Button("Exportar a PDF");
        exportButton.setOnAction(e -> {
            try {
                exportTablesToPDF(this);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });


        Button closeButton = new Button("Cerrar");
        closeButton.setOnAction(e -> this.close());


        HBox buttonBox = new HBox(10, exportButton, closeButton);


        VBox vbox = new VBox(10, buttonBox, table1, table2, table3);
        vbox.setSpacing(10);

        Scene scene = new Scene(vbox, 600, 700);
        this.setScene(scene);
        this.show();
    }

    private TableView<Person> createTable() {
        TableView<Person> table = new TableView<>();

        TableColumn<Person, String> firstNameCol = new TableColumn<>("Nombre");
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));

        TableColumn<Person, String> lastNameCol = new TableColumn<>("Apellido");
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        table.getColumns().addAll(firstNameCol, lastNameCol);
        table.setPrefHeight(150);

        return table;
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
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
            contentStream.beginText();
            contentStream.newLineAtOffset(50, 750);


            contentStream.showText("Tabla 1:");
            contentStream.newLineAtOffset(0, -20);
            writeTableToPDF(contentStream, table1);

            contentStream.newLineAtOffset(0, -40);
            contentStream.showText("Tabla 2:");
            contentStream.newLineAtOffset(0, -20);
            writeTableToPDF(contentStream, table2);

            contentStream.newLineAtOffset(0, -40);
            contentStream.showText("Tabla 3:");
            contentStream.newLineAtOffset(0, -20);
            writeTableToPDF(contentStream, table3);

            contentStream.endText();
            contentStream.close();


            document.save(file);
            document.close();

            System.out.println("PDF guardado en: " + file.getAbsolutePath());
        }
    }

    private void writeTableToPDF(PDPageContentStream contentStream, TableView<Person> table) throws IOException {
        int yOffset = 0;
        for (Person person : table.getItems()) {
            contentStream.showText(person.getFirstName() + " " + person.getLastName());
            contentStream.newLineAtOffset(0, -20);
            yOffset += 20;
        }
    }

    public static class Person {
        private String firstName;
        private String lastName;

        public Person(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }
    }
}

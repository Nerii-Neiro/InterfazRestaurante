module com.example.demo2 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires java.desktop;

    opens com.example.demo2 to javafx.fxml;
    requires org.kordamp.bootstrapfx.core;
    exports com.example.demo2;
    requires mysql.connector.j;
    requires java.sql;
    opens com.example.demo2.modulos;
}
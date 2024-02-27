module com.strikkeapp.strikkeapp {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires com.microsoft.sqlserver.jdbc;
    requires java.sql;

    opens com.strikkeapp.strikkeapp to javafx.fxml;
    exports com.strikkeapp.strikkeapp;
    exports com.strikkeapp.strikkeapp.controllers;
    opens com.strikkeapp.strikkeapp.controllers to javafx.fxml;
}
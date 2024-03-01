module com.strikkeapp.strikkeapp {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires com.microsoft.sqlserver.jdbc;
    requires java.sql;

    exports com.strikkeapp.strikkeapp;
    opens com.strikkeapp.strikkeapp to javafx.fxml;
    exports com.strikkeapp.strikkeapp.controllers;
    opens com.strikkeapp.strikkeapp.controllers to javafx.fxml;
    exports com.strikkeapp.strikkeapp.controllers.loginControllers;
    opens com.strikkeapp.strikkeapp.controllers.loginControllers to javafx.fxml;
    exports com.strikkeapp.strikkeapp.dbo;
    opens com.strikkeapp.strikkeapp.dbo to javafx.fxml;
}
module com.strikkeapp.strikkeapp {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.strikkeapp.strikkeapp to javafx.fxml;
    exports com.strikkeapp.strikkeapp;
}
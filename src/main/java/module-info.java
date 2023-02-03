open module airhacks.control {
    requires jakarta.persistence;
    requires java.annotation;
    requires javafx.controls;
    requires javafx.fxml;
    requires javax.inject;
    requires org.apache.derby.engine;
    requires eclipselink;
    requires afterburner.fx;
    requires jakarta.activation;

    exports com.airhacks.control;
}
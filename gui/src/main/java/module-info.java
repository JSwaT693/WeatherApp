module gui {
    requires client;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens pl.edu.pwr.jswatowski.Lab4.gui to javafx.fxml;
    exports pl.edu.pwr.jswatowski.Lab4.gui;
}
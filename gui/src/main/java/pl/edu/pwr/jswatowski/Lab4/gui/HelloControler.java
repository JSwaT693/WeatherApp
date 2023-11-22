package pl.edu.pwr.jswatowski.Lab4.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import pl.edu.pwr.jswatowski.lab4.client.Client;

public class HelloControler {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        Client.name = "Swat";
        welcomeText.setText("Welcome to JavaFX Application " + Client.name + "!");
    }
}

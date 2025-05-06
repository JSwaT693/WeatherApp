package pl.edu.pwr.jswatowski.Lab4.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    public static void main( String[] args ) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        var fxmlLoader = new FXMLLoader(App.class.getResource("/menu.fxml"));
        var scene = new Scene(fxmlLoader.load());
        stage.setTitle("Menu");
        stage.setScene(scene);
        stage.show();
    }
}

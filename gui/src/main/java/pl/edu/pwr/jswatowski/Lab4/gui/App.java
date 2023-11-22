package pl.edu.pwr.jswatowski.Lab4.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.edu.pwr.jswatowski.lab4.client.data.Station;
import pl.edu.pwr.jswatowski.lab4.client.repository.API;
import pl.edu.pwr.jswatowski.lab4.client.repository.DataBase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App extends Application {
    public static void main( String[] args ) throws SQLException {
        API api = new API();
        DataBase db = new DataBase();
        db.addStations();
        List<Station> stationList = new ArrayList<>();
        for (Station station : stationList) {
            System.out.println(station);
        }

        //launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
}

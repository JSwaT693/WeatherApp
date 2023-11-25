package pl.edu.pwr.jswatowski.Lab4.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.edu.pwr.jswatowski.lab4.client.repository.API;
import pl.edu.pwr.jswatowski.lab4.client.repository.DataBase;

import java.sql.SQLException;

/**
 * Hello world!
 *
 */
public class App extends Application {
    public static void main( String[] args ) throws SQLException {
//        API api = new API();
//        DataBase db = new DataBase();
//        db.addStations();
//        List<Station> stationList = new ArrayList<>();
//        stationList = db.getStations();
//        for (Station station : stationList) {
//            System.out.println(station.getName());
//        }

        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Menu");
        stage.setScene(scene);
        stage.show();
    }
}

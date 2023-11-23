package pl.edu.pwr.jswatowski.Lab4.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import pl.edu.pwr.jswatowski.lab4.client.data.Station;

public class MenuController {
    @FXML
    private TableView<Station> stationTableView;
    @FXML
    private TableColumn<Station, Integer> idColumn;
    @FXML
    private TableColumn<Station, String> stationColumn;
    @FXML
    private ScrollBar scrollBar;
    @FXML
    private Button button;

    public void enter(ActionEvent actionEvent) {

    }
}

package pl.edu.pwr.jswatowski.Lab4.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import pl.edu.pwr.jswatowski.lab4.client.data.Station;
import pl.edu.pwr.jswatowski.lab4.client.repository.DataBase;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MenuController implements Initializable {
    private DataBase dataBase = new DataBase();
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
    ObservableList<Station> stationsList = FXCollections.observableList(dataBase.getIDsTownsList());

    public MenuController() throws SQLException {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idColumn.setCellValueFactory(new PropertyValueFactory<Station,Integer>("id"));
        stationColumn.setCellValueFactory(new PropertyValueFactory<Station, String>("name"));

        stationTableView.setItems(stationsList);
    }

    public void enter(ActionEvent actionEvent) {

    }


}

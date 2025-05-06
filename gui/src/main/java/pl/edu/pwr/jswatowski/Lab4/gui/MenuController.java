package pl.edu.pwr.jswatowski.Lab4.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import pl.edu.pwr.jswatowski.lab4.client.data.Station;
import pl.edu.pwr.jswatowski.lab4.client.repository.DataBase;

import java.io.IOException;
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
    ObservableList<Station> stationsList = FXCollections.observableList(dataBase.getIDsTownsList());

    public MenuController() throws SQLException {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TableView.TableViewSelectionModel<Station> selectionModel = stationTableView.getSelectionModel();
        selectionModel.setSelectionMode(SelectionMode.SINGLE);
        stationTableView.setSelectionModel(selectionModel);
        idColumn.setCellValueFactory(new PropertyValueFactory<Station,Integer>("id"));
        stationColumn.setCellValueFactory(new PropertyValueFactory<Station, String>("name"));

        stationTableView.setItems(stationsList);
    }

    @FXML
    public void enter(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        var loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/data.fxml"));
        Parent root = loader.load();
        var scene = new Scene(root);

        DataController controller = loader.getController();
        controller.getStation(stationTableView.getSelectionModel().getSelectedItem());

        stage.setTitle("Data");
        stage.setScene(scene);
    }
    @FXML
    public void refreshData(ActionEvent actionEvent) {
        dataBase.addStations();
        stationTableView.refresh();
    }

}

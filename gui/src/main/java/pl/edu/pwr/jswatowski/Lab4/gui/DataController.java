package pl.edu.pwr.jswatowski.Lab4.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import pl.edu.pwr.jswatowski.lab4.client.data.Station;
import pl.edu.pwr.jswatowski.lab4.client.repository.DataBase;

import java.io.IOException;
import java.sql.SQLException;


public class DataController{
    DataBase dataBase = new DataBase();

    @FXML
    private Button backBtn;

    @FXML
    private TableView<Station> dataTable;

    @FXML
    private TableColumn<Station, String> dateColumn;

    @FXML
    private TableColumn<Station, Double> humidityColumn;

    @FXML
    private Label nameLabel;

    @FXML
    private TableColumn<Station, Double> pressureColumn;

    @FXML
    private TableColumn<Station, Double> rainTotColumn;

    @FXML
    private TableColumn<Station, Double> tempColumn;

    @FXML
    private TableColumn<Station, Integer> timeColumn;

    @FXML
    private TableColumn<Station, Integer> windDirColumn;

    @FXML
    private TableColumn<Station, Integer> windSpeedColumn;
    Station selectedStation;
    ObservableList<Station> dataList;
    ;
    public DataController() throws SQLException {
    }

    public void getStation(Station station) {
        selectedStation = station;

        nameLabel.setText(station.getId() + "  " + station.getName());

        dateColumn.setCellValueFactory(new PropertyValueFactory<Station, String>("date"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<Station, Integer>("time"));
        tempColumn.setCellValueFactory(new PropertyValueFactory<Station, Double>("temperature"));
        windSpeedColumn.setCellValueFactory(new PropertyValueFactory<Station, Integer>("windSpeed"));
        windDirColumn.setCellValueFactory(new PropertyValueFactory<Station, Integer>("windDirection"));
        humidityColumn.setCellValueFactory(new PropertyValueFactory<Station, Double>("humidity"));
        rainTotColumn.setCellValueFactory(new PropertyValueFactory<Station, Double>("rainTotal"));
        pressureColumn.setCellValueFactory(new PropertyValueFactory<Station, Double>("pressure"));

        dataList = FXCollections.observableList(dataBase.getStationData(selectedStation));
        dataTable.setItems(dataList);
    }

    @FXML
    void goBack(ActionEvent event) throws IOException {
        Stage stage = (Stage) backBtn.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Menu");
        stage.setScene(scene);
    }

    public void refreshData(ActionEvent actionEvent) {
        dataBase.addStations();
        getStation(selectedStation);
        dataTable.refresh();
    }

    public void goToCharts(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        var loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/charts.fxml"));
        Parent root = loader.load();
        var scene = new Scene(root);

        ChartsController controller = loader.getController();
        controller.getStation(selectedStation, dataList);
        stage.setTitle("Charts");
        stage.setScene(scene);
    }
}

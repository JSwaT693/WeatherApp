package pl.edu.pwr.jswatowski.Lab4.gui;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import pl.edu.pwr.jswatowski.lab4.client.data.Station;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;

public class ChartsController {
    @FXML
    LineChart<?, ?> dataChart;

    @FXML
    private Button backBtn;

    @FXML
    private Button humidityBtn;

    @FXML
    private Button pressureBtn;

    @FXML
    private Button rainTotBtn;

    @FXML
    private Button tempBtn;

    @FXML
    private Label title;

    @FXML
    private Button windDirBtn;

    @FXML
    private Button windSpeedBtn;
    Station station;
    ObservableList<Station> dataList;

    public void getStation(Station station, ObservableList<Station> dataList) {
        this.station = station;
        this.dataList = dataList;
        title.setText(station.getId() + "  " + station.getName());
        NumberAxis xAxis = ((NumberAxis)dataChart.getXAxis());
        var firstDate = LocalDate.parse(dataList.get(0).getDate());
        xAxis.setTickLabelFormatter(new XAxisLabelConverter(firstDate));
        setTemp();
    }

    public void setTemp() {
        var firstDate = LocalDate.parse(dataList.get(0).getDate());
        dataChart.getData().clear();
        var series = new XYChart.Series();
        dataChart.getYAxis().setLabel("Temperature");
        dataChart.getXAxis().setLabel("Date");

        for (Station data : dataList) {
            var date = setTime(data, firstDate);
            series.getData().add(new XYChart.Data<>(date, data.getTemperature()));
        }
        dataChart.getData().add(series);
    }
    public void setTempAction(ActionEvent actionEvent) {
        setTemp();
    }
    @FXML
    public void setWindSpeed(ActionEvent actionEvent) {
        var firstDate = LocalDate.parse(dataList.get(0).getDate());
        dataChart.getData().clear();
        var series = new XYChart.Series();
        dataChart.getYAxis().setLabel("Wind Speed");
        dataChart.getXAxis().setLabel("Date");
        for (Station data : dataList) {
            var date = setTime(data, firstDate);
            series.getData().add(new XYChart.Data<>(date, data.getWindSpeed()));
        }
        dataChart.getData().add(series);
    }
    @FXML
    public void setWindDir(ActionEvent actionEvent) {
        var firstDate = LocalDate.parse(dataList.get(0).getDate());
        dataChart.getData().clear();
        var series = new XYChart.Series();
        dataChart.getYAxis().setLabel("Wind Direction");
        dataChart.getXAxis().setLabel("Date");
        for (Station data : dataList) {
            var date = setTime(data, firstDate);
            series.getData().add(new XYChart.Data<>(date, data.getWindDirection()));
        }
        dataChart.getData().add(series);
    }
    @FXML
    public void setHumidity(ActionEvent actionEvent) {
        var firstDate = LocalDate.parse(dataList.get(0).getDate());
        dataChart.getData().clear();
        var series = new XYChart.Series();
        dataChart.getYAxis().setLabel("Humidity");
        dataChart.getXAxis().setLabel("Date");
        for (Station data : dataList) {
            var date = setTime(data, firstDate);
            series.getData().add(new XYChart.Data<>(date, data.getHumidity()));
        }
        dataChart.getData().add(series);
    }
    @FXML
    public void setRainTot(ActionEvent actionEvent) {
        var firstDate = LocalDate.parse(dataList.get(0).getDate());
        dataChart.getData().clear();
        var series = new XYChart.Series();
        dataChart.getYAxis().setLabel("Rainfall total");
        dataChart.getXAxis().setLabel("Date");
        for (Station data : dataList) {
            var date = setTime(data, firstDate);
            series.getData().add(new XYChart.Data<>(date, data.getRainTotal()));
        }
        dataChart.getData().add(series);
    }
    @FXML
    public void setPressure(ActionEvent actionEvent) {
        var firstDate = LocalDate.parse(dataList.get(0).getDate());
        dataChart.getData().clear();
        var series = new XYChart.Series();
        dataChart.getYAxis().setLabel("Pressure");
        dataChart.getXAxis().setLabel("Date");
        for (Station data : dataList) {
            var date = setTime(data, firstDate);
            series.getData().add(new XYChart.Data<>(date, data.getPressure()));
        }
        dataChart.getData().add(series);
    }


    @FXML
    void back(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        var loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/data.fxml"));
        Parent root = loader.load();
        var scene = new Scene(root);

        DataController controller = loader.getController();
        controller.getStation(station);

        stage.setTitle("Data");
        stage.setScene(scene);
    }

    private int setTime(Station data, LocalDate firstDate) {
        var date = LocalDate.parse(data.getDate());
        var daysDiff = Period.between(firstDate, date).getDays();
        var dateVal = daysDiff * 24 + data.getTime();
        return dateVal;
    }

}

class XAxisLabelConverter extends StringConverter<Number> {
    LocalDate firstDay;
    public XAxisLabelConverter(LocalDate firstDay) {
        this.firstDay = firstDay;
    }

    @Override
    public String toString(Number number) {
        var days = number.intValue() / 24;
        var hour = number.intValue() % 24;
        var data = firstDay.plusDays(days);
        return data + " " + hour + ":00:00";
    }

    @Override
    public Number fromString(String s) {
        return null;
    }
}


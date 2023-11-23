package pl.edu.pwr.jswatowski.lab4.client.data;

import com.google.gson.annotations.SerializedName;

public class Station {
    public Station(int id, String name, String date, int time, double temperature, int windSpeed,
                   int windDirection, double humidity, double rainTotal, double pressure) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.time = time;
        this.temperature = temperature;
        this.windSpeed = windSpeed;
        this.windDirection = windDirection;
        this.humidity = humidity;
        this.rainTotal = rainTotal;
        this.pressure = pressure;
    }

    public Station(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @SerializedName("id_stacji")
    private int id;
    @SerializedName("stacja")
    private String name;
    @SerializedName("data_pomiaru")
    private String date;
    @SerializedName("godzina_pomiaru")
    private int time;
    @SerializedName("temperatura")
    private double temperature;
    @SerializedName("predkosc_wiatru")
    private int windSpeed;
    @SerializedName("kierunek_wiatru")
    private int windDirection;
    @SerializedName("wilgotnosc_wzgledna")
    private double humidity;
    @SerializedName("suma_opadu")
    private double rainTotal;
    @SerializedName("cisnienie")
    private double pressure;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public int getTime() {
        return time;
    }

    public double getTemperature() {
        return temperature;
    }

    public int getWindSpeed() {
        return windSpeed;
    }

    public int getWindDirection() {
        return windDirection;
    }

    public double getHumidity() {
        return humidity;
    }

    public double getRainTotal() {
        return rainTotal;
    }

    public double getPressure() {
        return pressure;
    }
}
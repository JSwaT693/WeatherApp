package pl.edu.pwr.jswatowski.lab4.client.repository;

import pl.edu.pwr.jswatowski.lab4.client.data.Station;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataBase {
    API api = new API();
    private static final String DATABASE_URL = "jdbc:derby:stationsdb;create=true";
    private final static String TABLE_NAME = "station";
    private final static String FIELD_ID = "id";
    private final static String FIELD_STATION_ID = "stationID";
    private final static String FIELD_NAME = "name";
    private final static String FIELD_DATE = "date";
    private final static String FIELD_TIME = "time";
    private final static String FIELD_TEMP = "temperature";
    private final static String FIELD_WINDSPEED = "windSpeed";
    private final static String FIELD_WINDDIR = "windDirection";
    private final static String FIELD_HUMIDITY = "humidity";
    private final static String FIELD_RAINTOT = "rainfallTotal";
    private final static String FIELD_PRESSURE = "pressure";
    Connection connection = null;
    Statement statement = null;
    private final static String CREATE_TABLE_STATEMENT = String.format("CREATE TABLE %s "
            + "(%s BIGINT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) "
            + ", %s INT"
            + ", %s VARCHAR(128)"
            + ", %s DATE"
            + ", %s INT"
            + ", %s DOUBLE"
            + ", %s INT"
            + ", %s INT"
            + ", %s DOUBLE"
            + ", %s DOUBLE"
            + ", %s DOUBLE)", TABLE_NAME, FIELD_ID, FIELD_STATION_ID, FIELD_NAME, FIELD_DATE, FIELD_TIME, FIELD_TEMP,
            FIELD_WINDSPEED, FIELD_WINDDIR, FIELD_HUMIDITY, FIELD_RAINTOT, FIELD_PRESSURE);
    private final static String SELECT_ALL_QUERY = String.format("SELECT %s, %s, %s, %s, %s, %s, %s, %s, %s, %s" + " FROM %s"
            , FIELD_STATION_ID, FIELD_NAME, FIELD_DATE, FIELD_TIME, FIELD_TEMP, FIELD_WINDSPEED
            , FIELD_WINDDIR, FIELD_HUMIDITY, FIELD_RAINTOT, FIELD_PRESSURE, TABLE_NAME);
    private final static String INSERT_STATEMENT = String.format("INSERT INTO %s(%s, %s, %s, %s, %s, %s, %s, %s, %s, %s) "
                    + "VALUES (%%d, '%%s', '%%s', %%d, %%.2f, %%d, %%d, %%.2f, %%.2f, %%.2f)"
            , TABLE_NAME, FIELD_STATION_ID, FIELD_NAME, FIELD_DATE, FIELD_TIME, FIELD_TEMP, FIELD_WINDSPEED
            , FIELD_WINDDIR, FIELD_HUMIDITY, FIELD_RAINTOT, FIELD_PRESSURE);

    private final static String IF_ALREADY_COLLECTED = String.format("SELECT * FROM %s WHERE %s = %%d AND %s = '%%s' AND %s = %%d"
            , TABLE_NAME, FIELD_STATION_ID, FIELD_DATE, FIELD_TIME);

    private final static String IDS_AND_TOWNS = String.format("SELECT %s, name FROM %s GROUP BY ROLLUP (%s, %s)"
            , FIELD_STATION_ID, TABLE_NAME, FIELD_STATION_ID, FIELD_NAME);

    public DataBase() throws SQLException {
        try {
            connection = DriverManager.getConnection(DATABASE_URL);
            statement = connection.createStatement();
            if (!doesTableExists(TABLE_NAME, connection)) {
                createDB();
            }
        } catch (SQLException e) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void createDB() throws SQLException {
        statement.execute(CREATE_TABLE_STATEMENT);
    }

    public void addStations() {
        Station[] stations = api.getAPI();
        for (int i = 0; i < stations.length; i++) {
            try {
                Station station = stations[i];
                var sql = String.format(Locale.ENGLISH, IF_ALREADY_COLLECTED, station.getId(), station.getDate(), station.getTime());
                ResultSet result = statement.executeQuery(sql);
                if (!result.next()) {
                    sql = String.format(Locale.ENGLISH, INSERT_STATEMENT, station.getId(), station.getName(), station.getDate(), station.getTime(), station.getTemperature()
                            , station.getWindSpeed(), station.getWindDirection(), station.getHumidity(), station.getRainTotal(), station.getPressure());

                    statement.execute(sql);
                }
            } catch (SQLException e) {
                Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }

    public List<Station> getStations() {
        var list = new ArrayList<Station>();
        try {
            var result = statement.executeQuery(SELECT_ALL_QUERY);
            while (result.next()) {
                var id = result.getInt(FIELD_STATION_ID);
                var name = result.getString(FIELD_NAME);
                var date = result.getString(FIELD_DATE);
                var time = result.getInt(FIELD_TIME);
                var temp = result.getDouble(FIELD_TEMP);
                var windSpeed = result.getInt(FIELD_WINDSPEED);
                var windDir = result.getInt(FIELD_WINDDIR);
                var humidity = result.getDouble(FIELD_HUMIDITY);
                var rainTotal = result.getDouble(FIELD_RAINTOT);
                var pressure = result.getDouble(FIELD_PRESSURE);
                list.add(new Station(id, name, date, time, temp, windSpeed, windDir, humidity, rainTotal, pressure));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    private List<Station> getIDsTowns() {
        var list = new ArrayList<Station>();
        try {
            var result = statement.executeQuery(SELECT_ALL_QUERY);
            while (result.next()) {
                var id = result.getInt(FIELD_STATION_ID);
                var name = result.getString(FIELD_NAME);
                list.add(new Station(id, name));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    private static boolean doesTableExists(String tableName, Connection connection) throws SQLException {
        DatabaseMetaData meta = connection.getMetaData();
        ResultSet result = meta.getTables(null, null, tableName.toUpperCase(), null);

        return result.next();
    }

}

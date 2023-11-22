package pl.edu.pwr.jswatowski.lab4.client.repository;

import pl.edu.pwr.jswatowski.lab4.client.data.Station;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataBase {
    API api = new API();
    private static final String DATABASE_URL = "jdbc:derby:stationsdb;create=true";
    private final static String TABLE_NAME = "station";
    private final static String FIELD_ID = "id";
    private final static String FIELD_NAME = "name";
    private final static String FIELD_DATE = "date";
    private final static String FIELD_TIME = "time";
    private final static String FIELD_TEMP = "temperature";
    private final static String FIELD_WINDSPEED = "windSpeed";
    private final static String FIELD_WINDDIR = "windDirection";
    private final static String FIELD_HUMIDITY = "humidity";
    private final static String FIELD_RAINTOT = "rainfallTotal";
    private final static String FIELD_PRESSURE = "pressure";
    Connection conn = null;
    Statement statement = null;
    private final static String CREATE_TABLE_STATEMENT = String.format("CREATE TABLE %s "
            + "(%s int primary key"
            + ", %s VARCHAR(128)"
            + ", %s VARCHAR(128)"
            + ", %s INT"
            + ", %s DOUBLE"
            + ", %s INT"
            + ", %s INT"
            + ", %s DOUBLE"
            + ", %s DOUBLE"
            + ", %s DOUBLE)", TABLE_NAME, FIELD_ID, FIELD_NAME, FIELD_DATE, FIELD_TIME, FIELD_TEMP,
            FIELD_WINDSPEED, FIELD_WINDDIR, FIELD_HUMIDITY, FIELD_RAINTOT, FIELD_PRESSURE);
    private final static String SELECT_ALL_QUERY = String.format("SELECT %s, %s, %s, %s, %s, %s, %s, %s, %s, %s" + " FROM %s"
            , FIELD_ID, FIELD_NAME, FIELD_DATE, FIELD_TIME, FIELD_TEMP, FIELD_WINDSPEED
            , FIELD_WINDDIR, FIELD_HUMIDITY, FIELD_RAINTOT, FIELD_PRESSURE, TABLE_NAME);
    private final static String INSERT_STATEMENT = String.format("INSERT INTO %s(%s, %s, %s, %s, %s, %s, %s, %s, %s, %s) "
                    + "VALUES (%%d, '%%s', '%%s', %%d, %%f, %%d, %%d, %%f, %%f, %%f)"
            , TABLE_NAME, FIELD_ID, FIELD_NAME, FIELD_DATE, FIELD_TIME, FIELD_TEMP, FIELD_WINDSPEED
            , FIELD_WINDDIR, FIELD_HUMIDITY, FIELD_RAINTOT, FIELD_PRESSURE);

    //private final static String IF_ALREADY_COLLECTED = String.format("WHERE ")

    public DataBase() throws SQLException {
        try {
            conn = DriverManager.getConnection(DATABASE_URL);
            statement = conn.createStatement();
            if (!doesTableExists(TABLE_NAME, conn)) {
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
            var sql = String.format(INSERT_STATEMENT, station.getId(), station.getName(), station.getDate(), station.getTime(), station.getTemperature()
                    , station.getWindSpeed(), station.getWindDirection(), station.getHumidity(), station.getRainTotal(), station.getPressure());

            statement.execute(sql);
            } catch (SQLException e) {
                Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }

    public List<Station> getTowns() {
        var list = new ArrayList<Station>();
        try {
            var result = statement.executeQuery(SELECT_ALL_QUERY);
            while (result.next()) {
                var id = result.getInt(FIELD_ID);
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

    private static boolean doesTableExists(String tableName, Connection conn) throws SQLException {
        DatabaseMetaData meta = conn.getMetaData();
        ResultSet result = meta.getTables(null, null, tableName.toUpperCase(), null);

        return result.next();
    }

}

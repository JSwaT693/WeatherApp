module client {
    exports pl.edu.pwr.jswatowski.lab4.client.repository;
    exports pl.edu.pwr.jswatowski.lab4.client.data;
    requires com.google.gson;
    requires java.net.http;
    requires java.sql;
    opens pl.edu.pwr.jswatowski.lab4.client.data to com.google.gson, gui;
    opens pl.edu.pwr.jswatowski.lab4.client.repository to gui;
}
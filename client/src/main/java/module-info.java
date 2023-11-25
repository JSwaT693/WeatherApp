module client {
    exports pl.edu.pwr.jswatowski.lab4.client.repository;
    requires com.google.gson;
    requires java.net.http;
    requires java.sql;
    exports pl.edu.pwr.jswatowski.lab4.client.data;
    opens pl.edu.pwr.jswatowski.lab4.client.data to com.google.gson;
    opens pl.edu.pwr.jswatowski.lab4.client.repository to gui;
}
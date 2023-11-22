module client {
    exports pl.edu.pwr.jswatowski.lab4.client;
    requires com.google.gson;
    requires java.net.http;
    requires java.sql;
    opens pl.edu.pwr.jswatowski.lab4.client to com.google.gson;
    exports pl.edu.pwr.jswatowski.lab4.client.repository;
    opens pl.edu.pwr.jswatowski.lab4.client.repository to com.google.gson;
    exports pl.edu.pwr.jswatowski.lab4.client.data;
    opens pl.edu.pwr.jswatowski.lab4.client.data to com.google.gson;
}
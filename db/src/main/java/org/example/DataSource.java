package org.example;

import org.postgresql.ds.PGSimpleDataSource;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DataSource {
    private Connection connection;

    private void createDataSource() throws SQLException {
        ClassLoader classLoader = DataSource.class.getClassLoader();
        String configFile = "config";
        InputStream inputStream = classLoader.getResourceAsStream(configFile + ".properties");
        Properties prop = new Properties();

        try {
            final String url = "jdbc:postgresql://localhost:5432/";
            final PGSimpleDataSource dataSource = new PGSimpleDataSource();

            prop.load(inputStream);

            dataSource.setURL(url);
            dataSource.setDatabaseName(prop.getProperty("db_name"));
            dataSource.setUser(prop.getProperty("user"));
            dataSource.setPassword(prop.getProperty("password"));

            connection = dataSource.getConnection();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection() throws SQLException {
        if (connection == null) {
            createDataSource();
        }
        return connection;
    }
}

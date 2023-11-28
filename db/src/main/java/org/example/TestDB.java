package org.example;

import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestDB {
    public static void main(String[] args) {
        final DataSource dataSource = createDataSource();
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            getLabels(connection);
        } catch (SQLException exception) {
            System.out.println(exception);
        }

    }

    private static DataSource createDataSource() {
        final String url = "jdbc:postgresql://localhost:5432/mylibrary?user=user2&password=password";
        final PGSimpleDataSource dataSource = new PGSimpleDataSource();

        dataSource.setURL(url);

        return dataSource;
    }

    private static void getLabels(Connection connection) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM label");
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            System.out.println(
                    " Id of label: " + rs.getString("id_label") +
                            "; Label's name: " + rs.getString("name") +
                    "; Net worth: " + rs.getString("net_worth") +
                            "; Adress" + rs.getString("address"));
        }
    }
}
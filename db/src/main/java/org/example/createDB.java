package org.example;

import java.sql.*;

public class createDB {

    private final Connection connection;

    createDB() throws SQLException {
        connection = new DataSource().getConnection();
    }


    private void createLabelTable() throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("CREATE TABLE label (" +
                "id_label int PRIMARY KEY, " +
                "name varchar(45) unique, " +
                "net_worth money, " +
                "address varchar(50) not null);");

        stmt.executeUpdate();
    }

    private void createAlbumTable() throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("CREATE TABLE if not exists album (" +
                "label_id int not null, " +
                "FOREIGN KEY (label_id) REFERENCES label(id_label), " +
                "title varchar(50) not null, " +
                "artist_id int not null, " +
                "FOREIGN KEY (artist_id) REFERENCES artist(artist_id), " +
                "album_id int PRIMARY KEY, " +
                "release_date DATE not null, " +
                "genre varchar(30), " +
                "totalsaledunits int not null);");

        stmt.executeUpdate();
    }

    private void createArtist() throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("CREATE TABLE if not exists artist (" +
                "artist_id int PRIMARY KEY, " +
                "artist_name varchar(50) not null, " +
                "birthday DATE not null, " +
                "label_id int, " +
                "FOREIGN KEY (label_id) REFERENCES label(id_label), " +
                "pseudonym varchar(50) unique not null, " +
                "total_sold_copies int not null);");

        stmt.executeUpdate();
    }

    public void createStructure() throws SQLException {
        createLabelTable();
        createAlbumTable();
        createArtist();
    }
}

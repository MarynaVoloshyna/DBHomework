package org.flats;

import shared.Flat;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FlatImplementsDAO implements FlatsDAO {
private final Connection connection;

    public FlatImplementsDAO(Connection connection) {
        this.connection = connection;
    }

    public void createTable() {
        try{
            try(Statement statement = connection.createStatement()) {
//                statement.execute("DROP TABLE IF EXISTS Flat");
                statement.execute("CREATE TABLE Flat(id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                        "district VARCHAR(15), street VARCHAR (25), square DECIMAL, rooms INT NOT NULL, price DECIMAL)");
            }
         }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void addFlat(String district, String street, Double square, Integer rooms, Double price) {
 try{
     try(PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Flat (district, street, square, rooms, price) VALUES (?,?,?,?,?)")){
         preparedStatement.setString(1, district);
         preparedStatement.setString(2, street);
         preparedStatement.setDouble(3, square);
         preparedStatement.setInt(4, rooms);
         preparedStatement.setDouble(5, price);
         preparedStatement.executeUpdate();
     }
 } catch (SQLException e) {
     throw new RuntimeException(e);
 }
    }

    private void getList(List<Flat> result, ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            Flat flat = new Flat();
            flat.setId(resultSet.getInt(1));
            flat.setDistrict(resultSet.getString(2));
            flat.setStreet(resultSet.getString(3));
            flat.setSquare(resultSet.getDouble(4));
            flat.setRooms(resultSet.getInt(5));
            flat.setPrice(resultSet.getDouble(6));
            result.add(flat);


        }
    }


    public List<Flat> getAll() {
        List <Flat> result = new ArrayList<>();
        try {
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet =  statement.executeQuery("SELECT * FROM Flat")) {
                    getList(result, resultSet);
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
    public List<Flat> getByFilter(Double min, Double max) {
        List <Flat> result = new ArrayList<>();
        try {
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet =  statement.executeQuery("SELECT * FROM Flat WHERE  square > " + min + "AND square <" + max)) {
                    getList(result, resultSet);
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public List<Flat> getByFilter(Integer rooms, Double price) {
        List <Flat> result = new ArrayList<>();
        try {
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet =  statement.executeQuery("SELECT * FROM Flat WHERE rooms > " + rooms + "AND price <" + price)) {
                    getList(result, resultSet);
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }



    public long count() {
        try {
            try (Statement st = connection.createStatement()) {
                try (ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM Flat")) {
                    if (rs.next())
                        return rs.getLong(1);
                    else
                        throw new RuntimeException("Count failed");
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}


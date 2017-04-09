package com.github.chabanenk0.servlet.Service;

import com.github.chabanenk0.servlet.Entity.Review;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class ReviewRepository
{
    private Connection databaseConnection;


    public ReviewRepository(String databaseName, String userName, String password) throws SQLException {
        String dbUrl = "jdbc:mysql://127.0.0.1:3306/"+databaseName+"?verifyServerCertificate=false&useSSL=true"; // mysql
        //dbUrl = "jdbc:h2:mem:h2java";
        //dbUrl = "jdbc:h2:file:~/h2java";
        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        this.databaseConnection = DriverManager.getConnection(dbUrl, userName, password);
    }

    public List<Review> getReviews() {
        List<Review> reviews = new LinkedList<Review>();
        String name;
        String message;
        int rating;

        try {
            this.addReviewsFromDatabaseToList(reviews);
        } catch (SQLException exception) {
            try {
                this.createSchema();
                this.addReviewsFromDatabaseToList(reviews);
            } catch (SQLException exception2) {
                System.out.println("Unable to create database schema");
                exception.printStackTrace();
                exception2.printStackTrace();
            }
        }

        return reviews;
    }

    private void createSchema() throws SQLException {
        Statement statement = this.databaseConnection.createStatement();
        statement.executeUpdate("CREATE TABLE reviews (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, name VARCHAR (255), message TEXT, rating INT(5), date DATETIME DEFAULT now())");
    }

    private void addReviewsFromDatabaseToList(List<Review> reviews) throws SQLException {
        Statement statement = this.databaseConnection.createStatement();
        String name;
        String message;
        int rating;
        String date;
        ResultSet resultSet = statement.executeQuery("SELECT name, message, rating, date FROM reviews ORDER BY date DESC LIMIT 10");
        while (resultSet.next()) {
            name = resultSet.getString(1);
            message = resultSet.getString(2);
            rating = resultSet.getInt(3);
            date = resultSet.getString(4);
            reviews.add(new Review(name, message, rating, date));
        }
    }

    public int insertReview(Review review) throws SQLException {
        PreparedStatement statement = this.databaseConnection.prepareStatement(
                "INSERT INTO reviews (name, message, rating) VALUES(?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, review.getName());
        statement.setString(2, review.getMessage());
        statement.setInt(3, review.getRating());
        int numberOfChanged = statement.executeUpdate();
        ResultSet rs = statement.getGeneratedKeys();
        int lastId = -1;
        if (rs.next()){
            lastId = rs.getInt(1);
        } else {
            throw new SQLException("No id of the inserted object");
        }

        return lastId;
    }
}

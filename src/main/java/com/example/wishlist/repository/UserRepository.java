package com.example.wishlist.repository;

import com.example.wishlist.model.User;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.Optional;
@Repository
public class UserRepository{

    String url = System.getenv("url");
    String password = System.getenv("password");
    String root = System.getenv("root");

    public Optional<User> findByEmail(String email) {
        String sqlFindEmail = "SELECT * FROM users WHERE email = ?";

        try {
            Connection con = DriverManager.getConnection(url, password, root);
            PreparedStatement statement = con.prepareStatement(sqlFindEmail);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String password = resultSet.getString("password");
                String role = resultSet.getString("role");
                User user = new User(email, password, role);
                return Optional.of(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public void save(User user) {
        String sqlSaveUser = "INSERT INTO users (email, password, role) VALUES (?, ?, ?)";

        try {
            Connection con = DriverManager.getConnection(url, password, root);
            PreparedStatement statement = con.prepareStatement(sqlSaveUser);
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getRole());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
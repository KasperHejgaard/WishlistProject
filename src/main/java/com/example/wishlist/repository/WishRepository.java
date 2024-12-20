package com.example.wishlist.repository;
import com.example.wishlist.model.Wish;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class WishRepository {

    /*public String url = System.getenv("url");
    public String password = System.getenv("password");
    public String user = System.getenv("user");*/

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String user;

    @Value("${spring.datasource.password}")
    private String password;

    public Wish findWishByID(int id) {
        Wish wish = new Wish();
        String sql = "SELECT * FROM wish_list WHERE wishID = ?";

        try (Connection con = DriverManager.getConnection(url, user, password)) {
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                wish.setWishID(rs.getInt("wishID"));
                wish.setName(rs.getString("name"));
                wish.setQuantity(rs.getInt("quantity"));
                wish.setDescription(rs.getString("description"));
                wish.setPrice(rs.getDouble("price"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return wish;
    }

    public Wish createWish(String name, int quantity, String description, double price, String link, boolean reserved) {
        Wish wish = new Wish(name, quantity, description, price, link, reserved);

        String sqlCreateWish = "INSERT INTO wish_list (name, quantity, description, price, link, reserved) VALUES(?,?,?,?,?,?)";

        try (Connection con = DriverManager.getConnection(url, user, password)) {

            PreparedStatement statement = con.prepareStatement(sqlCreateWish, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, wish.getName());
            statement.setInt(2, wish.getQuantity());
            statement.setString(3, wish.getDescription());
            statement.setDouble(4, wish.getPrice());
            statement.setString(5, wish.getLink());
            statement.setBoolean(6, wish.getReserved());

            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int wishID = generatedKeys.getInt(1);
                wish.setWishID(wishID);
        }
        } catch (Exception e) {
        e.printStackTrace();
        }
        return wish;
    }

    public List<Wish> readWishes() {
        List<Wish> wishes = new ArrayList<>();
        String sqlReadWishes = "SELECT * FROM wish_list";

        try (Connection con = DriverManager.getConnection(url, user, password)) {
            PreparedStatement statement = con.prepareStatement(sqlReadWishes);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Wish wish = new Wish();
                wish.setWishID(rs.getInt("wishID"));
                wish.setName(rs.getString("name"));
                wish.setDescription(rs.getString("description"));
                wish.setQuantity(rs.getInt("quantity"));
                wish.setPrice(rs.getDouble("price"));
                wish.setLink(rs.getString("link"));
                wish.setReserved(rs.getBoolean("Reserved"));
                wishes.add(wish);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return wishes;
    }

    public void updateWish(int wishID, String name, int quantity, String description, double price, String link, boolean reserved) {
        String sqlUpdateWish = "UPDATE wish_list SET name = ?, quantity = ?, description = ?, price = ?, link = ?, reserved = ? WHERE wishID = ?";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            PreparedStatement statement = connection.prepareStatement(sqlUpdateWish);
            statement.setString(1, name);
            statement.setInt(2, quantity);
            statement.setString(3, description);
            statement.setDouble(4, price);
            statement.setString(5, link);
            statement.setBoolean(6, reserved);
            statement.setInt(7, wishID);


            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int deleteWish(int id) {
        int updatedRows = 0;
        String sqlString = "DELETE FROM wish_list WHERE wishID = ?";
        try (Connection con = DriverManager.getConnection(url, user, password)) {
            PreparedStatement ps = con.prepareStatement(sqlString);
            ps.setInt(1, id);
            updatedRows = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return updatedRows;
    }

}



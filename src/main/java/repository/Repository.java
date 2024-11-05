package repository;
import model.Wish;

import java.sql.*;

public class Repository {

    String url = System.getenv("url");
    String password = System.getenv("password");
    String user = System.getenv("user");

public Wish createWish(String name, int quantity, String description, double price) {
    Wish wish = new Wish(name, quantity, description, price);

    String sqlCreateWish = "INSERT INTO wish_list (name, quantity, description, price) VALUES(?,?,?,?)";

    try (Connection con = DriverManager.getConnection(url, user, password)) {

        // Log forbindelsesstatus
        System.out.println("Database connection established");

        // Forbered sql-sætning og log værdierne der bliver sat
        System.out.println("Executing sql: " + sqlCreateWish);
        System.out.println("Inserting values - Name: " + wish.getName()
                + ", Description: " + wish.getDescription()
                + ", Quantity: " + wish.getQuantity()
                + ", Price: " + wish.getPrice());

        PreparedStatement statement = con.prepareStatement(sqlCreateWish, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, wish.getName());
        statement.setInt(2, wish.getQuantity());
        statement.setString(3, wish.getDescription());
        statement.setDouble(4, wish.getPrice());

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



}

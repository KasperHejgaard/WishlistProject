package repository;
import model.Wish;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Repository {

    String url = System.getenv("url");
    String password = System.getenv("password");
    String user = System.getenv("user");

    public Wish createWish(String name, int quantity, String description, double price) {
        Wish wish = new Wish(name, quantity, description, price);

        String sqlCreateWish = "INSERT INTO wish_list (name, quantity, description, price) VALUES(?,?,?,?)";

        try (Connection con = DriverManager.getConnection(url, user, password)) {

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
                wishes.add(wish);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return wishes;
    }



    public int deleteWish(int id) {
        int updatedRows = 0;
        String sqlString = "DELETE FROM wishes WHERE id = " + id;
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



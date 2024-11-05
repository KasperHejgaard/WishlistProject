package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Repository {

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



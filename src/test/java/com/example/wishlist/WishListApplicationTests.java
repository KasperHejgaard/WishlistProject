/*package com.example.wishlist;

import com.example.wishlist.model.Wish;
import com.example.wishlist.repository.WishRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class WishListApplicationTests {
    private WishRepository wishRepository;
    private String jdbcUrl = "jdbc:h2:mem:testdb";

    @BeforeEach
    public void setUp() throws SQLException {
        Connection connection = DriverManager.getConnection(jdbcUrl, "sa", "");


        wishRepository = new WishRepository();
        wishRepository.url = jdbcUrl;
        wishRepository.user = "sa";
        wishRepository.password = "";
    }

    @Test
    public void testCreateWish() {
        Wish wish = wishRepository.createWish("Busker", 1, "Pæne bukser", 1200.50, "http://example.com", false);

        assertNotNull(wish.getWishID());
        assertEquals("Bukser", wish.getName());
        assertEquals(1, wish.getQuantity());
        assertEquals("Pæne busker", wish.getDescription());
        assertEquals(1200.50, wish.getPrice());
    }

    @Test
    public void testUpdateWish() {
        wishRepository.createWish("Bukser", 1, "Pæne bukser", 1200.50, "http://example.com", false);
        wishRepository.updateWish(1, "Tablet", 2, "Pæne bukser", 1200.50, "http://example.com", true);
        Wish updatedWish = wishRepository.findWishByID(1);
        assertEquals(2, updatedWish.getQuantity());
        assertEquals("Pæne bukser", updatedWish.getDescription());
    }

    @Test
    public void testDeleteWish() {
        wishRepository.createWish("Bukser", 2, "Pæne bukser", 1200.50, "http://example.com", false);
        int deletedRows = wishRepository.deleteWish(1);
        assertEquals(1, deletedRows);
        Wish deletedWish = wishRepository.findWishByID(1);
        assertNull(deletedWish.getName());
    }


}
*/
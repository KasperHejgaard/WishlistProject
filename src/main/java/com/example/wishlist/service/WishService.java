package com.example.wishlist.service;
import com.example.wishlist.model.User;
import com.example.wishlist.model.Wish;
import com.example.wishlist.repository.UserRepository;
import org.springframework.stereotype.Service;
import com.example.wishlist.repository.WishRepository;

import java.util.List;
import java.util.Optional;


@Service
public class WishService {

    private final WishRepository wishRepository;
    private UserRepository userRepository;

    public WishService(WishRepository wishRepository) {
        this.wishRepository = wishRepository;
    }

    public Wish findWishByID(int id) {
        return wishRepository.findWishByID(id);
    }

    public void createWish(String name, int quantity, String description, double price) {
        wishRepository.createWish(name, quantity, description, price);
    }

    public List<Wish> readWishes() {
        return wishRepository.readWishes();
    }

    public void updateWish(int wishID, String name, int quantity, String description, double price) {
        wishRepository.updateWish(wishID, name, quantity, description, price);
    }

    public void deleteWish(int id){
        wishRepository.deleteWish(id);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void registerUser(String email, String password, String role) {
        User user = new User(email, password, role);
        userRepository.save(user);
    }

    public boolean validateUserPassword(String email, String password) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        return userOpt.isPresent();
    }

}

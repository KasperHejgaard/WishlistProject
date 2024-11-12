package com.example.wishlist.service;
import com.example.wishlist.model.User;
import com.example.wishlist.model.Wish;
import com.example.wishlist.repository.Repository;
import com.example.wishlist.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class Service {

    private final Repository repository;
    private UserRepository userRepository;

    public Service(Repository repository) {
        this.repository = repository;
    }

    public Wish findWishByID(int id) {
        return repository.findWishByID(id);
    }

    public void createWish(String name, int quantity, String description, double price) {
        repository.createWish(name, quantity, description, price);
    }

    public List<Wish> readWishes() {
        return repository.readWishes();
    }

    public void updateWish(int wishID, String name, int quantity, String description, double price) {
        repository.updateWish(wishID, name, quantity, description, price);
    }

    public void deleteWish(int id){
        repository.deleteWish(id);
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

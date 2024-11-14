package com.example.wishlist.service;
import com.example.wishlist.model.User;
import com.example.wishlist.model.Wish;
import org.springframework.stereotype.Service;
import com.example.wishlist.repository.WishRepository;

import java.util.List;
import java.util.Optional;


@Service
public class WishService {

    private final WishRepository wishRepository;

    public WishService(WishRepository wishRepository) {
        this.wishRepository = wishRepository;
    }

    public Wish findWishByID(int id) {
        return wishRepository.findWishByID(id);
    }

    public void createWish(String name, int quantity, String description, double price, String link, boolean reserved) {
        wishRepository.createWish(name, quantity, description, price, link, reserved);
    }

    public List<Wish> readWishes() {
        return wishRepository.readWishes();
    }

    public void updateWish(int wishID, String name, int quantity, String description, double price, String link, boolean reserved) {
        wishRepository.updateWish(wishID, name, quantity, description, price, link, reserved);
    }

    public void deleteWish(int id){
        wishRepository.deleteWish(id);
    }


}

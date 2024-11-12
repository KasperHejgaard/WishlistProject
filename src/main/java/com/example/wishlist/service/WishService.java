package com.example.wishlist.service;
import com.example.wishlist.model.Wish;
import org.springframework.stereotype.Service;
import com.example.wishlist.repository.WishRepository;

import java.util.List;


@Service
public class WishService {

    private final WishRepository wishRepository;

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
}

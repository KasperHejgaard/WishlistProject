package service;
import model.Wish;
import repository.Repository;

import java.util.List;

public class Service {

    private final Repository repository;

    public Service(Repository repository) {
        this.repository = repository;
    }

    public Wish findWishByID(int id) {
        repository.findWishByID(id);
    }

    public void createWish(String name, int quantity, String description, double price) {
        repository.createWish(name, quantity, description, price);
    }

    public List<Wish> readWishes() {
        repository.readWishes();
    }

    public void updateWish(int wishID, String name, int quantity, String description, double price) {
        repository.updateWish(wishID, name, quantity, description, price);
    }

    public void deleteWish(int id){
        repository.deleteWish(id);
    }
}

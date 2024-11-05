package service;

import model.Model;
import repository.Repository;

public class Service {

    private final Repository repository;

    public Service(Repository repository) {
        this.repository = repository;
    }


    public void deleteWish(int id){
        repository.deleteWish(id);
    }
}

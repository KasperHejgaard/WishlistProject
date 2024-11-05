package controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import service.Service;

@org.springframework.stereotype.Controller
@RequestMapping("/wishes")
public class Controller {
    private final Service service;

    public Controller(Service service) {
        this.service = service;
    }


    @PostMapping("delete/{id}")
    public String deleteWishById(@PathVariable int id) {
        service.deleteWish(id);
        return "redirect:/wishes";
    }
}

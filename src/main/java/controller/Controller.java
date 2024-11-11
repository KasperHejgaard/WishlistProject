package controller;

import model.Wish;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.Service;

import java.util.List;

@org.springframework.stereotype.Controller
@RequestMapping("/wishes")
public class Controller {
    private final Service service;

    public Controller(Service service) {
        this.service = service;
    }

    // @GetMapping("/login")

    // CREATE WISH

    @GetMapping("/createWish")
    public String createWish(Model model) {
        Wish wish = new Wish();
        model.addAttribute("wish", wish);
        return "createWish";
    }

    // READ WISHES

    @GetMapping("/wishes")
    public String readWishes(Model model) {
        List<Wish> wishes = service.readWishes();
        model.addAttribute("wishes", wishes);
        return "readWishes";
    }

    //UPDATE WISH (Måske save funktionalitet? Måske ikke nødvendigt, når det er PostMappin)

    @GetMapping("/{id}/edit")
    public String editWish(@PathVariable("id") int wishID, Model model) {
        Wish wish = service.findWishByID(wishID);
        model.addAttribute("wish", wish);
        return "edit";
    }

    @PostMapping("/update/{id}")
    public String updateWish(@RequestParam int wishID,
                             @RequestParam String name,
                             @RequestParam int quantity,
                             @RequestParam String description,
                             @RequestParam double price) {
        service.updateWish(wishID, name, quantity, description, price);
        return "redirect:/wishes";
    }

    //DELETE WISH

    @PostMapping("delete/{id}")
    public String deleteWishById(@PathVariable int id) {
        service.deleteWish(id);
        return "redirect:/wishes";
    }
}

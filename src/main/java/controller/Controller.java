package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import model.Wish;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.Service;

import java.util.List;

@org.springframework.stereotype.Controller
@RequestMapping("/wish_list")
public class Controller {
    private final Service service;

    public Controller(Service service) {
        this.service = service;
    }

    // SESSION
/*
    @GetMapping("/")
    public String index(Model model, HttpSession session) {
        if (session.getAttribute("user") =! null) {
            model.addAttribute("userAvailable", true);
            model.addAttribute("user", session.getAttribute("user"));
        } else {
            model.addAttribute("userAvailable", false);
        }
        return "home/index";
    }

 */

    // CREATE WISH

    @GetMapping("/create")
    public String createWish(Model model) {
        Wish wish = new Wish();
        model.addAttribute("wish", wish);
        return "createWish";
    }

    @PostMapping("/save")
    public String saveAttraction(@RequestParam String name,
                                 @RequestParam int quantity,
                                 @RequestParam String description,
                                 @RequestParam double price) {
        System.out.println("Save method called with Name: " + name);
        service.createWish(name, quantity, description, price);

        return "redirect:/wish_list";
    }

    // READ WISHES

    @GetMapping("/wish_list")
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

    @PostMapping("/update")
    public String updateWish(@RequestParam int wishID,
                             @RequestParam String name,
                             @RequestParam int quantity,
                             @RequestParam String description,
                             @RequestParam double price) {
        service.updateWish(wishID, name, quantity, description, price);
        return "redirect:/wish_list";
    }

    //DELETE WISH

    @PostMapping("delete/{id}")
    public String deleteWishById(@PathVariable int id) {
        service.deleteWish(id);
        return "redirect:/wish_list";
    }


    @ExceptionHandler(Exception.class)
    public String handleError(Model model, Exception exception){
        model.addAttribute("message", exception.getMessage());
        return "error";
    }
    }




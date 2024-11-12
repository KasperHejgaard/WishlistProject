package com.example.wishlist.controller;


import com.example.wishlist.model.Wish;
import com.example.wishlist.service.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

   @GetMapping("/register")
    public String showRegistrationPage() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String email, @RequestParam String password, @RequestParam String role, Model model) {
        service.registerUser(email, password, role);
        return "redirect:/login";  // Redirect to login page after successful registration
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }


    @PostMapping("/login")
    public String loginUser(@RequestParam String email, @RequestParam String password, Model model) {
        boolean isAuthenticated = service.validateUserPassword(email, password);
        if (isAuthenticated) {
            return "redirect:/user/home";  // Redirect to user dashboard or home page
        } else {
            model.addAttribute("loginError", "Invalid email or password.");
            return "login";
        }
    }

}


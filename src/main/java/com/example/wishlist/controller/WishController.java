package com.example.wishlist.controller;

import com.example.wishlist.model.Wish;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.wishlist.service.WishService;

import java.util.List;

@Controller
@RequestMapping("/wish_list")
public class WishController {
    private final WishService wishService;

    public WishController(WishService wishService) {
        this.wishService = wishService;
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
    public String saveWish(@RequestParam String name,
                                 @RequestParam int quantity,
                                 @RequestParam String description,
                                 @RequestParam double price) {
        System.out.println("Save method called with Name: " + name);
        wishService.createWish(name, quantity, description, price);

        return "redirect:/wish_list/wishes";
    }

    // READ WISHES

    @GetMapping("/wishes")
    public String readWishes(Model model) {
        List<Wish> wishes = wishService.readWishes();
        model.addAttribute("wishes", wishes);
        return "readWishes";
    }

    //UPDATE WISH (Måske save funktionalitet? Måske ikke nødvendigt, når det er PostMappin)

    @GetMapping("/{id}/edit")
    public String editWish(@PathVariable("id") int wishID, Model model) {
        Wish wish = wishService.findWishByID(wishID);
        model.addAttribute("wish", wish);
        return "edit";
    }

    @PostMapping("/edit")
    public String updateWish(@RequestParam int wishID,
                             @RequestParam String name,
                             @RequestParam int quantity,
                             @RequestParam String description,
                             @RequestParam double price) {
        System.out.println(name);
        wishService.updateWish(wishID, name, quantity, description, price);
        return "redirect:/wish_list/wishes";
    }

    //DELETE WISH

    @PostMapping("delete/{id}")
    public String deleteWishById(@PathVariable int id) {
        wishService.deleteWish(id);
        return "redirect:/wish_list/wishes";
    }


    @ExceptionHandler(Exception.class)
    public String handleError(Model model, Exception exception){
        model.addAttribute("message", exception.getMessage());
        return "error";
    }

    @GetMapping("/register")
    public String showRegistrationPage() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String email, @RequestParam String password, @RequestParam String role, Model model) {
        wishService.registerUser(email, password, role);
        return "redirect:/login";  // Redirect to login page after successful registration
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }


    @PostMapping("/login")
    public String loginUser(@RequestParam String email, @RequestParam String password, Model model) {
        boolean isAuthenticated = wishService.validateUserPassword(email, password);
        if (isAuthenticated) {
            return "redirect:/user/home";  // Redirect to user dashboard or home page
        } else {
            model.addAttribute("loginError", "Invalid email or password.");
            return "login";
        }
    }


    }




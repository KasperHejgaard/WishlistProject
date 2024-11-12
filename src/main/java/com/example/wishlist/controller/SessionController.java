/* package controller;

import jakarta.servlet.http.HttpSession;
import model.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import service.ProfileService;

public class SessionController {

    @GetMapping("/login")
    public String login() {
        return "home/login";
    }

    ProfileService profileService;

    @PostMapping("validate/login")
    public String validateLogin(HttpSession session,
                                @RequestParam String username,
                                @RequestParam String password) {
        Profile profile = profileService.getProfile(username, password);
        if (profile != null) {
            session.setAttribute("profile", profile);
            return "redirect/logged_in";
        } else {
            return "home/login";
        }
    }
}

 */

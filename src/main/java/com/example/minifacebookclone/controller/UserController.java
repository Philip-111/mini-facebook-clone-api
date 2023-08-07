package com.example.minifacebookclone.controller;

import com.example.minifacebookclone.entities.FacebookUser;
import com.example.minifacebookclone.entities.Post;
import com.example.minifacebookclone.repositories.UserRepository;
import com.example.minifacebookclone.services.PostServicesImpl;
import com.example.minifacebookclone.services.UserServicesImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class UserController {
    private final UserServicesImpl userServices;
    private final UserRepository userRepository;
    private final PostServicesImpl postServices;

    @Autowired
    public UserController(UserServicesImpl userServices, UserRepository userRepository, PostServicesImpl postServices) {
        this.userServices = userServices;
        this.userRepository = userRepository;
        this.postServices = postServices;
    }

    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {
        model.addAttribute("FacebookUser", new FacebookUser());
        return "registration";
    }


    @PostMapping("/registration")
    public String registerUser(@ModelAttribute("user")FacebookUser user) {
        try {
            userServices.registerUser(user);
            return "redirect:/welcome";
        } catch (IllegalArgumentException e) {
            return "registration";
        }
    }

    @GetMapping("/welcome")
    public String showWelcomePage() {
        return "welcome";
    }

    @PostMapping("/welcome")
    public String processLogin(@RequestParam("email") String email,
                               @RequestParam("password") String password,
                               HttpSession session,
                               Model model) {
        Optional<FacebookUser> optionalUser = userServices.loginUser(email, password);

        if (optionalUser.isPresent()) {
            FacebookUser user = optionalUser.get();

            session.setAttribute("user", user);
            return "redirect:/home";
        } else {
            model.addAttribute("error", "Invalid email or password");
            return "welcome";
        }
    }
    @GetMapping("/home")
    public String showHomePage(Model model, HttpSession session) {
        FacebookUser user = (FacebookUser) session.getAttribute("user");

        if (user != null) {

            model.addAttribute("userName", user.getName());

            List<Post> posts = postServices.getAllPosts();

            model.addAttribute("posts", posts);

            return "home";
        } else {
            return "redirect:/welcome";
        }
    }
}

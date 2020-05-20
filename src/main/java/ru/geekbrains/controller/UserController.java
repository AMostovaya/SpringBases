package ru.geekbrains.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.geekbrains.persist.enity.User;
import ru.geekbrains.persist.repo.UserRepository;

@RequestMapping("/user")
@Controller
public class UserController {
    
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    private UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public String userList(Model model){
        log.info("User list");
        model.addAttribute("users", userRepository.findAll());
        return "users";
    }

    @GetMapping("new")
    public String createUser(Model model) {
        log.info("Create new user");
        model.addAttribute("user", new User());
        return "user";
    }

    @PostMapping
    public String saveUser(User user){
        log.info("User saved");
        userRepository.saveUser(user);
        return "redirect:/user";
    }
}

package ru.geekbrains.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.persist.entity.User;
import ru.geekbrains.service.PageUserService;
import ru.geekbrains.service.UserService;

import java.util.List;
import java.util.Optional;

//@RequestMapping("/user")
@Controller
public class UserController {
    
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    private UserService userService;
    private PageUserService pageUserService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/minmaxagefilter")
    public String userList(Model model,
                           @RequestParam("minAge") Integer minAge,
                           @RequestParam("maxAge") Integer maxAge){
        log.info("User list with minAge{} and maxAge{}", minAge, maxAge);
        if (minAge == null){
            minAge = 0;
        }
        if (maxAge == null){
            maxAge = Integer.MAX_VALUE;
        }

        List<User> usersList = userService.findAllByAgeBetween(minAge, maxAge);
        model.addAttribute("users", usersList);
        return "users";
    }

    @GetMapping("/user/new")
    public String createUser(Model model) {
        log.info("Create new user");
        model.addAttribute("user", new User());
        return "user";
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public String saveUser(User user) {
        log.info("Save user method");

        userService.save(user);
        return "redirect:/user";
    }


    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

//    @GetMapping("/user")
//    public String showUsers(Model model) {
//        List<User> userList = userService.findAll();
//        model.addAttribute("users", userList);
//        return "users";
//    }

    @GetMapping("/user")
    public String showUserPage(Model model) {
        List<User> userList = userService.findAll();
        model.addAttribute("users", userList);
        return "users";
    }

    @Autowired
    public void setPageUserService(PageUserService pageUserService) {
        this.pageUserService = pageUserService;
    }

    @GetMapping("/user/find")
    public String showUsersOnPages(Model model,
            @RequestParam(name = "pageNumber", required = false) Optional<Integer> pageNumber,
            @RequestParam(name = "size", required = false) Optional<Integer> size){
        int currentPage = pageNumber.orElse(0);
        int pageSize = size.orElse(5);

        List<User> userList = pageUserService.findAllWithPaging(currentPage, pageSize);
        model.addAttribute("users", userList);

        return "users";
    }


}

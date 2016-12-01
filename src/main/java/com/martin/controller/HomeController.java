package com.martin.controller;

import com.martin.dataaccess.UserRepository;
import com.martin.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController
{
    private final UserRepository repository;

    @Autowired
    public HomeController(UserRepository repository)
    {
        this.repository = repository;
    }

    @RequestMapping("/")
    public String index()
    {
        return "index";
    }

    @GetMapping("/register")
    public String register(Model model)
    {
        model.addAttribute("user", new User());
        return "register";
    }

    @GetMapping("/login")
    public String login(Model model)
    {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/login")
    @ResponseBody
    public User login(User user)
    {
        return repository.findByName(user.getName());
    }

    @PostMapping("/register")
    @ResponseBody
    public User register(User user)
    {
        repository.save(user);

        return user;
    }
}

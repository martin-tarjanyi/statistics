package com.martin.api.controller;

import com.martin.api.model.User;
import com.martin.service.UserRegistrationService;
import com.martin.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class HomeController
{
    private final UserLoginService userLoginService;
    private final UserRegistrationService userRegistrationService;

    @Autowired
    public HomeController(UserLoginService userLoginService, UserRegistrationService userRegistrationService)
    {
        this.userLoginService = userLoginService;
        this.userRegistrationService = userRegistrationService;
    }

    @RequestMapping("/")
    public String index()
    {
        return "index";
    }

    @RequestMapping("/user")
    public String user(Model model)
    {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("username", username);
        return "user";
    }

    @GetMapping("/register")
    public String register(Model model)
    {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String register(User user, RedirectAttributes redirectAttributes)
    {
        if (userRegistrationService.userExistsWithUsername(user))
        {
            redirectAttributes.addFlashAttribute("error", "User already exists with this name. Please, try again.");

            return "redirect:/register";
        }

        userRegistrationService.register(user);

        redirectAttributes.addFlashAttribute("success", "Registration is successful, please login.");

        return "redirect:/login";
    }
}

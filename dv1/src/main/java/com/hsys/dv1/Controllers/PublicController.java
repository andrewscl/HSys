package com.hsys.dv1.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.hsys.dv1.Entities.User;
import com.hsys.dv1.Services.SignUpService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class PublicController {

    private final SignUpService signUpService;

    @GetMapping("/public/home")
    public String showHomePage () {
        return "index";
    }

    @GetMapping ("/public/signup")
    public String showSignUpForm (Model model){
        model.addAttribute("user", new User());
        return "signup";
    }

    @PostMapping("/public/signup")
    public String processSingUpForm (User user, Model model){
        signUpService.register(user);
        return "redirect:/public/login";
    }

}

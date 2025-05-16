package com.hsys.dv1.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hsys.dv1.Entities.User;
import com.hsys.dv1.Services.SignUpService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final SignUpService signUpService;

    @GetMapping ("/signup")
    public String showSignUpForm (Model model){
        model.addAttribute("user", new User());
        return "auth/signup";
    }

    @PostMapping("/signup")
    public String processSingUpForm (User user, Model model){
        signUpService.register(user);
        return "redirect:/auth/signin";
    }

    @GetMapping ("/signin")
    public String showSignInForm (Model model){
        model.addAttribute("user", new User());
        return "auth/signin";
    }
}
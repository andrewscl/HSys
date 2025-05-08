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

    @GetMapping ("/auth/signup")
    public String showSignUpForm (Model model){
        model.addAttribute("user", new User());
        return "auth/signup";
    }

    @PostMapping("/auth/signup")
    public String processSingUpForm (User user, Model model){

        try {
        signUpService.register(user);
        return "redirect:/auth/signin";
        } catch (RuntimeException e) {
            if(e.getMessage().equals("Username already exists")) {
                model.addAttribute("error",
                "Nombre de usuario ya se encuentra registrado.");
                return "/auth/signup";
            } else {
                model.addAttribute("error",
                "Ocurrio un error durante el resgistro");
                return "/auth/signup";
            }
        }

    }

    @GetMapping ("/auth/signin")
    public String showSignInForm (Model model){
        model.addAttribute("user", new User());
        return "auth/signin";
    }


}

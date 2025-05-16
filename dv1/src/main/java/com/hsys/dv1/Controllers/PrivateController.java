package com.hsys.dv1.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/private")
@RequiredArgsConstructor

public class PrivateController {

    @GetMapping("/home")
    public String showHomeNav () {
        return "/private/private-home";
    }    

    @GetMapping("/employees")
    public String showEmployeesNav () {
        return "/employees/nav-employees";
    }

}

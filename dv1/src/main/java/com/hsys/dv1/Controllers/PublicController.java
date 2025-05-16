package com.hsys.dv1.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hsys.dv1.Services.AppService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/public")
@RequiredArgsConstructor
public class PublicController {

    private final AppService appService;

    @GetMapping("/home")
    public String showHomePage (Model model) {
        model.addAttribute("apps", appService.findPublicApps());
        return "index";
    }

}

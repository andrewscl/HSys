package com.hsys.dv1.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hsys.dv1.Entities.Role;
import com.hsys.dv1.Services.RoleService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final RoleService roleService;

    @GetMapping("/dashboard")
    public String showAdministratorNav () {
        return "/admin/admin-dashboard";
    }    

    @GetMapping("/create-role")
    public String showCreateRoleForm (Model model) {
        model.addAttribute("role", new Role());
        return "/admin/create-role-form";
    }

    @PostMapping("/create-role")
    public String processCreateRoleForm (@RequestParam("role") String roleName, Model model) {
        Role role = new Role();
        role.setRole(roleName);
        roleService.createRol(role);
        return "/admin/create-role-form";
    }

}

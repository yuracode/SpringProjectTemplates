package com.example.demo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index(Authentication authentication, Model model) {
        String username = (authentication != null && authentication.getName() != null)
                ? authentication.getName() : "anonymous";
        model.addAttribute("username", username);

        boolean isAdmin = false;
        if (authentication != null) {
            for (GrantedAuthority ga : authentication.getAuthorities()) {
                if ("ROLE_ADMIN".equals(ga.getAuthority())) {
                    isAdmin = true;
                    break;
                }
            }
        }

        return isAdmin ? "index_admin" : "index_user";
    }

}

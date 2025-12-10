package com.example.demo.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class LogoutController {

    @GetMapping("/perform-logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            try {
                request.logout();
            } catch (Exception e) {
                // fallback: clear context and invalidate session
                SecurityContextHolder.clearContext();
                try {
                    request.getSession(false).invalidate();
                } catch (Exception ex) {
                    // ignore
                }
            }
        }
        return "redirect:/login?logout";
    }

}

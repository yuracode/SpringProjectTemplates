package com.example.demo.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    @GetMapping("/login")
    public String login(Principal principal, Model model, String error, String logout) {
        if (principal != null) {
            return "redirect:/";
        }
        if (error != null) {
            model.addAttribute("error", "ログインに失敗しました。ユーザ名/パスワードを確認してください。");
        }
        if (logout != null) {
            model.addAttribute("message", "ログアウトしました。");
        }
        return "login";
    }

}

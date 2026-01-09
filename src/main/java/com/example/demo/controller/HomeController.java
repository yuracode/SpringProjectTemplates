package com.example.demo.controller;

import java.security.Principal;
import java.util.List;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.mapper.ImageFile;
import com.example.demo.mapper.ImageFileMapper;
import com.example.demo.mapper.ProjectUser;
import com.example.demo.mapper.ProjectUserMapper;
import com.example.demo.mapper.SubmissionSummary;

@Controller
public class HomeController {

    @Autowired
    private ProjectUserMapper projectUserMapper;

    @Autowired
    private ImageFileMapper imageFileMapper;

    @GetMapping("/")
    public String index(Principal principal, Model model) {
        model.addAttribute("username", principal != null ? principal.getName() : "anonymous");
        // ログインユーザーの画像ファイル情報を取得
        if (principal != null) {
            ProjectUser currentUser = projectUserMapper.findByUsername(principal.getName());
            if (currentUser != null) {
                ImageFile imageFile = imageFileMapper.findByUserId(currentUser.getId());
                if (imageFile != null) {
                    model.addAttribute("imageFile", imageFile);
                    model.addAttribute("imageUrl", "/image/view/" + currentUser.getId());
                }
            }
        }
        // ROLE_ADMIN の場合、ユーザー一覧を表示するために取得
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            boolean isAdmin = auth.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .anyMatch("ROLE_ADMIN"::equals);
            if (isAdmin) {
                List<ProjectUser> users = projectUserMapper.findAll();
                model.addAttribute("users", users);
            }
        }
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
    @GetMapping("/screen1")
    public String screen1() {
        return "screen1";
    }
        @GetMapping("/screen2")
    public String screen2() {
        return "screen2";
    }

    @GetMapping("/admin/submissions")
    public String submissions(Model model) {
        String project = "Spring Boot課題";
        List<SubmissionSummary> submitted = projectUserMapper.findSubmittedSummariesByProject(project);
        List<ProjectUser> notSubmitted = projectUserMapper.findUsersWithoutSubmissionByProject(project);
        model.addAttribute("project", project);
        model.addAttribute("submitted", submitted);
        model.addAttribute("notSubmitted", notSubmitted);
        return "submissions";
    }
}

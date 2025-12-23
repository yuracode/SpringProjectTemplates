package com.example.demo.mapper;

import lombok.Data;

@Data
public class ProjectUser {
    private Long id;
    private String username;
    private String password;
    private boolean enabled;
    private String roles;
}

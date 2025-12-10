package com.example.demo.mapper;

public interface ProjectUserMapper {

    ProjectUser findByUsername(String username);

    void insert(ProjectUser user);

}

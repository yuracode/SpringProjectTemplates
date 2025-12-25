package com.example.demo.mapper;

import java.util.List;

public interface ProjectUserMapper {

    ProjectUser findByUsername(String username);

    void insert(ProjectUser user);

    List<ProjectUser> findAll();

}

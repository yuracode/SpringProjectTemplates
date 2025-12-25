package com.example.demo.mapper;

import java.util.List;

public interface ProjectUserMapper {

    ProjectUser findByUsername(String username);

    void insert(ProjectUser user);

    List<ProjectUser> findAll();

    List<ProjectUser> findUsersWithSubmissionByProject(String projectName);

    List<ProjectUser> findUsersWithoutSubmissionByProject(String projectName);

    List<SubmissionSummary> findSubmittedSummariesByProject(String projectName);

}

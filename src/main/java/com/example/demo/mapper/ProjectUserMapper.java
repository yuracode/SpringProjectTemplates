package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ProjectUserMapper {

    ProjectUser findByUsername(String username);

    void insert(ProjectUser user);

    List<ProjectUser> findAll();

    List<ProjectUser> findUsersWithSubmissionByProject(String projectName);

    List<ProjectUser> findUsersWithoutSubmissionByProject(String projectName);

    List<SubmissionSummary> findSubmittedSummariesByProject(String projectName);

    void updateImagePath(Long id, String imagePath);

}

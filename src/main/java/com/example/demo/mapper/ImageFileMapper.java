package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ImageFileMapper {
    void insert(ImageFile imageFile);
    ImageFile findByUserId(Long userId);
    List<ImageFile> findAllByUserId(Long userId);
}

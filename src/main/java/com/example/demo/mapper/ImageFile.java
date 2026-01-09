package com.example.demo.mapper;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ImageFile {
    private Long id;
    private Long userId;
    private String filename;
    private byte[] fileData;
    private String fileType;
    private Long fileSize;
    private LocalDateTime uploadedAt;
}

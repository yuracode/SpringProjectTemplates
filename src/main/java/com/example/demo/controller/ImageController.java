package com.example.demo.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.mapper.ImageFile;
import com.example.demo.mapper.ImageFileMapper;
import com.example.demo.mapper.ProjectUser;
import com.example.demo.mapper.ProjectUserMapper;

@Controller
@RequestMapping("/image")
public class ImageController {

    @Autowired
    private ProjectUserMapper projectUserMapper;

    @Autowired
    private ImageFileMapper imageFileMapper;

    /**
     * 画像アップロードフォーム表示
     */
    @GetMapping("/upload")
    public String showUploadForm(Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        }
        return "image-upload";
    }

    /**
     * 画像アップロード処理
     */
    @PostMapping("/upload")
    public String uploadImage(
            @RequestParam("imageFile") MultipartFile file,
            Principal principal,
            RedirectAttributes redirectAttributes) {

        if (principal == null) {
            return "redirect:/login";
        }

        // ファイルが空でないかチェック
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "ファイルを選択してください");
            return "redirect:/image/upload";
        }

        // ファイル拡張子チェック（画像形式のみ）
        String originalFilename = file.getOriginalFilename();
        if (!isImageFile(originalFilename)) {
            redirectAttributes.addFlashAttribute("message", "画像ファイルのみアップロード可能です");
            return "redirect:/image/upload";
        }

        try {
            // ファイルをバイト配列として読み込む
            byte[] fileData = file.getBytes();

            // DBに画像ファイル情報をBLOBとして保存
            String username = principal.getName();
            ProjectUser user = projectUserMapper.findByUsername(username);
            if (user != null) {
                ImageFile imageFile = new ImageFile();
                imageFile.setUserId(user.getId());
                imageFile.setFilename(originalFilename);
                imageFile.setFileData(fileData);
                imageFile.setFileType(file.getContentType());
                imageFile.setFileSize(file.getSize());
                imageFile.setUploadedAt(LocalDateTime.now());
                imageFileMapper.insert(imageFile);
            }

            redirectAttributes.addFlashAttribute("message", "画像がアップロードされました");
        } catch (IOException e) {
            redirectAttributes.addFlashAttribute("message", "ファイルアップロードに失敗しました: " + e.getMessage());
        }

        return "redirect:/image/upload";
    }

    /**
     * DBに保存された画像を取得（BLOBから配信）
     */
    @GetMapping("/view/{id}")
    public ResponseEntity<byte[]> getImageById(@PathVariable Long id) {
        try {
            ImageFile imageFile = imageFileMapper.findByUserId(id);
            if (imageFile == null || imageFile.getFileData() == null) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok()
                    .header("Content-Type", imageFile.getFileType() != null ? imageFile.getFileType() : "image/jpeg")
                    .header("Content-Disposition", "inline; filename=\"" + imageFile.getFilename() + "\"")
                    .body(imageFile.getFileData());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * 画像ファイルかどうかをチェック
     */
    private boolean isImageFile(String filename) {
        if (filename == null) {
            return false;
        }
        String lowerFilename = filename.toLowerCase();
        return lowerFilename.endsWith(".jpg") ||
               lowerFilename.endsWith(".jpeg") ||
               lowerFilename.endsWith(".png") ||
               lowerFilename.endsWith(".gif") ||
               lowerFilename.endsWith(".webp");
    }
}

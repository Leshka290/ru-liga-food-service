package ru.liga.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.liga.entity.Image;
import ru.liga.exception.ImageNotFoundException;
import ru.liga.repository.ImageRepository;
import ru.liga.service.ImageService;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Slf4j
@RequiredArgsConstructor
@Service
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    @Value("${item.image.dir.path}")
    private String imageDir;

    @Override
    public void transferImageToResponse(Long imageId, HttpServletResponse response) throws IOException {
        Image image = imageRepository.findById(imageId).orElseThrow(ImageNotFoundException::new);
        try (InputStream is = Files.newInputStream(image.getPath());
             OutputStream os = response.getOutputStream()) {
            response.setStatus(200);
            response.setContentType(image.getMediaType());
            response.setContentLength((int) image.getFileSize());
            is.transferTo(os);
        }
    }

    @Override
    public Image saveImageFile(MultipartFile imageFile) throws IOException {
        Path filePath = createPathFromFile(imageFile);
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);
        try (InputStream is = imageFile.getInputStream();
             OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
             BufferedInputStream bis = new BufferedInputStream(is, 1024);
             BufferedOutputStream bos = new BufferedOutputStream(os, 1024)) {
            bis.transferTo(bos);
        }
        return saveImageDetails(imageFile, filePath);
    }

    private Image saveImageDetails(MultipartFile imageFile, Path filePath) {
        Image image = imageRepository.findByFilePath(filePath.toString()).orElse(new Image());
        image.setFilePath(filePath.toString());
        image.setFileExtension(getExtension(Objects.requireNonNull(imageFile.getOriginalFilename())));
        image.setFileSize(imageFile.getSize());
        image.setMediaType(imageFile.getContentType());
        return imageRepository.saveAndFlush(image);
    }

    private Path createPathFromFile(MultipartFile imageFile) {
        Path path = Path.of(String.format("%s/%s", imageDir, imageFile.getOriginalFilename()));
        if (Files.exists(path)) {
            path = Path.of(String.format("%s/%s.%s", imageDir, generateFileName(),
                    getExtension(Objects.requireNonNull(imageFile.getOriginalFilename(), ""))));
        }
        return path;
    }

    private String generateFileName() {
        int length = 8;
        boolean useLetters = true;
        boolean useNumbers = true;
        return RandomStringUtils.random(length, useLetters, useNumbers);
    }

    private String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }
}

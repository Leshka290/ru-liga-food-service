package ru.liga.service;

import org.springframework.web.multipart.MultipartFile;
import ru.liga.entity.Image;

import java.io.IOException;

public interface ImageService {
    Image saveImageFile(MultipartFile imageFile) throws IOException;
}

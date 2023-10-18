package ru.liga.service;

import org.springframework.web.multipart.MultipartFile;
import ru.liga.entity.Image;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ImageService {
    void transferImageToResponse(Long imageId, HttpServletResponse response) throws IOException;
    Image saveImageFile(MultipartFile imageFile) throws IOException;
}

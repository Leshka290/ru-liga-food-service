package ru.liga.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.liga.entity.Image;
import ru.liga.service.ImageService;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Service
public class ImageServiceImpl implements ImageService {

    @Override
    public Image saveImageFile(MultipartFile imageFile) throws IOException {
        Image image = new Image();
        image.setFilePath(imageFile.getContentType());
        return image;
    }
}

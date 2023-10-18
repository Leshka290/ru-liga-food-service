package ru.liga.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.liga.service.ImageService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@Transactional
@RequiredArgsConstructor
public class ImageController {
    private final ImageService imageService;

    @GetMapping("/image/{id}")
    public void transferImageToResponse(@PathVariable Long id, HttpServletResponse response) throws IOException {
        imageService.transferImageToResponse(id, response);
    }
}

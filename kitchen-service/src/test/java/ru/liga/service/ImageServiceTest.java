package ru.liga.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import ru.liga.repository.ImageRepository;
import ru.liga.service.impl.ImageServiceImpl;

import java.io.IOException;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ImageServiceTest {
    @Mock
    private ImageRepository imageRepository;

    @InjectMocks
    private ImageServiceImpl imageService;

    @Test
    public void testSaveImageFile() throws IOException {
        MultipartFile imageFile = new MockMultipartFile("test.jpg", "test.jpg",
                "image/jpeg", "test data".getBytes());

        when(imageRepository.findByFilePath(anyString())).thenReturn(Optional.empty());

        imageService.saveImageFile(imageFile);

        verify(imageRepository, times(1)).saveAndFlush(any());
    }
}

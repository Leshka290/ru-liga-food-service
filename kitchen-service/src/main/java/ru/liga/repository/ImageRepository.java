package ru.liga.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.liga.entity.Image;

import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, Long> {
    Optional<Image> findByFilePath(String filePath);
}

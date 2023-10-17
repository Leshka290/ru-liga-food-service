package ru.liga.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.liga.entity.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {
}

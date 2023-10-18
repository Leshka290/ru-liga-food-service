package ru.liga.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.nio.file.Path;

@Entity
@Data
@NoArgsConstructor
@Table(name = "images")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id", nullable = false)
    private long id;
    @Column(name = "file_size", nullable = false)
    private long fileSize;
    @Column(name = "file_path", nullable = false)
    private String filePath;
    @Column(name = "media_type", nullable = false)
    private String mediaType;
    @Column(name = "file_extension", nullable = false)
    private String fileExtension;

    public Path getPath() {
        return Path.of(this.filePath);
    }

    public String getUrl() {
        return String.format("/%s/%s", getPath().getParent(), getId());
    }
}

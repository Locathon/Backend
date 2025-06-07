package com.Locathon.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StyleTransformLog {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime timestamp;

    @Column(length = 2000)
    private String originalText;

    @Column(length = 2000)
    private String transformedText;

    @PrePersist
    public void prePersist() {
        timestamp = LocalDateTime.now();
    }
}
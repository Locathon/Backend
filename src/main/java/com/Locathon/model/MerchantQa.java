package com.Locathon.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(
        uniqueConstraints = {@UniqueConstraint(columnNames = {"merchantId", "question"})}
)
public class MerchantQa {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String merchantId;

    @Column(length = 1000)
    private String question;

    @Column(length = 2000)
    private String answer;

    private String category;
}

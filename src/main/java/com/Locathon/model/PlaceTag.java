package com.Locathon.model;

import jakarta.persistence.*;

@Entity
public class PlaceTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tag;  // 예시 => #감성, #데이트

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id")
    private Place place;
}

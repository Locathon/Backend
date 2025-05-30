package com.Locathon.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Place {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;        //장소 이름
    private String description; //장소 설명
    private Double latitude;    //위도
    private Double longitude;   //경도
    private String address;     //주소

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "create_by")
    private Member createdBy;

    @PrePersist
    @PreUpdate
    private void validateMerchantMember(){
        if (createdBy == null || createdBy.getRole() != MemberRole.MERCHANT){
            throw new IllegalStateException("Place는 Merchant만 등록할 수 있습니다.");
        }
    }
    @OneToMany(mappedBy = "place", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PlaceTag> tags = new ArrayList<>();

    @OneToMany(mappedBy = "place", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CoursePlace> coursePlaces = new ArrayList<>();
}

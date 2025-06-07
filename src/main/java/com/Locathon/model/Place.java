package com.Locathon.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@RequiredArgsConstructor
public class Place {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;        //장소 이름
    private String description; //장소 설명
    private String address;     //주소

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "create_by")
    private Member createdBy;

    @PrePersist
    @PreUpdate
    private void validateMerchantMember(){
        if (createdBy == null || createdBy.getRole() == MemberRole.USER){
            throw new IllegalStateException("Place는 USER는 등록할 수 없습니다.");
        }
    }

    @OneToMany(mappedBy = "place", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CoursePlace> coursePlaces = new ArrayList<>();
}

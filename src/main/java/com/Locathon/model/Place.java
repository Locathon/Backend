package com.Locathon.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@RequiredArgsConstructor
public class Place {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;        //장소 이름

    private String title;
    private String content; //장소 설명

    private Long latitude;
    private Long longitude;   //위도, 경도(프론트로부터 받아올 것)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "create_by")
    private Member createdBy;

    @PrePersist
    @PreUpdate
    private void validateMerchantMember(){
        if (createdBy == null || createdBy.getRole() != MemberRole.RESIDENT){
            throw new IllegalStateException("Place는 Resident만 등록할 수 있습니다.");
        }
    }


}

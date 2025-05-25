package com.Locathon.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "member")
public class Member {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "member_id", unique = true, nullable = false)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MemberRole role;  // 일반 사용자 or 소상공인 여부

    private String storeName;
    private String storeAddress;

    @Builder
    public Member(String email, String password, MemberRole role,
                  String storeName, String storeAddress) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.storeName = storeName;
        this.storeAddress = storeAddress;
    }

    public void changePassword(String newPassword) {
        this.password = newPassword;
    }
}

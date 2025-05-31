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

    @Column(nullable = false)
    private String username;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MemberRole role;  // 일반 사용자 or 거주민 or 소상공인 여부

    @Builder
    public Member(String email, String password, String username, MemberRole role) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.role = role;
    }

    public void changePassword(String newPassword) {
        this.password = newPassword;
    }
}

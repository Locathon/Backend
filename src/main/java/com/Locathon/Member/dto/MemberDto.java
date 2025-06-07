package com.Locathon.Member.dto;

import com.Locathon.Member.model.Member;
import com.Locathon.Member.model.MemberRole;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberDto {
    private String email;
    private String password;
    private String username;

    private String role;

    public Member toEntity() {
        return Member.builder()
                .email(email)
                .password(password)
                .username(username)
                .role(MemberRole.valueOf(role))
                .build();
    }
}

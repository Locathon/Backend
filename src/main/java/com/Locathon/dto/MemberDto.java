package com.Locathon.dto;

import com.Locathon.model.Member;
import com.Locathon.model.MemberRole;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberDto {
    private String email;
    private String password;

    private String role;
    private String storeName;     // MERCHANT인 경우만 입력
    private String storeAddress;  // MERCHANT인 경우만 입력
    private String phoneNumber;

    public Member toEntity() {
        return Member.builder()
                .email(email)
                .password(password)
                .role(MemberRole.valueOf(role))
                .storeName(role.equals("MERCHANT") ? storeName : null)
                .storeAddress(role.equals("MERCHANT") ? storeAddress : null)
                .build();
    }
}

package com.Locathon.Member.service;

import com.Locathon.Member.dto.MemberDto;
import com.Locathon.Member.repository.MemberRepository;
import com.Locathon.model.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public Long register(MemberDto dto){
        if (memberRepository.findByEmail(dto.getEmail()).isPresent()){
            throw  new IllegalArgumentException("이미 사용중인 이메일입니다.");
        }
        String encodedPassword = passwordEncoder.encode(dto.getPassword());
        Member member = dto.toEntity();
        member.changePassword(encodedPassword);
        memberRepository.save(member);

        return member.getId();
    }
}

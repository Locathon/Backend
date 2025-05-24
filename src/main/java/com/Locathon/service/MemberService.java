package com.Locathon.service;

import com.Locathon.dto.MemberDto;
import com.Locathon.model.Member;
import com.Locathon.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Long register(MemberDto dto){
        if (memberRepository.findByEmail(dto.getEmail()).isPresent()){
            throw  new IllegalArgumentException("이미 사용중인 이메일입니다.");
        }
        Member member = Member.builder()
                .email(dto.getEmail())
                .password(dto.getPassword())
                .build();
        return memberRepository.save(member).getId();
    }
}

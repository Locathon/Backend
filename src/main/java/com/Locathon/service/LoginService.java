package com.Locathon.service;

import com.Locathon.dto.LoginDto;
import com.Locathon.jwt.JwtTokenProvider;
import com.Locathon.model.Member;
import com.Locathon.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public String login(LoginDto dto){
        Member member = memberRepository.findByEmail(dto.getEmail())
                .orElseThrow(()-> new IllegalArgumentException("가입되지 않은 이메일입니다."));


        if (!passwordEncoder.matches(dto.getPassword(), member.getPassword())){
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        return jwtTokenProvider.createToken(member.getEmail(), member.getRole());
    }
}

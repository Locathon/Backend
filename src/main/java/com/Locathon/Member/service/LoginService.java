package com.Locathon.Member.service;

import com.Locathon.Member.dto.LoginDto;
import com.Locathon.Member.repository.MemberRepository;
import com.Locathon.config.jwt.JwtTokenProvider;
import com.Locathon.Member.model.Member;
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

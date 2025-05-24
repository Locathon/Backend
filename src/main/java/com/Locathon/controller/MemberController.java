package com.Locathon.controller;

import com.Locathon.dto.MemberDto;
import com.Locathon.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody MemberDto dto){
        Long id = memberService.register(dto);
        return ResponseEntity.ok("회원가입 성공! Id: " + id);
    }
}

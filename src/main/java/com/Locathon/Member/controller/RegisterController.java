package com.Locathon.Member.controller;

import com.Locathon.Member.dto.MemberDto;
import com.Locathon.Member.service.RegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class RegisterController {
    private final RegisterService registerService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody MemberDto dto){
        Long id = registerService.register(dto);
        return ResponseEntity.ok("회원가입 성공! Id: " + id);
    }

}

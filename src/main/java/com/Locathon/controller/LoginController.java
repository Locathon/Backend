package com.Locathon.controller;

import com.Locathon.dto.LoginDto;
import com.Locathon.model.Member;
import com.Locathon.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto dto){
        Member member = loginService.login(dto);
        return ResponseEntity.ok("로그인 성공! " + member.getEmail());
    }
}

package com.Locathon.Member.controller;

import com.Locathon.Member.dto.LoginDto;
import com.Locathon.Member.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<Map> login(@RequestBody LoginDto dto){
        String token = loginService.login(dto);
        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("email", dto.getEmail());
        response.put("message", "로그인 성공");

        return ResponseEntity.ok(response);
    }
}

package com.Locathon.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/protected")
@RequiredArgsConstructor
public class ProtectedController {
    @GetMapping
    public String securedTest(@AuthenticationPrincipal com.Locathon.service.MemberDetails userDetails) {
        return "인증된 사용자: " + userDetails.getUsername();
    }
}

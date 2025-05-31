package com.Locathon.controller;

import com.Locathon.dto.PlaceDto;
import com.Locathon.service.MemberDetails;
import com.Locathon.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/places")
@RequiredArgsConstructor
public class PlaceController {

    private final PlaceService placeService;

    // 장소 등록 (Merchant와 Resident만 가능)
    @PostMapping
    public ResponseEntity<PlaceDto> createPlace(@RequestBody PlaceDto placeDto, @AuthenticationPrincipal MemberDetails memberDetails) {
        PlaceDto created = placeService.createPlace(placeDto, memberDetails);
        return ResponseEntity.ok(created);
    }

    // 장소 단건 조회
    @GetMapping("/{id}")
    public ResponseEntity<PlaceDto> getPlaceById(@PathVariable Long id) {
        PlaceDto place = placeService.getPlaceById(id);
        return ResponseEntity.ok(place);
    }

    // 전체 장소 목록 중 keyword에 따른 장소 조회
    @GetMapping
    public ResponseEntity<List<PlaceDto>> getAllPlaces(@RequestParam(value = "keyword", required = false) String keyword) {
        if (keyword != null && !keyword.trim().isEmpty()) {
            return ResponseEntity.ok(placeService.searchPlaces(keyword));
        } else {
            return ResponseEntity.ok(placeService.getAllPlaces());
        }
    }
}
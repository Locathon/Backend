package com.Locathon.service;

import com.Locathon.dto.PlaceDto;
import com.Locathon.model.Member;
import com.Locathon.model.MemberRole;
import com.Locathon.model.Place;
import com.Locathon.repository.MemberRepository;
import com.Locathon.repository.PlaceRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlaceService {

    private final PlaceRepository placeRepository;

    // 장소 등록
    @Transactional
    public PlaceDto createPlace(PlaceDto placeDto, MemberDetails memberDetails) {
        Member member = memberDetails.getMember();

        if (member.getRole() != MemberRole.MERCHANT) {
            throw new IllegalStateException("Place는 Merchant만 등록할 수 있습니다.");
        }

        Place place = new Place();
        place.setName(placeDto.getName());
        place.setDescription(placeDto.getDescription());
        place.setLatitude(placeDto.getLatitude());
        place.setLongitude(placeDto.getLongitude());
        place.setAddress(placeDto.getAddress());
        place.setCreatedBy(member);

        Place saved = placeRepository.save(place);

        return mapToDto(saved);
    }

    // 장소 상세 조회
    public PlaceDto getPlaceById(Long id) {
        Place place = placeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 장소가 없습니다."));

        return mapToDto(place);
    }

    // 전체 장소 목록
    public List<PlaceDto> getAllPlaces() {
        return placeRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    // 장소 이름으로 검색
    public List<PlaceDto> searchPlaces(String keyword) {
        return placeRepository.findByNameContaining(keyword).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private PlaceDto mapToDto(Place place) {
        return PlaceDto.builder()
                .name(place.getName())
                .description(place.getDescription())
                .latitude(place.getLatitude())
                .longitude(place.getLongitude())
                .address(place.getAddress())
                .build();
    }
}
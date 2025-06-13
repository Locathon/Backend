package com.Locathon.Place.service;

import com.Locathon.Member.service.MemberDetails;
import com.Locathon.Place.dto.PlaceDto;
import com.Locathon.model.Member;
import com.Locathon.model.MemberRole;
import com.Locathon.model.Place;
import com.Locathon.Place.repository.PlaceRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

        if (member.getRole() == MemberRole.USER) {
            throw new IllegalStateException("Place는 USER가 등록할 수 없습니다.");
        }

        Place place = new Place();
        place.setName(placeDto.getName());
        place.setTitle(placeDto.getTitle());
        place.setContent(placeDto.getContent());
        place.setLatitude(placeDto.getLatitude());
        place.setLongitude(placeDto.getLongitude());
        place.setCreatedBy(member);

        List<String> imageUrls = new ArrayList<>();
        int limit = Math.min(placeDto.getImageUrls().size(), 3);

        for (int i = 0; i < limit; i++) {
            String imageUrl = placeDto.getImageUrls().get(i);
            imageUrls.add(imageUrl);
        }

        place.setImageUrls(imageUrls);
        Place saved = placeRepository.save(place);

        return PlaceDto.builder()
                .name(saved.getName())
                .title(saved.getTitle())
                .content(saved.getContent())
                .latitude(saved.getLatitude())
                .longitude(saved.getLongitude())
                .imageUrls(saved.getImageUrls())
                .build();
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
                .id(place.getId())
                .name(place.getName())
                .title(place.getTitle())
                .content(place.getContent())
                .latitude(place.getLatitude())
                .longitude(place.getLongitude())
                .imageUrls(place.getImageUrls())
                .build();
    }
}
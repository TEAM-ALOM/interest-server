package com.example.interestserver.service;

import com.example.interestserver.domain.Field;
import com.example.interestserver.domain.Interest;
import com.example.interestserver.dto.InterestDTO;
import com.example.interestserver.dto.InterestRequestDTO;
import com.example.interestserver.dto.InterestResponseDTO;
import com.example.interestserver.repository.InterestRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InterestService {

    private final InterestRepository interestRepository;

    public InterestService(InterestRepository interestRepository) {
        this.interestRepository = interestRepository;
    }

    public InterestDTO getInterestDTOById(Long id) {
        Interest interest = interestRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Interest not found with ID: " + id));
        return convertToDTO(interest);
    }

    public InterestDTO getInterestDTOByMemberId(String memberId) {
        Interest interest = interestRepository.findByMemberId(memberId);
        return convertToDTO(interest);
    }

    public List<InterestDTO> getInterestsByRange(double minLat, double maxLat, double minLong, double maxLong) {
        List<Interest> interests = interestRepository.findByLatitudeBetweenAndLongitudeBetween(minLat, maxLat, minLong, maxLong);
        return interests.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public List<InterestDTO> getInterestsByHighScore(double minScore) {
        List<Interest> interests = interestRepository.findByScoreGreaterThan(minScore);
        return interests.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public InterestResponseDTO createInterest(InterestRequestDTO interestDTO) {
        Interest interest = convertToEntity(interestDTO);
        Interest savedInterest = interestRepository.save(interest);
        return convertToDTO(savedInterest);
    }

    public void deleteInterest(Long id) {
        interestRepository.deleteById(id);
    }

    private InterestDTO convertToDTO(Interest interest) {
        InterestDTO interestDTO = new InterestDTO();
        interestDTO.setMemberId(interest.getMemberId());
        interestDTO.setLatitude(interest.getLatitude());
        interestDTO.setLongitude(interest.getLongitude());
        interestDTO.setScore(interest.getScore());
        interestDTO.setNickName(interest.getNickName());
        interestDTO.setField(interest.getField().name());
        return interestDTO;
    }

    private InterestResponseDTO convertToDTO(Interest interest) {
        InterestResponseDTO interestResponseDTO = new InterestResponseDTO();
        interestResponseDTO.setMemberId(interest.getMemberId());
        interestResponseDTO.setLatitude(interest.getLatitude());
        interestResponseDTO.setLongitude(interest.getLongitude());
        interestResponseDTO.setScore(interest.getScore());
        interestResponseDTO.setNickName(interest.getNickName());
        interestResponseDTO.setField(interest.getField().name());
        return interestResponseDTO;
    }

    private Interest convertToEntity(InterestRequestDTO interestDTO)
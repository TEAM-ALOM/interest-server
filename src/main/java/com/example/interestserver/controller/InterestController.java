package com.example.interestserver.controller;

import com.example.interestserver.dto.InterestDTO;
import com.example.interestserver.dto.InterestRequestDTO;
import com.example.interestserver.dto.InterestResponseDTO;
import com.example.interestserver.service.InterestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/interests")
public class InterestController {

    private final InterestService interestService;

    public InterestController(InterestService interestService) {
        this.interestService = interestService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<InterestDTO> getInterestById(@PathVariable Long id) {
        InterestDTO interest = interestService.getInterestDTOById(id);
        return ResponseEntity.ok(interest);
    }

    @GetMapping("/member/{memberId}")
    public ResponseEntity<InterestDTO> getInterestByMemberId(@PathVariable String memberId) {
        InterestDTO interest = interestService.getInterestDTOByMemberId(memberId);
        return ResponseEntity.ok(interest);
    }

    @GetMapping("/range")
    public ResponseEntity<List<InterestDTO>> getInterestsByRange(
            @RequestParam double minLat, @RequestParam double maxLat,
            @RequestParam double minLong, @RequestParam double maxLong) {
        List<InterestDTO> interests = interestService.getInterestsByRange(minLat, maxLat, minLong, maxLong);
        return ResponseEntity.ok(interests);
    }

    @GetMapping("/score-greater-than")
    public ResponseEntity<List<InterestDTO>> getInterestsByHighScore(@RequestParam double minScore) {
        List<InterestDTO> interests = interestService.getInterestsByHighScore(minScore);
        return ResponseEntity.ok(interests);
    }

    @PostMapping
    public ResponseEntity<InterestResponseDTO> createInterest(@RequestBody InterestRequestDTO interestDTO) {
        InterestResponseDTO createdInterest = interestService.createInterest(interestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdInterest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInterest(@PathVariable Long id) {
        interestService.deleteInterest(id);
        return ResponseEntity.noContent().build();
    }
}
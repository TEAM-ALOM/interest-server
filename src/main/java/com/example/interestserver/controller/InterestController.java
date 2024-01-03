package com.example.interestserver.controller;

import com.example.interestserver.dto.InterestDTO;
import com.example.interestserver.domain.Interest;
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

    // GET: /api/v1/interests/{id}
    @GetMapping("/{id}")
    public ResponseEntity<InterestDTO> getInterestById(@PathVariable Long id) {
        InterestDTO interest = interestService.getInterestDTOById(id);
        return ResponseEntity.ok(interest);
    }

    // GET: /api/v1/interests/member/{memberId}
    @GetMapping("/member/{memberId}")
    public ResponseEntity<InterestDTO> getInterestByMemberId(@PathVariable String memberId) {
        InterestDTO interest = interestService.getInterestDTOByMemberId(memberId);
        return ResponseEntity.ok(interest);
    }

    // GET: /api/v1/interests/range?minLat=&maxLat=&minLong=&maxLong=
    @GetMapping("/range")
    public ResponseEntity<List<InterestDTO>> getInterestsByRange(
            @RequestParam double minLat, @RequestParam double maxLat,
            @RequestParam double minLong, @RequestParam double maxLong) {
        List<InterestDTO> interests = interestService.getInterestsByRange(minLat, maxLat, minLong, maxLong);
        return ResponseEntity.ok(interests);
    }

    // GET: /api/v1/interests/high-score?minScore=
    @GetMapping("/high-score")
    public ResponseEntity<List<InterestDTO>> getInterestsByHighScore(@RequestParam double minScore) {
        List<InterestDTO> interests = interestService.getInterestsByHighScore(minScore);
        return ResponseEntity.ok(interests);
    }

    // POST: /api/v1/interests
    @PostMapping
    public ResponseEntity<InterestDTO> createInterest(@RequestBody InterestDTO interestDTO) {
        InterestDTO createdInterest = interestService.createInterest(interestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdInterest);
    }

    // DELETE: /api/v1/interests/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInterest(@PathVariable Long id) {
        interestService.deleteInterest(id);
        return ResponseEntity.noContent().build();
    }
}

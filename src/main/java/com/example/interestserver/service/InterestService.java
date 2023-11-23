package com.example.interestserver.service;

import com.example.interestserver.domain.Interest;
import com.example.interestserver.repository.InterestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InterestService {
    private final InterestRepository interestRepository;

    // 비지니스 로직 추가

    public List<Interest> getAllInterests() {
        return interestRepository.findAll();
    }

    public Interest getInterestById(Long id) {
        return interestRepository.findById(id).orElse(null);
    }

    public Interest saveInterest(Interest interest) {
        return interestRepository.save(interest);
    }

    public void deleteInterest(Long id) {
        interestRepository.deleteById(id);
    }

    //조회
    //삭제
    //삽입
}
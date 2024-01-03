package com.example.interestserver.repository;

import com.example.interestserver.domain.Interest;
import com.example.interestserver.domain.Field;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InterestRepository extends JpaRepository<Interest, Long> {

    Interest findByMemberId(String memberId);

    List<Interest> findByLatitudeBetweenAndLongitudeBetween(double minLat, double maxLat, double minLong, double maxLong);

    List<Interest> findByScoreGreaterThan(double score);

    List<Interest> findByField(Field field);
}

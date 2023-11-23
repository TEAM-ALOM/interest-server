package com.example.interestserver.repository;

import com.example.interestserver.domain.Field;
import com.example.interestserver.domain.Interest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InterestRepository extends JpaRepository<Interest, Long> {
    //비지니스 로직 추가
    List<Interest> findAllByField(Field field);
    //조회
    //삭제
    //삽입
}

//서비스 최대한 할 수 있는데 까지 구현하기
//라고 했던 것

//저장 조회
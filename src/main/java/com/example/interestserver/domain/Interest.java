package com.example.interestserver.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Interest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String memberId;
    private double latitude;
    private double longitude;
    private double score;
    private String nickName;
    private Field field;

    public Interest(double latitude, double longitude, double score, String nickName, String field) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.score = score;
        this.nickName = nickName;
        this.field = Field.valueOf(field);
    }
}
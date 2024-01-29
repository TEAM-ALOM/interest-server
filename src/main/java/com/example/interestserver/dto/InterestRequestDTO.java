package com.example.interestserver.dto;

import lombok.Data;

@Data
public class InterestRequestDTO {
    private double latitude;
    private double longitude;
    private double score;
    private String nickName;
    private String field;
}
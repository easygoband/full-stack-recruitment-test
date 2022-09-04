package com.fernando.zssn.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SurvivorRequestDto {
    private String name;
    private String surname;
    private Integer age;
    private Float latitude;
    private Float longitude;
}

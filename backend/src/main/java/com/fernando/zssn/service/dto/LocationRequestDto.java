package com.fernando.zssn.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LocationRequestDto {
    private Float latitude;
    private Float longitude;
}

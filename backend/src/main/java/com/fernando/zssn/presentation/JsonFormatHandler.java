package com.fernando.zssn.presentation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JsonFormatHandler {
    private Object data;
    private Integer code;
    private String message;
    private Long totalResults;
}

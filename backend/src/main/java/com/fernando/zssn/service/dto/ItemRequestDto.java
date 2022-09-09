package com.fernando.zssn.service.dto;

import com.fernando.zssn.persistence.entity.type.ItemType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ItemRequestDto {
    private ItemType type;
    private Integer quantity;
}

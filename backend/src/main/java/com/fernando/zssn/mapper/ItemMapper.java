package com.fernando.zssn.mapper;

import com.fernando.zssn.mapper.contract.IMapper;
import com.fernando.zssn.persistence.entity.Item;
import com.fernando.zssn.service.dto.ItemRequestDto;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper implements IMapper<ItemRequestDto, Item> {

    @Override
    public Item map(ItemRequestDto in) {
        Item item = new Item();
        item.setType(in.getType());
        item.setQuantity(in.getQuantity());
        return item;
    }
}

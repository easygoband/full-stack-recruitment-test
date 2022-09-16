package com.fernando.zssn.service;

import com.fernando.zssn.persistence.entity.Item;
import com.fernando.zssn.persistence.repository.ItemRepository;
import com.fernando.zssn.presentation.JsonPresenter;
import com.fernando.zssn.presentation.contract.IViewModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    private final JsonPresenter output;

    public ItemService(ItemRepository itemRepository, JsonPresenter output) {
        this.itemRepository = itemRepository;
        this.output = output;
    }

    public IViewModel fetchAllItems() {
        List<Item> items = this.itemRepository.findAll();
        long itemsTotal = this.itemRepository.count();

        return this.output.collectionResponse(items, itemsTotal);
    }
}

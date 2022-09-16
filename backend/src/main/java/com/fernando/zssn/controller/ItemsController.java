package com.fernando.zssn.controller;

import com.fernando.zssn.presentation.JsonFormatHandler;
import com.fernando.zssn.service.ItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/items")
public class ItemsController {
    private final ItemService itemService;

    public ItemsController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public ResponseEntity<JsonFormatHandler> fetchItems() {
        return this.itemService.fetchAllItems().getResponse();
    }
}

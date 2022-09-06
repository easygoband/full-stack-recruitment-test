package com.fernando.zssn.controller;

import com.fernando.zssn.presentation.JsonFormatHandler;
import com.fernando.zssn.service.SurvivorService;
import com.fernando.zssn.service.dto.LocationRequestDto;
import com.fernando.zssn.service.dto.SurvivorRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/survivors")
public class SurvivorsController {

    private final SurvivorService survivorService;

    public SurvivorsController(SurvivorService survivorService) {
        this.survivorService = survivorService;
    }

    @PostMapping
    public ResponseEntity<JsonFormatHandler> createSurvivor(@RequestBody SurvivorRequestDto survivorRequest) {
        return this.survivorService.createSurvivor(survivorRequest).getResponse();
    }

    @PutMapping(value = "/{id}/location")
    public ResponseEntity<JsonFormatHandler> updateSurvivorLocation(@PathVariable Long id, @RequestBody LocationRequestDto locationRequest) {
        return this.survivorService.updateSurvivorLocation(id, locationRequest).getResponse();
    }
}

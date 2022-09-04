package com.fernando.zssn.controller;

import com.fernando.zssn.presentation.JsonFormatHandler;
import com.fernando.zssn.service.SurvivorService;
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
        //return new ResponseEntity<>(survivor, HttpStatus.CREATED);
    }

    @GetMapping
    public String hello() {
        return "Hola";
    }
}

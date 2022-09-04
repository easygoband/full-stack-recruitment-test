package com.fernando.zssn.controller;

import com.fernando.zssn.ResponseCustom;
import com.fernando.zssn.persistence.entity.Survivor;
import com.fernando.zssn.service.SurvivorService;
import com.fernando.zssn.service.dto.SurvivorRequestDto;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<Survivor> createSurvivor(@RequestBody SurvivorRequestDto survivorRequest) {
        Survivor survivor = this.survivorService.createSurvivor(survivorRequest);
        return new ResponseEntity<>(survivor, HttpStatus.CREATED);
    }

    @GetMapping
    public String hello() {
        return "Hola";
    }
}

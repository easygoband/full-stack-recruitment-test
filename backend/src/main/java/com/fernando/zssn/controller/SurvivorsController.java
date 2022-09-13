package com.fernando.zssn.controller;

import com.fernando.zssn.persistence.entity.Survivor;
import com.fernando.zssn.presentation.JsonFormatHandler;
import com.fernando.zssn.service.SurvivorService;
import com.fernando.zssn.service.criteria.Page;
import com.fernando.zssn.service.criteria.type.OrderType;
import com.fernando.zssn.service.dto.LocationRequestDto;
import com.fernando.zssn.service.dto.SurvivorRequestDto;
import com.fernando.zssn.service.specification.SurvivorSpec;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    @GetMapping
    @ResponseBody
    public ResponseEntity<JsonFormatHandler> fetchSurvivors(@RequestParam(value = "search", required = false) String search) {
        return this.survivorService.fetchAllSurvivors(search).getResponse();
    }

    @PutMapping(value = "/{id}/location")
    public ResponseEntity<JsonFormatHandler> updateSurvivorLocation(@PathVariable Long id, @RequestBody LocationRequestDto locationRequest) {
        return this.survivorService.updateSurvivorLocation(id, locationRequest).getResponse();
    }

    @PutMapping(value = "/{id}/infected")
    public ResponseEntity<JsonFormatHandler> flagInfectedSurvivor(@PathVariable Long id) {
        return this.survivorService.updateInfectedReportsBySurvivor(id).getResponse();
    }
}

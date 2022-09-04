package com.fernando.zssn.service;

import com.fernando.zssn.mapper.SurvivorMapper;
import com.fernando.zssn.persistence.entity.Survivor;
import com.fernando.zssn.persistence.repository.SurvivorRepository;
import com.fernando.zssn.service.dto.SurvivorRequestDto;
import org.springframework.stereotype.Service;

@Service
public class SurvivorService {

    private final SurvivorRepository repository;

    private final SurvivorMapper mapper;

    public SurvivorService(SurvivorRepository repository, SurvivorMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Survivor createSurvivor(SurvivorRequestDto survivorRequest) {
        Survivor survivor = mapper.map(survivorRequest);
        this.repository.save(survivor);
        return survivor;
    }
}

package com.fernando.zssn.service;

import com.fernando.zssn.mapper.SurvivorMapper;
import com.fernando.zssn.persistence.entity.Survivor;
import com.fernando.zssn.persistence.repository.SurvivorRepository;
import com.fernando.zssn.presentation.JsonPresenter;
import com.fernando.zssn.presentation.contract.IViewModel;
import com.fernando.zssn.service.dto.SurvivorRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SurvivorService {

    private final SurvivorRepository repository;

    private final SurvivorMapper mapper;

    private final JsonPresenter output;

    public SurvivorService(SurvivorRepository repository, SurvivorMapper mapper, JsonPresenter output) {
        this.repository = repository;
        this.mapper = mapper;
        this.output = output;
    }

    public IViewModel createSurvivor(SurvivorRequestDto survivorRequest) {
        Survivor survivor = mapper.map(survivorRequest);
        this.repository.save(survivor);
        return this.output.createdResponse(survivor);
    }
}

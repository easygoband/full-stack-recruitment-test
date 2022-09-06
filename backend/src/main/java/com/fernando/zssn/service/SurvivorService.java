package com.fernando.zssn.service;

import com.fernando.zssn.mapper.SurvivorMapper;
import com.fernando.zssn.persistence.entity.Survivor;
import com.fernando.zssn.persistence.repository.SurvivorRepository;
import com.fernando.zssn.presentation.JsonPresenter;
import com.fernando.zssn.presentation.contract.IViewModel;
import com.fernando.zssn.service.dto.LocationRequestDto;
import com.fernando.zssn.service.dto.SurvivorRequestDto;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public IViewModel updateSurvivorLocation(Long id, LocationRequestDto locationRequest) {
        Optional<Survivor> optionalSurvivor = repository.findById(id);

        if (!optionalSurvivor.isPresent()) {
            return this.output.notFoundResponse("Survivor with id (" + id + ") not found");
        }

        Survivor survivor = optionalSurvivor.get();
        survivor.setLatitude(locationRequest.getLatitude());
        survivor.setLongitude(locationRequest.getLongitude());

        repository.save(survivor);
        return this.output.successResponse("Survivor location with id (" + id + ") updated");
    }
}

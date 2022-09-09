package com.fernando.zssn.service;

import com.fernando.zssn.mapper.ItemMapper;
import com.fernando.zssn.mapper.SurvivorMapper;
import com.fernando.zssn.persistence.entity.Item;
import com.fernando.zssn.persistence.entity.Survivor;
import com.fernando.zssn.persistence.repository.ItemRepository;
import com.fernando.zssn.persistence.repository.SurvivorRepository;
import com.fernando.zssn.presentation.JsonPresenter;
import com.fernando.zssn.presentation.contract.IViewModel;
import com.fernando.zssn.service.dto.LocationRequestDto;
import com.fernando.zssn.service.dto.SurvivorRequestDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SurvivorService {

    private final SurvivorRepository repository;

    private final SurvivorMapper mapper;

    private final ItemMapper itemMapper;

    private final JsonPresenter output;

    private final ItemRepository itemRepository;

    public SurvivorService(
            SurvivorRepository repository,
            SurvivorMapper mapper,
            JsonPresenter output,
            ItemMapper itemMapper,
            ItemRepository itemRepository
    ) {
        this.repository = repository;
        this.mapper = mapper;
        this.output = output;
        this.itemMapper = itemMapper;
        this.itemRepository = itemRepository;
    }

    public IViewModel createSurvivor(SurvivorRequestDto survivorRequest) {
        Survivor survivor = mapper.map(survivorRequest);

        survivor = this.repository.save(survivor);

        List<Item> items = survivorRequest
                .getItems()
                .stream()
                .filter(item -> item.getQuantity() > 0)
                .map(itemMapper::map)
                .toList();

        Survivor finalSurvivor = survivor;
        items.forEach(item -> {
            item.setSurvivor(finalSurvivor);
            item.setPoints(item.calculatePoints());
        });

        this.itemRepository.saveAll(items);

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

    public IViewModel updateInfectedReportsBySurvivor(Long id) {
        Optional<Survivor> optionalSurvivor = repository.findById(id);

        if (optionalSurvivor.isPresent()) {
            return this.output.notFoundResponse("Survivor with id (" + id + ") not found");
        }

        Survivor survivor = optionalSurvivor.get();
        survivor.addInfectedReport();

        if (survivor.getInfectedReports() >= 3) {
            survivor.setIsInfected(true);
        }

        repository.save(survivor);
        return this.output.successResponse("Survivor with id (" + id + ") was reported as infected");
    }
}

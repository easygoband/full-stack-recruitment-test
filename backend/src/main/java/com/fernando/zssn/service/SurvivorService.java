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
import com.fernando.zssn.service.specification.SurvivorSpecificationBuilder;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

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


        List<Item> items = survivorRequest
                .getItems()
                .stream()
                .filter(item -> item.getQuantity() > 0)
                .map(itemMapper::map)
                .toList();

        AtomicReference<Integer> points = new AtomicReference<>(0);

        items.forEach(item -> {
            item.setPoints(item.calculatePoints());
            points.updateAndGet(v -> v + item.getPoints());
        });

        survivor.setPoints(points.get());

        survivor = this.repository.save(survivor);

        Survivor finalSurvivor = survivor;

        items.forEach(item -> {
            item.setSurvivor(finalSurvivor);
        });

        this.itemRepository.saveAll(items);

        return this.output.createdResponse(survivor);
    }

    public IViewModel fetchSingleSurvivor(Long id) {
        Optional<Survivor> optionalSurvivor = repository.findById(id);

        if (optionalSurvivor.isEmpty()) {
            return  this.output.notFoundResponse("Survivor with id (" + id + ") not found");
        }

        Survivor survivor = optionalSurvivor.get();
        return this.output.fetchResourceResponse(survivor);
    }

    public IViewModel fetchAllSurvivors(String search) {
        SurvivorSpecificationBuilder searchCriteria = new SurvivorSpecificationBuilder();
        Specification<Survivor> specification = searchCriteria.build(search);

        List<Survivor> survivors = this.repository.findAll(specification);
        long survivorsTotal = this.repository.count(specification);

        return this.output.collectionResponse(survivors,survivorsTotal);
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

        if (!optionalSurvivor.isPresent()) {
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

package com.fernando.zssn.mapper;

import com.fernando.zssn.mapper.contract.IMapper;
import com.fernando.zssn.persistence.entity.Survivor;
import com.fernando.zssn.service.dto.SurvivorRequestDto;
import org.springframework.stereotype.Component;

@Component
public class SurvivorMapper implements IMapper<SurvivorRequestDto, Survivor> {

    @Override
    public Survivor map(SurvivorRequestDto in) {
        Survivor survivor = new Survivor();
        survivor.setName(in.getName());
        survivor.setSurname(in.getSurname());
        survivor.setAge(in.getAge());
        survivor.setLatitude(in.getLatitude());
        survivor.setLongitude(in.getLongitude());
        survivor.setInfectedReports(0);
        survivor.setIsInfected(false);
        return survivor;
    }
}

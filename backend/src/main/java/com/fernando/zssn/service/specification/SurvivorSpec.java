package com.fernando.zssn.service.specification;

import com.fernando.zssn.persistence.entity.Survivor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class SurvivorSpec {

    public static Specification<Survivor> getSpec(String name, String surname) {
        return ((root, query, criteriaBuilder) -> {
            List<Predicate> predicate = new ArrayList<>();

            if (name != null && !(name.isEmpty()))
                predicate.add(criteriaBuilder.like(root.get("name"),"%" + name + "%"));

            if (surname != null && !(surname.isEmpty()))
                predicate.add(criteriaBuilder.equal(root.get("surname"),surname));

            return criteriaBuilder.and(predicate.toArray(new Predicate[0]));
        });
    }

}

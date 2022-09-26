package com.fernando.zssn.service.specification;

import com.fernando.zssn.persistence.entity.Survivor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SurvivorSpecificationBuilder {

    public Specification<Survivor> build(String search) {
        return ((root, query, criteriaBuilder) -> {
            List<Predicate> predicate = new ArrayList<>();

            Pattern pattern = Pattern.compile("(\\w+?)(:|<|>|~)(\\w+?),");
            Matcher matcher = pattern.matcher(search + ",");

            while (matcher.find()) {
                switch (matcher.group(2)) {
                    case ":" -> predicate.add(criteriaBuilder.equal(root.get(matcher.group(1)), matcher.group(3)));
                    case "~" -> predicate.add(criteriaBuilder.like(root.get(matcher.group(1)), "%" + matcher.group(3) + "%"));
                    case ">" -> predicate.add(criteriaBuilder.greaterThan(root.get(matcher.group(1)), matcher.group(3)));
                    case "<" -> predicate.add(criteriaBuilder.lessThan(root.get(matcher.group(1)), matcher.group(3)));
                }
            }

            return criteriaBuilder.and(predicate.toArray(new Predicate[0]));
        });
    }
}

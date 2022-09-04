package com.fernando.zssn.persistence.repository;

import com.fernando.zssn.persistence.entity.Survivor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SurvivorRepository extends JpaRepository<Survivor, Long> {
}

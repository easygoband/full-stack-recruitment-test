package com.fernando.zssn.persistence.repository;

import com.fernando.zssn.persistence.entity.Survivor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SurvivorRepository extends JpaRepository<Survivor, Long>, JpaSpecificationExecutor<Survivor> {
}

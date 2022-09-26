package com.fernando.zssn.persistence.repository;

import com.fernando.zssn.persistence.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}

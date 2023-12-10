package com.klagan.inditex.persistence.repository;

import com.klagan.inditex.persistence.entity.PricesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PricesRepository extends JpaRepository<PricesEntity, Integer>, PricesRepositoryCustom {
}


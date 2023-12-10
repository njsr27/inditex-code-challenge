package com.klagan.inditex.domain.repository;

import com.klagan.inditex.domain.repository.entity.PricesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PricesRepository extends JpaRepository<PricesEntity, Integer>, PricesRepositoryCustom {
}


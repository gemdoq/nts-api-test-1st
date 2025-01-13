package com.example.apitest250109.domain.taxschedule.repository;

import com.example.apitest250109.domain.taxschedule.model.entity.TaxScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaxScheduleRepository extends JpaRepository<TaxScheduleEntity, Long> {
}
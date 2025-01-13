package com.example.apitest250109.domain.taxschedule.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class TaxSchedule {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String taxScheduleDate; // 세무일정
	private String taxContent;      // 세무내용
	private String remark;          // 비고
}
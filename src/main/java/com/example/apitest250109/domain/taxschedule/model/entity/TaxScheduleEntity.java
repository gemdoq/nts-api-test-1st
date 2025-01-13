package com.example.apitest250109.domain.taxschedule.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "tb_tax_schedule")
public class TaxScheduleEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String taxScheduleDate; // 세무일정
	private String taxContent;      // 세무내용
	private String remark;          // 비고
}
package com.example.apitest250109.domain.taxschedule.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class TaxScheduleResponse {

	@JsonProperty("currentCount")
	private int currentCount;

	@JsonProperty("data")
	private List<DataItem> data;

	@Data
	public static class DataItem {
		@JsonProperty("세무내용")
		private String taxContent;

		@JsonProperty("세무일정")
		private String scheduleDate;

		@JsonProperty("비고")
		private String remark;
	}
}

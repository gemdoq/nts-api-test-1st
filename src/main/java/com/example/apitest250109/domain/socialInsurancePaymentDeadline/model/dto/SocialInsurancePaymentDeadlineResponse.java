package com.example.apitest250109.domain.socialInsurancePaymentDeadline.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class SocialInsurancePaymentDeadlineResponse {

	@JsonProperty("currentCount")
	private int currentCount;

	@JsonProperty("data")
	private List<DataItem> data;

	@Data
	public static class DataItem {
		@JsonProperty("고지년월")
		private String noticeYearMonth;

		@JsonProperty("납부마감일")
		private String paymentDeadline;

		@JsonProperty("공휴일 납부마감일")
		private String holidayDeadline;
	}

}

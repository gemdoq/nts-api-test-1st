package com.example.apitest250109.global.api;

import com.example.apitest250109.domain.taxschedule.model.dto.TaxScheduleResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "taxScheduleApi", url = "${TAX_SCHEDULE_API_URL_BASE}")
public interface TaxScheduleFeignClient {

	@GetMapping
	TaxScheduleResponse getTaxSchedules(
			@RequestParam("page") int page,
			@RequestParam("perPage") int perPage,
			@RequestParam("returnType") String returnType,
			@RequestParam("serviceKey") String serviceKey
	);
}

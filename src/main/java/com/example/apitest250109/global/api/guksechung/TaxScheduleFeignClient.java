package com.example.apitest250109.global.api.guksechung;

import com.example.apitest250109.domain.taxschedule.model.dto.TaxScheduleResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "taxScheduleApi", url = "${api.guksechung.base-url}")
public interface TaxScheduleFeignClient {

	@GetMapping(value = "${api.guksechung.taxSchedule.endpoint}")
	TaxScheduleResponse getTaxSchedules(
			@RequestParam("page") int page,
			@RequestParam("perPage") int perPage,
			@RequestParam("returnType") String returnType,
			@RequestParam("serviceKey") String serviceKey
	);
}

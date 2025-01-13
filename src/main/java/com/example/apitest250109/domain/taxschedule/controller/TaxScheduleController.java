package com.example.apitest250109.domain.taxschedule.controller;

import com.example.apitest250109.domain.taxschedule.service.TaxScheduleService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tax-schedule")
public class TaxScheduleController {

	private final TaxScheduleService service;

	@Value("${TAX_SCHEDULE_API_URL_BASE}")
	private String defaultBaseUrl;

	@Value("${TAX_SCHEDULE_API_PAGE}")
	private int defaultPage;

	@Value("${TAX_SCHEDULE_API_PER_PAGE}")
	private int defaultPerPage;

	public TaxScheduleController(TaxScheduleService service) {
		this.service = service;
	}

	@GetMapping("/fetch-and-save")
	public String fetchAndSaveSchedules(
			@RequestParam(value = "urlBase", required = false) String baseUrl,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "perPage", required = false) Integer perPage
	) {
		try {
			String finalBaseUrl = (baseUrl != null) ? baseUrl : defaultBaseUrl;
			int finalPage = (page != null) ? page : defaultPage;
			int finalPerPage = (perPage != null) ? perPage : defaultPerPage;

			service.fetchAndSaveSchedules(finalBaseUrl, finalPage, finalPerPage);
			return "데이터 저장 성공!";
		} catch (Exception e) {
			return "에러 발생: " + e.getMessage();
		}
	}
}

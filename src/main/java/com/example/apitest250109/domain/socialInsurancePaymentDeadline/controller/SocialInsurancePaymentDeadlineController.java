package com.example.apitest250109.domain.socialInsurancePaymentDeadline.controller;

import com.example.apitest250109.domain.socialInsurancePaymentDeadline.service.SocialInsurancePaymentDeadlineService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/social-insurance-payment-deadline")
public class SocialInsurancePaymentDeadlineController {

	private final SocialInsurancePaymentDeadlineService service;

	@Value("${api.publicdata.socialInsurancePaymentDeadline.page}")
	private int defaultPage;

	@Value("${api.publicdata.socialInsurancePaymentDeadline.per-page}")
	private int defaultPerPage;

	public SocialInsurancePaymentDeadlineController(SocialInsurancePaymentDeadlineService service) {
		this.service = service;
	}

	@GetMapping("/fetch-and-save")
	public String fetchAndSaveDeadlines(
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "perPage", required = false) Integer perPage
	) {
		try {
			int finalPage = (page != null) ? page : defaultPage;
			int finalPerPage = (perPage != null) ? perPage : defaultPerPage;

			service.fetchAndSaveSocialInsurancePaymentDeadlines(finalPage, finalPerPage);
			return "데이터 저장 성공!";
		} catch (Exception e) {
			return "에러 발생: " + e.getMessage();
		}
	}
}

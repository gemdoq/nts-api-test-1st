package com.example.apitest250109.global.api.publicdata;

import com.example.apitest250109.domain.socialInsurancePaymentDeadline.model.dto.SocialInsurancePaymentDeadlineResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "socialInsurancePaymentDeadlineApi", url = "${api.publicdata.base-url}")
public interface SocialInsurancePaymentDeadlineFeignClient {

	@GetMapping(value = "${api.publicdata.socialInsurancePaymentDeadline.endpoint}")
	SocialInsurancePaymentDeadlineResponse getSocialInsurancePaymentDeadlines(
			@RequestParam("page") int page,
			@RequestParam("perPage") int perPage,
			@RequestParam("returnType") String returnType,
			@RequestParam("serviceKey") String serviceKey
	);
}

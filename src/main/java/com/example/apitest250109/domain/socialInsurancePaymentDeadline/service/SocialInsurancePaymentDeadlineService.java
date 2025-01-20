package com.example.apitest250109.domain.socialInsurancePaymentDeadline.service;

import com.example.apitest250109.domain.socialInsurancePaymentDeadline.model.dto.SocialInsurancePaymentDeadlineResponse;
import com.example.apitest250109.domain.socialInsurancePaymentDeadline.model.entity.SocialInsurancePaymentDeadlineEntity;
import com.example.apitest250109.domain.socialInsurancePaymentDeadline.repository.SocialInsurancePaymentDeadlineRepository;
import com.example.apitest250109.global.api.guksechung.SocialInsurancePaymentDeadlineFeignClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SocialInsurancePaymentDeadlineService {

	private final SocialInsurancePaymentDeadlineFeignClient feignClient;
	private final SocialInsurancePaymentDeadlineRepository repository;

	@Value("${api.guksechung.socialInsurancePaymentDeadline.return-type}")
	private String defaultReturnType;

	@Value("${GUKSECHUNG_API_SERVICE_KEY}")
	private String defaultServiceKey;

	public SocialInsurancePaymentDeadlineService(SocialInsurancePaymentDeadlineFeignClient feignClient, SocialInsurancePaymentDeadlineRepository repository) {
		this.feignClient = feignClient;
		this.repository = repository;
	}

	public void fetchAndSaveSocialInsurancePaymentDeadlines(int page, int perPage) {
		try {
			// FeignClient를 통해 API 호출
			SocialInsurancePaymentDeadlineResponse response = feignClient.getSocialInsurancePaymentDeadlines(
					page, perPage, defaultReturnType, defaultServiceKey
			);

			if (response == null) {
				throw new RuntimeException("API 호출 결과 응답이 null입니다.");
			}

			if (response.getData() == null || response.getData().isEmpty()) {
				throw new RuntimeException("API 호출 결과 데이터가 비어있습니다.");
			}

			// 응답 데이터 저장
			response.getData().forEach(dataItem -> {
				SocialInsurancePaymentDeadlineEntity deadlineEntity = new SocialInsurancePaymentDeadlineEntity();
				deadlineEntity.setNoticeYearMonth(dataItem.getNoticeYearMonth());
				deadlineEntity.setPaymentDeadline(dataItem.getPaymentDeadline());
				deadlineEntity.setHolidayDeadline(dataItem.getHolidayDeadline());
				repository.save(deadlineEntity);
			});
		} catch (Exception e) {
			throw new RuntimeException("API 호출 및 데이터 저장 중 오류 발생: " + e.getMessage(), e);
		}
	}
}

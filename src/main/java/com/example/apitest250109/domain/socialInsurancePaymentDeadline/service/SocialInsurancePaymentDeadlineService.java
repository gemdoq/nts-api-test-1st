package com.example.apitest250109.domain.socialInsurancePaymentDeadline.service;

import com.example.apitest250109.domain.socialInsurancePaymentDeadline.model.dto.SocialInsurancePaymentDeadlineResponse;
import com.example.apitest250109.domain.socialInsurancePaymentDeadline.model.entity.SocialInsurancePaymentDeadlineEntity;
import com.example.apitest250109.domain.socialInsurancePaymentDeadline.repository.SocialInsurancePaymentDeadlineRepository;
import com.example.apitest250109.global.api.publicdata.SocialInsurancePaymentDeadlineFeignClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SocialInsurancePaymentDeadlineService {

	private final SocialInsurancePaymentDeadlineFeignClient feignClient;
	private final SocialInsurancePaymentDeadlineRepository repository;

	@Value("${api.publicdata.socialInsurancePaymentDeadline.return-type}")
	private String defaultReturnType;

	@Value("${PUBLICDATA_API_SERVICE_KEY}")
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

			if (response == null || response.getData() == null || response.getData().isEmpty()) {
				throw new RuntimeException("API 호출 결과가 유효하지 않습니다.");
			}

			List<SocialInsurancePaymentDeadlineEntity> entities = response.getData().stream()
					.map(dataItem -> {
						SocialInsurancePaymentDeadlineEntity entity = new SocialInsurancePaymentDeadlineEntity();
						entity.setNoticeYearMonth(dataItem.getNoticeYearMonth());
						entity.setPaymentDeadline(dataItem.getPaymentDeadline());
						entity.setHolidayDeadline(dataItem.getHolidayDeadline());
						return entity;
					})
					.collect(Collectors.toList());
			repository.saveAll(entities);
		} catch (Exception e) {
			throw new RuntimeException("API 호출 및 데이터 저장 중 오류 발생: " + e.getMessage(), e);
		}
	}
}

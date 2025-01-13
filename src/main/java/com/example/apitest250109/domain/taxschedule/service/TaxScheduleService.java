package com.example.apitest250109.domain.taxschedule.service;

import com.example.apitest250109.domain.taxschedule.model.dto.TaxScheduleResponse;
import com.example.apitest250109.domain.taxschedule.model.entity.TaxScheduleEntity;
import com.example.apitest250109.domain.taxschedule.repository.TaxScheduleRepository;
import com.example.apitest250109.global.api.guksechung.TaxScheduleFeignClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TaxScheduleService {

	private final TaxScheduleFeignClient feignClient;
	private final TaxScheduleRepository repository;

	@Value("${TAX_SCHEDULE_API_RETURN_TYPE}")
	private String defaultReturnType;

	@Value("${TAX_SCHEDULE_API_SERVICE_KEY}")
	private String defaultServiceKey;

	public TaxScheduleService(TaxScheduleFeignClient feignClient, TaxScheduleRepository repository) {
		this.feignClient = feignClient;
		this.repository = repository;
	}

	public void fetchAndSaveSchedules(String baseUrl, int page, int perPage) {
		try {
			// FeignClient를 통해 API 호출
			TaxScheduleResponse response = feignClient.getTaxSchedules(
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
				TaxScheduleEntity taxScheduleEntity = new TaxScheduleEntity();
				taxScheduleEntity.setTaxScheduleDate(dataItem.getScheduleDate());
				taxScheduleEntity.setTaxContent(dataItem.getTaxContent());
				taxScheduleEntity.setRemark(dataItem.getRemark());
				repository.save(taxScheduleEntity);
			});
		} catch (Exception e) {
			throw new RuntimeException("API 호출 및 데이터 저장 중 오류 발생: " + e.getMessage(), e);
		}
	}
}
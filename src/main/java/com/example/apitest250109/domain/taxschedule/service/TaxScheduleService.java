package com.example.apitest250109.domain.taxschedule.service;

import com.example.apitest250109.domain.taxschedule.model.dto.TaxScheduleResponse;
import com.example.apitest250109.domain.taxschedule.model.entity.TaxScheduleEntity;
import com.example.apitest250109.domain.taxschedule.repository.TaxScheduleRepository;
import com.example.apitest250109.global.api.publicdata.TaxScheduleFeignClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TaxScheduleService {

	private final TaxScheduleFeignClient feignClient;
	private final TaxScheduleRepository repository;

	@Value("${api.publicdata.taxSchedule.return-type}")
	private String defaultReturnType;

	@Value("${PUBLICDATA_API_SERVICE_KEY}")
	private String defaultServiceKey;

	public TaxScheduleService(TaxScheduleFeignClient feignClient, TaxScheduleRepository repository) {
		this.feignClient = feignClient;
		this.repository = repository;
	}

	public void fetchAndSaveSchedules(int page, int perPage) {
		try {
			// FeignClient를 통해 API 호출
			TaxScheduleResponse response = feignClient.getTaxSchedules(
					page, perPage, defaultReturnType, defaultServiceKey
			);

			if (response == null || response.getData() == null || response.getData().isEmpty()) {
				throw new RuntimeException("API 호출 결과가 유효하지 않습니다.");
			}

			List<TaxScheduleEntity> entities = response.getData().stream()
					.map(dataItem -> {
						TaxScheduleEntity entity = new TaxScheduleEntity();
						entity.setTaxScheduleDate(dataItem.getScheduleDate());
						entity.setTaxContent(dataItem.getTaxContent());
						entity.setRemark(dataItem.getRemark());
						return entity;
					})
					.collect(Collectors.toList());
			repository.saveAll(entities);
		} catch (Exception e) {
			throw new RuntimeException("API 호출 및 데이터 저장 중 오류 발생: " + e.getMessage(), e);
		}
	}
}
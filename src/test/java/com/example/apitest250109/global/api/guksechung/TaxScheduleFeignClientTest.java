package com.example.apitest250109.global.api;

import com.example.apitest250109.domain.taxschedule.model.dto.TaxScheduleResponse;
import com.example.apitest250109.global.api.guksechung.TaxScheduleFeignClient;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Value;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class TaxScheduleFeignClientTest {

	@Value("${TAX_SCHEDULE_API_RETURN_TYPE}")
	private String defaultReturnType;

	@Value("${TAX_SCHEDULE_API_SERVICE_KEY}")
	private String defaultServiceKey;

	private final TaxScheduleFeignClient feignClient = Mockito.mock(TaxScheduleFeignClient.class);

	@Test
	public void testGetTaxSchedules() {

		TaxScheduleResponse expectedResponse = new TaxScheduleResponse();
		expectedResponse.setCurrentCount(1);

		when(feignClient.getTaxSchedules(1, 300, defaultReturnType, defaultServiceKey))
				.thenReturn(expectedResponse);


		TaxScheduleResponse actualResponse = feignClient.getTaxSchedules(1, 300, defaultReturnType, defaultServiceKey);


		assertEquals(expectedResponse.getCurrentCount(), actualResponse.getCurrentCount());
	}
}
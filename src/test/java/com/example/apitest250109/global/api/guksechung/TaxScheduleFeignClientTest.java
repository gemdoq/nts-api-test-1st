package com.example.apitest250109.global.api.guksechung;

import com.example.apitest250109.domain.taxschedule.model.dto.TaxScheduleResponse;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class TaxScheduleFeignClientTest {

	private String defaultReturnType = "json";
	private String defaultServiceKey = "serviceKey";

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
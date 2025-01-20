package com.example.apitest250109.global.api.guksechung;

import com.example.apitest250109.domain.socialInsurancePaymentDeadline.model.dto.SocialInsurancePaymentDeadlineResponse;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class SocialInsurancePaymentDeadlineFeignClientTest {

	private String defaultReturnType = "json";
	private String defaultServiceKey = "serviceKey";

	private final SocialInsurancePaymentDeadlineFeignClient feignClient = Mockito.mock(SocialInsurancePaymentDeadlineFeignClient.class);

	@Test
	public void testGetSocialInsurancePaymentDeadline() {

		SocialInsurancePaymentDeadlineResponse expectedResponse = new SocialInsurancePaymentDeadlineResponse();
		expectedResponse.setCurrentCount(1);

		when(feignClient.getSocialInsurancePaymentDeadlines(1, 300, defaultReturnType, defaultServiceKey))
				.thenReturn(expectedResponse);

		SocialInsurancePaymentDeadlineResponse actualResponse = feignClient.getSocialInsurancePaymentDeadlines(1, 300, defaultReturnType, defaultServiceKey);

		assertEquals(expectedResponse.getCurrentCount(), actualResponse.getCurrentCount());
	}
}

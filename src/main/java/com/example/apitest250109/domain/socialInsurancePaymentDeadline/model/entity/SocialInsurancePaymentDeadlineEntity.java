package com.example.apitest250109.domain.socialInsurancePaymentDeadline.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "tb_social_insurance_payment_deadline")
public class SocialInsurancePaymentDeadlineEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String noticeYearMonth;  // 고지년월
	private String paymentDeadline; // 납부마감일
	private String holidayDeadline;   // 공휴일 납부마감일
}

package com.javaacademy;

import com.javaacademy.insurance.InsuranceContract;
import com.javaacademy.insurance.InsuranceType;
import com.javaacademy.insurance_service.InsuranceService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import static java.math.BigDecimal.valueOf;

@SpringBootApplication
public class InsuranceApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(InsuranceApplication.class, args);
		InsuranceService insuranceService = context.getBean(InsuranceService.class);
		InsuranceContract insuranceContract = insuranceService.giveInsuranceOffer(valueOf(200_000),
				"Иванов Иван Иванович",
				InsuranceType.HEALTH_INSURANCE);
		insuranceService.payInsurance(insuranceContract.getContractNumber());
	}
}

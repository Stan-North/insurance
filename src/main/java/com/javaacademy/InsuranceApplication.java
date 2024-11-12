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
		System.out.println("стоимость: " + insuranceContract.getInsurancePrice());
		System.out.println("номер: " + insuranceContract.getContractNumber());
		System.out.println("тип: " + insuranceContract.getInsuranceType());
		System.out.println("статус: " + insuranceContract.getContractStatus());
		System.out.println("страна: " + insuranceContract.getCountry());
		System.out.println("клиент: " + insuranceContract.getClientFullName());
		System.out.println("стоимость покрытия: " + insuranceContract.getCoverageCost());
		System.out.println("валюта: " + insuranceContract.getCurrency());
	}
}

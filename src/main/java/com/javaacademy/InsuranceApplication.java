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
		System.out.println("стоимость:" + insuranceContract.getInsurancePrice());
		System.out.println("номер:" + insuranceContract.getContractNumber());
		System.out.println("тип:" + insuranceContract.getInsuranceType());
		System.out.println("статус:" + insuranceContract.getContractStatus());
		System.out.println("страна:" + insuranceContract.getCountry());
		System.out.println("клиент:" + insuranceContract.getClientFullName());
		System.out.println("стоимость покрытия:" + insuranceContract.getCoverageCost());
		System.out.println("валюта:" + insuranceContract.getCurrency());
	}

	//1800
	//10800
	//Ситуация №1: Рассчитать стоимость страховки при грабеже, сумма покрытия 50 000. Ожидаемая стоимость: 2800.
	//    Ситуация №2: Рассчитать стоимость страховки при мед страховке, сумма покрытия 200 000. Ожидаемая стоимость: 6800.
}

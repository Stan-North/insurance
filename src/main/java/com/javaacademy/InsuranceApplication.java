package com.javaacademy;

import com.javaacademy.insurance.InsuranceContract;
import com.javaacademy.insurance.InsuranceType;
import com.javaacademy.insurance_service.InsuranceService;
import lombok.Generated;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import static java.math.BigDecimal.valueOf;

@SpringBootApplication
@Generated
public class InsuranceApplication {

    public static final int COVERAGE_PRICE = 200_000;
    public static final String CLIENT_NAME = "Иванов иван иванович";

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(InsuranceApplication.class, args);
        InsuranceService insuranceService = context.getBean(InsuranceService.class);
        InsuranceContract insuranceContract = insuranceService.giveInsuranceOffer(valueOf(COVERAGE_PRICE),
                CLIENT_NAME,
                InsuranceType.HEALTH_INSURANCE);
        insuranceService.payInsurance(insuranceContract.getContractNumber());
    }
}

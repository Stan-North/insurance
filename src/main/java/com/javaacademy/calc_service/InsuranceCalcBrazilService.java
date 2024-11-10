package com.javaacademy.calc_service;

import com.javaacademy.insurance.InsuranceType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static java.math.BigDecimal.ZERO;
import static java.math.BigDecimal.valueOf;

@Component
@Profile(value = "brazil")
public class InsuranceCalcBrazilService implements InsuranceCalcService {
    private static final BigDecimal ROBBERY_INCREASE = valueOf(300);
    private static final BigDecimal HEALTH_INCREASE = valueOf(800);

    @Value("${app.health-coefficient}")
    private Double healthCoefficient;
    @Value("${app.robbery-coefficient}")
    private Double robberyCoefficient;


    @Override
    public BigDecimal calcInsuranceCost(BigDecimal coverageCost, InsuranceType insuranceType) {
        BigDecimal result = ZERO;
        if (insuranceType.equals(InsuranceType.ROBBERY_INSURANCE)) {
            result = coverageCost.multiply(valueOf(robberyCoefficient)).add(ROBBERY_INCREASE);
        } else if (insuranceType.equals(InsuranceType.HEALTH_INSURANCE)) {
            result = coverageCost.multiply(valueOf(healthCoefficient)).add(HEALTH_INCREASE);
        }
        return result;
    }
}

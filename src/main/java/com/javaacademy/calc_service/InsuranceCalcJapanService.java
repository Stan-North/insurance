package com.javaacademy.calc_service;

import com.javaacademy.insurance.InsuranceType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import static java.math.BigDecimal.valueOf;
import static java.math.BigDecimal.ZERO;

import java.math.BigDecimal;

@Component
@Profile(value = "japan")
public class InsuranceCalcJapanService implements InsuranceCalcService {
    private static final BigDecimal ROBBERY_INCREASE = valueOf(10_000);
    private static final BigDecimal HEALTH_INCREASE = valueOf(12_000);

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

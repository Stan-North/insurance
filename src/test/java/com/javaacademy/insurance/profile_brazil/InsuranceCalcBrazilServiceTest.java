package com.javaacademy.insurance.profile_brazil;

import com.javaacademy.calc_service.InsuranceCalcBrazilService;
import com.javaacademy.insurance.InsuranceType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;

import static java.math.BigDecimal.valueOf;

@ActiveProfiles("brazil")
@SpringBootTest
public class InsuranceCalcBrazilServiceTest {
    @Autowired
    InsuranceCalcBrazilService insuranceCalcBrazilService;

    @Test
    public void robberyCalcSuccess() {
        BigDecimal result = insuranceCalcBrazilService.calcInsuranceCost(valueOf(50_000),
                InsuranceType.ROBBERY_INSURANCE);
        BigDecimal integerValue = result.setScale(0, BigDecimal.ROUND_DOWN);
        BigDecimal expected = valueOf(2800);
        Assertions.assertEquals(expected, integerValue);
    }

    @Test
    public void healthCalcSuccess() {
        BigDecimal result = insuranceCalcBrazilService.calcInsuranceCost(valueOf(200_000),
                InsuranceType.HEALTH_INSURANCE);
        BigDecimal integerValue = result.setScale(0, BigDecimal.ROUND_DOWN);
        BigDecimal expected = valueOf(6800);
        Assertions.assertEquals(expected, integerValue);
    }
}

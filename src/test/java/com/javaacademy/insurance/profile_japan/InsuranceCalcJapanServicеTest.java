package com.javaacademy.insurance.profile_japan;

import com.javaacademy.calc_service.InsuranceCalcJapanService;
import com.javaacademy.insurance.InsuranceType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;

import static java.math.BigDecimal.valueOf;

@ActiveProfiles("japan")
@SpringBootTest
public class InsuranceCalcJapanServicеTest {
    @Autowired
    InsuranceCalcJapanService insuranceCalcJapanService;

    @Test
    public void robberyCalcSuccess() {
        BigDecimal result = insuranceCalcJapanService.calcInsuranceCost(valueOf(1_000_000),
                InsuranceType.ROBBERY_INSURANCE);
        BigDecimal integerValue = result.setScale(0, BigDecimal.ROUND_DOWN);
        BigDecimal expected = valueOf(20_000);
        Assertions.assertEquals(expected, integerValue);
    }

    @Test
    public void healthCalcSuccess() {
        BigDecimal result = insuranceCalcJapanService.calcInsuranceCost(valueOf(10_000_000),
                InsuranceType.HEALTH_INSURANCE);
        BigDecimal integerValue = result.setScale(0, BigDecimal.ROUND_DOWN);
        BigDecimal expected = valueOf(162_000);
        Assertions.assertEquals(expected, integerValue);
    }

}

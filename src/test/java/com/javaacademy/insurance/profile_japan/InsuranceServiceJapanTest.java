package com.javaacademy.insurance.profile_japan;

import com.javaacademy.calc_service.InsuranceCalcJapanService;
import com.javaacademy.insurance.*;
import com.javaacademy.insurance_service.InsuranceServiceJapan;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.math.RoundingMode;

import static java.math.BigDecimal.ROUND_DOWN;
import static java.math.BigDecimal.valueOf;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("japan")
public class InsuranceServiceJapanTest {
    @Autowired
    InsuranceServiceJapan insuranceServiceJapan;

    @MockBean
    InsuranceCalcJapanService calculator;

    @MockBean
    Archive archive;

    @MockBean
    ContractNumberGenerator generator;

    @Test
    public void insuranceOfferSuccess() {
        Mockito.when(generator.generateNumber()).thenReturn("001");
        Mockito.when(calculator.calcInsuranceCost(Mockito.any(), Mockito.any())).thenReturn(valueOf(20_000));

        InsuranceContract result = insuranceServiceJapan.giveInsuranceOffer(valueOf(1_000_000), "Иванов Иван Иванович",
                InsuranceType.ROBBERY_INSURANCE);

        assertEquals("001", result.getContractNumber());
        assertEquals(valueOf(20_000), result.getInsurancePrice().setScale(0, ROUND_DOWN));
        assertEquals(valueOf(1_000_000), result.getCoverageCost().setScale(0, ROUND_DOWN));
        assertEquals("yen", result.getCurrency());
        assertEquals("Иванов Иван Иванович", result.getClientFullName());
        assertEquals("Japan", result.getCountry());
        assertEquals(InsuranceType.ROBBERY_INSURANCE, result.getInsuranceType());
        assertEquals(ContractStatus.UNPAID, result.getContractStatus());
    }

    @Test
    public void insuranceOfferSuccess2() {
        Mockito.when(generator.generateNumber()).thenReturn("001");
        Mockito.when(calculator.calcInsuranceCost(Mockito.any(), Mockito.any())).thenReturn(valueOf(162_000));

        InsuranceContract result = insuranceServiceJapan.giveInsuranceOffer(valueOf(1_000_000),
                "Иванов Иван Иванович",
                InsuranceType.ROBBERY_INSURANCE);

        assertEquals("001", result.getContractNumber());
        assertEquals(valueOf(162_000), result.getInsurancePrice().setScale(0, ROUND_DOWN));
        assertEquals(valueOf(1_000_000), result.getCoverageCost().setScale(0, ROUND_DOWN));
        assertEquals("yen", result.getCurrency());
        assertEquals("Иванов Иван Иванович", result.getClientFullName());
        assertEquals("Japan", result.getCountry());
        assertEquals(InsuranceType.ROBBERY_INSURANCE, result.getInsuranceType());
        assertEquals(ContractStatus.UNPAID, result.getContractStatus());
    }

    @Test
    public void payContractSuccess() {
        InsuranceContract insuranceContract = new InsuranceContract("001",
                valueOf(162_000),
                valueOf(10_000_000),
                "yen",
                "Иванов Иван Иванович",
                "Japan",
                InsuranceType.HEALTH_INSURANCE,
                ContractStatus.UNPAID);
        Mockito.when(archive.getContract("001")).thenReturn(insuranceContract);
        Mockito.when(calculator.calcInsuranceCost(Mockito.any(), Mockito.any()))
                .thenReturn(valueOf(162_000).setScale(0,RoundingMode.DOWN));

        InsuranceContract insuranceContract1 = insuranceServiceJapan.payInsurance("001");

        assertEquals("001", insuranceContract1.getContractNumber());
        assertEquals(valueOf(162_000), insuranceContract1.getInsurancePrice() );
        assertEquals(valueOf(10_000_000), insuranceContract1.getCoverageCost()
                .setScale(0, RoundingMode.DOWN));
        assertEquals("yen", insuranceContract1.getCurrency());
        assertEquals("Иванов Иван Иванович", insuranceContract1.getClientFullName());
        assertEquals("Japan", insuranceContract1.getCountry());
        assertEquals(InsuranceType.HEALTH_INSURANCE, insuranceContract1.getInsuranceType());
        assertEquals(ContractStatus.PAID, insuranceContract.getContractStatus());
    }
}


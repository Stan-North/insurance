package com.javaacademy.insurance_service;

import com.javaacademy.calc_service.InsuranceCalcJapanService;
import com.javaacademy.exception.ContractException;
import com.javaacademy.insurance.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Profile(value = "japan")
@RequiredArgsConstructor
public class InsuranceServiceJapan implements InsuranceService {
    private static final String CONTRACT_DOES_NOT_EXIST = "такого договора не существует";

    @Value("${app.country}")
    private String country;
    @Value("${app.currency}")
    private String currency;

    private final ContractNumberGenerator contractNumberGenerator;

    private final Archive archive;
    private final InsuranceCalcJapanService insuranceCalcJapanService;


    @Override
    public InsuranceContract giveInsuranceOffer(BigDecimal coverageCost, String clientFullName, InsuranceType insuranceType) {
        String contractNumber = contractNumberGenerator.generateNumber();
        BigDecimal insurancePrice = insuranceCalcJapanService.calcInsuranceCost(coverageCost, insuranceType);
        InsuranceContract insuranceContract = new InsuranceContract(contractNumber, insurancePrice, coverageCost,
                currency, clientFullName, country, insuranceType, ContractStatus.UNPAID);
        archive.addContract(insuranceContract);
        return insuranceContract;
    }

    @Override
    public InsuranceContract payInsurance(String contractNumber) {
        InsuranceContract insuranceContract = archive.getContract(contractNumber);
        if (insuranceContract == null) {
            throw new ContractException(CONTRACT_DOES_NOT_EXIST);
        }
        insuranceContract.setContractStatus(ContractStatus.PAID);
        return insuranceContract;
    }
}

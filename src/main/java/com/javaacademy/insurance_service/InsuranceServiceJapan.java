package com.javaacademy.insurance_service;

import com.javaacademy.calc_service.InsuranceCalcJapanService;
import com.javaacademy.exception.ContractException;
import com.javaacademy.insurance.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Profile(value = "japan")
public class InsuranceServiceJapan implements InsuranceService {
    private static final String CONTRACT_DOES_NOT_EXIST = "такого договора не существует";

    @Value("${app.country}")
    private String country;
    @Value("${app.currency}")
    private String currency;
    private Archive archive;
    private InsuranceCalcJapanService insuranceCalcJapanService;

    public InsuranceServiceJapan(Archive archive,
                                 @Qualifier("insuranceCalcJapanService") InsuranceCalcJapanService insuranceCalcJapanService) {
        this.archive = archive;
        this.insuranceCalcJapanService = insuranceCalcJapanService;
    }

    @Override
    public InsuranceContract giveInsuranceOffer(BigDecimal coverageCost, String clientFullName, InsuranceType insuranceType) {
        String contractNumber = ContractNumberGenerator.generateNumber();
        BigDecimal insurancePrice = insuranceCalcJapanService.calcInsuranceCost(coverageCost, insuranceType);
        InsuranceContract insuranceContract = new InsuranceContract(contractNumber, insurancePrice, coverageCost,
                currency, clientFullName, country, insuranceType, ContractStatus.UNPAID);
        archive.addContract(insuranceContract.getContractNumber(), insuranceContract);
        return insuranceContract;
    }

    @Override
    public InsuranceContract payInsurance(String contractNumber) {
        InsuranceContract insuranceContract;
        if (archive.getContracts().containsKey(contractNumber)){
            insuranceContract = archive.getContracts().get(contractNumber);
            insuranceContract.setContractStatus(ContractStatus.PAID);
        } else {
            throw new ContractException(CONTRACT_DOES_NOT_EXIST);
        }
        return insuranceContract;
    }
}

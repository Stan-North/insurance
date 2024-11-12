package com.javaacademy.insurance;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
public class InsuranceContract {

    private final String contractNumber;
    private final BigDecimal insurancePrice;
    private final BigDecimal coverageCost;
    private final String currency;
    private final String clientFullName;
    private final String country;
    private final InsuranceType insuranceType;
    @Setter
    private ContractStatus contractStatus;

    public InsuranceContract(String contractNumber, BigDecimal insurancePrice, BigDecimal coverageCost,
                             String currency, String clientFullName, String country,
                             InsuranceType insuranceType, ContractStatus contractStatus) {
        this.contractNumber = contractNumber;
        this.insurancePrice = insurancePrice;
        this.coverageCost = coverageCost;
        this.currency = currency;
        this.clientFullName = clientFullName;
        this.country = country;
        this.insuranceType = insuranceType;
        this.contractStatus = contractStatus;
    }
}

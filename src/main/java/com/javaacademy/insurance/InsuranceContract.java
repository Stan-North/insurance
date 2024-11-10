package com.javaacademy.insurance;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
public class InsuranceContract {

    private String contractNumber;
    private BigDecimal insurancePrice;
    private BigDecimal coverageCost;
    private String currency;
    private String clientFullName;
    private String country;
    private InsuranceType insuranceType;
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

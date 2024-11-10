package com.javaacademy.insurance_service;

import com.javaacademy.insurance.InsuranceContract;
import com.javaacademy.insurance.InsuranceType;

import java.math.BigDecimal;

public interface InsuranceService {

    InsuranceContract giveInsuranceOffer(BigDecimal coverageCost, String clientFullName, InsuranceType insuranceType);

    InsuranceContract payInsurance(String contractNumber);

}

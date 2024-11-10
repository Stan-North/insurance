package com.javaacademy.calc_service;

import com.javaacademy.insurance.InsuranceType;

import java.math.BigDecimal;

public interface InsuranceCalcService {

    BigDecimal calcInsuranceCost(BigDecimal coverageCost, InsuranceType insuranceType);
}

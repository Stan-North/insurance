package com.javaacademy.insurance;

import org.springframework.stereotype.Component;

import java.util.TreeMap;

@Component
public class Archive {
    private final TreeMap<String, InsuranceContract> contracts = new TreeMap<>();

    public void addContract(InsuranceContract insuranceContract) {
        contracts.put(insuranceContract.getContractNumber(), insuranceContract);
    }

    public InsuranceContract getContract(String contractNumber) {
        return contracts.get(contractNumber);
    }
}

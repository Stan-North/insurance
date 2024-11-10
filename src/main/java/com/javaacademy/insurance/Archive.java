package com.javaacademy.insurance;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
@Getter
public class Archive {
    private final HashMap<String, InsuranceContract> contracts = new HashMap<>();

    public void addContract(String contractNumber, InsuranceContract insuranceContract) {
        contracts.put(contractNumber, insuranceContract);
    }
}

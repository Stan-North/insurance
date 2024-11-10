package com.javaacademy.insurance;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum InsuranceType {
    HEALTH_INSURANCE("медицинское страхование"),
    ROBBERY_INSURANCE("защита от грабежа");

    //медицинское страхование или защита от грабежа
    private final String description;
}

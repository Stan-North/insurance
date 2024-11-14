package com.javaacademy.insurance;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class ContractNumberGenerator {
    private static final int COUNTER_OF_DIGIT = 4;
    private static final String DELIMITER = "-";
    public static final int BOUND = 10;

    public String generateNumber() {
        Random random = new Random();
        String character = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < COUNTER_OF_DIGIT; i++) {
            builder.append(character.charAt(random.nextInt(character.length())));
        }
        builder.append(DELIMITER);
        for (int i = 0; i < COUNTER_OF_DIGIT; i++) {
            builder.append(random.nextInt(BOUND));
        }
        builder.append(DELIMITER);
        for (int i = 0; i < COUNTER_OF_DIGIT; i++) {
            builder.append(random.nextInt(BOUND));
        }
        return builder.toString();
    }
}

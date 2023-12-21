package com.example.demo.Validation;

import java.util.Arrays;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class SportListValidator implements ConstraintValidator<SportListConstraint, String> {

    @Override
    public boolean isValid(String sportType, ConstraintValidatorContext context) {
        String sportList[] = {"Volleyball", "Hockey", "Basketball", "Football"};


        return sportType != null && Arrays.asList(sportList).contains(sportType);
    }
    
}

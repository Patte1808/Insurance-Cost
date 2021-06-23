package models.insurances;


import models.calculation_strategy.CalculationStrategy;

public abstract class Insurance {
    private final String displayName;
    private final CalculationStrategy calculationStrategy;

    public Insurance(String displayName, CalculationStrategy calculationStrategy) {
        this.displayName = displayName;
        this.calculationStrategy = calculationStrategy;
    }

    public String getDisplayName() {
        return displayName;
    }

    public CalculationStrategy getCalculationStrategy() {
        return calculationStrategy;
    }
}

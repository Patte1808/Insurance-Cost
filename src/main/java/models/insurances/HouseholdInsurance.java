package models.insurances;

import models.calculation_strategy.CalculationStrategy;
import org.joda.money.Money;

public class HouseholdInsurance extends Insurance {
    private final Money pricePerSquareMeter;

    public HouseholdInsurance(String displayName, Money pricePerSquareMeter, CalculationStrategy calculationStrategy) {
        super(displayName, calculationStrategy);
        this.pricePerSquareMeter = pricePerSquareMeter;
    }

    public Money getPricePerSquareMeter() {
        return pricePerSquareMeter;
    }
}

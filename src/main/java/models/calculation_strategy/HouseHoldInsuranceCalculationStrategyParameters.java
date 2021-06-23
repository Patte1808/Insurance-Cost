package models.calculation_strategy;

import org.joda.money.Money;

import java.util.Objects;

/**
 * Holds information to calculate the price for an household insurance
 * If in the future more parameters will be used for different calculations, those parameters will need to be added into this class
 */
public class HouseHoldInsuranceCalculationStrategyParameters implements InsuranceCalculationStrategyParameters {

    private final double squareMeters;
    private final Money pricePerSquareMeter;

    public HouseHoldInsuranceCalculationStrategyParameters(double squareMeters, Money pricePerSquareMeter) {
        this.squareMeters = squareMeters;
        this.pricePerSquareMeter = pricePerSquareMeter;
    }

    public double getSquareMeters() {
        return squareMeters;
    }

    public Money getPricePerSquareMeter() {
        return pricePerSquareMeter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HouseHoldInsuranceCalculationStrategyParameters that = (HouseHoldInsuranceCalculationStrategyParameters) o;
        return Double.compare(that.squareMeters, squareMeters) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(squareMeters);
    }
}

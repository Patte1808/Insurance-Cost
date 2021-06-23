package strategies;

import models.calculation_strategy.HouseHoldInsuranceCalculationStrategyParameters;
import org.joda.money.Money;

import java.math.RoundingMode;

public class SimpleHouseHoldInsurancePriceCalculationStrategy implements InsurancePriceCalculationStrategy<HouseHoldInsuranceCalculationStrategyParameters> {
    @Override
    public Money calculateInsurancePrice(HouseHoldInsuranceCalculationStrategyParameters parameters) {
        return parameters.getPricePerSquareMeter().multipliedBy(parameters.getSquareMeters(), RoundingMode.FLOOR);
    }
}

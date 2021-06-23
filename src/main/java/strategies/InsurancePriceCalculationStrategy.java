package strategies;

import models.calculation_strategy.InsuranceCalculationStrategyParameters;
import org.joda.money.Money;

/**
 * Provides an interface to implement different calculation strategies for the insurance sum
 * To make it more flexible parameters can freely be added to the `InsuranceCalculationStrategyParameters` and then used in your concrete implementation
 * of the CalculationStrategy
 */
public interface InsurancePriceCalculationStrategy<T extends InsuranceCalculationStrategyParameters> {
    Money calculateInsurancePrice(T parameters);
}

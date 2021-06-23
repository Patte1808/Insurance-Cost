package factories;

import models.calculation_strategy.CalculationStrategy;
import models.calculation_strategy.InsuranceCalculationStrategyParameters;
import strategies.InsurancePriceCalculationStrategy;

public interface StrategyFactory<T extends InsuranceCalculationStrategyParameters> {
    void register(CalculationStrategy calculationStrategy, InsurancePriceCalculationStrategy<T> insurancePriceCalculationStrategy);

    InsurancePriceCalculationStrategy<T> getStrategy(CalculationStrategy calculationStrategy);
}

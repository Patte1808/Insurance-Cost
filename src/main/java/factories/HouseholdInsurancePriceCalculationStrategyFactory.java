package factories;

import models.calculation_strategy.CalculationStrategy;
import models.calculation_strategy.HouseHoldInsuranceCalculationStrategyParameters;
import strategies.InsurancePriceCalculationStrategy;

import java.util.HashMap;
import java.util.Map;

public class HouseholdInsurancePriceCalculationStrategyFactory implements StrategyFactory<HouseHoldInsuranceCalculationStrategyParameters> {

    private final Map<CalculationStrategy, InsurancePriceCalculationStrategy<HouseHoldInsuranceCalculationStrategyParameters>> strategyMap = new HashMap<>();

    @Override
    public void register(CalculationStrategy calculationStrategy, InsurancePriceCalculationStrategy<HouseHoldInsuranceCalculationStrategyParameters> insurancePriceCalculationStrategy) {
        strategyMap.put(calculationStrategy, insurancePriceCalculationStrategy);
    }

    @Override
    public InsurancePriceCalculationStrategy<HouseHoldInsuranceCalculationStrategyParameters> getStrategy(CalculationStrategy calculationStrategy) {
        InsurancePriceCalculationStrategy<HouseHoldInsuranceCalculationStrategyParameters> insurancePriceCalculationStrategy = strategyMap.get(calculationStrategy);

        if (insurancePriceCalculationStrategy == null) {
            throw new IllegalArgumentException(String.format("Strategy for calculation type %s not found!", calculationStrategy));
        }

        return insurancePriceCalculationStrategy;
    }
}

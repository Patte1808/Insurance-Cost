package factories;

import models.calculation_strategy.CalculationStrategy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import strategies.InsurancePriceCalculationStrategy;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class HouseholdInsurancePriceCalculationStrategyFactoryTest {
    private StrategyFactory strategyFactory;

    @Mock
    InsurancePriceCalculationStrategy insurancePriceCalculationStrategy;

    @BeforeAll
    void setup() {
        MockitoAnnotations.openMocks(this);
        strategyFactory = new HouseholdInsurancePriceCalculationStrategyFactory();
    }

    @Test
    void shouldThrowAnErrorIfTryingToGetUnregisteredStrategy() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> strategyFactory.getStrategy(CalculationStrategy.DUMMY));
    }

    @Test
    void shouldBeAbleToRegisterANewStrategy() {
        strategyFactory.register(CalculationStrategy.SIMPLE, insurancePriceCalculationStrategy);

        Assertions.assertDoesNotThrow(() -> strategyFactory.getStrategy(CalculationStrategy.SIMPLE));
    }
}

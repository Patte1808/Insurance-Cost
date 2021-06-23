package strategies;

import models.calculation_strategy.HouseHoldInsuranceCalculationStrategyParameters;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SimpleHouseHoldInsurancePriceCalculationStrategyTest {

    private InsurancePriceCalculationStrategy insurancePriceCalculationStrategy;

    @BeforeAll
    void setup() {
        this.insurancePriceCalculationStrategy = new SimpleHouseHoldInsurancePriceCalculationStrategy();
    }

    @Test
    void shouldReturnCorrectInsuranceSumFor_Kompakt() {
        Money calculatedPrice = insurancePriceCalculationStrategy.calculateInsurancePrice(new HouseHoldInsuranceCalculationStrategyParameters(10, Money.of(CurrencyUnit.EUR, 650)));
        Assertions.assertEquals(Money.of(CurrencyUnit.EUR, 6500), calculatedPrice);
    }
}

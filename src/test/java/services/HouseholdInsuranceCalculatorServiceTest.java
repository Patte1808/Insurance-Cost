package services;

import exceptions.InsuranceProductNotFoundException;
import factories.StrategyFactory;
import models.calculation_strategy.CalculationStrategy;
import models.calculation_strategy.HouseHoldInsuranceCalculationStrategyParameters;
import models.insurances.HouseholdInsurance;
import models.calculation_strategy.InsuranceCalculationStrategyParameters;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import repositories.InsuranceRepository;
import strategies.InsurancePriceCalculationStrategy;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class HouseholdInsuranceCalculatorServiceTest {
    @Mock
    InsuranceRepository insuranceRepository;

    @Mock
    InsurancePriceCalculationStrategy insurancePriceCalculationStrategy;

    @Mock
    StrategyFactory strategyFactory;

    private HouseholdInsuranceCalculatorService householdInsuranceCalculatorService;
    private HouseholdInsurance dummyHouseholdInsurance;
    private final InsuranceProductNotFoundException insuranceProductNotFoundException = new InsuranceProductNotFoundException("");

    final private double dummySquareMeters = 5;
    final private Money dummyCalculatedMoney = Money.of(CurrencyUnit.EUR, 3250);
    final private String dummyProductName = "test";

    @BeforeAll
    void setup() {
        MockitoAnnotations.openMocks(this);
        householdInsuranceCalculatorService = new HouseholdInsuranceCalculatorService(insuranceRepository, strategyFactory);
        dummyHouseholdInsurance = new HouseholdInsurance(dummyProductName, Money.of(CurrencyUnit.EUR, 650), CalculationStrategy.SIMPLE);

        Mockito.doReturn(insurancePriceCalculationStrategy).when(strategyFactory).getStrategy(CalculationStrategy.SIMPLE);
    }

    @Test
    void shouldCall_CalculateInsuranceSum_whenFindingInsuranceProduct() throws InsuranceProductNotFoundException {
        Mockito.doReturn(dummyHouseholdInsurance).when(insuranceRepository).findByInsuranceProductName(dummyProductName);
        InsuranceCalculationStrategyParameters parameters = new HouseHoldInsuranceCalculationStrategyParameters(5, dummyHouseholdInsurance.getPricePerSquareMeter());
        Mockito.doReturn(dummyCalculatedMoney).when(insurancePriceCalculationStrategy).calculateInsurancePrice(parameters);

        householdInsuranceCalculatorService.calculateInsuranceSum(dummyProductName, dummySquareMeters);

        Mockito.verify(insurancePriceCalculationStrategy).calculateInsurancePrice(parameters);
    }

    @Test
    void shouldReturnCorrectMoneyAmount_whenCalling_calculateInsuranceSum() throws InsuranceProductNotFoundException {
        Mockito.doReturn(dummyHouseholdInsurance).when(insuranceRepository).findByInsuranceProductName(dummyProductName);
        InsuranceCalculationStrategyParameters parameters = new HouseHoldInsuranceCalculationStrategyParameters(5, dummyHouseholdInsurance.getPricePerSquareMeter());
        Mockito.doReturn(dummyCalculatedMoney).when(insurancePriceCalculationStrategy).calculateInsurancePrice(parameters);

        Money money = householdInsuranceCalculatorService.calculateInsuranceSum(dummyProductName, dummySquareMeters);
        Assertions.assertEquals(dummyCalculatedMoney, money);
    }

    @Test
    void shouldReturn_InsuranceProductNotFoundException_whenTryingToFindProductWhichDoesNotExist() throws InsuranceProductNotFoundException {
        Mockito.doThrow(insuranceProductNotFoundException).when(insuranceRepository).findByInsuranceProductName(dummyProductName);

        Assertions.assertThrows(InsuranceProductNotFoundException.class, () -> insuranceRepository.findByInsuranceProductName(dummyProductName));
    }
}

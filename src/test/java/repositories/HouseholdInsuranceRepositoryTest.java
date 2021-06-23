package repositories;

import exceptions.InsuranceProductNotFoundException;
import models.calculation_strategy.CalculationStrategy;
import models.insurances.HouseholdInsurance;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.MockitoAnnotations;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class HouseholdInsuranceRepositoryTest {

    private InsuranceRepository<HouseholdInsurance> insuranceRepository;
    private HouseholdInsurance dummyKompaktProduct;
    private HouseholdInsurance dummyOptimalProduct;

    @BeforeAll
    void setup() {
        MockitoAnnotations.openMocks(this);
        dummyKompaktProduct = new HouseholdInsurance("Kompakt", Money.of(CurrencyUnit.EUR, 650), CalculationStrategy.SIMPLE);
        dummyOptimalProduct = new HouseholdInsurance("Optimal", Money.of(CurrencyUnit.EUR, 700), CalculationStrategy.SIMPLE);
        insuranceRepository = new HouseholdInsuranceRepository();
    }

    @Test
    void shouldBeAbleToAdd_Kompakt_Insurance() {
        Assertions.assertThrows(InsuranceProductNotFoundException.class, () -> insuranceRepository.findByInsuranceProductName("Kompakt"));

        insuranceRepository.addInsurance(dummyKompaktProduct);

        Assertions.assertDoesNotThrow(() -> {
            insuranceRepository.findByInsuranceProductName("Kompakt");
        });
    }

    @Test
    void shouldBeAbleToAdd_Optimal_Insurance() {
        Assertions.assertThrows(InsuranceProductNotFoundException.class, () -> insuranceRepository.findByInsuranceProductName("Optimal"));

        insuranceRepository.addInsurance(dummyOptimalProduct);

        Assertions.assertDoesNotThrow(() -> {
            insuranceRepository.findByInsuranceProductName("Optimal");
        });
    }

    @Test
    void shouldThrow_InsuranceProductNotFoundException_when_findByInsuranceProductName_wasCalledWithNotExistingProductName() {
        Assertions.assertThrows(InsuranceProductNotFoundException.class, () -> insuranceRepository.findByInsuranceProductName("1234"));
    }
}
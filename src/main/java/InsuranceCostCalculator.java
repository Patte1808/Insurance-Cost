import exceptions.InsuranceProductNotFoundException;
import factories.HouseholdInsurancePriceCalculationStrategyFactory;
import factories.StrategyFactory;
import models.calculation_strategy.CalculationStrategy;
import models.calculation_strategy.HouseHoldInsuranceCalculationStrategyParameters;
import models.insurances.HouseholdInsurance;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import repositories.HouseholdInsuranceRepository;
import services.HouseholdInsuranceCalculatorService;
import strategies.SimpleHouseHoldInsurancePriceCalculationStrategy;

import java.util.Random;

public class InsuranceCostCalculator {
    public static void main(String[] args) {
        StrategyFactory<HouseHoldInsuranceCalculationStrategyParameters> strategyFactory = new HouseholdInsurancePriceCalculationStrategyFactory();
        strategyFactory.register(CalculationStrategy.SIMPLE, new SimpleHouseHoldInsurancePriceCalculationStrategy());

        HouseholdInsuranceCalculatorService householdInsuranceCalculatorService = new HouseholdInsuranceCalculatorService(new HouseholdInsuranceRepository(), strategyFactory);
        householdInsuranceCalculatorService.addInsurance(new HouseholdInsurance("Kompakt", Money.of(CurrencyUnit.EUR, 650), CalculationStrategy.SIMPLE));
        householdInsuranceCalculatorService.addInsurance(new HouseholdInsurance("Optimal", Money.of(CurrencyUnit.EUR, 700), CalculationStrategy.SIMPLE));

        Random random = new Random();
        double squareMetersForCustomer = random.nextDouble();
        String[] productNames = new String[]{ "Kompakt", "Optimal" };

        for (String productName : productNames) {
            try {
                Money insuranceSum = householdInsuranceCalculatorService.calculateInsuranceSum(productName, squareMetersForCustomer);
                System.out.printf("Die Wohnfläche die zur Kalukation benutzt wurde beträgt %s, dadurch ergibt sich ein Versicherungspreis von %s für das Produkt %s%n", squareMetersForCustomer, insuranceSum, productName);
            } catch (InsuranceProductNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}

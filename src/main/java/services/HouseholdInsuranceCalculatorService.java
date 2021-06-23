package services;

import exceptions.InsuranceProductNotFoundException;
import factories.StrategyFactory;
import models.calculation_strategy.HouseHoldInsuranceCalculationStrategyParameters;
import models.insurances.HouseholdInsurance;
import org.joda.money.Money;
import repositories.InsuranceRepository;
import strategies.InsurancePriceCalculationStrategy;

public class HouseholdInsuranceCalculatorService {

    private final InsuranceRepository<HouseholdInsurance> householdInsuranceRepository;
    private final StrategyFactory<HouseHoldInsuranceCalculationStrategyParameters> strategyFactory;

    public HouseholdInsuranceCalculatorService(InsuranceRepository<HouseholdInsurance> repository, StrategyFactory<HouseHoldInsuranceCalculationStrategyParameters> strategyFactory) {
        this.householdInsuranceRepository = repository;
        this.strategyFactory = strategyFactory;
    }

    /**
     * Given a productName and the amount of squaremeters, it will return the calculated insurance sum of the provided productName.
     * @param productName - insurance product name
     * @param squareMeters - amount of square meters the customer wants to cover
     * @return calculated insurance sum
     * @throws InsuranceProductNotFoundException
     */
    public Money calculateInsuranceSum(String productName, double squareMeters) throws InsuranceProductNotFoundException {
        HouseholdInsurance householdInsurance = householdInsuranceRepository.findByInsuranceProductName(productName);

        InsurancePriceCalculationStrategy<HouseHoldInsuranceCalculationStrategyParameters> strategy = strategyFactory.getStrategy(householdInsurance.getCalculationStrategy());
        return strategy.calculateInsurancePrice(new HouseHoldInsuranceCalculationStrategyParameters(squareMeters, householdInsurance.getPricePerSquareMeter()));
    }

    public void addInsurance(HouseholdInsurance insurance) {
        householdInsuranceRepository.addInsurance(insurance);
    }
}

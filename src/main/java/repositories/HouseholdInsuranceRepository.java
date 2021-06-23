package repositories;

import exceptions.InsuranceProductNotFoundException;
import models.insurances.HouseholdInsurance;

import java.util.HashMap;
import java.util.Map;

/**
 * A very simple key-value in-memory repository
 */
public class HouseholdInsuranceRepository implements InsuranceRepository<HouseholdInsurance> {

    private final Map<String, HouseholdInsurance> householdInsuranceMap;

    public HouseholdInsuranceRepository() {
        this.householdInsuranceMap = new HashMap<>();
    }

    @Override
    public HouseholdInsurance findByInsuranceProductName(String name) throws InsuranceProductNotFoundException {
        if (!this.householdInsuranceMap.containsKey(name)) {
            throw new InsuranceProductNotFoundException(String.format("Insurance product %s not found", name));
        }

        return householdInsuranceMap.get(name);
    }

    @Override
    public void addInsurance(HouseholdInsurance insurance) {
        this.householdInsuranceMap.put(insurance.getDisplayName(), insurance);
    }
}

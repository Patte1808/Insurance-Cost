package repositories;

import exceptions.InsuranceProductNotFoundException;
import models.insurances.Insurance;

public interface InsuranceRepository<T extends Insurance> {
    /**
     * Returns an Insurance instance
     * If the ProductName doesn't match any Insurance which is in the repository, it will fail and throw an InsuranceProductNotFoundException
     * @param name - insurance product name
     * @return the Insurance instance
     * @throws InsuranceProductNotFoundException
     */
    T findByInsuranceProductName(String name) throws InsuranceProductNotFoundException;

    void addInsurance(T insurance);
}

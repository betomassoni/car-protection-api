package br.com.robertomassoni.carProtection.service;

import br.com.robertomassoni.carProtection.dto.model.InsurancePolicyDto;
import java.util.List;

public interface InsurancePolicyService {
    void deleteAllInsurancePolicies();
    List<InsurancePolicyDto> getInsurancePolicies();
    InsurancePolicyDto saveInsurancePolicy(InsurancePolicyDto insurancePolicyDto);
    InsurancePolicyDto getInsurancePolicy(String id);
    InsurancePolicyDto deleteInsurancePolicy(String id);
    InsurancePolicyDto updateInsurancePolicy(String id, InsurancePolicyDto insurancePolicyDto);
}
